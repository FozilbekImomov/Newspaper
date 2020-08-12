package uz.fozilbekimomov.newspaper.core.repo.saved

import androidx.lifecycle.LiveData
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface SavedRepo {
    fun getSavedData(): LiveData<List<ArticleData>>
    suspend fun deleteAllDData(): Int
}