package com.pru.kmmskelton.android.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutResID: Int) : Fragment(layoutResID) {

    abstract fun setupViews()

    abstract fun setupObservers()

    abstract fun setupListeners()
}