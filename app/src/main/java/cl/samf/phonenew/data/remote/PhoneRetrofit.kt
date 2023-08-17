package cl.samf.phonenew.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhoneRetrofit {

    companion object {
        private val URL_BASE =
            "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun getRetrofitClient(): PhoneApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create((PhoneApi::class.java))

        }
    }
}