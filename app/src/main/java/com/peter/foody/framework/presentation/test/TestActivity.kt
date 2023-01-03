package com.peter.foody.framework.presentation.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.peter.foody.databinding.ActivityTestBinding
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.material.tabs.TabLayoutMediator
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.presentation.categories.CategoryViewModel

@AndroidEntryPoint

class TestActivity : AppCompatActivity() {

    private val viewModel: CategoryViewModel by viewModels()
    lateinit var binding: ActivityTestBinding
    var array = arrayOf(
        "One",
        "Two","Two",
        "Three"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  binding.viewModel = viewModel
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        binding.lifecycleOwner = this
        viewModel.getcategory("6")
        viewModel.categoryLiveData.observe(this){
            when (it) {
                is State.Loading -> Log.d("0", "")
                is State.Success ->

                    if (it.data.State == 1) {
                        var array2 =  it.data.data

                }else{
                    State.Error(it.data.Message)
                }

                is State.Error-> Toast.makeText(this,it.toError(), Toast.LENGTH_SHORT).show()
            }

        }
//        var arr = arrayOf("")
//        for (item in ) {
//
//        }


        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = array[position]
        }.attach()
    }
}