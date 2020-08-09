package id.itborneo.beritakini.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.itborneo.beritakini.data.Repository
import id.itborneo.beritakini.data.model.News

class MainViewModel : ViewModel(){

    fun getNews(): LiveData<List<News>> = Repository.getNews()

}