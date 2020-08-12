package uz.fozilbekimomov.newspaper.core.cache

import android.content.Context
import android.content.SharedPreferences
import uz.fozilbekimomov.newspaper.BuildConfig

class MyCache private constructor(context: Context) {

    init {
        preferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    companion object {

        private val KEY_COUNTRY = "country"
        private val NIGHT_MODE = "night_mode"

        private var preferences: SharedPreferences? = null
        private var myCache: MyCache? = null
        fun init(context: Context) {
            if (myCache == null) {
                myCache = MyCache(context)
            }
        }

        fun getMyCache(): MyCache? = myCache
    }

    fun setCountry(country: String) {
        preferences?.edit()?.putString(KEY_COUNTRY, country)?.apply()
    }

    fun getCountry(): String {
        return preferences?.getString(KEY_COUNTRY, "ru")!!
    }

    fun setNightMode(isNight: Boolean) {
        preferences?.edit()?.putBoolean(NIGHT_MODE, isNight)?.apply()
    }

    fun getNightMode(): Boolean {
        return preferences?.getBoolean(NIGHT_MODE, false)!!
    }


}