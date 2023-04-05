package com.example.base_demo.ui.main

import androidx.lifecycle.viewModelScope
import com.example.base_demo.ui.base.BaseViewModel
import com.example.base_demo.repos.MainRepository
import com.example.base_demo.util.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {

    fun getUsers() {
        viewModelScope.launch {
            val numbers = repository.getNumber()
        }
    }

}