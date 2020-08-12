package uz.fozilbekimomov.newspaper.core.di.moduls.fragment.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.fozilbekimomov.newspaper.core.repo.category.CategoryRepo
import uz.fozilbekimomov.newspaper.core.repo.category.CategoryRepoImpl
import uz.fozilbekimomov.newspaper.core.vm.ViewModelProviderFactory
import uz.fozilbekimomov.newspaper.ui.allNews.CategoryContract
import uz.fozilbekimomov.newspaper.ui.allNews.CategoryFragment
import uz.fozilbekimomov.newspaper.ui.allNews.CategoryVM
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
interface CategoryVMM {

    @Binds
    fun bindCategoryFragment(home: CategoryFragment): CategoryContract.View

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(CategoryVM::class)
    fun categoryFragmentVM(groupVm: CategoryVM): ViewModel

    @Binds
    fun getCategoryRepository(repo: CategoryRepoImpl): CategoryRepo


}