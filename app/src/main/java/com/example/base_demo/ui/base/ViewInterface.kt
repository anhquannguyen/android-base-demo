package com.example.base_demo.ui.base

import android.os.Bundle

interface ViewInterface {
    fun setOnClick()
    fun initView(savedInstanceState: Bundle?)
    fun bindingStateView()
    fun bindingAction()
}
