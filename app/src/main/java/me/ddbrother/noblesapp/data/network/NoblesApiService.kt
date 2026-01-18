package me.ddbrother.noblesapp.data.network

import me.ddbrother.noblesapp.data.model.UserProfile
import retrofit2.http.GET
import retrofit2.http.Query

interface NoblesApiService {
    @GET("iosNoblesAppWeb/jsonaboutme.php")
    suspend fun getUserProfile(@Query("iosPIN") pin: String): UserProfile
}
