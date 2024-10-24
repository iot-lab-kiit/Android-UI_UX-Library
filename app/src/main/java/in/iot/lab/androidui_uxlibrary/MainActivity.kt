package `in`.iot.lab.androidui_uxlibrary

import BottomNavBarScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import `in`.iot.lab.androidui_uxlibrary.ui.theme.AndroidUI_UXLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidUI_UXLibraryTheme {
                BottomNavBarScreen()
            }
        }
    }
}