package uz.fozilbekimomov.newspaper.core.repo.description

import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface DescriptionRepo {
    suspend fun insertData(articleData: ArticleData): Long
    suspend fun checkData(articleData: ArticleData): Boolean
    suspend fun deleteData(articleData: ArticleData): Int
}