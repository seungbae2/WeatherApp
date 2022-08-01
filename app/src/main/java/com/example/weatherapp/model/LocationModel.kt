package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("city") val city: CityModel,
    @SerializedName("list") var list: List<ForecastModel>,
)

data class CityModel(
    @SerializedName("name") val name: String
)

data class ForecastModel(
    @SerializedName("dt_txt") val dtTxt: String,
    @SerializedName("main") val main: MainModel,
    @SerializedName("weather") val weather: List<WeatherModel>
)

data class MainModel(
    @SerializedName("temp_min") val minTemp: Double?,
    @SerializedName("temp_max") val maxTemp: Double?,
)

data class WeatherModel(
    @SerializedName("id") val id:Int,
    @SerializedName("main") val main: String, //날씨
    @SerializedName("description") val description: String, //날씨 상세설명
    @SerializedName("icon") val icon: String, //날씨 아이콘
)