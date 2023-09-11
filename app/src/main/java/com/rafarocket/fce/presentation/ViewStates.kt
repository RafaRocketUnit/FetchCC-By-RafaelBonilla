package com.rafarocket.fce.presentation

/**
 * ViewStates of results from endpoint
 *
 * HiringListObtained: is the list ordered and filtered ready to show on screen.
 * Error: Shows any endpoint error thrown by exception.
 * Idle: Idle state.
 */
sealed class ViewStates {
    data class HiringListObtained(val result: Map<Int, List<HiringUi>>): ViewStates()
    data class Error(val error: String): ViewStates()
    object Idle: ViewStates()
}
