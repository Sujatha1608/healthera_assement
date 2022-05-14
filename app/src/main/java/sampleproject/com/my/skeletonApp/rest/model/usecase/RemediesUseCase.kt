package sampleproject.com.my.skeletonApp.rest.model.usecase

import io.reactivex.rxjava3.core.Single
import sampleproject.com.my.skeletonApp.core.util.SchedulerProvider
import sampleproject.com.my.skeletonApp.rest.UseCaseWithOutParam
import sampleproject.com.my.skeletonApp.rest.model.RemediesResponse
import sampleproject.com.my.skeletonApp.rest.repository.RemediesRepository

class RemediesUseCase(schedulerProvider: SchedulerProvider, private val getRemediesRepository: RemediesRepository)
    : UseCaseWithOutParam<RemediesResponse>(schedulerProvider) {
    override fun buildUseCaseObservable(): Single<RemediesResponse> {
        return getRemediesRepository.getRemedies()
    }

}