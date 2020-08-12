package uz.fozilbekimomov.newspaper.core.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import uz.fozilbekimomov.newspaper.R


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


inline fun <reified V : View> V?.hide() {
    this?.visibility = View.GONE
}

inline fun <reified V : View> V?.show() {
    this?.visibility = View.VISIBLE
}


inline fun <reified I : ImageView> I.load(url: String/*, @DrawableRes placeholder: Int=R.drawable.place_holder*/) {
    Glide.with(this).load(url).placeholder(R.drawable.place_holder)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true).into(this)
}

fun Context.color(@ColorRes colorId: Int): Int {
    return if (Build.VERSION.SDK_INT > 23) {
        this.resources.getColor(colorId, this.theme)
    } else {
        ContextCompat.getColor(this, colorId)

    }
}


inline fun <reified V : View> V.scaleView(
    startScale: Float,
    endScale: Float
) {
    val animation: Animation = ScaleAnimation(
        startScale, endScale,
        startScale, endScale,
        Animation.RELATIVE_TO_SELF, 0.6f,
        Animation.RELATIVE_TO_SELF, 0.6f
    )
    animation.fillAfter = true
    animation.duration = 500
    this.startAnimation(animation)
}

inline fun <reified V : View> V.alphaView(
    fromAlpha: Float,
    toAlpha: Float
) {
    val animation: Animation = AlphaAnimation(fromAlpha, toAlpha)
    animation.fillAfter = true
    animation.duration = 500
    this.startAnimation(animation)
}

