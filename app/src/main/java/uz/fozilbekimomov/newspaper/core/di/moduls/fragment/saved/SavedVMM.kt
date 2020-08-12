package uz.fozilbekimomov.newspaper.core.di.moduls.fragment.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.fozilbekimomov.newspaper.core.repo.saved.SavedRepo
import uz.fozilbekimomov.newspaper.core.repo.saved.SavedRepoImpl
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.homePages.page3.SavedContract
import uz.fozilbekimomov.newspaper.ui.homePages.page3.SavedFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page3.SavedVM
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
interface SavedVMM {

    @Binds
    fun bindSavedFragment(home: SavedFragment): SavedContract.View

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(SavedVM::class)
    fun savedFragmentVM(vm: SavedVM): ViewModel

    @Binds
    fun getSavedRepository(repo: SavedRepoImpl): SavedRepo


}