package com.pru.kmmskelton.android.presentation.patient_list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.pru.kmmskelton.android.databinding.ItemPatientBinding
import com.pru.kmmskelton.data.models.response.Patient

class PatientListAdapter(private val listener: ((position: Int) -> Unit)? = null) :
    RecyclerView.Adapter<PatientListAdapter.PatientViewHolder>() {

    inner class PatientViewHolder
    constructor(
        val binding: ItemPatientBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Patient) = with(itemView) {
            binding.tvPatientName.text = item.patientName
            binding.tvAge.text = "Age : ${item.patientAge}"
            binding.tvIllCode.text = "ILL Code : ${item.patientIllCode}"
            itemView.setOnClickListener {
                if (adapterPosition != -1) {
                    listener?.let {
                        it(adapterPosition)
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = ItemPatientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bind(differ.currentList.get(position))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: MutableList<Patient>) {
        differ.submitList(list)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Patient>() {

        override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem.patientId == newItem.patientId
        }

        override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

}