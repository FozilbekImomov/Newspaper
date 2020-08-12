package uz.fozilbekimomov.newspaper.core.network.dataSource.home

import uz.fozilbekimomov.newspaper.core.network.dataSource.BaseDataSource
import uz.fozilbekimomov.newspaper.core.network.service.HomeService
import javax.inject.Inject


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


class TopHeadLineDataSource @Inject constructor(
    private val service: HomeService
) : BaseDataSource() {
    suspend fun getTopHeadLineData(page: Int) = getResult { service.getTopHeadline(page) }
}