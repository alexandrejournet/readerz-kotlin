package com.zakin.readerzmultiplatform.android.ui.adapters.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zakin.readerzmultiplatform.android.ui.fragments.BibliothequeFragment
import com.zakin.readerzmultiplatform.android.ui.fragments.ExplorerFragment
import com.zakin.readerzmultiplatform.android.ui.fragments.SettingsFragment

class TabPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return BibliothequeFragment()
            1 -> return ExplorerFragment()
            2 -> return SettingsFragment()
        }
        return BibliothequeFragment()
    }
}