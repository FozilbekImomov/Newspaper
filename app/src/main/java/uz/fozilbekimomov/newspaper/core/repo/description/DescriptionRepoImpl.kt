package uz.fozilbekimomov.newspaper.core.repo.description

import uz.fozilbekimomov.newspaper.core.db.dao.ArticlesDao
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData
import javax.inject.Inject


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


class DescriptionRepoImpl @Inject constructor(
    private var dao: ArticlesDao
) : DescriptionRepo {
    override suspend fun insertData(articleData: ArticleData): Long {
        return dao.insert(articleData)
    }

    override suspend fun checkData(articleData: ArticleData): Boolean {
        return dao.checkData(articleData.mTitle, articleData.mUrl).isNotEmpty()
    }

    override suspend fun deleteData(articleData: ArticleData): Int {
        return dao.delete(articleData)
    }

}