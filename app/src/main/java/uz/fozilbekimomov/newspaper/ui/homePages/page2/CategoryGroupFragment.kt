package uz.fozilbekimomov.newspaper.ui.homePages.page2

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_category.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.list.CategoryAdapter
import uz.fozilbekimomov.newspaper.core.list.OnItemClickListener
import uz.fozilbekimomov.newspaper.core.models.category.CategoryData
import uz.fozilbekimomov.newspaper.core.models.preCategory.PreCategoryItem
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.base.BaseFragment
import javax.inject.Inject

class CategoryGroupFragment : BaseFragment(R.layout.fragment_category), CategoryGroupContract.View,
    CategoryAdapter.OnAllItemClickListener, OnItemClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: CategoryGroupVM by viewModels { viewModelProviderFactory }

    private val categoryDataObserver = Observer(::setData)

    private val adapter = CategoryAdapter()

    override fun onViewCreate() {

        viewModel.preCategoryDat.observe(this, categoryDataObserver)
        viewModel.loadCategoryData(requireContext())
        categoryList.layoutManager = LinearLayoutManager(requireContext())
        categoryList.adapter = adapter
        adapter.setALLClickListener(this)
        adapter.setItemClickListener(this)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setError(message: String?) {

    }

    fun setData(data: PreCategoryItem) {
        Log.d(TAG, "setData: $data")
        adapter.setData(data)
    }

    override fun onAllClick(data: CategoryData) {

        Log.d(TAG, "onAllClick: $data")

        val bundle = bundleOf(
            "categoryData" to data
        )
        findNavController().navigate(R.id.toCategoryFragment, bundle)
    }


    override fun onItemClick(any: Any) {
        if (any is ArticleData) {
            val bundle = bundleOf("articleData" to any)
            findNavController().navigate(R.id.toDescriptionFragment, bundle)
        }
    }


}