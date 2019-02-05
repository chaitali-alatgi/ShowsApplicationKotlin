package network

import io.reactivex.Observable
import model.Show
import retrofit2.http.GET

interface ShowsApi {
    @GET("shows")
    fun getShows() : Observable<List<Show>>
}