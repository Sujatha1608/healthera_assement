package sampleproject.com.my.skeletonApp.rest.model.usecase

import io.reactivex.rxjava3.core.Single
import sampleproject.com.my.skeletonApp.core.util.SchedulerProvider
import sampleproject.com.my.skeletonApp.rest.UseCaseWithOutParam
import sampleproject.com.my.skeletonApp.rest.model.AdherencesResponse
import sampleproject.com.my.skeletonApp.rest.repository.AdherencesRepository

class AdherencesUseCase(schedulerProvider: SchedulerProvider, private val getAdhereRepository: AdherencesRepository)
    : UseCaseWithOutParam<AdherencesResponse>(schedulerProvider) {
    override fun buildUseCaseObservable(): Single<AdherencesResponse> {
        return getAdhereRepository.getAdherences()
    }

}