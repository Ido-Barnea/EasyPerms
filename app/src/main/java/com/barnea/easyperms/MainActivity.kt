package com.barnea.easyperms

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barnea.library.EasyPerms

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayListOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION)

        EasyPerms.addPermissions(permissions)
            .addCallback(object: EasyPerms.EasyPermsCallback {
                override fun onSuccess() {
                    Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(e: String) {
                    Toast.makeText(this@MainActivity, e, Toast.LENGTH_SHORT).show()
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