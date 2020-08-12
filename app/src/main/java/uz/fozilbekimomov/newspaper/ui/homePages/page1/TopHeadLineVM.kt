package uz.fozilbekimomov.newspaper.ui.homePages.page1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import uz.fozilbekimomov.newspaper.core.db.dao.ArticlesDao
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.repo.home.TopHeadLineRepo
import uz.fozilbekimomov.newspaper.core.utils.Resource
import uz.fozilbekimomov.newspaper.core.vm.BaseViewModel
import javax.inject.Inject

class TopHeadLineVM @Inject constructor(
    private val repo: TopHeadLineRepo,
    private val view: TopHeadLineContract.View,
    private val dao: ArticlesDao
) : BaseViewModel(), TopHeadLineContract.ViewModel {

    private var page = 1
    private val _newsData = MutableLiveData<List<ArticleData>?>()
    val newsLiveDat: LiveData<List<ArticleData>?>
        get() = _newsData

    override fun loadTopHeadLineData(saveData: Boolean) {
        view.showProgress()
        launch {
            val response = withContext(Dispatchers.IO) {
                repo.getTopHeadLineData(page)
            }

            when (response.status) {
                Resource.Status.SUCCESS -> {
                    _newsData.postValue(response.data?.mArticles)

                    if (saveData) {
                        response.data?.let {
                            val d = dao.insertArticles(it.mArticles)
                        }
                    }
                    page++
                }
                Resource.Status.ERROR -> {
                    view.setError(response.message)
                    view.hideProgress()
                    if (saveData) {
                        getLocalData()
                    }
                }

                else -> view.setError("ViewModel->$response")
            }

        }
        view.hideProgress()

    }

    override fun getLocalData() {
        launch {
            val localData = withContext(Dispatchers.IO) {
                delay(1000)
                dao.getAllArticles()
            }
            _newsData.postValue(localData)
        }
    }

}