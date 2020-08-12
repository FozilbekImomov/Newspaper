package uz.fozilbekimomov.newspaper.core.repo.home

import uz.fozilbekimomov.newspaper.core.models.topHeadlines.TopHeadlineResponseData
import uz.fozilbekimomov.newspaper.core.network.dataSource.home.TopHeadLineDataSource
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


class TopHeadLineRepoImpl @Inject constructor(
    private val remoteDataSource: TopHeadLineDataSource
) : TopHeadLineRepo {
    override suspend fun getTopHeadLineData(page: Int): Resource<TopHeadlineResponseData> {
        return remoteDataSource.getTopHeadLineData(page)
    }
}