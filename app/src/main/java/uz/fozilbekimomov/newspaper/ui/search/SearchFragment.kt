package uz.fozilbekimomov.newspaper.ui.search

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.list.OnItemClickListener
import uz.fozilbekimomov.newspaper.core.list.SearchAdapter
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.utils.hide
import uz.fozilbekimomov.newspaper.core.utils.show
import uz.fozilbekimomov.newspaper.core.views.ResponsiveRecyclerView
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.base.BaseFragment
import uz.fozilbekimomov.newspaper.ui.main.MainActivity
import javax.inject.Inject


class SearchFragment : BaseFragment(R.layout.fragment_search),
    SearchContract.View, ResponsiveRecyclerView.RecyclerViewScrollListener, TextWatcher,
    OnItemClickListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: SearchVM by viewModels { viewModelProviderFactory }

    private val homeDataObserver = Observer(::setHomeData)

    private val adapter = SearchAdapter()

    var searchText = ""

    override fun onViewCreate() {
        viewModel.newsLiveDat.observe(this, homeDataObserver)
        viewModel.searchData(searchText)

        searchView.addTextChangedListener(this)
        searchView?.requestFocus()
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        contentList.adapter = adapter
        contentList.layoutManager = LinearLayoutManager(requireContext())
        contentList.setScrollListener(this)
        contentList.setPaginationEnable(true)
        adapter.setItemClickListener(this)
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT)

    }

    override fun showProgress() {
        progressSearch.show()
    }

    override fun hideProgress() {
        progressSearch.hide()
    }

    override fun clearData() {
        adapter.clearData()
    }

    override fun setError(message: String?) {
        Toast.makeText(requireContext(), "$message", Toast.LENGTH_SHORT).show()
    }

    fun setHomeData(data: List<ArticleData>) {
        Log.d(TAG, "setHomeData: $data")
        adapter.setData(data)
    }

    override fun loadRecyclerData() {
        viewModel.searchData(searchText)
    }

    override fun showFab() {
        search_group.show()
        backButton.show()
    }

    override fun hideFab() {
        search_group.hide()
        backButton.hide()
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        s?.let {
            viewModel.searchData(s.toString())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
        (activity as MainActivity).hideTopActionBar()
        showKeyboard(requireActivity())
    }


    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).showBottomNavigation()
        (activity as MainActivity).showTopActionBar()
        hideKeyboardS(requireActivity())
    }

    fun hideKeyboardS(activity: Activity?) {
        val imm = activity?.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(activity: Activity?) {
        val imm = activity?.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.toggleSoftInputFromWindow(
            view?.applicationWindowToken,
            InputMethodManager.SHOW_FORCED, 0
        )
    }

    override fun onItemClick(any: Any) {
        if (any is ArticleData) {
            val bundle = bundleOf("articleData" to any)
            findNavController().navigate(R.id.toDescriptionFragment, bundle)
        }
    }

}