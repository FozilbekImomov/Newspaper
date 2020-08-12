package uz.fozilbekimomov.newspaper.core.di.moduls

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.fozilbekimomov.newspaper.core.di.moduls.activity.main.MainActivityModule
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.MainFragmentsProvider
import uz.fozilbekimomov.newspaper.ui.main.MainActivity


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 7/29/20
 * @project ZarBazar
 */


@Module
interface ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainFragmentsProvider::class])
    fun contributeMainActivity(): MainActivity

}