package uz.fozilbekimomov.newspaper.ui.homePages.page3

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import uz.fozilbekimomov.newspaper.core.repo.saved.SavedRepo
import uz.fozilbekimomov.newspaper.core.vm.BaseViewModel
import javax.inject.Inject

class SavedVM @Inject constructor(
    private val repo: SavedRepo,
    private val view: SavedContract.View
) : BaseViewModel(), SavedContract.ViewModel {


    val getSavedData: LiveData<List<ArticleData>>
        get() = repo.getSavedData()

    override fun clearData(context: Context) {
        launch {
            val d = withContext(Dispatchers.IO) {
                repo.deleteAllDData()
            }
            if (d > 0) {
                view.setMessage(context.getString(R.string.deleted_all_date))
            }
        }
    }


}