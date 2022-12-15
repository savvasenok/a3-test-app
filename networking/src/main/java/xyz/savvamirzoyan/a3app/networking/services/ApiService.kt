package xyz.savvamirzoyan.a3app.networking.services

import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import xyz.savvamirzoyan.a3app.networking.model.AuthToken

const val API_VERSION = "v1"

interface ApiService {

    @POST("/$API_VERSION/login")
    fun login(): Single<AuthToken>
}
