package uz.fozilbekimomov.newspaper.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.fozilbekimomov.newspaper.core.models.topHeadlines.TopHeadlineResponseData


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 8/8/20
 * @project Newspaper
 */


interface HomeService {
    @GET("top-headlines")
    suspend fun getTopHeadline(@Query("page") page: Int): Response<TopHeadlineResponseData>

    @GET("top-headlines")
    suspend fun getCategory(
        @Query("page") page: Int,
        @Query("category") category: String
    ): Response<TopHeadlineResponseData>

    @GET("top-headlines")
    suspend fun getLimitCategory(
        @Query("page") page: Int,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int
    ): Response<TopHeadlineResponseData>

    @GET("top-headlines")
    suspend fun getSearchData(
        @Query("page") page: Int,
        @Query("q") search: String
    ): Response<TopHeadlineResponseData>

}