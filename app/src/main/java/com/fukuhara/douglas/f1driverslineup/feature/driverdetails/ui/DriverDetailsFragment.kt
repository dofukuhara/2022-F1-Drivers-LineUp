package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fukuhara.douglas.f1driverslineup.R
import com.fukuhara.douglas.f1driverslineup.databinding.DriverDetailFragmentBinding
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.di.driverDetailsModule
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.viewmodel.DriverDetailsViewModel
import com.fukuhara.douglas.lib.common.logger.AppLogger
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class DriverDetailsFragment : Fragment(R.layout.driver_detail_fragment) {
    private var _binding: DriverDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val navArgs: DriverDetailsFragmentArgs by navArgs()
    private val viewModel: DriverDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DriverDetailFragmentBinding.inflate(inflater, container, false)

        loadKoinModules(driverDetailsModule)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null

        unloadKoinModules(driverDetailsModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initViewModel(navArgs.driverModel)

        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        setupToolbarCustomizationObserver()
        setupDriverRaceDetailsList()
    }

    private fun setupToolbarCustomizationObserver() {
        viewModel.toolbarTitle.observe(viewLifecycleOwner) { toolbarTitle ->
            binding.driverDetailToolbar.setText(toolbarTitle)
            binding.driverDetailToolbar.setUpBackButton { findNavController().navigateUp() }
        }
    }

    private fun setupDriverRaceDetailsList() {
        viewModel.driverRaceDetailsModel.observe(viewLifecycleOwner) { listOfRaces ->
            val appLogger: AppLogger by inject()
            appLogger.v("FUKUHARALOG", listOfRaces.toString())
        }
    }
}
