package uz.fozilbekimomov.newspaper.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.fozilbekimomov.newspaper.core.db.dao.ArticlesDao
import uz.fozilbekimomov.newspaper.core.models.TypeConverter
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */

@Database(entities = [ArticleData::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val articleDao: ArticlesDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "news")
                .fallbackToDestructiveMigration()
                .build()
    }


}