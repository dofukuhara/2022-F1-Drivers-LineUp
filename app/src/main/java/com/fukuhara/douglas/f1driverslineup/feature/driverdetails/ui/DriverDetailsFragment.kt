package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.fukuhara.douglas.f1driverslineup.R
import com.fukuhara.douglas.f1driverslineup.databinding.DriverDetailFragmentBinding
import com.fukuhara.douglas.lib.common.logger.AppLogger
import org.koin.android.ext.android.inject

class DriverDetailsFragment : Fragment(R.layout.driver_detail_fragment) {
    private var _binding: DriverDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val navArgs: DriverDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DriverDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appLogger: AppLogger by inject()
        appLogger.v("DriverDetailsFragment", "-> ${navArgs.driverModel}")
    }
}
