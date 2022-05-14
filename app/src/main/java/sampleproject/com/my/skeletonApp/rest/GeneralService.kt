package sampleproject.com.my.skeletonApp.rest

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import sampleproject.com.my.skeletonApp.rest.model.AdherencesResponse
import sampleproject.com.my.skeletonApp.rest.model.RemediesResponse

interface GeneralService {


    @GET("adherences")
    fun getAdherences(): Single<AdherencesResponse>

    @GET("remedies")
    fun getRemedies(): Single<RemediesResponse>
}