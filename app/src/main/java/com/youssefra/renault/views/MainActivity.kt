package com.youssefra.renault.views

import android.content.ComponentCallbacks2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.youssefra.renault.data.local.PermissionManager
import com.youssefra.renault.views.car.CarViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity(), ComponentCallbacks2 {

    @Inject
    lateinit var permissionManager: PermissionManager


    companion object {
        var baseActivity: MainActivity? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        baseActivity = this

        setContent {

            val carViewModel: CarViewModel = viewModel()

            MainScreen(carViewModel)
        }
    }




}


