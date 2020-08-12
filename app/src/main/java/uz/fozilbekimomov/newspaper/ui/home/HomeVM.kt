package uz.fozilbekimomov.newspaper.ui.home

import uz.fozilbekimomov.newspaper.core.vm.BaseViewModel
import javax.inject.Inject


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


class HomeVM @Inject constructor(
    private val view: HomeContract.View
) :
    BaseViewModel(), HomeContract.ViewModel