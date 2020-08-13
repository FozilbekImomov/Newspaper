package uz.fozilbekimomov.newspaper.core.di.moduls.network

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uz.fozilbekimomov.newspaper.BuildConfig
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.core.cache.MyCache
import uz.fozilbekimomov.newspaper.core.network.BaseUrl
import uz.fozilbekimomov.newspaper.core.network.dataSource.home.CategoryDataSource
import uz.fozilbekimomov.newspaper.core.network.dataSource.home.LimitCategoryDataSource
import uz.fozilbekimomov.newspaper.core.network.dataSource.home.TopHeadLineDataSource
import uz.fozilbekimomov.newspaper.core.network.service.HomeService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        chuck: ChuckInterceptor,
        app: Application
    ): OkHttpClient {

        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var request: Request = chain.request()
                val url: HttpUrl =
                    request.url.newBuilder()
                        .addQueryParameter("apiKey", BuildConfig.MYKEY)
                        .addQueryParameter("country", MyCache.getMyCache()!!.getCountry()).build()
                request = request.newBuilder().url(url).build()
                return chain.proceed(request)
            }

        }

        if (BuildConfig.DEBUG && false) {
            return OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .connectTimeout(3L, TimeUnit.MINUTES)
                .readTimeout(3L, TimeUnit.MINUTES)
                .writeTimeout(3L, TimeUnit.MINUTES)
                .addInterceptor(chuck)
                .build()

        } else {
            return OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .connectTimeout(3L, TimeUnit.MINUTES)
                .readTimeout(3L, TimeUnit.MINUTES)
                .writeTimeout(3L, TimeUnit.MINUTES)
                .build()
        }
    }


//    @Provides
//    @Singleton
//    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
//            override fun log(message: String) {
//             Timber.i(message)
//            }
//        })
//    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(s: String) {
                    Timber.d(s)
                }
            })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(context: Application): ChuckInterceptor = ChuckInterceptor(context)


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient, baseUrl: BaseUrl): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl.baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)


    @Singleton
    @Provides
    fun provideHomeDataSource(movieService: HomeService) = TopHeadLineDataSource(movieService)


    @Singleton
    @Provides
    fun provideCategoryLimitDataSource(movieService: HomeService) =
        LimitCategoryDataSource(movieService)


    @Singleton
    @Provides
    fun provideCategoryDataSource(movieService: HomeService) = CategoryDataSource(movieService)


//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext appContext: Context) =
//        AppDatabase.getDatabase(appContext)
//
//    @Singleton
//    @Provides
//    fun provideMovieDao(db: AppDatabase) = db.movieDao()
//
//    @Singleton
//    @Provides
//    fun provideMovieDetailDao(db: AppDatabase) = db.movieDetailDao()
//
//    @Singleton
//    @Provides
//    fun providePopularMovieRepository(
//        remoteDataSource: PopularDataSource,
//        localDataSource: PopularDao
//    ) =
//        PopularMoviesRepo(remoteDataSource, localDataSource)
//
//
//    @Singleton
//    @Provides
//    fun provideDetailMovieRepository(
//        remoteDataSource: MovieDetailDataSource,
//        localDataSource: MovieDetailDao
//    ) =
//        MovieDetailRepo(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun getBaseUrl(app: Application): BaseUrl {
        val url: String = app.getString(R.string.base_url)
        return BaseUrl(url)
    }
}