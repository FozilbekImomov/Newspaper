package uz.fozilbekimomov.newspaper.ui.homePages.page3

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.list.OnItemClickListener
import uz.fozilbekimomov.newspaper.core.list.SearchAdapter
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.base.BaseFragment
import javax.inject.Inject

class SavedFragment : BaseFragment(R.layout.fragment_saved), SavedContract.View,
    OnItemClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: SavedVM by viewModels { viewModelProviderFactory }

    private val setDataObserver = Observer(::setData)

    private val adapter = SearchAdapter()

    override fun onViewCreate() {
//        viewModel.getSavedData.observe(this, setDataObserver)
        contentList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        contentList.adapter = adapter
        adapter.setItemClickListener(this)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter.clearData()
        viewModel.getSavedData.observe(this, setDataObserver)
    }

    override fun onDetach() {
        adapter.clearData()
        super.onDetach()
    }


    override fun setMessage(message: String?) {
        message?.let { Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show() }
    }

    fun setData(data: List<ArticleData>) {
        adapter.clearData()
        adapter.setData(data)
    }

    override fun onItemClick(any: Any) {
        if (any is ArticleData) {
            val bundle = bundleOf("articleData" to any)
            findNavController().navigate(R.id.toDescriptionFragment, bundle)
        }
    }


}