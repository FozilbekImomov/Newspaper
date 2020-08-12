package uz.fozilbekimomov.newspaper.core.di.moduls.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.fozilbekimomov.newspaper.core.repo.home.TopHeadLineRepo
import uz.fozilbekimomov.newspaper.core.repo.home.TopHeadLineRepoImpl
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.homePages.page1.TopHeadLineContract
import uz.fozilbekimomov.newspaper.ui.homePages.page1.TopHeadLineFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page1.TopHeadLineVM
import uz.fozilbekimomov.zarbazar24.core.di.annotation.ViewModelKey


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


@Module
interface TopHeadLineVMM {

    @Binds
    fun bindTopHeadLineFragment(home: TopHeadLineFragment): TopHeadLineContract.View

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(TopHeadLineVM::class)
    fun topHeadlineFragmentVM(vm: TopHeadLineVM): ViewModel

    @Binds
    fun getTopHeadlineRepository(repo: TopHeadLineRepoImpl): TopHeadLineRepo


}