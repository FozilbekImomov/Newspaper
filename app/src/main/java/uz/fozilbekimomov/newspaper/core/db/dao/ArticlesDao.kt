package uz.fozilbekimomov.newspaper.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.ArticleData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): List<ArticleData>

    @Query("SELECT * FROM article_table")
    fun getAllSaved(): LiveData<List<ArticleData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ArticleData::class)
    suspend fun insertArticles(data: List<ArticleData>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: ArticleData): Long

    @Delete(entity = ArticleData::class)
    suspend fun delete(data: ArticleData): Int

    @Query("SELECT * FROM article_table WHERE mTitle = :title and  mUrl = :url")
    fun checkData(title: String, url: String): List<ArticleData>

    @Query("DELETE FROM article_table")
    suspend fun deleteAllArticles(): Int

}