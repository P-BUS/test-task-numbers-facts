package com.example.test_task_weather_forecast.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task_weather_forecast.R
import com.example.test_task_weather_forecast.data.model.WeatherModel
import com.example.test_task_weather_forecast.databinding.WeatherFragmentBinding
import com.example.test_task_weather_forecast.ui.adapters.ForecastListAdapter
import com.example.test_task_weather_forecast.ui.viewmodel.WeatherViewModel
import com.example.test_task_weather_forecast.utils.ImageLoader
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

const val TAG = "WeatherFragment"

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val sharedViewModel: WeatherViewModel by activityViewModels()
    private lateinit var binding: WeatherFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        recyclerView = binding.rvWeatherScroll
        val adapter = ForecastListAdapter()
        recyclerView.adapter = adapter

        // Submit city name in editText field
        binding.etEditCity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val editText = binding.etEditCity.text.toString()
                sharedViewModel.refreshWeather(editText)
                // Hides the keyboard
                parentFragment?.hideKeyboard()
                true
            } else {
                false
            }
        }

        // Observe weather forecast information
        lifecycleScope.launch {
            sharedViewModel.weatherForecast
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect {
                    bindForecast(it)
                    adapter.submitList(it.list)
                }
        }

        // Determine which permissions the system has granted to your app
        val locationPermissionRequest = activity?.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    false
                ) -> {
                    // Location access granted.
                    Snackbar.make(
                        binding.root,
                        getString(R.string.snackbar_permission),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    // No location access granted.
                    Snackbar.make(
                        binding.root,
                        getString(R.string.snackbar_no_permission),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
        // check whether app already has the permissions,
        // and whether app needs to show a permission rationale dialog
        locationPermissionRequest?.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.getCurrentLocation(
            CurrentLocationRequest.
        )
            .addOnSuccessListener { location : Location? ->
                // Got last known location.
            }

    }


    private fun bindForecast(forecast: WeatherModel) {
        binding.tvCity.text = forecast.cityName
        binding.tvTemperature.visibility = View.VISIBLE // Needed not to see string parameters on empty screen
        binding.tvTemperature.text = getString(R.string.temperature, forecast.list.first().temp)
        binding.tvWeatherDescription.text = forecast.list.first().weather.last().description
        ImageLoader.loadImage(binding.ivWeatherIcon, forecast.list.first().weather.first().icon)
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
