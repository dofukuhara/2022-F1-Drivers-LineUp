package com.fukuhara.douglas.f1driverslineup.feature.driverslist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fukuhara.douglas.f1driverslineup.R
import com.fukuhara.douglas.f1driverslineup.databinding.DriversListFragmentBinding
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.di.driversListModule
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.viewmodel.DriversListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class DriversListFragment : Fragment(R.layout.drivers_list_fragment) {
    private var _binding: DriversListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DriversListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(driversListModule)

        _binding = DriversListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null

        unloadKoinModules(driversListModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()

        setupLiveDataObservers()
    }

    private fun setupLiveDataObservers() {
        viewModel.driversListModel.observe(viewLifecycleOwner) { driversListModels ->
            // TODO : Populate view with Drivers List content
        }
    }
}