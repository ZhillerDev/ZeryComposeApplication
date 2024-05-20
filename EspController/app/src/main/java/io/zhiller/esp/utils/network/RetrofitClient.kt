package io.zhiller.esp.utils.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
  private const val BASE_URL = "http://xfaf7h.natappfree.cc/"

  val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(
      GsonConverterFactory.create(GsonBuilder().setLenient().create())
    )
    .build()
}