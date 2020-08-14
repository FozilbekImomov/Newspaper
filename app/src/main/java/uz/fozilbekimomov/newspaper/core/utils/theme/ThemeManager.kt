package uz.fozilbekimomov.newspaper.core.utils.theme

import uz.fozilbekimomov.newspaper.core.cache.MyCache
import javax.inject.Inject

class ThemeManager @Inject constructor() {

    var themes: ArrayList<Theme> = ArrayList()

    init {
        themes.add(ClassicTheme())
        // Theme classdan voris olgan klasslar shu yeda add qilinadi
    }

    // amaldagi temeni qaytaradi
    var currentTheme: Theme
        get() = findThemeById(MyCache.getMyCache()?.getTheme() ?: getDefaultTheme().id)
        set(value) {
            MyCache.getMyCache()?.saveTheme(value.id)
        }

    private fun findThemeById(id: Long): Theme {
        themes.forEach {
            if (it.id == id)
                return it
        }
        return getDefaultTheme()
    }


    fun getDefaultTheme(): Theme {
        return themes[0]
    }


    // Barcha temalar ro'yhati tema almashtirgadinga joyda chaqirish uchun
    fun getAllThemes() = themes
}