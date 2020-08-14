package uz.fozilbekimomov.newspaper.core.utils.theme

import uz.fozilbekimomov.newspaper.R

class ClassicTheme : Theme()
{
    override val id: Long
        get() = CLASSIC_THEME
    override val name: Int
        get() = R.string.classicTheme
    override val style: Int
        get() = R.style.AppTheme
    override val colorPrimary: Int
        get() = R.color.colorPrimary
    override val colorPrimaryDark: Int
        get() = R.color.colorPrimaryDark
    override val colorAccent: Int
        get() = R.color.colorAccent
    override val backgroundColor: Int
        get() = R.color.background
    override val backgroundLight: Int
        get() = R.color.background
    override val defTextColor: Int
        get() = R.color.primary_text_color
    override val secondaryTextColor: Int
        get() = R.color.textSecondaryColor
}