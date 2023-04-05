package com.example.base_demo.ui.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment : Fragment() {

    private val pressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    protected open fun onBackPressed() {
        requireActivity().finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addBackPressedCallback()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeBackPressedCallBack()
    }

    private fun addBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(this, pressedCallback)
    }

    private fun removeBackPressedCallBack() {
        pressedCallback.remove()
    }

}