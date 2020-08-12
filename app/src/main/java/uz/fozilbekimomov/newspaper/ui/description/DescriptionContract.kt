package uz.fozilbekimomov.newspaper.ui.description

import android.content.Context
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface DescriptionContract {
    interface View {
        fun showMessage(message: String)
        fun savedState(d: Boolean)
    }

    interface ViewModel {
        fun saveToBase(articleData: ArticleData, context: Context)
        fun isSaved(articleData: ArticleData)
        fun deleteFromBase(articleData: ArticleData, context: Context)
    }
}