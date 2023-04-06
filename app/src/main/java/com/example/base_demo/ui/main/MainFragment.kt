package com.example.base_demo.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.base_demo.R
import com.example.base_demo.databinding.FragmentMainBinding
import com.example.base_demo.ui.base.BaseFragment
import com.example.base_demo.ui.tabcreate.CreateFragment
import com.example.base_demo.ui.tabhistory.HistoryFragment
import com.example.base_demo.ui.tabscan.ScanFragment
import com.example.base_demo.ui.tabsetting.SettingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private val listTab = arrayListOf<Fragment>(
        ScanFragment(),
        HistoryFragment(),
        CreateFragment(),
        SettingFragment()
    )

    override fun initView(savedInstanceState: Bundle?) {
        setupBottomNavBar()
    }

    private fun setupBottomNavBar() {
        changeTabByPosition(0)
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_scan -> changeTabByPosition(0)
                R.id.tab_history -> changeTabByPosition(1)
                R.id.tab_create -> changeTabByPosition(2)
                R.id.tab_setting -> changeTabByPosition(3)
            }
            return@setOnItemSelectedListener true
        }

        binding.bottomNav.menu.forEachIndexed { index, item ->
            binding.bottomNav.findViewById<View>(item.itemId).setOnLongClickListener {
                binding.bottomNav.selectedItemId = item.itemId
                changeTabByPosition(index)
                return@setOnLongClickListener true
            }
        }
    }

    private fun changeTabByPosition(position: Int) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.container, listTab[position])
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}