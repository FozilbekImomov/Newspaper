package uz.fozilbekimomov.newspaper.core.models.topHeadlines


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class TopHeadlineResponseData(
    @SerializedName("status") val mStatus: String, // ok
    @SerializedName("totalResults") val mTotalResults: Int, // 30
    @SerializedName("articles") val mArticles: List<ArticleData>
) : Parcelable