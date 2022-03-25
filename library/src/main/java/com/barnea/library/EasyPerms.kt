package com.barnea.library

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object EasyPerms: Activity() {

    private val permissions = ArrayList<String>()

    private var onSuccessCallback: (() -> Unit)? = null
    private var onFailureCallback: ((errorMessage: String?) -> Unit)? = null
    private var permissionDenied = false

    fun check(context: Context) {
        if (permissions.isNotEmpty()) {
            if (!hasPermissions(context)){
                // request permissions
                requestPermissions(context)
            } else onSuccessCallback?.invoke()
        }
    }

    private fun hasPermissions(context: Context): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) return false
        }
        return true
    }

    private fun requestPermissions(context: Context){
        ActivityCompat.requestPermissions(context as Activity, permissions.toTypedArray(), 100)
        permissions.clear()
    }

    fun addOnSuccessCallback(callback: (() -> Unit)?): EasyPerms {
        onSuccessCallback = callback
        return this
    }

    fun addOnFailureCallback(callback: ((errorMessage: String?) -> Unit)?): EasyPerms {
        onFailureCallback = callback
        return this
    }

    fun addPermission(permission: String): EasyPerms {
        permissions.add(permission)
        return this
    }

    fun addPermissions(permissions: ArrayList<String>): EasyPerms {
        this.permissions.addAll(permissions)
        return this
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty()){
            for (result in grantResults){
                if (result != PackageManager.PERMISSION_GRANTED) {
                    onFailureCallback?.invoke("Permission ${permissions[grantResults.indexOf(result)]} Denied")
                    permissionDenied = true
                    break
                }
            }
            if (!permissionDenied) onSuccessCallback?.invoke()
            permissionDenied = false
        }
    }
}