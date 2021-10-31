package com.pru.kmmskelton.android.base

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {
    abstract fun setupViews()

    abstract fun setupObservers()

    abstract fun setupListeners()
}