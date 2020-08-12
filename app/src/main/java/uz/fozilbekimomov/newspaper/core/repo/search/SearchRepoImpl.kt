package uz.fozilbekimomov.newspaper.core.repo.search

import uz.fozilbekimomov.newspaper.core.models.topHeadlines.TopHeadlineResponseData
import uz.fozilbekimomov.newspaper.core.network.dataSource.home.SearchDataSource
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


class SearchRepoImpl @Inject constructor(
    private val remoteDataSource: SearchDataSource
) : SearchRepo {

    override suspend fun getSearchData(
        page: Int,
        search: String
    ): Resource<TopHeadlineResponseData> {
        return remoteDataSource.getSearchData(page, search)
    }
}