package xyz.savvamirzoyan.a3app.networking.services

import retrofit2.http.POST
import xyz.savvamirzoyan.a3app.networking.model.AuthToken

const val API_VERSION = "v1"

interface ApiService {

    @POST("/$API_VERSION/login")
    suspend fun login(): AuthToken
}
