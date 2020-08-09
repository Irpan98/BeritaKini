package id.itborneo.beritakini.data.remote

import id.itborneo.beritakini.BuildConfig.API_KEY
import id.itborneo.beritakini.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {


    @GET("v2/top-headlines?country=id&apiKey=$API_KEY")
    fun getNews(): Call<NewsResponse>

}