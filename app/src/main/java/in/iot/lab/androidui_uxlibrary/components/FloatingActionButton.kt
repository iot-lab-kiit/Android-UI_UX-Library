package `in`.iot.lab.androidui_uxlibrary.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * A customizable Floating Action Button with animations for click and scroll events,
 * on click the button scales up slightly, and when linked to a LazyListState it hides on scroll down
 * and shows on scroll up.
 * @param modifier Modifier to be applied to the FAB.
 * @param icon The icon to be displayed inside the FAB.
 * @param onClick The lambda to be executed when the FAB is clicked.
 * @param listState Optional LazyListState to link the FAB's visibility to scroll events.
 * @param fabSize The size of the FAB.
 * @param animationSpeedMillis The speed of the animations in milliseconds.
 */
@Composable
fun AnimatedFAB(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Add,
    onClick: () -> Unit,
    listState: LazyListState? = null,
    fabSize: Dp = 56.dp,
    animationSpeedMillis: Int = 300,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val clickScale = remember { Animatable(1f) }
    val visibilityScale = remember { Animatable(1f) }
    val coroutineScope = rememberCoroutineScope()

    // Click animation
    LaunchedEffect(isPressed) {
        coroutineScope.launch {
            clickScale.animateTo(
                targetValue = if (isPressed) 1.2f else 1f,
                animationSpec = tween(durationMillis = animationSpeedMillis / 2)
            )
        }
    }

    // Scroll animation
    if (listState != null) {
        val isScrollingUp = listState.isScrollingUp()
        val isVisible by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex == 0 || isScrollingUp
            }
        }

        LaunchedEffect(isVisible) {
            coroutineScope.launch {
                visibilityScale.animateTo(
                    targetValue = if (isVisible) 1f else 0f,
                    animationSpec = tween(durationMillis = animationSpeedMillis, easing = FastOutSlowInEasing)
                )
            }
        }
    }


    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .size(fabSize)
            .scale(clickScale.value * visibilityScale.value),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = if (visibilityScale.value > 0.5f) 6.dp else 0.dp
        ),
        interactionSource = interactionSource,
    ) {
        Icon(imageVector = icon, contentDescription = "Animated FAB")
    }
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            val isScrollingUp = if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset > firstVisibleItemScrollOffset
            }

            previousIndex = firstVisibleItemIndex
            previousScrollOffset = firstVisibleItemScrollOffset

            isScrollingUp
        }
    }.value
}


@Preview(showBackground = true)
@Composable
private fun AnimatedFABPreview() {
    val listState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(50) { index ->
                Text(text = "Item ${index + 1}", modifier = Modifier.padding(16.dp))
            }
        }
        AnimatedFAB(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {},
            listState = listState,
            fabSize = 64.dp,
            animationSpeedMillis = 500
        )
    }
}
