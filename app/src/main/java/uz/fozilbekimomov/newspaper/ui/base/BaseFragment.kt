package uz.fozilbekimomov.newspaper.ui.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import dagger.android.support.DaggerFragment
import uz.fozilbekimomov.newspaper.core.utils.theme.Theme
import uz.fozilbekimomov.newspaper.core.utils.theme.ThemeManager
import javax.inject.Inject


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 7/29/20
 * @project ZarBazar
 */


abstract class BaseFragment(@LayoutRes private val contentLayoutId: Int) : DaggerFragment() {

    val TAG = "JJJJJ:${this::class.simpleName}"


    @Inject
    lateinit var themeManager: ThemeManager

    @LayoutRes
    var layoutId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentLayoutId, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreate()
        notifyThemeChanged()
    }

    abstract fun onViewCreate()


    protected fun notifyThemeChanged() = onCreateTheme(themeManager.currentTheme)

    open fun onCreateTheme(theme: Theme) {
        activity?.setTheme(theme.style)
        // status bar va navigation bar shu yerda control qilinadi.
        // Activity restart bo'lmasligi uchun flag ishlatish kerak bo'lmasa theme set bo'lmaydi
        // Bu funksiya kerakli fragmentda override qilib ishlatilaveradi. super.onCreateTheme(theme) bu ni o'chirilmasa bo'ldi
    }


}