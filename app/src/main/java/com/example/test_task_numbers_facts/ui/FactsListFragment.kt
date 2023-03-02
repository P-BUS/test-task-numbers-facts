package com.example.test_task_numbers_facts.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_numbers_facts.R
import com.example.test_task_numbers_facts.data.model.NumberFactModel
import com.example.test_task_numbers_facts.databinding.FactsListFragmentBinding
import com.example.test_task_numbers_facts.ui.adapters.ForecastListAdapter
import com.example.test_task_numbers_facts.ui.viewmodel.FactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FactsListFragment : Fragment() {
    private val sharedViewModel: FactsViewModel by activityViewModels()
    private lateinit var binding: FactsListFragmentBinding
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FactsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.rvWeatherScroll
        val recyclerLayoutManager = LinearLayoutManager(requireContext())
        recyclerLayoutManager.apply {
            recyclerView.layoutManager = this
            // reverse the direction of adding the new item
            this.reverseLayout = true
            this.stackFromEnd = true
        }

        val adapter = ForecastListAdapter { currentFact ->
            sharedViewModel.updateCurrentFact(currentFact)
            findNavController().navigate(R.id.action_factsListFragment_to_factDetailsFragment)
        }

        recyclerView.adapter = adapter

        // Submit city name in editText field
        binding.etEditCity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val editText = binding.etEditCity.text.toString().trim()
                sharedViewModel.retrieveFact(editText)
                // Hides the keyboard
                parentFragment?.hideKeyboard()
                true
            } else {
                false
            }
        }

        // Observe facts flow information
        lifecycleScope.launch {
            sharedViewModel.numberFacts
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect { list ->
                    adapter.submitList(list)
                    list.map { fact -> bindForecast(fact) }
                    recyclerView.smoothScrollToPosition(adapter.itemCount)
                }
        }

        binding.button.setOnClickListener {
            sharedViewModel.retrieveRandomFact()
        }
    }

    private fun bindForecast(forecast: NumberFactModel) {
        binding.tvCity.text = forecast.number
        binding.tvTemperature.text = forecast.numberFact
    }

    // Hides the keyboard
    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
