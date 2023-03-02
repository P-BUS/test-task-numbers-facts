package com.example.test_task_numbers_facts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.test_task_numbers_facts.data.model.NumberFactModel
import com.example.test_task_numbers_facts.databinding.FactDetailsFragmentBinding
import com.example.test_task_numbers_facts.ui.viewmodel.FactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FactDetailsFragment : Fragment() {
    private val sharedViewModel: FactsViewModel by activityViewModels()
    private lateinit var binding: FactDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FactDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Observe facts flow information
        lifecycleScope.launch {
            sharedViewModel.currentFact
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect { fact ->
                    bindFact(fact)
                }
        }

    }

    private fun bindFact(fact: NumberFactModel) {
        binding.tvNumberDetails.text = fact.number
        binding.tvFactDetails.text = fact.numberFact
    }



}