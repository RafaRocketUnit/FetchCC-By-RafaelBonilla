package com.rafarocket.fce.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafarocket.fce.domain.GetHiringListUseCase
import com.rafarocket.fce.toHiringUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel that gets the data to render on UI layer.
 */
@HiltViewModel
class HiringViewModel @Inject constructor(private val useCase: GetHiringListUseCase): ViewModel() {

    private val _hiring = MutableStateFlow<ViewStates>(ViewStates.Idle)
    val hiring: Flow<ViewStates> = _hiring

    fun fetchHiringList() {
        viewModelScope.launch {
            val result = useCase.invoke()
            _hiring.value = result.fold(
                ifRight = { orderedResult ->
                    ViewStates.HiringListObtained(
                        orderedResult.map { entry ->
                            Pair(
                                entry.key,
                                entry.value.map {
                                    it.toHiringUi()
                                }
                            )
                        }.toMap()
                    )
                },
                ifLeft = {
                    ViewStates.Error(it.error)
                }
            )
        }
    }
}