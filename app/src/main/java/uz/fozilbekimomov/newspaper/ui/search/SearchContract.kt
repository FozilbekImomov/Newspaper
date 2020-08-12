package uz.fozilbekimomov.newspaper.ui.search


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface SearchContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun clearData()
        fun setError(message: String?)
    }

    interface ViewModel {
        fun searchData(search: String)
    }
}