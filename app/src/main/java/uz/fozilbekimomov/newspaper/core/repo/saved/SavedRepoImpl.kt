package uz.fozilbekimomov.newspaper.core.repo.saved

import androidx.lifecycle.LiveData
import uz.fozilbekimomov.newspaper.core.db.dao.ArticlesDao
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import javax.inject.Inject


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


class SavedRepoImpl @Inject constructor(
    private val dao: ArticlesDao
) : SavedRepo {
    override fun getSavedData(): LiveData<List<ArticleData>> {
        return dao.getAllSaved()
    }

    override suspend fun deleteAllDData(): Int {
        return dao.deleteAllArticles()
    }

}