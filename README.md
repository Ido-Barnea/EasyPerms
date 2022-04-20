# :key: EasyPerms
A simple library to help developers ask for permissions more easily

# :question: How can I add this to my project?
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

# :fast_forward: Quick use
```kotlin
val permissions = arrayListOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION)

EasyPerms.addPermissions(permissions)
	.addCallback(object: EasyPermsCallback {
		override fun onSuccess() {
			// Permission accepted, success!	
		}

		override fun onFailure(error: String) {
			// Permission denied
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
        EasyPerms.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
```

# :briefcase: License
```
Copyright 2022 Ido Barnea

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
