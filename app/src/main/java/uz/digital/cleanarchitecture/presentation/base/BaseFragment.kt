package uz.digital.cleanarchitecture.presentation.base

import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment(
    @LayoutRes layout: Int
) : Fragment(layout) {
    fun Fragment.snackBar(text: String) {
        Snackbar.make(this.requireView(), text, Snackbar.LENGTH_SHORT).show()
    }

    fun View.click(action: (View) -> Unit) {
        this.setOnClickListener {
            action(it)
        }
    }
}