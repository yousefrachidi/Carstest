package com.youssefra.renault.data.local

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import com.youssefra.renault.views.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionManager @Inject constructor(@ApplicationContext val context: Context) {
    private var permissionsLauncher: ActivityResultLauncher<Array<String>>? = null


    fun checkAllStoragePermission(): Array<String> {
        val permissionsToRequest = mutableListOf<String>()
        if (!writeStoragePermissionCheck()) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!readStoragePermissionCheck()) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        return permissionsToRequest.toTypedArray()
    }

    fun checkAllNeededPermission(): Array<String> {
        val permissionsToRequest = mutableListOf<String>()
        if (!notificationPermissionCheck()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        if (!writeStoragePermissionCheck()) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!readStoragePermissionCheck()) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        return permissionsToRequest.toTypedArray()
    }

    fun requestPermission(
        array: Array<String>,
        permissionsLauncher: ActivityResultLauncher<Array<String>>?=null
    ) {
        this.permissionsLauncher = permissionsLauncher
        if (array.isNotEmpty()) {
            this.permissionsLauncher?.launch(array)
        }
    }

    private fun notificationPermissionCheck(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun readStoragePermissionCheck(): Boolean {
        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

        return minSdk29 || ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun writeStoragePermissionCheck(): Boolean {
        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
        return minSdk29 || ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkSendSmsPermission(): Boolean {
        val isGaranted= ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
        if (!isGaranted){
            MainActivity.baseActivity?.let {
                requestPermissions(it,
                    arrayOf(Manifest.permission.SEND_SMS),
                    20)
            }
        }
        return isGaranted
    }




}