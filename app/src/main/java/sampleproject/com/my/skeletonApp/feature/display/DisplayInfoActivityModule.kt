package sampleproject.com.my.skeletonApp.feature.display

import sampleproject.com.my.skeletonApp.AppPreference
import dagger.Module
import dagger.Provides
import sampleproject.com.my.skeletonApp.rest.model.usecase.AdherencesUseCase
import sampleproject.com.my.skeletonApp.rest.model.usecase.RemediesUseCase


@Module
class DisplayInfoActivityModule {
    @Provides
    fun provideViewModel(dataSettUseCase: AdherencesUseCase, remediesUseCase: RemediesUseCase) = DisplayInfoViewModel(dataSettUseCase, remediesUseCase)
}