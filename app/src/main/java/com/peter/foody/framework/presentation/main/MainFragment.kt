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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.peter.foody.business.model.Slider
import com.peter.foody.business.model.foods.FoodBill
import com.peter.foody.business.model.foods.SetBillModel
import com.peter.foody.business.usecases.State
import com.peter.foody.databinding.FragmentMainBinding
import com.peter.foody.framework.presentation.adapters.*
import com.peter.foody.framework.presentation.reports.PrintPic
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    var list=ArrayList<FoodBill>()
    lateinit var adapter2 : CategoriesAdapter


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

    var numberOfBill :Int = 0

    var msgToPrint = ""

    var CusID:String = ""
    var name:String = ""
    var Total= ""
    var TotalReturn=""
    var Unpaid_deferred=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

       val macAddress =
           Settings.Secure.getString(activity!!.getContentResolver(), "android_id")



        binding.payBtn.setOnClickListener {
            registerBillAndPrint("hph")
            if(list.isEmpty()&&list.size==0){

            }else{
                var progressDoalog: ProgressDialog  = ProgressDialog(activity)
                progressDoalog.max = 100
                progressDoalog.setMessage("Its loading....")
                progressDoalog.setTitle(" جارى تسجيل الفاتوره ")
                //    progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //    progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDoalog.show()

                viewModel.setBill(SetBillModel("6", macAddress,list))
                viewModel.ruternBill.observe(activity!!){
                    Toast.makeText(context, it.toData().toString(), Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, it.toData().toString(), Toast.LENGTH_SHORT).show()
                    // noteeee
                    progressDoalog.dismiss()

                }
            }



        }


         adapter2 = CategoriesAdapter(onCategoryClickListener = OnCategoryClickListener {
          //  it
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()

            // set value in array list
            var  arrayList = ArrayList<Int>()

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

            // Initialize dialog
            val dialog = Dialog(context!!)

            // set custom dialog

            // set custom dialog
            dialog.setContentView(R.layout.list_content)

            // set custom height and width

            // set custom height and width
            dialog.window!!.setLayout(300, 300)

            // set transparent background

            // set transparent background
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))

            // show dialog

            // show dialog
            dialog.show()

            // Initialize and assign variable
            // EditText editText=dialog.findViewById(R.id.edit_text);

            // Initialize and assign variable
            // EditText editText=dialog.findViewById(R.id.edit_text);
            val listView = dialog.findViewById<ListView>(R.id.list)

            // Initialize array adapter

            // Initialize array adapter
            val adapterlist: ArrayAdapter<Int> =
                ArrayAdapter<Int>(context!!, R.layout.simple_list_item_1, arrayList)

            // set adapter

            // set adapter
            listView.adapter = adapterlist


            listView.onItemClickListener =
                OnItemClickListener { parent, view, position, id ->
                    Toast.makeText(context,  adapterlist.getItem(position).toString(), Toast.LENGTH_SHORT).show()
                   list.get(it).contaty= adapterlist.getItem(position)!!

                   list.get(it).balanc= list.get(it).contaty*list.get(it).Selling_Price
                    //= adapterlist.getItem(position)!!

                    adapter2.notifyDataSetChanged()
                    calculateBalance(list)

                   // list.set(it,String replaceElement);
                    dialog.dismiss()
                }
           // Toast.makeText(context, it.ItemName, Toast.LENGTH_SHORT).show()
        })

       binding. textView3.setOnClickListener {

           var list2=ArrayList<Slider>()
           list2.add(Slider(""))
           list2.add(Slider(""))
           list2.add(Slider(""))
           list2.add(Slider(""))
           list2.add(Slider(""))
           list2.add(Slider(""))
           list2.add(Slider(""))
           list2.add(Slider(""))
           val adapter12 =  SlidersAdapter( onSliderClickListener= OnSliderClickListener{


           })
           binding.offers.adapter =    adapter12
           adapter12 .submitList(list2)
       }


        binding.Add.setOnClickListener {
            val name=binding.nameTv.text.toString()
            val code=  binding.CodeID.text.toString()
            val contaty=  binding.cashEditText.text.toString()
            val Selling_Price=  binding.saleTv.text.toString()
            val  balanc=  binding.deferredEditText.text.toString()

            list.add(FoodBill(

               1,name,

                        name,
                                code,
                        contaty.toInt(),
                        Selling_Price.toDouble(),
                        balanc.toDouble(),
                        1,
                        1,
                        0.0,
                0.0,
                0.0,
                1,
                1,
                1,
            ) )
            binding.categories.adapter = adapter2
            adapter2.submitList(list)
            calculateBalance(list)

        }


        binding.button2.setOnClickListener {
            // set value in array list
            val arrayList = java.util.ArrayList<Int>()

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

            // Initialize dialog
            val dialog = Dialog(context!!)

            // set custom dialog

            // set custom dialog
            dialog.setContentView(R.layout.list_content)

            // set custom height and width

            // set custom height and width
            dialog.window!!.setLayout(300, 300)

            // set transparent background

            // set transparent background
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))

            // show dialog

            // show dialog
            dialog.show()

            // Initialize and assign variable
            // EditText editText=dialog.findViewById(R.id.edit_text);

            // Initialize and assign variable
            // EditText editText=dialog.findViewById(R.id.edit_text);
            val listView = dialog.findViewById<ListView>(R.id.list)

            // Initialize array adapter

            // Initialize array adapter
            val adapterlist: ArrayAdapter<Int> =
                ArrayAdapter<Int>(context!!, R.layout.simple_list_item_1, arrayList)

            // set adapter

            // set adapter
            listView.adapter = adapterlist

            listView.onItemClickListener =
                OnItemClickListener { parent, view, position, id -> // when item selected from list
                   binding.cashEditText.setText(adapterlist.getItem(position).toString() + "")
                    dialog.dismiss()
                }

        }

        binding.cashEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
              //  Toast.makeText(applicationContext, p0.toString(), Toast.LENGTH_SHORT).show()
                if (p0?.isEmpty() == true) {
                    binding.deferredEditText.setText("0.0")
                } else {
                    if (  binding.saleTv.text.isEmpty()&&  binding.saleTv.text.length==0){


                        binding.saleTv.setText("0.0")

                }else{
                        binding.deferredEditText.setText("")

                        var Total=   binding.saleTv.text.toString().toDouble()*
                                binding.cashEditText.text.toString().toDouble()
                        binding.deferredEditText.setText(Total.toString())
                        binding.deferredEditText.setText(Total.toString())
                }

                }
            }
        })

        binding.offers.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)
            val adapter = OffersAdapter(onOfferClickListener = OnOfferClickListener {

                binding.nameTv.setText("")
                binding.saleTv.setText("")
                binding.cashEditText.setText("0")
                binding.CodeID.setText("")

                binding.deferredEditText.setText("0.0")
                binding.nameTv.setText(it.ItemName)
                binding.saleTv.setText(it.Selling_Price.toString())
                binding.CodeID.setText(it.Barcode)

                Toast.makeText(context, it.ItemName, Toast.LENGTH_SHORT).show()
            })

        viewModel.Food.observe(viewLifecycleOwner){
        when(it){
            is State.Loading ->Log.d("0","")
            is State.Success -> if (it.data.State==1){
                binding.offers.adapter =    adapter
                adapter.submitList(it.toData()!!.data)
            }

              //  Log.d("0","")
            is State.Error ->Log.d("0","")

        }
        //    binding.offers.adapter =    adapter
           // adapter.submitList( it)
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


        binding.textView2.setOnClickListener {
            viewModel.Food.observe(viewLifecycleOwner) {
                when (it) {
                    is State.Loading -> Log.d("0", "")
                    is State.Success -> if (it.data.State == 1) {
                        binding.offers.adapter = adapter
                        adapter.submitList(it.toData()!!.data)
                    }else{
                         State.Error(it.data.Message)
                    }

                    is State.Error-> Toast.makeText(activity,it.toError(),Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

    private fun calculateBalance(list: ArrayList<FoodBill>) {
        var totalBalance = 0.0
        for (i in list.indices) {
            totalBalance += list[i].balanc
        }
        binding.totalBalance.text = totalBalance.toString()
    }
    @Throws(IOException::class)
    fun registerBillAndPrint(numerofBill:String) {




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

    /*
 * This will send data to be printed by the bluetooth printer
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
            (Paint.ANTI_ALIAS_FLAG
                    or Paint.LINEAR_TEXT_FLAG)
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



}