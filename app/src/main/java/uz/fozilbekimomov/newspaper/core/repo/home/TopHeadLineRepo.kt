package uz.fozilbekimomov.newspaper.core.repo.home

import uz.fozilbekimomov.newspaper.core.models.topHeadlines.TopHeadlineResponseData
import uz.fozilbekimomov.newspaper.core.utils.Resource


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface TopHeadLineRepo {
    suspend fun getTopHeadLineData(page: Int): Resource<TopHeadlineResponseData>
}