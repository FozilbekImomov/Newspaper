package uz.fozilbekimomov.newspaper.core.di.moduls.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.home.HomeContract
import uz.fozilbekimomov.newspaper.ui.home.HomeFragment
import uz.fozilbekimomov.newspaper.ui.home.HomeVM
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
interface HomeVMM {

    @Binds
    fun bindHomeFragment(home: HomeFragment): HomeContract.View

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(HomeVM::class)
    fun homeFragmentVM(vm: HomeVM): ViewModel


}