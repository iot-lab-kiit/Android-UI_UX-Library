import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import `in`.iot.lab.androidui_uxlibrary.ui.theme.AndroidUI_UXLibraryTheme

@Composable
fun MyScreen() {
    val items = listOf(
        BottomNavigationItem(Icons.Default.Home, "Home"),
        BottomNavigationItem(Icons.Default.Settings, "Settings"),
        BottomNavigationItem(Icons.Default.Person, "Profile")
    )
    var selectedItem by remember { mutableStateOf(items[0]) }

    AndroidUI_UXLibraryTheme {
        BottomNavigationBar(
            items = items,
            selectedItem = selectedItem,
            onItemSelected = { selectedItem = it },
            selectedItemColor = MaterialTheme.colorScheme.primary,
            unselectedItemColor = MaterialTheme.colorScheme.secondary
        )
    }
}