package uz.fozilbekimomov.newspaper.ui.allNews

import android.content.Context
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_all.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.list.OnItemClickListener
import uz.fozilbekimomov.newspaper.core.list.SearchAdapter
import uz.fozilbekimomov.newspaper.core.models.category.CategoryData
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.utils.hide
import uz.fozilbekimomov.newspaper.core.utils.show
import uz.fozilbekimomov.newspaper.core.views.ResponsiveRecyclerView
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.base.BaseFragment
import uz.fozilbekimomov.newspaper.ui.main.MainActivity
import javax.inject.Inject

class CategoryFragment : BaseFragment(R.layout.fragment_all),
    ResponsiveRecyclerView.RecyclerViewScrollListener, CategoryContract.View, OnItemClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: CategoryVM by viewModels { viewModelProviderFactory }

    private val newsDataObserver = Observer(::setNewsData)

    private val adapter = SearchAdapter()

    private var category: CategoryData? = null

    override fun onViewCreate() {
        viewModel.newsLiveDat.observe(this, newsDataObserver)
        category = requireArguments().getParcelable("categoryData")

        contentList.adapter = adapter
        adapter.setItemClickListener(this)
        contentList.layoutManager = LinearLayoutManager(requireContext())
        contentList.setPaginationEnable(true)
        contentList.setScrollListener(this)
        category?.let {
            titleFragment.text = it.name
            viewModel.loadData(it.id)
        }
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun setNewsData(data: List<ArticleData>) {
        Log.d(TAG, "setHomeData: $data")
        adapter.setData(data)
    }

    override fun loadRecyclerData() {
        category?.let {
            viewModel.loadData(it.id)
        }
    }

    override fun showFab() {

    }

    override fun hideFab() {

    }

    override fun showProgress() {
        progressSearch.show()
    }

    override fun hideProgress() {
        progressSearch.hide()
    }

    override fun setError(message: String?) {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
        (activity as MainActivity).hideTopActionBar()
    }


    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).showBottomNavigation()
        (activity as MainActivity).showTopActionBar()
    }

    override fun onItemClick(any: Any) {
        if (any is ArticleData) {
            val bundle = bundleOf("articleData" to any)
            findNavController().navigate(R.id.toDescriptionFragment, bundle)
        }
    }


}