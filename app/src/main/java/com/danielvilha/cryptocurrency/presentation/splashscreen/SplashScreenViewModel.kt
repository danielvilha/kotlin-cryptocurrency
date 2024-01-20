package com.danielvilha.cryptocurrency.presentation.splashscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Daniel Ferreira de Lima Vilha 26/12/2023.
 */
class SplashScreenViewModel : ViewModel() {

    private val _valid : MutableStateFlow<Boolean?> = MutableStateFlow(false)
    val valid : StateFlow<Boolean?> = _valid

    fun trackSplashScreenStarted() {
        //dummy method
    }
}