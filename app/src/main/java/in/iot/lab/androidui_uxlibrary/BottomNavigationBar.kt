import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.iot.lab.androidui_uxlibrary.ui.theme.AndroidUI_UXLibraryTheme

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: BottomNavigationItem,
    onItemSelected: (BottomNavigationItem) -> Unit,
    backgroundColor: Color = AndroidUI_UXLibraryTheme.colors.background,
    selectedItemColor: Color = AndroidUI_UXLibraryTheme.colors.primary,
    unselectedItemColor: Color = AndroidUI_UXLibraryTheme.colors.onBackground,
) {
    BottomNavigation(
        backgroundColor = backgroundColor,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = item == selectedItem,
                onClick = { onItemSelected(item) },
                selectedContentColor = selectedItemColor,
                unselectedContentColor = unselectedItemColor
            )
        }
    }
}

data class BottomNavigationItem(
    val icon: Int,
    val label: String
)

// Usage
@Composable
fun MyScreen() {
    val items = listOf(
        BottomNavigationItem(R.drawable.ic_home, "Home"),
        BottomNavigationItem(R.drawable.ic_settings, "Settings"),
        BottomNavigationItem(R.drawable.ic_profile, "Profile")
    )
    var selectedItem by remember { mutableStateOf(items[0]) }

    AndroidUI_UXLibraryTheme {
        BottomNavigationBar(
            items = items,
            selectedItem = selectedItem,
            onItemSelected = { selectedItem = it }
        )
    }
}

// Preview
@Preview
@Composable
fun PreviewMyScreen() {
    AndroidUI_UXLibraryTheme {
        MyScreen()
    }
}