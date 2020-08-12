package uz.fozilbekimomov.newspaper.core.di.moduls.fragment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.fozilbekimomov.newspaper.core.repo.search.SearchRepo
import uz.fozilbekimomov.newspaper.core.repo.search.SearchRepoImpl
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.search.SearchContract
import uz.fozilbekimomov.newspaper.ui.search.SearchFragment
import uz.fozilbekimomov.newspaper.ui.search.SearchVM
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
interface SearchVMM {

    @Binds
    fun bindHomeFragment(home: SearchFragment): SearchContract.View

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(SearchVM::class)
    fun homeFragmentVM(vm: SearchVM): ViewModel

    @Binds
    fun getHomeRepository(repo: SearchRepoImpl): SearchRepo


}