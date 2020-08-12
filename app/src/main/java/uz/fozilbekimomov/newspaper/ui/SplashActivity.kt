package uz.fozilbekimomov.newspaper.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.fozilbekimomov.newspaper.R
import uz.fozilbekimomov.newspaper.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
//        setupAnimation()
    }

    private fun setupAnimation() {
//        newsPaper.speed = 0.6F
//        newsPaper.repeatMode =
//            LottieDrawable.RESTART
    }
}