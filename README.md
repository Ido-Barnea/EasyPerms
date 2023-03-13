# EasyPerms :key:
A library designed to help developers easily request permissions.

# How can I add this to my project? :question:
> Step 1: Add the JitPack repository to your build file
  ```gradle
  allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
  ```
> Step 2: Add the dependency
  ```gradle
  dependencies {
	        implementation 'com.github.Ido-Barnea:EasyPerms:1.0.4'
	}
  ```
  That's it!

# Quick use :fast_forward:
```kotlin
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
```
```kotlin
override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPerms.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }
```
