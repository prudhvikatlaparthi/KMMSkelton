package com.pru.kmmskelton.android.presentation.addPatient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.pru.kmmskelton.android.base.BaseBottomSheetDialogFragment
import com.pru.kmmskelton.android.databinding.FragmentAddPatientBinding
import com.pru.kmmskelton.android.utils.showMessage
import com.pru.kmmskelton.util.Resource
import com.pru.kmmskelton.util.ValidateForm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddPatientFragment : BaseBottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddPatientBinding
    private val addPatientViewModel: AddPatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddPatientBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        setupObservers()
    }

    override fun setupViews() {

    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                addPatientViewModel.patientsState.collectLatest { result ->
                    when (result) {
                        is Resource.Success -> {
                            findNavController().popBackStack()
                        }
                        is Resource.Error -> {
                            showMessage(result.errorMessage ?: "Something went wrong")
                        }
                    }
                }
            }
        }
    }

    override fun setupListeners() {
        binding.btSave.setOnClickListener {
            val result =
                addPatientViewModel.validatePatientForm(patientName = binding.etPatientName.text.toString(),
                    patientAge = binding.etAgeName.text.toString(),
                    patientILLCode = binding.etPatientILLCode.text.toString())
            when (result) {
                is ValidateForm.ISValidFalse -> {
                    showMessage(result.message!!)
                }
                else -> {
                    addPatientViewModel.admitPatient(patientName = binding.etPatientName.text.toString(),
                        patientAge = binding.etAgeName.text.toString(),
                        patientILLCode = binding.etPatientILLCode.text.toString())
                }
            }
        }
    }
}