package cl.samf.phonenew.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface PhoneApi {

    @GET("products")
    suspend fun getData(): Response<List<PhoneList>>

}