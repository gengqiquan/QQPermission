package com.gengqiquan.permission

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gengqiquan.library.QQPermission


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        )
    }
}
