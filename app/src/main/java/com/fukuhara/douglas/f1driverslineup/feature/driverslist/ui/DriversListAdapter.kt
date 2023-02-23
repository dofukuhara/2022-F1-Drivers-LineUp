package com.fukuhara.douglas.f1driverslineup.feature.driverslist.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fukuhara.douglas.f1driverslineup.databinding.DriverItemBinding
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriverModel
import com.fukuhara.douglas.lib.common.services.ImageLoader

class DriversListAdapter(private val dataset: List<DriverModel>, private val imageLoader: ImageLoader) : RecyclerView.Adapter<DriversListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversListViewHolder {
        val binding = DriverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DriversListViewHolder(binding)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: DriversListViewHolder, position: Int) {
        holder.onBind(dataset[position], imageLoader)
    }
}

class DriversListViewHolder(private val binding: DriverItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: DriverModel, imageLoader: ImageLoader) {
        with(binding) {
            driverItemName.text = data.name
            driverItemTeam.text = data.teamAndNumber
            driverItemTeamColor.setBackgroundColor(Color.parseColor(data.teamColor))

            imageLoader.load(data.imageUrl, driverItemAvatar)
            imageLoader.load(data.countryFlagUrl, driverItemCountryFlag)
        }
    }
}
