package uz.fozilbekimomov.newspaper.ui.hiLight


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface HiLightContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun setError(message: String?)
    }

    interface ViewModel {
        fun loadHomeData(saveData: Boolean)
        fun getLocalData()
    }
}