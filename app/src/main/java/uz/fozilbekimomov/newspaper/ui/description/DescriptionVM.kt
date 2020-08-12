package uz.fozilbekimomov.newspaper.ui.description

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.repo.description.DescriptionRepo
import uz.fozilbekimomov.newspaper.core.vm.BaseViewModel
import javax.inject.Inject

class DescriptionVM @Inject constructor(
    private val repo: DescriptionRepo,
    private val view: DescriptionContract.View
) : BaseViewModel(), DescriptionContract.ViewModel {

    override fun saveToBase(articleData: ArticleData, context: Context) {
        launch {
            val d = withContext(Dispatchers.IO) {
                repo.insertData(articleData)
            }
            if (d != -1L) {
                view.showMessage(context.getString(R.string.saved_to_favourites))
            }
        }
    }

    override fun isSaved(articleData: ArticleData) {
        launch {
            val d = withContext(Dispatchers.IO) {
                repo.checkData(articleData)
            }

            view.savedState(d)
        }
    }

    override fun deleteFromBase(articleData: ArticleData, context: Context) {
        launch {
            val d = withContext(Dispatchers.IO) {
                repo.deleteData(articleData)
            }
            if (d != -1) {
                view.showMessage(context.getString(R.string.removed_to_favourites))
            }
        }
    }

}