package uz.fozilbekimomov.newspaper.ui.homePages.page3

import android.content.Context


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface SavedContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun setMessage(message: String?)
    }

    interface ViewModel {
        fun clearData(context: Context)
    }
}