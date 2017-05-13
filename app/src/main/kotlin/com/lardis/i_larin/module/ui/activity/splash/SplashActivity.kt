package com.lardis.i_larin.module.ui.activity.login

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.lardis.i_larin.module.ui.activity.navigation.NavigationActivity
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKSdk


class SplashActivity : MvpAppCompatActivity() {
    val EMAIL = "EMAIL"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (VKSdk.isLoggedIn()) {


            val intent = Intent(this, NavigationActivity::class.java)
            intent.putExtra(EMAIL, VKAccessToken.currentToken().userId)
            startActivity(intent)
        } else {


            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)


        }

    }

}
