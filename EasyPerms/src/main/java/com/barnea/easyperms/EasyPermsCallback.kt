package com.barnea.easyperms

interface EasyPermsCallback {

    fun onPermissionAccepted()
    fun onPermissionDenied(error: String)

}