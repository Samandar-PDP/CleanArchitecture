package uz.digital.cleanarchitecture.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.digital.cleanarchitecture.R
import uz.digital.cleanarchitecture.presentation.base.BaseActivity

class AuthActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportActionBar?.hide()
    }
}