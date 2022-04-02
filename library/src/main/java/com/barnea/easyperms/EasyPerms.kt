package com.barnea.easyperms

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

object EasyPerms : Activity() {
    private val permissions = ArrayList<String>()

    private var permissionsCallback: EasyPermsCallback? = null
    private var permissionDenied = false

    /**
     * checks if user accepted the requested permissions, if not, requests them
     * @param context activity context
     */
    fun check(context: Context) {
        if (permissions.isNotEmpty()) {
            if (!hasPermissions(context)) {
                // request permissions
                requestPermissions(context)
            } else permissionsCallback?.onPermissionAccepted()
        }
    }

    /**
     * @param context activity context
     * @return true if the user has already accepted the request permissions, false otherwise
     */
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

    /**
     * @param context activity context
     * @return EasyPerms object
     */
    private fun requestPermissions(context: Context) {
        ActivityCompat.requestPermissions(context as Activity, permissions.toTypedArray(), 100)
        permissions.clear()
    }

    /**
     * @param permissionsCallback EasyPermsCallback
     * @return EasyPerms object
     */
    fun addCallback(permissionsCallback: EasyPermsCallback): EasyPerms {
        this.permissionsCallback = permissionsCallback
        return this
    }

    /**
     * @param permission a permission
     * @return EasyPerms object
     */
    fun addPermission(permission: String): EasyPerms {
        permissions.add(permission)
        return this
    }

    /**
     * @param permissions a list of permissions
     * @return EasyPerms object
     */
    fun addPermissions(permissions: ArrayList<String>): EasyPerms {
        this.permissions.addAll(permissions)
        return this
    }

    /**
     * @return checks if the permissions were granted, if not, invokes permission denied callback
     * otherwise, invokes permission accepted callback
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty()) {
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    val permissionText = permissions[grantResults.indexOf(result)].replace(
                        "android.permission.",
                        ""
                    ) // android.permission.CAMERA -> CAMERA

                    permissionsCallback?.onPermissionDenied("$permissionText permission denied")
                    permissionDenied = true
                    break
                }
            }
            if (!permissionDenied) permissionsCallback?.onPermissionAccepted()
            permissionDenied = false
        }
    }
}