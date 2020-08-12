package uz.fozilbekimomov.newspaper.core.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_top_item.view.*
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


class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<ArticleData>()
    private var onItemClickListener: OnItemClickListener? = null

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    private enum class Types {
        TOP, CONTENT
    }

    fun setData(articles: List<ArticleData>) {
        data.addAll(articles)
        notifyItemRangeInserted(data.size - articles.size, articles.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            Types.TOP.ordinal -> {
                TopViewViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.home_top_item, parent, false)
                )
            }
            else -> {
                ContentViewViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_home_content, parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> Types.TOP.ordinal
            else -> Types.CONTENT.ordinal
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.alphaView(0.2f, 1f)

        when (getItemViewType(position)) {
            Types.TOP.ordinal -> {
                (holder as TopViewViewHolder).bindData(data[position])
            }
            else -> {
                (holder as ContentViewViewHolder).bindData(data[position])
            }
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(data[position])
        }

    }

    inner class TopViewViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindData(d: ArticleData) {

            val curFormaterDate = SimpleDateFormat("yyyy-mm-dd")
            val dateObj: Date = curFormaterDate.parse(d.mPublishedAt!!.substring(0, 10))
            val postFormater =
                SimpleDateFormat("dd-MMMM, yyyy", Locale(MyCache.getMyCache()!!.getCountry()))

            val newDateStr: String = postFormater.format(dateObj)


            val ymdFormat =
                SimpleDateFormat("yyyy-MM-dd", Locale(MyCache.getMyCache()!!.getCountry()))
            val EEEddMMMyyyy =
                SimpleDateFormat("EEE dd-MM-yyyy", Locale(MyCache.getMyCache()!!.getCountry()))
            val date = d.mPublishedAt.split("T")[0]


            itemView.timeNews.text = "${parseDate(date, ymdFormat, EEEddMMMyyyy)}"

            d.mUrlToImage?.let { itemView.imageNews?.load(it) }
            itemView.titleNews.text = d.mTitle
            itemView.descriptionNews.text = d.mDescription
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
//2020-08-09T05:27:00Z


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