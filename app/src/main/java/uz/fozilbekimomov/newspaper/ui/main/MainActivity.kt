package uz.fozilbekimomov.newspaper.ui.main

import android.app.UiModeManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.format.DateFormat
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.core.os.postDelayed
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.layout_main_menu.*
import kotlinx.android.synthetic.main.main_container.*
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.cache.MyCache
import uz.fozilbekimomov.newspaper.core.utils.hide
import uz.fozilbekimomov.newspaper.core.utils.setItemStatusBarColor
import uz.fozilbekimomov.newspaper.core.utils.show
import uz.fozilbekimomov.newspaper.ui.base.BaseActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    private var drawerLayout: DrawerLayout? = null
    lateinit var navController: NavController
    override fun onCreateActivity(savedInstanceState: Bundle?) {


        if (MyCache.getMyCache()!!.getNightMode()) {
            changeMode.setImageResource(R.drawable.ic_sun)
            setItemStatusBarColor(Color.BLACK, false)
        } else {
            changeMode.setImageResource(R.drawable.ic_moon)
            setItemStatusBarColor(Color.WHITE, true)
        }

        setupViews()
    }

    private fun setupViews() {

        drawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout?.setBackgroundColor(resources.getColor(R.color.white))
        drawerLayout?.drawerElevation = 10f
//        drawerLayout?.setScrimColor(resources.getColor(R.color.colorAccent))

        val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            private val scaleFactor = 6f
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX: Float = drawerView.width * slideOffset
                content.translationX = -slideX
                content.scaleX = 1 - slideOffset / scaleFactor
                content.scaleY = 1 - slideOffset / scaleFactor
            }
        }

        drawerLayout?.addDrawerListener(actionBarDrawerToggle)


        backButton.setOnClickListener {
            drawerLayout?.closeDrawer(GravityCompat.END, true)
        }

        menu.setOnClickListener {
            drawerLayout?.openDrawer(GravityCompat.END, true)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
//        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        //var appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
        var appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.homeFragment,
                    R.id.hiLightFragment,
                    R.id.searchFragment
                )
            )
//        NavigationUI.setupActionBarWithNavController(this,navHostFragment.navController, appBarConfiguration)


        val d = Date()
        val s = DateFormat.format("EEEE. dd-MM", d.time)
        currentDate.text = s.toString()

        search.setOnClickListener {
            navController.navigate(R.id.toSearchFragment)
        }


        val uiMModeManage = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager

        changeMode.setOnClickListener {

//            val isNight = MyCache.getMyCache()!!.getNightMode()


//            if (isNight) {
//                uiMModeManage.nightMode=UiModeManager.MODE_NIGHT_NO
//            }else{
//                uiMModeManage.nightMode=UiModeManager.MODE_NIGHT_YES
//            }
            notifyThemeChanged()
//            recreate()
//            MyCache.getMyCache()!!.setNightMode(!isNight)
//            ProcessPhoenix.triggerRebirth(this)

//            setLanguage()
            restart()
        }

    }


    fun parseDate(
        inputDateString: String?,
        inputDateFormat: SimpleDateFormat,
        outputDateFormat: SimpleDateFormat
    ): String? {
        var date: Date? = null
        var outputDateString: String? = null
        try {
            date = inputDateFormat.parse(inputDateString)
            outputDateString = outputDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDateString
    }


    fun showBottomNavigation() {
//        bottomNavView.show()
    }

    fun showTopActionBar() {
        headerHome.show()
    }

    fun hideBottomNavigation() {
//        bottomNavView.hide()
    }

    fun hideTopActionBar() {
        headerHome.hide()
    }

    private var backPressedOnce = false

    override fun onBackPressed() {
        if (navController.graph.startDestination == navController.currentDestination?.id) {
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }

            backPressedOnce = true
            Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(2000) {
                backPressedOnce = false
            }
        } else {
            super.onBackPressed()
        }
    }

    fun ChangeLanguage(view: View) {
        if (view is AppCompatButton) {
            MyCache.getMyCache()?.setCountry(view.tag.toString())
            setLanguage()
            restart()
        }
    }

}