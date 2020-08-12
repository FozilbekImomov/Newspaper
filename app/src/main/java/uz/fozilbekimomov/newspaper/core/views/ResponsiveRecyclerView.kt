package uz.fozilbekimomov.newspaper.core.views

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


open class ResponsiveRecyclerView : RecyclerView, ColumnCounter {
    private var gridLayoutManager: GridLayoutManager? = null
    private var mContext: Context? = null
    private var scrollListener: RecyclerViewScrollListener? = null
    private var dataLoadedlistener: RecyclerViewDataLoadedListener? = null
    private var columnNumber = 2
    private var contentType = BIG
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private val startingPageIndex = 0
    private val visibleThreshold = 2
    private var loading = true
    private var isEndlessScrollable = true
    private val errorDialogShown = false

    constructor(context: Context) : super(context) {
        setup(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        setup(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        setup(context, attrs)
    }

    private fun setup(
        context: Context,
        attrs: AttributeSet?
    ) {
        if (isInEditMode) return
        this.mContext = context
        notifyColumns()
    }

    fun setScrollListener(scrollListener: RecyclerViewScrollListener?) {
        this.scrollListener = scrollListener
    }

    fun setDataLoadedlistener(dataLoadedlistener: RecyclerViewDataLoadedListener?) {
        this.dataLoadedlistener = dataLoadedlistener
    }

    fun setContentTypeBig() {
        contentType = BIG
        notifyColumns()
    }

    fun setContentTypeMiddle() {
        contentType = MIDDLE
        notifyColumns()
    }

    fun setContentTypeLarge() {
        contentType = LARGE
        notifyColumns()
    }

    fun setPaginationEnable(paginationEnable: Boolean) {
        isEndlessScrollable = paginationEnable
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        if (scrollListener != null && layoutManager != null) {
            if (dy >= 0) {
                if (dy > 0) scrollListener!!.hideFab()
                if (isEndlessScrollable) onEndlessScroll()
            } else {
                scrollListener!!.showFab()
            }
        }
    }

    fun onLoadFailed() {
        if (loading) loading = false
    }

    private fun onEndlessScroll() {
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager!!.itemCount
        if (layoutManager is GridLayoutManager) {
            lastVisibleItemPosition =
                (layoutManager as GridLayoutManager?)!!.findLastVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            lastVisibleItemPosition =
                (layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
        }
        if (totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                loading = true
            }
        }
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            if (currentPage == 0) currentPage++
            scrollListener!!.loadRecyclerData()
            loading = true
        }
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
        if (layout is GridLayoutManager) {
            gridLayoutManager = layout
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        notifyColumns()
        if (adapter != null) adapter!!.notifyDataSetChanged()
    }

    private fun notifyColumns() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            when (contentType) {
                BIG -> {
                    columnNumber = if (isTablet) {
                        4
                    } else {
                        2
                    }
                }
                MIDDLE -> {
                    columnNumber = if (isTablet) {
                        6
                    } else {
                        3
                    }
                }
                LARGE -> {
                    columnNumber = if (isTablet) {
                        2
                    } else {
                        1
                    }
                }
            }
        } else {
            when (contentType) {
                BIG -> {
                    columnNumber = if (isTablet) {
                        6
                    } else {
                        4
                    }
                }
                MIDDLE -> {
                    columnNumber = if (isTablet) {
                        8
                    } else {
                        6
                    }
                }
                LARGE -> {
                    columnNumber = if (isTablet) {
                        4
                    } else {
                        2
                    }
                }
            }
        }
        if (gridLayoutManager != null) gridLayoutManager!!.spanCount = columnNumber
    }

    val isTablet: Boolean
        get() {
            val xlarge =
                mContext!!.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == 4
            val large =
                mContext!!.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
            return xlarge || large
        }

    val isTV: Boolean
        get() {
            val uiMode = mContext!!.resources.configuration.uiMode
            return uiMode and Configuration.UI_MODE_TYPE_MASK == Configuration.UI_MODE_TYPE_TELEVISION
        }

    override fun getColumnNumber(): Int {
        return columnNumber
    }

    interface RecyclerViewScrollListener {
        fun loadRecyclerData()
        fun showFab()
        fun hideFab()
    }

    interface RecyclerViewDataLoadedListener {
        fun onLoad(size: Int)
    }

    companion object {
        private const val LARGE = 0
        private const val BIG = 1
        private const val MIDDLE = 2
    }
}