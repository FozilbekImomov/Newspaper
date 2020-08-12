package uz.fozilbekimomov.newspaper.core.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import uz.fozilbekimomov.newspaper.core.app.App
import uz.fozilbekimomov.newspaper.core.di.db.AppDatabaseModule
import uz.fozilbekimomov.newspaper.core.di.moduls.ActivityBuilderModule
import uz.fozilbekimomov.newspaper.core.di.moduls.network.NetworkModule
import javax.inject.Singleton

/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 7/29/20
 * @project ZarBazar
 */


@Singleton
@Component(
    modules = [
        ActivityBuilderModule::class,
        NetworkModule::class,
        AppDatabaseModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}