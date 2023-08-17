package cl.samf.phonenew.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {

    @GET("products")
    suspend fun getData(): Response<List<PhoneList>>

    @GET("details/{id}")
    suspend fun getDetailsPhone(@Path("id") id: Int): Response<DetailsPhone>

}