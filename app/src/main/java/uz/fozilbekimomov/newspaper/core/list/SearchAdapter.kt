package uz.fozilbekimomov.newspaper.core.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_home_content.view.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.cache.MyCache
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.utils.alphaView
import uz.fozilbekimomov.newspaper.core.utils.load
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/9/20
 * @project Newspaper
 */


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ContentViewViewHolder>() {

    private val data = ArrayList<ArticleData>()
    private var onItemClickListener: OnItemClickListener? = null

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setData(articles: List<ArticleData>) {
        data.addAll(articles)
        notifyItemRangeInserted(data.size - articles.size, articles.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ContentViewViewHolder {

        return ContentViewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_content, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SearchAdapter.ContentViewViewHolder, position: Int) {
        holder.itemView.alphaView(0.2f, 1f)
        holder.bindData(data[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(data[position])
        }
    }

    fun parseDate(
        inputDateString: String?,
        inputDateFormat: SimpleDateFormat,
        outputDateFormat: SimpleDateFormat
    ): String? {
        var date: Date? = null
        var outputDateString: String? = null
        try {
            date = inputDateFormat.parse(inputDateString)
            outputDateString = outputDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDateString
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    inner class ContentViewViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindData(d: ArticleData) {

            d.mUrlToImage?.let { itemView.imageNewsContent?.load(it) }

            val ymdFormat =
                SimpleDateFormat("yyyy-MM-dd", Locale(MyCache.getMyCache()!!.getCountry()))
            val EEEddMMMyyyy =
                SimpleDateFormat("EEE dd-MM yyyy", Locale(MyCache.getMyCache()!!.getCountry()))
            val date = d.mPublishedAt!!.split("T")[0]

            itemView.timeNewsContent.text = "${parseDate(date, ymdFormat, EEEddMMMyyyy)}"

            itemView.titleNewsContent.text = d.mTitle
        }
    }

}