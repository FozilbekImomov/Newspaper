package uz.fozilbekimomov.newspaper.ui.homePages.page1


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface TopHeadLineContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun setError(message: String?)
    }

    interface ViewModel {
        fun loadTopHeadLineData(saveData: Boolean)
        fun getLocalData()
    }
}