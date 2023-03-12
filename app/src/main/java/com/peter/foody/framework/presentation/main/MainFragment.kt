package com.peter.foody.framework.presentation.main

import android.R
import android.app.Dialog
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.text.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hend.calldetailsrecorder.data.roomContacts.backup.ItemsBackup
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.classes.ItemDatum
import com.peter.foody.data.remote.model.models.AddOrderModels
import com.peter.foody.data.remote.model.models.CustomerAddress
import com.peter.foody.data.remote.model.models.CustomerOrders
import com.peter.foody.data.remote.model.models.ItemsModels
import com.peter.foody.data.roomContacts.productRoom.ItemsBill
import com.peter.foody.databinding.FragmentMainBinding
import com.peter.foody.framework.presentation.adapters.*
import com.peter.foody.framework.presentation.main.reports.utilShared.SharedPreferencesBillStatu
import com.peter.foody.framework.presentation.reports.PrintPic
import com.peter.foody.framework.presentation.ui.account.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val accountviewModel: AccountViewModel by viewModels()
    var list = ArrayList<ItemsModels>()
    var AddOrderModelslist = ArrayList<AddOrderModels>()
    lateinit var adapter2: CategoriesAdapter

    var address = ArrayList<CustomerAddress>()
    var order = ArrayList<CustomerOrders>()
    var mBluetoothAdapter: BluetoothAdapter? = null
    var mmSocket: BluetoothSocket? = null
    var mmDevice: BluetoothDevice? = null

    var mmOutputStream: OutputStream? = null
    var mmInputStream: InputStream? = null
    var workerThread: Thread? = null

    lateinit var readBuffer: ByteArray
    var readBufferPosition = 0
    var counter = 0


    @Volatile
    var stopWorker = false

    var numberOfBill: Int = 0

    var msgToPrint = ""

    var CusID: String = ""
    var name: String = ""
    var Total = ""
    var TotalReturn = ""
    var Unpaid_deferred = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        SharedPreferencesBillStatu.init(context);

        val macAddress =
            Settings.Secure.getString(activity!!.getContentResolver(), "android_id")

        //   val d=   UploadDetlsCallRepo()
        //  viewModel.sentRingingAction()
        binding.payBtn.setOnClickListener {
            // registerBillAndPrint("hph")
            if (list.isEmpty() && list.size == 0) {
                Toast.makeText(context,"الفاتورة فارغة", Toast.LENGTH_SHORT).show()

            } else {
//                var progressDoalog: ProgressDialog = ProgressDialog(activity)
//                progressDoalog.max = 100
//                progressDoalog.setMessage("Its loading....")
//                progressDoalog.setTitle(" جارى تسجيل الفاتوره ")
//                progressDoalog.show()
                Toast.makeText(context,  "تم الدفع", Toast.LENGTH_SHORT).show()
                viewModel.createHeader(list)
                list.clear()
                adapter2.notifyDataSetChanged()
                binding.totalBalance.setText("")
            }

        }

        var phoneNum = ""
        // viewModel.sentRingingAction()

       // viewModel.getCustomerDetailsVM("01015490078", 1, "732a8c1694b73f08")
        viewModel.customerDetails.observe(viewLifecycleOwner) {
            when (it) {
                is State.Loading -> Log.d("aziz", "")
                is State.Success -> {
                    binding.nameTv.setText(it.data.data.CusName)
                    binding.saleTv.setText(it.data.data.CustomerPhone.get(0).CusPhone)
                    it.data
                }
                is State.Error -> {

                    Log.d("aziz", it.toError())
                }
            }
        }
        var customerName = ""
        binding.putNameID.setOnClickListener(View.OnClickListener {

            CheckNameField()
            customerName = binding.nameEdTxt.text.toString()


        })
        var customerAddress = ""
        var customerExistAddress = ""
        binding.putAddressID.setOnClickListener(View.OnClickListener {

            CheckAddressField()
            customerAddress = binding.addressEdTxt.text.toString()

        })



        AddOrderModelslist.add(
            AddOrderModels(
                29017,
                "pizza ",
                "6222000505368",
                "فول ب الزيت الحار",
                "sample string 5",
                "2023-02-26T00:46:31.380369-07:00",
                1,
                64,
                6,
                1,
                1,
                1,
                1.0,
                "GS1",
                "sample string 8",
                "99999999",
                "sample string 10",
                "sample string 11",
                "EA"
            )
        )


        // viewModel.setCustomeAddOrdersVM("01015490078", customerName, 1,"732a8c1694b73f08",customerAddress,customerExistAddress,AddOrderModelslist )


        viewModel.customerDetailsOrders.observe(viewLifecycleOwner) {
            order = it as ArrayList<CustomerOrders>
            binding.Add.setOnClickListener(View.OnClickListener {


                var onlyOrder = ArrayList<String>()
                for (item in order.indices) {

                    onlyOrder.add(order.get(item).ItemName)

                }
                onlyOrder

                // Initialize dialog
                val dialog = Dialog(context!!)

                // set custom dialog
                dialog.setContentView(R.layout.list_content)

                // set custom height and width
                dialog.window!!.setLayout(450, 700)

                // set transparent background
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))

                // show dialog
                dialog.show()

                // Initialize and assign variable
                // EditText editText=dialog.findViewById(R.id.edit_text);
                val listView = dialog.findViewById<ListView>(R.id.list)

                // Initialize array adapter
                val adapterlist: ArrayAdapter<String> =
                    ArrayAdapter<String>(context!!, R.layout.simple_list_item_1, onlyOrder)

                // set adapter
                listView.adapter = adapterlist
                listView.onItemClickListener =
                    OnItemClickListener { parent, view, position, id ->
                        list.add(
                            ItemsModels(
                                order.get(position).Record_ID,
                                order.get(position).ItemName,
                                order.get(position).Barcode,
                                order.get(position).Description,
                                order.get(position).Editor,
                                order.get(position).Date,
                                order.get(position).UnitType,
                                order.get(position).UnitType,
                                order.get(position).ItemCode,
                                order.get(position).Price,
                                1
                            )
                        )





                        binding.categories.adapter = adapter2
                        adapter2.submitList(list)




                        dialog.dismiss()
                    }

            })

        }
        viewModel.customerDetailsAddress.observe(viewLifecycleOwner) {
            address = it as ArrayList<CustomerAddress>
            binding.button2.setOnClickListener(View.OnClickListener {
                binding.cashEditText.setText("")

                // Initialize dialog
                var onlyAdress = ArrayList<String>()
                for (item in address.indices) {

                    onlyAdress.add(address.get(item).CusAddress)

                }
                onlyAdress
                val dialog = Dialog(context!!)

                // set custom dialog
                dialog.setContentView(R.layout.list_content)

                // set custom height and width
                dialog.window!!.setLayout(450, 700)

                // set transparent background
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))

                // show dialog
                dialog.show()

                // Initialize and assign variable
                // EditText editText=dialog.findViewById(R.id.edit_text);
                val listView = dialog.findViewById<ListView>(R.id.list)

                // Initialize array adapter
                val adapterlist: ArrayAdapter<String> =
                    ArrayAdapter<String>(context!!, R.layout.simple_list_item_1, onlyAdress)

                // set adapter
                listView.adapter = adapterlist
                listView.onItemClickListener =
                    OnItemClickListener { parent, view, position, id ->


                        binding.cashEditText.setText(adapterlist.getItem(position).toString())

                        customerAddress = adapterlist.getItem(position).toString()
                        customerExistAddress =
                            binding.addressEdTxt.setText(customerAddress).toString()






                        dialog.dismiss()
                    }

            })

        }

        adapter2 = CategoriesAdapter(onCategoryClickListener = OnCategoryClickListener {
            //  Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()

            // set value in array list
            var arrayList = ArrayList<Int>()

            // set value in array list
            arrayList.add(1)
            arrayList.add(2)
            arrayList.add(3)
            arrayList.add(4)
            arrayList.add(5)
            arrayList.add(6)
            arrayList.add(7)
            arrayList.add(8)
            arrayList.add(9)
            arrayList.add(10)
            arrayList.add(11)
            arrayList.add(12)
            arrayList.add(13)
            arrayList.add(14)
            arrayList.add(15)

            // Initialize dialog
            val dialog = Dialog(context!!)

            // set custom dialog
            dialog.setContentView(R.layout.list_content)

            // set custom height and width
            dialog.window!!.setLayout(300, 300)

            // set transparent background
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))

            // show dialog
            dialog.show()

            // Initialize and assign variable
            // EditText editText=dialog.findViewById(R.id.edit_text);
            val listView = dialog.findViewById<ListView>(R.id.list)

            // Initialize array adapter
            val adapterlist: ArrayAdapter<Int> =
                ArrayAdapter<Int>(context!!, R.layout.simple_list_item_1, arrayList)

            // set adapter
            listView.adapter = adapterlist

            listView.onItemClickListener =
                OnItemClickListener { parent, view, position, id ->

                    Toast.makeText(
                        context,
                        adapterlist.getItem(position).toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    list.get(it).Quantity = adapterlist.getItem(position)!!
                    var totalPrice = list.get(it).Quantity * list.get(it).Price
                    list.get(it).Price = totalPrice
                    Toast.makeText(
                        context, totalPrice.toString()
                        //  adapterlist.getItem(position).toString()
                        , Toast.LENGTH_SHORT
                    ).show()

                    calculateBalance(list)
                    adapter2.notifyDataSetChanged()

                    dialog.dismiss()
                }
        })


        binding.cashEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                //  Toast.makeText(applicationContext, p0.toString(), Toast.LENGTH_SHORT).show()
                if (p0?.isEmpty() == true) {
                    binding.deferredEditText.setText("0.0")
                    calculateBalance(list)
                } else {
                    if (binding.saleTv.text.isEmpty() && binding.saleTv.text.length == 0) {

                        binding.saleTv.setText("0.0")
                    } else {
                        binding.deferredEditText.setText("")

                        //                       var Total = binding.saleTv.text.toString().toDouble() *
//                                binding.cashEditText.text.toString().toDouble()
                        binding.deferredEditText.setText(Total.toString())
                        binding.deferredEditText.setText(Total.toString())
                    }
                }
            }
        })

        binding.offers.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        val adapter = OffersAdapter(onOfferClickListener = OnOfferClickListener {

            list.add(
                ItemsModels(
                    it.Record_ID,
                    it.ItemName!!,
                    it.Barcode!!,
                    it.Description!!,
                    it.Editor!!,
                    it.Date!!,
                    it.UnitType!!,
                    it.ItemType!!,
                    it.ItemCode.toString(),
                    it.Price,
                    1
                )
            )
            calculateBalance(list)
            binding.categories.adapter = adapter2
            adapter2.submitList(list)

        })

        viewModel.getItemsFromLocalDB()
        viewModel.getItemsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.offers.adapter = adapter
        }


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //  Toast.makeText(ItemsActivity.this, "Swipe to delete", Toast.LENGTH_SHORT).show();
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Snackbar.make(binding.categories, "Deleted item", Snackbar.LENGTH_SHORT)
                    .setAction(list[viewHolder.adapterPosition].ItemName,
                        View.OnClickListener { }).show()
                list.removeAt(viewHolder.adapterPosition)
                calculateBalance(list)
                adapter2.notifyDataSetChanged()
            }
        }).attachToRecyclerView(binding.categories)

        Log.d("ddddddddd", "s")

        //   lifecycleScope.launch {}
        // val ss=  replaceLastChar("353541")
        //   Log.d("replaceLastChar",ss)
        return binding.root
    }

    private fun replaceLastChar(original: String): String {
        if (original.lastIndexOf('1')
            > 0
        ) {
            val n = "N"
            var oo = n + original
            return oo
        }


//        {
//            return original.dropLast(1) + replacement
//        }
        return original
    }


    private fun createBill() {
        val itemData: ArrayList<ItemDatum> = ArrayList<ItemDatum>()
        val ItemsBillRoom: ArrayList<ItemsBill> = ArrayList<ItemsBill>()
        val ItemsBillRoomBackup: ArrayList<ItemsBackup> = ArrayList<ItemsBackup>()
        var price = 0.0
        var Tax = 0.0
        var totalPrice = 0.0
        var itemId = 0
        for (i in list) {

            //To Create List to UUID
            itemData.add(
                viewModel.converterProductToItemDatum(
                    java.lang.Double.valueOf(i.Quantity.toDouble()),
                    java.lang.Double.valueOf(i.Price),
                    java.lang.Double.valueOf(i.Price),
                    i.Description,
                    i.Barcode,
                    i.UnitType,
                    i.ItemType,
                    java.lang.String.valueOf(i.ItemCode)
                )
            )

            //   //To Create List to Room
            //   ItemsBillRoom.add(
            //       homeViewModel.setItemsRoom(
            //           i.getDescription(),
            //           java.lang.Double.valueOf(i.getPrice()),
            //           numberRicet,
            //           itemId.toString(),
            //           java.lang.Double.valueOf(i.getQuantity()),
            //           i.getUnitType(),
            //           i.getItemType(),
            //           java.lang.String.valueOf(i.getItemCode()),
            //           i.getBarcode()
            //       )
            //   )

            //   //To Create List to Room Backup
            //   ItemsBillRoomBackup.add(
            //       homeViewModel.setItemsRoomBackup(
            //           i.getDescription(),
            //           java.lang.Double.valueOf(i.getPrice()),
            //           numberRicet,
            //           itemId.toString()
            //       )
            //   )

            // Tax += i.getTax();
            // price += i.getprice();
            // totalPrice += i.getTotal();
//
            price += java.lang.Double.valueOf(i.Price)
            Tax = price * 14.0 / 100
            totalPrice = price + Tax
            itemId++
            Log.d("onSuccesss", price.toString() + "")
        }


        Log.d("onSuccess", price.toString() + "")
        Log.d("onSuccess", Tax.toString() + "")
        Log.d("onSuccess", totalPrice.toString() + "")


        //To Create UUID

//      val uu: String = viewModel.CreateUUID(numberRicet, "", TimeRicet, itemData)
//      Log.d("onSuccess", uu)
//      val QR =
//          " https://preprod.invoicing.eta.gov.eg/receipts/search/$uu"


//      //    String url ="https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
//      //To Create New Bill in Room db


//      //    String url ="https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
//      //To Create New Bill in Room db
//      val headerBill = HeaderBill()
//      headerBill.setUUID(uu)
//      headerBill.setBillNumber(numberRicet)
//      headerBill.setInvoiceDate(TimeRicet)
//      headerBill.setTax(Tax)
//      headerBill.setNetPrice(price)
//      headerBill.setTotalPrice(totalPrice)
//      headerBill.setLink(QR)

//      //To Insert New Bill in Room db

//      //To Insert New Bill in Room db
//      homeViewModel.headBill(headerBill, ItemsBillRoom, activity)

        // Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);

        // Log.d("onSuccess",g.toJson(r));
        // Send(r);
        //     printp(QR,"android.binder.printer");
        // String bill = setpill();
        //    ssss();
        // setPrintBT(bill, QR);
        // printp(QR, "android.binder.printer");

        //To Create online Bill
        //  Root createRoot = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);
        //  homeViewModel.Send(createRoot, HeaderOnline, ItemsBillRoomOnlin, getActivity());

        // Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);

        // Log.d("onSuccess",g.toJson(r));
        // Send(r);
        //     printp(QR,"android.binder.printer");
        // String bill = setpill();
        //    ssss();
        // setPrintBT(bill, QR);
        // printp(QR, "android.binder.printer");

        //To Create online Bill
        //  Root createRoot = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);
        //  homeViewModel.Send(createRoot, HeaderOnline, ItemsBillRoomOnlin, getActivity());
        list.clear()

    }


    private fun calculateBalance(list: ArrayList<ItemsModels>) {
        var totalBalance = 0.0
        for (i in list.indices) {
            totalBalance += list[i].Price * list[i].Quantity
        }
        binding.totalBalance.text = totalBalance.toString()
    }

    @Throws(IOException::class)
    fun registerBillAndPrint(numerofBill: String) {


        msgToPrint = msgToPrint.plus("Technology & Business Integration")
        msgToPrint = msgToPrint.plus("\n")
        //  }
        //  }
        print()
        //  Log.i("MSG_TO_PRINT", msgToPrint)
        //  print.value = true
        //   }
    }

    fun print() {
        findBT()
        openBT()
        sendData()

    }

    fun findBT() {
        Log.e("PrintError1", "e.message.toString()")

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            if (mBluetoothAdapter == null) {
                Toast.makeText(context, "No bluetooth adapter available", Toast.LENGTH_SHORT).show()
                // viewModel.message.value = "No bluetooth adapter available"
            }
            if (mBluetoothAdapter?.isEnabled == false) {
                val enableBluetooth = Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE
                )
                startActivityForResult(enableBluetooth, 0)
            }
            val pairedDevices: Set<BluetoothDevice> = mBluetoothAdapter?.bondedDevices!!
            if (pairedDevices.isNotEmpty()) {
                for (device in pairedDevices) {

                    // MP300 is the name of the bluetooth printer device
                    if (device.name == "Printer001") {
                        mmDevice = device
                        break
                    }
                }
            }
            Toast.makeText(context, "Bluetooth Device Found", Toast.LENGTH_SHORT).show()

            //  viewModel.message.value = "Bluetooth Device Found"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Tries to open a connection to the bluetooth printer device
    @Throws(IOException::class)
    fun openBT() {
        try {
            // Standard SerialPortService ID
            val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
            mmSocket = mmDevice!!.createRfcommSocketToServiceRecord(uuid)
            mmSocket?.connect()
            mmOutputStream = mmSocket?.outputStream
            mmInputStream = mmSocket?.inputStream
            beginListenForData()
            Toast.makeText(context, "Bluetooth Opened", Toast.LENGTH_SHORT).show()

            //   viewModel.message.value = "Bluetooth Opened"
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // After opening a connection to bluetooth printer device,
    // we have to listen and check if a data were sent to be printed.
    fun beginListenForData() {
        try {
            val handler = Handler()

            // This is the ASCII code for a newline character
            val delimiter: Byte = 10
            stopWorker = false
            readBufferPosition = 0
            readBuffer = ByteArray(2000)
            workerThread = Thread {
                while (!Thread.currentThread().isInterrupted
                    && !stopWorker
                ) {
                    try {
                        val bytesAvailable: Int = mmInputStream!!.available()
                        if (bytesAvailable > 0) {
                            val packetBytes = ByteArray(bytesAvailable)
                            mmInputStream?.read(packetBytes)
                            for (i in 0 until bytesAvailable) {
                                val b = packetBytes[i]
                                if (b == delimiter) {
                                    val encodedBytes = ByteArray(readBufferPosition)
                                    System.arraycopy(
                                        readBuffer, 0,
                                        encodedBytes, 0,
                                        encodedBytes.size
                                    )
                                    val data = String(
                                        encodedBytes
                                    )
                                    readBufferPosition = 0
                                } else {
                                    readBuffer[readBufferPosition.inc()] = b
                                }
                            }
                        }
                    } catch (ex: IOException) {
                        stopWorker = true
                    }
                }
            }
            workerThread?.start()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
    This will send data to be printed by the bluetooth printer
     */
    @Throws(IOException::class)
    fun sendData() {
        try {
            // the text typed by the user

            var printPic = PrintPic.getInstance()
            printPic.init(
                textAsBitmap(
                    context = activity!!,
                    textSize = 20,
                    textWidth = 580
                )
                // viewModel.textAsBitmap(
                //     context = this!!,
                //     textSize = 18,
                //     textWidth = 370
                // )
            )

            var draw = printPic.printDraw()

            mmOutputStream!!.write(draw)
            // tell the user data were sent
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Log.e("PrintError1", e.message.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("PrintErro2r", e.message.toString())
        }
        closeBT()
    }

    // Close the connection to bluetooth printer.
    @Throws(IOException::class)
    fun closeBT() {

        try {
            stopWorker = true
            mmOutputStream?.close()
            mmInputStream?.close()
            mmSocket?.close()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun textAsBitmap(textWidth: Int, textSize: Int, context: Context): Bitmap? {

        // Get text dimensions
        val textPaint = TextPaint(
            Paint.ANTI_ALIAS_FLAG
                    or Paint.LINEAR_TEXT_FLAG
        )
        textPaint.style = Paint.Style.FILL_AND_STROKE

        //  val font = Typeface.createFromAsset(activity!!.assets, "font/kawkabregular.ttf")

        //    val typeface=font
        //   textPaint.typeface = typeface
        textPaint.color = Color.BLACK
        textPaint.textSize = textSize.toFloat()
        val mTextLayout = StaticLayout(
            msgToPrint, textPaint,
            textWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false
        )

        // Create bitmap and canvas to draw to
        val b = Bitmap.createBitmap(textWidth, mTextLayout.height, Bitmap.Config.RGB_565)
        val c = Canvas(b)

        // Draw background
        val paint = Paint(
            (Paint.ANTI_ALIAS_FLAG or Paint.LINEAR_TEXT_FLAG)
        )
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        //  paint.typeface = typeface
        c.drawPaint(paint)

// Draw text
        c.save()
        c.translate(0f, 0f)
        mTextLayout.draw(c)
        c.restore()
        return b
    }

    private fun CheckNameField(): Boolean {
        if (binding.nameEdTxt.length() == 0) {
            binding.nameEdTxt.setError("This field is required")
            return false
        }

        // after all validation return true.
        return true
    }

    private fun CheckAddressField(): Boolean {
        if (binding.addressEdTxt.length() == 0) {
            binding.addressEdTxt.setError("This field is required")
            return false
        }

        // after all validation return true.
        return true
    }


    fun BilNum(num: String): String? {
        var num = num
        var lastNum = ""
        for (i in 0 until num.length) {
            lastNum = num[i].toString()
        }
        val numChar = lastNum.toInt()
        num = if (numChar == 0) {
            "Z$num"
        } else if (numChar == 1) {
            "N$num"
        } else if (numChar == 2) {
            "X$num"
        } else if (numChar == 3) {
            "M$num"
        } else if (numChar == 4) {
            "A$num"
        } else if (numChar == 5) {
            "F$num"
        } else if (numChar == 6) {
            "W$num"
        } else if (numChar == 7) {
            "R$num"
        } else if (numChar == 8) {
            "H$num"
        } else {
            "J$num"
        }
        return num
    }


}