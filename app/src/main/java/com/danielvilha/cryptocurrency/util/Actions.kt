package com.danielvilha.cryptocurrency.util

import androidx.navigation.NavHostController

/**
 * Created by Daniel Ferreira de Lima Vilha 30/11/2023.
 */
class Actions(
    navController: NavHostController
) {

    val selectedCoin: (String) -> Unit = {
        navController.navigate(it)
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}