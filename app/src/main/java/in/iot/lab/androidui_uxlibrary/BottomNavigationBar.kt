import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.iot.lab.androidui_uxlibrary.ui.theme.AndroidUI_UXLibraryTheme

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: BottomNavigationItem,
    onItemSelected: (BottomNavigationItem) -> Unit,
    selectedItemColor: Color,
    unselectedItemColor: Color,
) {
    NavigationBar(
//        backgroundColor = backgroundColor,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = item == selectedItem,
                onClick = { onItemSelected(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = selectedItemColor,
                    unselectedTextColor = unselectedItemColor,
                    selectedIconColor = selectedItemColor,
                    unselectedIconColor = unselectedItemColor,
                )

            )
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector, val label: String
)


// Preview
@Preview
@Composable
fun PreviewMyScreen() {
    AndroidUI_UXLibraryTheme {
        MyScreen()
    }
}