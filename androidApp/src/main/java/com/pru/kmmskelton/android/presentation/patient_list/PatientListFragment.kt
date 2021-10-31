package com.pru.kmmskelton.android.presentation.patient_list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pru.kmmskelton.android.R
import com.pru.kmmskelton.android.base.BaseFragment
import com.pru.kmmskelton.android.databinding.FragmentPatientListBinding
import com.pru.kmmskelton.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
@InternalCoroutinesApi
class PatientListFragment : BaseFragment(R.layout.fragment_patient_list) {

    private lateinit var binding: FragmentPatientListBinding

    private val patientListAdapter: PatientListAdapter by lazy {
        PatientListAdapter {

        }
    }


    private val patientViewModel: PatientListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPatientListBinding.bind(view)
        setupViews()
        setupListeners()
        setupObservers()
    }

    override fun setupViews() {
        binding.patientsView.adapter = patientListAdapter
    }

    override fun setupObservers() {
        patientViewModel.patientsState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.swipeRL.isRefreshing = true
                    binding.tvLoader.isVisible = true
                }
                is Resource.Success -> {
                    binding.swipeRL.isRefreshing = false
                    binding.tvLoader.isVisible = false
                    patientListAdapter.submitList(result.data!!.toMutableList())
                }
                is Resource.Error -> {
                    binding.swipeRL.isRefreshing = false
                    binding.tvLoader.isVisible = true
                    binding.tvLoader.text = result.errorMessage
                }
            }
        }
    }

    override fun setupListeners() {
        binding.addPatient.setOnClickListener {
            val navController = findNavController()
            val action = PatientListFragmentDirections.actionPatientListFragmentToAddPatient()
            navController.navigate(action)
        }

        binding.swipeRL.setOnRefreshListener {
            patientViewModel.getPatients()
        }
    }
}
