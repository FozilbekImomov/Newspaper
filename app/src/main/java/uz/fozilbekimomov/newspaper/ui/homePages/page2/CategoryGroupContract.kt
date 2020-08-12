package uz.fozilbekimomov.newspaper.ui.homePages.page2

import android.content.Context
import uz.fozilbekimomov.newspaper.core.models.category.CategoryData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface CategoryGroupContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun setError(message: String?)
    }

    interface ViewModel {
        fun loadCategoryData(context: Context)
        fun loadPreCategory(category: CategoryData)
    }
}