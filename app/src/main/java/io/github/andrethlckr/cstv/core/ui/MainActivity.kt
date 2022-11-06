package io.github.andrethlckr.cstv.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.github.andrethlckr.cstv.core.ui.theme.CSTVTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CSTVTheme {
                CSTVNavHost()
            }
        }
    }
}
