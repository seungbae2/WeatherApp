package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.HeaderAdapter
import com.example.weatherapp.api.WeatherService
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.ForecastModel
import com.example.weatherapp.model.LocationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var headerAdapter: HeaderAdapter

    private lateinit var weatherService: WeatherService

    private var locationList = arrayListOf(
        LocationModel(CityModel("Seoul"), emptyList()),
        LocationModel(CityModel("London"), emptyList()),
        LocationModel(CityModel("Chicago"), emptyList())
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initRetrofit()


        initData()
    }

    private fun initRecyclerView() {
        headerAdapter = HeaderAdapter()

        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.weatherRecyclerView.adapter = headerAdapter
        headerAdapter.locationList = locationList
    }

    private fun initRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherService = retrofit.create(WeatherService::class.java)
    }

    private fun initData() {
        getForecast("Seoul")
        getForecast("London")
        getForecast("Chicago")
    }

    private fun getForecast(location: String) {

        weatherService.getForecasts(location,"metric",getString(R.string.openWeatherAPIKey))
            .enqueue(object: Callback<LocationModel> {
                override fun onResponse(
                    call: Call<LocationModel>,
                    response: Response<LocationModel>
                ) {
                    if(response.isSuccessful.not()) {
                        Log.e(TAG, "Not Success")
                        return
                    }

                    response.body()?.let {
                        Log.d(TAG, it.toString())

                        when(it.city.name) {
                            "Seoul" -> headerAdapter.notifyItemChanged(0)
                            "London" -> headerAdapter.notifyItemChanged(1)
                            "Chicago" -> headerAdapter.notifyItemChanged(2)
                        }

                    }
                }

                override fun onFailure(call: Call<LocationModel>, t: Throwable) {
                    Log.e(TAG, t.toString())
                }
            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}