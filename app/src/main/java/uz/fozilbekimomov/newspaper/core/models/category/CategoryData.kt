package uz.fozilbekimomov.newspaper.core.models.category

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/10/20
 * @project Newspaper
 */

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class CategoryData(val id: String, var name: String) : Parcelable