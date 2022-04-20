package com.barnea.easyperms

interface EasyPermsCallback {

    /**
     * This function is called when the user accepted the permission.
     */
    fun onPermissionAccepted(permission: String)

    /**
     * This function is called when the user denied the permission but did NOT
     * check "Never ask again", this is where you should explain to the user
     * why the permission is needed.
     */
    fun onPermissionDenied(permission: String)


    /**
     * This function is called when the user denied the permission and checked
     * "Never ask again". This is when you might want disable features in your app
     * that depend on this permission or open a dialog explaining to the user why
     * you need this permission and directing them to the app settings.
     */
    fun onPermissionDeniedPermanently(permission: String)

}