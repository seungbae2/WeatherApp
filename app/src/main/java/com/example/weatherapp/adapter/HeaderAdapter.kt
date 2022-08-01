package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemHeaderBinding
import com.example.weatherapp.model.LocationModel

class HeaderAdapter() : RecyclerView.Adapter<HeaderAdapter.ViewHolder>(){

    var locationList = arrayListOf<LocationModel>()

    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(locationList[position])
    }

    inner class ViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(location: LocationModel) {
            binding.headerText.text = location.city.name

            forecastAdapter = ForecastAdapter(location.list)
            binding.forecastRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.forecastRecyclerView.adapter = forecastAdapter
            forecastAdapter.notifyItemInserted(0)
            //forecastAdapter.forecastList = location.list as ArrayList<ForecastModel>
        }
    }

    override fun getItemCount() = locationList.size

}
