package uz.fozilbekimomov.newspaper.core.models.preCategory

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import uz.fozilbekimomov.newspaper.core.models.category.CategoryData
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData


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
data class PreCategoryItem(
    val categoryData: CategoryData,
    val data: List<ArticleData>?
) : Parcelable