package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.adapter.HeaderAdapter.Companion.diffUtil
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.model.ForecastModel
import com.example.weatherapp.model.LocationModel

class ForecastAdapter() : ListAdapter<ForecastModel,ForecastAdapter.ForecastViewHolder>(diffUtil) {
    //var forecastList = arrayListOf<ForecastModel>()

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    class ForecastViewHolder(private val binding: ItemWeatherBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: ForecastModel) {
            binding.dateTextView.text = forecast.dtTxt
            binding.weatherTextView.text = forecast.weather[0].description
            binding.maxTempTextView.text = forecast.main.maxTemp.toString()
            binding.minTempTextView.text = forecast.main.minTemp.toString()

            val icon = forecast.weather[0].icon

            Glide
                .with(binding.coverImageView.context)
                .load("https://openweathermap.org/img/wn/${icon}@2x.png")
                .into(binding.coverImageView)
        }
    }
    override fun getItemCount() = currentList.size

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ForecastModel>() {
            override fun areItemsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ForecastModel, newItem: ForecastModel): Boolean {
                return oldItem.dtTxt == newItem.dtTxt
            }

        }
    }
}