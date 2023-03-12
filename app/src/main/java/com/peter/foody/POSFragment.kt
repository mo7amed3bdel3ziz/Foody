package com.peter.foody

import android.R
import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.peter.foody.data.remote.model.models.ItemsModels
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.databinding.FragmentPOSBinding
import com.peter.foody.framework.presentation.adapters.CategoriesAdapter
import com.peter.foody.framework.presentation.adapters.OnCategoryClickListener
import com.peter.foody.framework.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener

@AndroidEntryPoint
class POSFragment : Fragment() {

    private lateinit var binding: FragmentPOSBinding
    private val viewModel: MainViewModel by viewModels()
    var listItems = ArrayList<ItemsModel>()
    lateinit var adapter2: CategoriesAdapter
    var list = ArrayList<ItemsModels>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPOSBinding.inflate(inflater)

        createAdabter()
        binding.cardView5.setOnClickListener(View.OnClickListener {
            popUp()
        })



    //    binding.categories.adapter = adapter2
    //    adapter2.submitList(list)

        viewModel.getItemsFromLocalDB()
        viewModel.getItemsLiveData.observe(viewLifecycleOwner) {
            listItems = it as ArrayList<ItemsModel>

        }


        return binding.root
    }

    fun popUp() {
        SimpleSearchDialogCompat(activity, "ادخل اسم المنتج  " + "\n", "search", null,
            listItems, SearchResultListener { baseSearchDialogCompat, item, pos ->
                list.add(
                    ItemsModels
                        (
                        1,
                        item.getTitle(),
                        item.getBarcodee(),
                        item.getDescriptionn(),
                        item.getEditorr(),
                        item.getDatee(),
                        item.getUnitTypee(),
                        item.getItemTypee(),
                        item.getItemCodee().toString(),
                        item.getPricee(),
                        1

                    )
                )
                calculateBalance(list)
                 binding.categories.adapter = adapter2
                 adapter2.submitList(list)
                baseSearchDialogCompat.dismiss()
            }).show()
    }


    fun createAdabter() {
        adapter2 = CategoriesAdapter(onCategoryClickListener = OnCategoryClickListener {
            //  Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()

            // set value in array list
            var arrayList = java.util.ArrayList<Int>()

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
                AdapterView.OnItemClickListener { parent, view, position, id ->

                    Toast.makeText(
                        context,
                        adapterlist.getItem(position).toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    list.get(it).Quantity = adapterlist.getItem(position)!!
                    var totalPrice = list.get(it).Quantity * list.get(it).Price
                    list.get(it).Price = totalPrice
                    Toast.makeText(context, totalPrice.toString(), Toast.LENGTH_SHORT).show()
                        //  adapterlist.getItem(position).toString()

                    calculateBalance(list)
                    adapter2.notifyDataSetChanged()

                    dialog.dismiss()
                }
        })
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

        binding.button2.setOnClickListener {
            // registerBillAndPrint("hph")
            if (list.isEmpty() && list.size == 0) {
                Toast.makeText(context,"الفاتورة فارغة", Toast.LENGTH_SHORT).show()

            } else {


                viewModel.createHeader(list)
                list.removeAll(list)
                calculateBalance(list)
                adapter2.notifyDataSetChanged()


            }

        }


    }

    private fun calculateBalance(list:ArrayList<ItemsModels>) {
        var totalBalance = 0.0
        for (i in list.indices) {
            totalBalance += list[i].Price
        }

         binding.totalBalance.setText(totalBalance.toString())
    }
}