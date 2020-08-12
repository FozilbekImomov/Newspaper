package uz.fozilbekimomov.newspaper.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import uz.fozilbekimomov.newspaper.core.db.dao.ArticlesDao
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.repo.search.SearchRepo
import uz.fozilbekimomov.newspaper.core.utils.Resource
import uz.fozilbekimomov.newspaper.core.vm.BaseViewModel
import javax.inject.Inject

class SearchVM @Inject constructor(
    private val repo: SearchRepo,
    private val view: SearchContract.View,
    private val dao: ArticlesDao
) : BaseViewModel(), SearchContract.ViewModel {

    private var page = 1
    private val _newsData = MutableLiveData<List<ArticleData>?>()
    val newsLiveDat: LiveData<List<ArticleData>?>
        get() = _newsData

    override fun searchData(search: String) {
        if (search.length > 3) {
            view.clearData()
            view.showProgress()
            launch {
                delay(2000)
                val response = withContext(Dispatchers.IO) {
                    repo.getSearchData(page, search)
                }
                when (response.status) {
                    Resource.Status.SUCCESS -> {
                        _newsData.postValue(response.data?.mArticles)
                        page++
                    }
                    Resource.Status.ERROR -> {
                        view.setError(response.message)
                        view.hideProgress()
                    }

                    else -> view.setError("ViewModel->$response")
                }

            }
            view.hideProgress()
        } else {
            view.hideProgress()
        }
    }
}