package id.itborneo.beritakini.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.itborneo.beritakini.data.model.News
import id.itborneo.beritakini.data.remote.ApiClient
import id.itborneo.beritakini.data.response.NewsResponse
import id.itborneo.beritakini.utils.Converter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    private val TAG = "Repository"
    fun getNews(): LiveData<List<News>> {
        val allNews = MutableLiveData<List<News>>()

        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d(TAG, "onFailure called, $t")

            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                var getAllnews = mutableListOf<News>()

                if (response.body() != null) {

                    val newsResponse = response.body() as NewsResponse
                    val results = newsResponse.articles
                    if (results != null) {
                        getAllnews = Converter.toNewsObject(results)
                        Log.d(TAG, "getAllNews $getAllnews")

                    }

                } else {
                    Log.d(TAG, "response.body adalah null")
                }

                allNews.postValue(getAllnews)
            }


        })

        return allNews

    }



}