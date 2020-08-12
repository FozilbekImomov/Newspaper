package uz.fozilbekimomov.newspaper.ui.base

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import dagger.android.support.DaggerAppCompatActivity
import uz.fozilbekimomov.newspaper.core.cache.MyCache
import uz.fozilbekimomov.newspaper.ui.SplashActivity
import java.util.*


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 7/29/20
 * @project ZarBazar
 */


abstract class BaseActivity(
    @LayoutRes private val layoutRes: Int? = null
) : DaggerAppCompatActivity() {

    @LayoutRes
    var layoutId: Int = 0

    lateinit var manager: FragmentManager
    private var listener: ((String) -> Unit)? = null
    var listenerLastFragment: ((Int) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (MyCache.getMyCache()!!.getNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        layoutRes?.let { setContentView(it) }
        onCreateActivity(savedInstanceState)
    }

    abstract fun onCreateActivity(savedInstanceState: Bundle?)


    protected fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    protected fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    var listenerHasLogin: ((Boolean) -> Unit)? = null

    companion object {
        var dLocale: Locale = Locale(MyCache.getMyCache()!!.getCountry())
    }

    init {
        updateConfig(this)
    }

    fun restart() {
        val intent = Intent(this, SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

    }

    fun updateConfig(wrapper: ContextThemeWrapper) {
        if (dLocale == Locale("")) // Do nothing if dLocale is null
            return

        Locale.setDefault(dLocale)
        val configuration = Configuration()
        configuration.setLocale(dLocale)
        wrapper.applyOverrideConfiguration(configuration)
    }


    fun setLanguage() {
        dLocale = Locale(MyCache.getMyCache()!!.getCountry())
        val config =
            Configuration(resources.configuration)
        Locale.setDefault(dLocale)
        config.setLocale(dLocale)

        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }


}