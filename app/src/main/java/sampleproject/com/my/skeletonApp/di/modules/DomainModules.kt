package sampleproject.com.my.skeletonApp.di.modules

import dagger.Module
import dagger.Provides
import sampleproject.com.my.skeletonApp.core.util.SchedulerProvider
import sampleproject.com.my.skeletonApp.rest.repository.AdherencesRepository
import sampleproject.com.my.skeletonApp.rest.model.usecase.AdherencesUseCase
import sampleproject.com.my.skeletonApp.rest.model.usecase.RemediesUseCase
import sampleproject.com.my.skeletonApp.rest.repository.RemediesRepository


@Module
class DomainModules {

    @Provides
    fun providesAdhereUseCase(schedulers: SchedulerProvider, adherencesRepository: AdherencesRepository) =
        AdherencesUseCase(schedulers, adherencesRepository)

    @Provides
    fun providesRemediUseCase(schedulers: SchedulerProvider, remediesRepository: RemediesRepository) =
        RemediesUseCase(schedulers, remediesRepository)



}


