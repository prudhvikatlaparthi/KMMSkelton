package com.pru.kmmskelton.android.presentation.hospital

import android.os.Bundle
import android.view.View
import com.pru.kmmskelton.android.R
import com.pru.kmmskelton.android.base.BaseFragment
import com.pru.kmmskelton.android.databinding.FragmentHospitalBinding


class HospitalFragment : BaseFragment(R.layout.fragment_hospital) {
    private lateinit var binding: FragmentHospitalBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHospitalBinding.bind(view)
        setupViews()
        setupListeners()
        setupObservers()
    }

    override fun setupViews() {

    }

    override fun setupObservers() {

    }

    override fun setupListeners() {
        binding.addPatient.setOnClickListener {

        }
    }
}