package id.itborneo.beritakini.utils

import id.itborneo.beritakini.data.model.News
import id.itborneo.beritakini.data.response.ArticlesItem

object Converter {

    fun toNewsObject(results: List<ArticlesItem?>): MutableList<News> {
        val allNews = mutableListOf<News>()

        results.forEach { news ->
            val getNews = News(
                news?.title,
                news?.source?.name,
                news?.author,
                news?.url,
                news?.urlToImage

            )
            allNews.add(getNews)
        }
        return allNews
    }
}
