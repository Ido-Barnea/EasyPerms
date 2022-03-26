package com.barnea.library

interface EasyPermsCallback {

    fun onPermissionAccepted()
    fun onPermissionDenied(error: String)

}