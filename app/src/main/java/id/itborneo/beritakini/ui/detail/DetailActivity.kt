package id.itborneo.beritakini.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.itborneo.beritakini.R
import id.itborneo.beritakini.data.model.News
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_news.view.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NEWS = "news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getDataIntent = intent.getParcelableExtra<News>(EXTRA_NEWS)
        if (getDataIntent != null) setDetailNews(getDataIntent)

    }


    private fun setDetailNews(news: News) {
        news.apply {
            tvTitle.text = title

            btnKeBerita.setOnClickListener {
                openWebPage(news.url)
            }
            tvSourceName.text = news.sourceName
            tvAuthor.text = news.author
            Glide.with(this@DetailActivity)
                .load(news.urlImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
                .into(ivNews)

        }


    }

    private fun openWebPage(url: String?) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )

        startActivity(intent)
    }
}