package uz.fozilbekimomov.newspaper.core.di.moduls.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.fozilbekimomov.newspaper.core.repo.categoryGroup.CategoryGroupRepo
import uz.fozilbekimomov.newspaper.core.repo.categoryGroup.CategoryGroupRepoImpl
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.homePages.page2.CategoryGroupContract
import uz.fozilbekimomov.newspaper.ui.homePages.page2.CategoryGroupFragment
import uz.fozilbekimomov.newspaper.ui.homePages.page2.CategoryGroupVM
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
interface CategoryGroupVMM {

    @Binds
    fun bindCategoryFragment(home: CategoryGroupFragment): CategoryGroupContract.View

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(CategoryGroupVM::class)
    fun categoryFragmentVM(groupVm: CategoryGroupVM): ViewModel

    @Binds
    fun getCategoryRepository(repo: CategoryGroupRepoImpl): CategoryGroupRepo


}