package com.peter.foody.framework.presentation.test

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peter.foody.framework.presentation.test.categoriesPager.FirstCategories
import com.peter.foody.framework.presentation.test.categoriesPager.SecondCategories
import com.peter.foody.framework.presentation.test.categoriesPager.ThirdCategories

private const val NUM_TABS = 3

public class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FirstCategories()
            1 -> return SecondCategories()
        }
        return ThirdCategories()
    }
}