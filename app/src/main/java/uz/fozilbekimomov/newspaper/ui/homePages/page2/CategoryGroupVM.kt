package uz.fozilbekimomov.newspaper.ui.homePages.page2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.models.category.CategoryData
import uz.fozilbekimomov.newspaper.core.models.preCategory.PreCategoryItem
import uz.fozilbekimomov.newspaper.core.repo.categoryGroup.CategoryGroupRepo
import uz.fozilbekimomov.newspaper.core.utils.Resource
import uz.fozilbekimomov.newspaper.core.vm.BaseViewModel
import javax.inject.Inject

class CategoryGroupVM @Inject constructor(
    private val categoryRepo: CategoryGroupRepo,
    private val view: CategoryGroupContract.View
) : BaseViewModel(), CategoryGroupContract.ViewModel {

    private var page = 1
//    private val _categoryDat = MutableLiveData<List<CategoryData>?>()
//    val categoryDat: LiveData<List<CategoryData>?>
//        get() = _categoryDat

    override fun loadCategoryData(context: Context) {
        val data = ArrayList<CategoryData>()
        data.add(CategoryData("business", context.getString(R.string.bussiness)))
        data.add(CategoryData("entertainment", context.getString(R.string.entertainment)))
        data.add(CategoryData("general", context.getString(R.string.general)))
        data.add(CategoryData("health", context.getString(R.string.health)))
        data.add(CategoryData("science", context.getString(R.string.science)))
        data.add(CategoryData("sports", context.getString(R.string.sport)))
        data.add(CategoryData("technology", context.getString(R.string.technology)))

        launch {
            for (i in data) {
                delay(500)
                loadPreCategory(i)
            }
        }

//        _categoryDat.postValue(data)
    }

    private val _preCategoryDat = MutableLiveData<PreCategoryItem?>()
    val preCategoryDat: LiveData<PreCategoryItem?>
        get() = _preCategoryDat

    override fun loadPreCategory(category: CategoryData) {

        view.showProgress()

        launch {
            val response = withContext(Dispatchers.IO) {
                categoryRepo.getLimitCategoryData(1, category.id)
            }
            when (response.status) {
                Resource.Status.SUCCESS -> {

                    val preCategoryItem = PreCategoryItem(
                        category,
                        response.data?.mArticles
                    )
                    _preCategoryDat.postValue(preCategoryItem)
                    view.hideProgress()
                }
                Resource.Status.ERROR -> {
                    view.setError(response.message)
                    view.hideProgress()
                }

                else -> view.setError("ViewModel->$response")
            }
        }
    }


}