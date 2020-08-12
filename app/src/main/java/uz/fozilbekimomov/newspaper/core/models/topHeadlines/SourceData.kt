package uz.fozilbekimomov.newspaper.core.models.topHeadlines


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class SourceData(
    @SerializedName("id") val mId: String?, // null
    @SerializedName("name") val mName: String // Pravda.com.ua
) : Parcelable