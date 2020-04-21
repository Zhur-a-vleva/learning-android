package data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("artists/{id}")

    fun getArtistData(
        @Path("id") artistId: Int,
        @Query("access_token") token: String
    ): Single<Data>

    @GET("artists/{id}/songs?per_page=50")

    fun getSongData(
        @Path("id") artistId: Int,
        @Query("access_token") token: String
    ): Single<Data>
}