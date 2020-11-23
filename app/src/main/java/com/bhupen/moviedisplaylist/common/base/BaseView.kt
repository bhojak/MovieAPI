package com.bhupen.moviedisplaylist.common.base

import android.widget.Toast

/**
 * Specifies view functions used throughout the [BaseFragment]s.
 * such as showMessage, showLoading, etc.
 */
interface BaseView {

    fun showMessage(message: String, length: Int = Toast.LENGTH_SHORT)
    fun showSnack(message: String, actionTitle: String, action: () -> Unit)
}

