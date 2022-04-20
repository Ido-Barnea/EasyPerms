package com.barnea.easyperms

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayListOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION)

        EasyPerms.addPermissions(permissions)
            .addCallback(object: EasyPermsCallback {
                override fun onPermissionAccepted(permission: String) {
                    // Permission accepted, success!
                }

                override fun onPermissionDenied(permission: String) {
                    // Permission denied
                }

                override fun onPermissionDeniedPermanently(permission: String) {
                    // Permission denied permanently
                }
            })
            .check(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPerms.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }
}