package com.example.viewtreeobserverusecases.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private val _currentFragmentTitle = MutableLiveData<String>()
    val currentFragmentTitle: LiveData<String> = _currentFragmentTitle

    fun updateFragmentTitle(title: String) {
        _currentFragmentTitle.value = title
    }
}