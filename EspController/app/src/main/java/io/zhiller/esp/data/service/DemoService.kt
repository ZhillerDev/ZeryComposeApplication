package io.zhiller.esp.data.service

import io.zhiller.esp.domain.Demo
import io.zhiller.esp.domain.dto.DemoDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DemoService{
  @POST("demo/test")
  fun createPost(@Body demo: Demo): Call<DemoDto>
}