package id.itborneo.beritakini.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.itborneo.beritakini.R
import id.itborneo.beritakini.data.model.News
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_news.view.*
import kotlinx.android.synthetic.main.item_news.view.ivNews
import kotlinx.android.synthetic.main.item_news.view.tvTitle

class MainAdapter(val clickListener: (News) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var allNews = listOf<News>()
    fun setNews(allNews: List<News>) {
        this.allNews = allNews
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = allNews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(allNews[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {

            itemView.apply {
                tvTitle.text = news.title
                Glide.with(context)
                    .load(news.urlImage)
                    .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
                    .into(ivNews)

                setOnClickListener {
                    clickListener(news)
                }
            }


        }
    }

}