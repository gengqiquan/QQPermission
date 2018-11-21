# QQPermission
android 6.0动态权限申请辅助库
拒绝申请弹出提示框，自动获取定制系统文本展示，引导用户到设置里授权

```
     QQPermission.with(this, Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ADD_VOICEMAIL,
                Manifest.permission.WRITE_CONTACTS).requestPermissions({
            //                        tv_text.text = "权限已允许"
        }
                , {
            //                        tv_text.text = "拒绝了权限"
        }
 ```

```
compile 'com.gengqiquan:QQPermission:0.0.11'
```
