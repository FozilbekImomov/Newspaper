package uz.fozilbekimomov.newspaper.core.di.db


import android.app.Application
import dagger.Module
import dagger.Provides
import uz.fozilbekimomov.newspaper.core.db.AppDatabase
import uz.fozilbekimomov.newspaper.core.db.dao.ArticlesDao
import javax.inject.Singleton

/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */

@Module
open class AppDatabaseModule {

//    @Provides
//    @Singleton
//    open fun getRoomDatabase(app: Application): AppDatabase = Room.inMemoryDatabaseBuilder(
//        app.applicationContext,
//        AppDatabase::class.java//, APP_DATABASE
//    ).build()


    @Singleton
    @Provides
    fun provideDatabase(appContext: Application) = AppDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    open fun getCategoryDao(db: AppDatabase): ArticlesDao = db.articleDao

}