package com.example.weatherapp.adapter

import android.location.Location
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemHeaderBinding
import com.example.weatherapp.model.LocationModel

class HeaderAdapter() : ListAdapter<LocationModel, HeaderAdapter.ViewHolder>(diffUtil){

    //var locationList = arrayListOf<LocationModel>()

    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(location: LocationModel) {
            binding.headerText.text = location.city.name

            forecastAdapter = ForecastAdapter()
            binding.forecastRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.forecastRecyclerView.adapter = forecastAdapter
            forecastAdapter.submitList(location.list)
            //forecastAdapter.forecastList = location.list as ArrayList<ForecastModel>
        }
    }

    override fun getItemCount() = currentList.size

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<LocationModel>() {
            override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
                return oldItem.city.name == newItem.city.name
            }

        }
    }
}
