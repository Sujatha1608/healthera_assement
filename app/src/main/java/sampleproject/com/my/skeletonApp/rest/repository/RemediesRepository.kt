package sampleproject.com.my.skeletonApp.rest.repository

import io.reactivex.rxjava3.core.Single
import sampleproject.com.my.skeletonApp.rest.GeneralService
import sampleproject.com.my.skeletonApp.rest.model.RemediesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemediesRepository @Inject constructor(private val apiServices: GeneralService){
    fun getRemedies(): Single<RemediesResponse>
    = apiServices.getRemedies()
}