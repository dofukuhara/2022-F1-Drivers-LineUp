package com.fukuhara.douglas.f1driverslineup.feature.driverslist.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fukuhara.douglas.f1driverslineup.databinding.DriverItemBinding
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriverModel
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriversListModel

class DriversListAdapter(private val dataset: DriversListModel) : RecyclerView.Adapter<DriversListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversListViewHolder {
        val binding = DriverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DriversListViewHolder(binding)
    }

    override fun getItemCount(): Int = dataset.drivers.size

    override fun onBindViewHolder(holder: DriversListViewHolder, position: Int) {
        holder.onBind(dataset.drivers[position])
    }
}

class DriversListViewHolder(private val binding: DriverItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: DriverModel) {
        with(binding) {
            driverItemName.text = data.name
            driverItemTeam.text = data.teamAndNumber
            driverItemTeamColor.setBackgroundColor(Color.parseColor(data.teamColor))
        }
    }
}
