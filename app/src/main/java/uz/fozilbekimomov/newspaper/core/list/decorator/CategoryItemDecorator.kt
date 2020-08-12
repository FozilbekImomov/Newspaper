package uz.fozilbekimomov.newspaper.core.list.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/11/20
 * @project Newspaper
 */


class CategoryItemDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val position = parent.getChildAdapterPosition(view)
        val count = parent.adapter!!.itemCount

        outRect.left = spacing / 2
        outRect.right = spacing / 2

        if (position == 0) {
            outRect.left = spacing
        }
        if (position == count - 1) {
            outRect.right = spacing
        }
    }
}