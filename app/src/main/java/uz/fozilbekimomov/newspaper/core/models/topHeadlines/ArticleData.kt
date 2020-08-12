package uz.fozilbekimomov.newspaper.core.models.topHeadlines


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import uz.fozilbekimomov.newspaper.core.models.TypeConverter

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
@Entity(tableName = "article_table")
data class ArticleData(
    @TypeConverters(TypeConverter::class)
    @SerializedName("source") val mSource: SourceData,
    @SerializedName("author") val mAuthor: String?, // Українська правда
    @PrimaryKey
    @SerializedName("title") val mTitle: String, // Кілька українців у Бейруті отримали незначні травми в результаті вибуху - посол - Українська правда
    @SerializedName("description") val mDescription: String?, // В результаті потужного вибуху у Бейруті серйозно постраждалих громадян України немає, але декілька українців отримали травми.
    @SerializedName("url") val mUrl: String, // https://www.pravda.com.ua/news/2020/08/5/7261898/
    @SerializedName("urlToImage") val mUrlToImage: String?, // https://img.pravda.com/images/doc/d/e/de48c38-clipboard01.jpg
    @SerializedName("publishedAt") val mPublishedAt: String?, // 2020-08-05T13:14:31Z
    @SerializedName("content") val mContent: String? // , . , ""  -." ', , , . , , ( , - .), … . , ' , ", - . , , ., , , , . , . " . " " - ", - . ,    . ,   . ,     . 100   4 . ,   2750 , 6 .  Rhosus,   . ,   300 ,   $3 .
) : Parcelable