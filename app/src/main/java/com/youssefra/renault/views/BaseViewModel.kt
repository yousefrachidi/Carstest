package com.youssefra.renault.views

import androidx.lifecycle.ViewModel

import com.youssefra.renault.data.local.PermissionManager
import com.youssefra.renault.data.local.PreferenceObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var preferenceObject: PreferenceObject

    @Inject
    lateinit var permissionManager: PermissionManager

}