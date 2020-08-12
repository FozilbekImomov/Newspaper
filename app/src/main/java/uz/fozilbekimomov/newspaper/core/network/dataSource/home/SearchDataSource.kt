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


class SearchDataSource @Inject constructor(
    private val service: HomeService
) : BaseDataSource() {
    suspend fun getSearchData(page: Int, search: String) =
        getResult { service.getSearchData(page, search) }
}