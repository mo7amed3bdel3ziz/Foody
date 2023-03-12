package com.peter.foody.framework.presentation.ui.receipts

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.facebook.stetho.json.ObjectMapper
import com.google.gson.Gson
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.classes.Receipts
import com.peter.foody.data.remote.model.classes.Root
import com.peter.foody.data.roomContacts.HeaderBill
import com.peter.foody.databinding.FragmentReceiptsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class receiptsFragment : Fragment() {
    private lateinit var binding: FragmentReceiptsBinding
    private val viewModel: SendReceiptsViewModel by viewModels()
    lateinit var adapter: AdabterBill
    var receiptslist = ArrayList<Root>()
    var headerslist = ArrayList<HeaderBill>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReceiptsBinding.inflate(inflater)
        var receiptsRootList = Receipts()
        receiptsRootList.setReceipts(receiptslist)

        val androidId: String = Settings.Secure.getString(
            activity!!.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        adapter = AdabterBill()
        binding.recyclerView.adapter = adapter



        viewModel.getBill()
        viewModel.headerListLiveData.observe(viewLifecycleOwner) {
            headerslist = it as ArrayList<HeaderBill>
            Log.d("headerListLiveData", it.get(0).netPrice.toString())
            binding.recyclerView.adapter = adapter
            adapter.submitList(headerslist)
            adapter.notifyDataSetChanged()

        }

        viewModel.ItemsByBillnum.observe(viewLifecycleOwner) {

            receiptslist.add(it)
            // Log.d("CreadteUUID",receiptslist.get(0).itemData.get(0).netSale.toString())
            //. Log.d("CreadteUUID",receiptslist.get(0).itemData.get(0).quantity.toString())
            Log.d("CreateUUID", receiptslist.get(0).itemData.get(0).total.toString())


        }

//        Log.d("accountviewModel",receiptsRootList.getReceipts().get(0).header.receiptNumber)
        binding.paybtn.setOnClickListener {
             var receiptsRootList = Receipts()
             receiptsRootList.setReceipts(receiptslist)
            Log.d("setCustomeAddOrder",   androidId)

        viewModel.setListBillRepoVM(receiptsRootList, androidId)
        val s= Gson()
        var d=   s.toJson(receiptsRootList)
            binding.editTextTextPersonName.setText(d)

            Log.d("setCustomeAddOrder",   d)
        }


        viewModel.setCustomeAddOrder.observe(viewLifecycleOwner) {

            when (it) {
                is State.Loading -> {
                    Log.d("setCustomeAddOrder", "")
                }
                is State.Success -> {
                    Log.d("setCustomeAddOrder", it.data.status.toString())
                    Log.d("setCustomeAddOrder", it.data.submitted.toString() + "00")
                    Log.d("setCustomeAddOrder", it.data.submitted.toString() + "00")
                    //  if (it.data.State == 2)
                    //   {
                    //
//
                    //   }
                    //   else  {
//             //                   Log.d("reportRecycleview", it.data.State .toString())
                    //       Toast.makeText(context, "لا يوجد تقارير في هذا اليوم", Toast.LENGTH_SHORT).show()
                    //   }
                }

                is State.Error ->{
                    Log.d("setCustomeAddOrder", it.messag.toString())
                }

            }

        }





        return binding.root
    }

}