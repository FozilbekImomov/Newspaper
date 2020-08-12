package uz.fozilbekimomov.newspaper.core.models

import android.os.Parcelable
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.SourceData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/6/20
 * @project RNews
 */

@Parcelize
class TypeConverter : Parcelable {
    @TypeConverter
    fun fromSourceDataToString(sourceData: SourceData?): String? {
        if (sourceData == null) {
            return null
        }

        val json = Gson()
        val type = object : TypeToken<SourceData>() {}.type
        return json.toJson(sourceData, type)
    }

    @TypeConverter
    fun fromStringToDataSource(sourceData: String?): SourceData? {
        if (sourceData == null) {
            return null
        }

        val json = Gson()
        val type = object : TypeToken<SourceData>() {}.type

        return json.fromJson(sourceData, type)

    }

}