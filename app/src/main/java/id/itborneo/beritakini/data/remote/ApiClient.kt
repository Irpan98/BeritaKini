package id.itborneo.beritakini.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val API_BASE = "https://newsapi.org/"

    fun create(): ApiServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
//            .client(okHTTPHeader())
            .build()
        return retrofit.create(ApiServices::class.java)
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()

            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )

            .build()
    }

}