package uz.fozilbekimomov.newspaper.core.di.moduls.fragment.description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.fozilbekimomov.newspaper.core.repo.description.DescriptionRepo
import uz.fozilbekimomov.newspaper.core.repo.description.DescriptionRepoImpl
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.description.DescriptionContract
import uz.fozilbekimomov.newspaper.ui.description.DescriptionFragment
import uz.fozilbekimomov.newspaper.ui.description.DescriptionVM
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
interface DescriptionVMM {

    @Binds
    fun bindDescriptionFragment(descriptionFragment: DescriptionFragment): DescriptionContract.View

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(DescriptionVM::class)
    fun categoryFragmentVM(groupVm: DescriptionVM): ViewModel

    @Binds
    fun getCategoryRepository(repo: DescriptionRepoImpl): DescriptionRepo


}