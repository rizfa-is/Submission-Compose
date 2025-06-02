package com.issog.submissioncompose.core.utils

import androidx.navigation.NavController
import androidx.navigation.NavOptions

object NavigationUtils {
    fun NavController.safeNavigate(route: String) {
        try {
            val navOption = NavOptions.Builder()
                .setEnterAnim(android.R.anim.fade_in)
                .setExitAnim(android.R.anim.fade_out)
                .setPopEnterAnim(android.R.anim.fade_in)
                .setPopExitAnim(android.R.anim.fade_out)
                .build()
            navigate(route, navOption)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}