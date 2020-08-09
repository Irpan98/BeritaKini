package id.itborneo.beritakini.ui.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.beritakini.R
import id.itborneo.beritakini.data.model.News
import id.itborneo.beritakini.ui.detail.DetailActivity
import id.itborneo.beritakini.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.partial_loading_recycler.*


class MainActivity : AppCompatActivity() {

    private lateinit var rvAdapter: MainAdapter

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()



        setViewModel()
    }


    private lateinit var viewModel: MainViewModel
    private fun setViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]


        getNews()

    }


    private fun getNews() {
        if (Utils().isInternetAvailable(this)) {
            viewModel.getNews().observe(this, Observer {
                Log.d(TAG, "observer called $it")
                rvAdapter.setNews(it)
                rvAdapter.notifyDataSetChanged()

                svLoading.stopShimmer()
                svLoading.visibility = View.GONE

            })
        } else {
            alertDialog()
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show()
        }
    }


    private fun setRecyclerView() {


        rvAdapter = MainAdapter {
            moveActivty(it)
        }

        rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = rvAdapter

        }
    }

    private fun alertDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("Tidak Ada Internet")
        builder.setMessage("BeritaKini membutuhkan internet. Silahkan Aktifkan Koneksi Internet dan tekan Retry.")

        builder.setPositiveButton(
            "Retry"
        ) { dialog, _ ->
            dialog.dismiss()
            getNews()
        }
        val dialog: AlertDialog = builder.create() // calling builder.create after adding buttons

        dialog.show()
        Toast.makeText(this, "Network Unavailable!", Toast.LENGTH_LONG).show()
    }


    private fun moveActivty(news: News) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_NEWS, news)
        startActivity(intent)
    }


}