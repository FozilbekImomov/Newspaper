package uz.fozilbekimomov.newspaper.core.di.moduls.fragment.hiLight

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.hiLight.HiLightContract
import uz.fozilbekimomov.newspaper.ui.hiLight.HiLightFragment


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


@Module
interface HILightVMM {

    @Binds
    fun bindHomeFragment(home: HiLightFragment): HiLightContract.View

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory


//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeVM::class)
//    fun homeFragmentVM(vm: HomeVM): ViewModel
//
//    @Binds
//    fun getHomeRepository(repo: HomeRepoImpl): HomeRepo


}