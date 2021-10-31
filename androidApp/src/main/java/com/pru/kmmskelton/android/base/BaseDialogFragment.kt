package com.pru.kmmskelton.android.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment(@LayoutRes layoutResID: Int) : DialogFragment(layoutResID){

    abstract fun setupViews()

    abstract fun setupObservers()

    abstract fun setupListeners()
}