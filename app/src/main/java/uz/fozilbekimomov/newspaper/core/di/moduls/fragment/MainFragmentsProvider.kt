package uz.fozilbekimomov.newspaper.core.di.moduls.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.category.CategoryVMM
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.description.DescriptionVMM
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.hiLight.HILightVMM
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.home.CategoryGroupVMM
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.home.HomeVMM
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.home.TopHeadLineVMM
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.saved.SavedVMM
import uz.fozilbekimomov.newspaper.core.di.moduls.fragment.search.SearchVMM
import uz.fozilbekimomov.newspaper.ui.allNews.CategoryFragment
import uz.fozilbekimomov.newspaper.ui.description.DescriptionFragment
import uz.fozilbekimomov.newspaper.ui.hiLight.HiLightFragment
import uz.fozilbekimomov.newspaper.ui.home.HomeFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page1.TopHeadLineFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page2.CategoryGroupFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page3.SavedFragment
import uz.fozilbekimomov.newspaper.ui.search.SearchFragment


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 7/29/20
 * @project ZarBazar
 */


@Module
interface MainFragmentsProvider {

    @ContributesAndroidInjector(modules = [HomeVMM::class])
    fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [TopHeadLineVMM::class])
    fun provideTopHeadlineFragment(): TopHeadLineFragment

    @ContributesAndroidInjector(modules = [CategoryGroupVMM::class])
    fun provideCategoryGroupFragment(): CategoryGroupFragment


    @ContributesAndroidInjector(modules = [HILightVMM::class])
    fun provideHiLightFragment(): HiLightFragment


    @ContributesAndroidInjector(modules = [SearchVMM::class])
    fun provideSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [CategoryVMM::class])
    fun provideCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector(modules = [DescriptionVMM::class])
    fun provideDescriptionFragment(): DescriptionFragment

    @ContributesAndroidInjector(modules = [SavedVMM::class])
    fun provideSavedFragment(): SavedFragment


}
