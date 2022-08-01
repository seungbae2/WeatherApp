package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.model.ForecastModel

class ForecastAdapter(private val forecastList: List<ForecastModel>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    //var forecastList = arrayListOf<ForecastModel>()

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
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
    override fun getItemCount() = forecastList.size

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}