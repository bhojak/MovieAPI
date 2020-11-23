package com.bhupen.moviedisplaylist.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bhupen.moviedisplaylist.common.utils.SingleLiveEvent


abstract class BaseViewModel() : ViewModel() {
    private val _errorLiveData by lazy { SingleLiveEvent<String?>() }

    /**
     * Dispatches error messages to the observer, in this case: [BaseFragment]
     * This method is only visible to the this class subTypes
     */
    protected fun dispatchMessage(msg: String?) {
        _errorLiveData.postValue(msg)
    }

    fun getErrorLiveData(): LiveData<String?> = _errorLiveData
}