package uz.fozilbekimomov.newspaper.ui.allNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.repo.category.CategoryRepo
import uz.fozilbekimomov.newspaper.core.utils.Resource
import uz.fozilbekimomov.newspaper.core.vm.BaseViewModel
import javax.inject.Inject

class CategoryVM @Inject constructor(
    private val view: CategoryContract.View,
    private val repo: CategoryRepo
) : BaseViewModel(), CategoryContract.ViewModel {

    private var page = 1
    private val _newsData = MutableLiveData<List<ArticleData>?>()
    val newsLiveDat: LiveData<List<ArticleData>?>
        get() = _newsData

    override fun loadData(category: String) {
        view.showProgress()
        launch {
            val response = withContext(Dispatchers.IO) {
                repo.getCategoryData(page, category)
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
            view.hideProgress()

        }
    }

}