package uz.fozilbekimomov.newspaper.core.repo.category

import uz.fozilbekimomov.newspaper.core.models.topHeadlines.TopHeadlineResponseData
import uz.fozilbekimomov.newspaper.core.network.dataSource.home.CategoryDataSource
import uz.fozilbekimomov.newspaper.core.utils.Resource
import javax.inject.Inject


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


class CategoryRepoImpl @Inject constructor(
    private val remoteDataSource: CategoryDataSource
) : CategoryRepo {
    override suspend fun getCategoryData(
        page: Int,
        category: String
    ): Resource<TopHeadlineResponseData> {
        return remoteDataSource.getCategoryData(page, category)
    }
}