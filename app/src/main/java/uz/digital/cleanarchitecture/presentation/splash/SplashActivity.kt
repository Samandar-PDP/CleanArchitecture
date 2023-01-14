package uz.digital.cleanarchitecture.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.digital.cleanarchitecture.R
import uz.digital.cleanarchitecture.presentation.auth.AuthActivity
import uz.digital.cleanarchitecture.presentation.base.BaseActivity
import uz.digital.cleanarchitecture.presentation.main.MainActivity

class SplashActivity : BaseActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                if (viewModel.isUserNotNull()) {
                    intent(MainActivity())
                } else {
                    intent(AuthActivity())
                }
            }
        }.start()
    }
}