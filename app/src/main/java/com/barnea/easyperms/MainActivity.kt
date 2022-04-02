package com.barnea.easyperms

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barnea.library.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayListOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION)

        EasyPerms.addPermissions(permissions)
            .addCallback(object: EasyPermsCallback {
                override fun onPermissionAccepted() {
                    // Permission accepted, success!
                }

                override fun onPermissionDenied(error: String) {
                    // Permission denied
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
        EasyPerms.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}