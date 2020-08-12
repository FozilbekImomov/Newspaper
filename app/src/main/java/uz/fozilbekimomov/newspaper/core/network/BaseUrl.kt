package uz.fozilbekimomov.newspaper.core.network

/**
 * @author fozilbekimomov @mail: fozilbekimomov@gmail.com 5/17/20
 */

data class BaseUrl(val baseUrl: String)

fun BaseUrl.socketUrl(): String {
    return baseUrl.plus("")
}