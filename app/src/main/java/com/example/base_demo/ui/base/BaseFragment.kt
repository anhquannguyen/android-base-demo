package com.example.base_demo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(private val inflate: FragmentBindingInflater<VB>) :
    Fragment(), ViewInterface {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding
            ?: throw IllegalStateException("Cannot access view after view destroyed or before view creation")

    protected val navController get() = findNavController()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(savedInstanceState)

        setOnClick()

        bindingStateView()

        bindingAction()
    }

    override fun setOnClick() {}

    override fun initView(savedInstanceState: Bundle?) {}

    override fun bindingStateView() {}

    override fun bindingAction() {}

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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