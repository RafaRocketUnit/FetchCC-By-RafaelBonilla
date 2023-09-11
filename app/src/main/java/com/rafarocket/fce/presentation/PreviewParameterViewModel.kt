package com.rafarocket.fce.presentation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rafarocket.fce.data.HiringRepositoryRetrofitImpl
import com.rafarocket.fce.data.HiringServiceApi
import com.rafarocket.fce.domain.GetHiringListUseCase
import javax.inject.Inject

/**
 * Preview Parameter to display the viewModel input in the composable.
 */
class PreviewParameterViewModel @Inject constructor(api: HiringServiceApi): PreviewParameterProvider<HiringViewModel> {
    private val repository = HiringRepositoryRetrofitImpl(api)
    private val useCase = GetHiringListUseCase(repository)
    private val viewModel = HiringViewModel(useCase)
    override val values: Sequence<HiringViewModel> = sequenceOf(viewModel)
}