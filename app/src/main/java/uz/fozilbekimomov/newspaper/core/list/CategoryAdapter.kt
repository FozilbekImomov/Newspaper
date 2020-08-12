package uz.fozilbekimomov.newspaper.core.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_category.view.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.models.category.CategoryData
import uz.fozilbekimomov.newspaper.core.models.preCategory.PreCategoryItem
import uz.fozilbekimomov.newspaper.core.utils.alphaView


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/11/20
 * @project Newspaper
 */


class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var data = ArrayList<PreCategoryItem>()

    private var onAllItemClickListener: OnAllItemClickListener? = null
    private var onItemClickListener: OnItemClickListener? = null

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setALLClickListener(onAllItemClickListener: OnAllItemClickListener) {
        this.onAllItemClickListener = onAllItemClickListener
    }

    fun setData(d: PreCategoryItem) {
        this.data.add(d)
        notifyItemInserted(data.size - 1)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val adapter = CategoryGroupAdapter(onItemClickListener)

        fun bindData(d: PreCategoryItem) {

//            val categoryMargin =
//                itemView.context.resources.getDimensionPixelSize(R.dimen.categoryMargin)
//            itemView.categoryItemContent.addItemDecoration(CategoryItemDecorator(categoryMargin))
            itemView.titleCategoryGroup.text = d.categoryData.name
            itemView.categoryItemContent.adapter = adapter
            itemView.categoryItemContent.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            d.data?.let { adapter.setData(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.alphaView(0.2f, 1f)
        holder.bindData(data[position])
        holder.itemView.allCategoryGroup.setOnClickListener {
            onAllItemClickListener?.onAllClick(data[position].categoryData)
        }

    }

    interface OnAllItemClickListener {
        fun onAllClick(data: CategoryData)
    }

}