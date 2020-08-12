package uz.fozilbekimomov.newspaper.ui.homePages.page1

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_top_head_line.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.list.HomeAdapter
import uz.fozilbekimomov.newspaper.core.list.OnItemClickListener
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.utils.hide
import uz.fozilbekimomov.newspaper.core.utils.show
import uz.fozilbekimomov.newspaper.core.views.ResponsiveRecyclerView
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.base.BaseFragment
import uz.fozilbekimomov.newspaper.ui.main.MainActivity
import javax.inject.Inject

class TopHeadLineFragment : BaseFragment(R.layout.fragment_top_head_line),
    TopHeadLineContract.View, ResponsiveRecyclerView.RecyclerViewScrollListener,
    OnItemClickListener {


    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: TopHeadLineVM by viewModels { viewModelProviderFactory }

    private val homeDataObserver = Observer(::setHomeData)

    private val adapter = HomeAdapter()

    override fun onViewCreate() {
        viewModel.newsLiveDat.observe(this, homeDataObserver)
        viewModel.loadTopHeadLineData(false)

        contentList.adapter = adapter
        adapter.setItemClickListener(this)
        contentList.layoutManager = LinearLayoutManager(requireContext())
        contentList.setScrollListener(this)
        contentList.setPaginationEnable(true)
    }

    override fun showProgress() {
        progressHome.show()
    }

    override fun hideProgress() {
        progressHome.hide()
    }

    override fun setError(message: String?) {
        Toast.makeText(requireContext(), "$message", Toast.LENGTH_SHORT).show()
    }

    fun setHomeData(data: List<ArticleData>) {
        Log.d(TAG, "setHomeData: $data")
        adapter.setData(data)
    }

    override fun loadRecyclerData() {
        viewModel.loadTopHeadLineData(false)
    }

    override fun showFab() {
        (activity as MainActivity).showBottomNavigation()
    }

    override fun hideFab() {
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onItemClick(any: Any) {
        if (any is ArticleData) {
            val bundle = bundleOf("articleData" to any)
            findNavController().navigate(R.id.toDescriptionFragment, bundle)
        }
    }


}