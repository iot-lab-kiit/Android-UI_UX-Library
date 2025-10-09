package `in`.iot.lab.androidui_uxlibrary.animations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * This is the Composable function for the Circular Loading Bar
 *
 * @param modifier Modifier to be applied to the layout
 */
@Composable
fun CircularLoadingBar(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    strokeWidth: Dp = 4.dp,
    trackColor: Color = Color.LightGray,
    strokeCap: StrokeCap = StrokeCap.Round
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = strokeWidth,
        trackColor = trackColor,
        strokeCap = strokeCap
    )
}

/**
 * This is the Composable function for the Linear Loading Bar
 *
 * @param modifier Modifier to be applied to the layout
 */
@Composable
fun LinearLoadingBar(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    trackColor: Color = Color.LightGray,
    strokeCap: StrokeCap = StrokeCap.Round
    
) {
    LinearProgressIndicator(
        color = color,
        trackColor = trackColor,
        strokeCap = strokeCap
    )
}

@Preview(showBackground = true)
@Composable
fun CircularLoadingBarPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularLoadingBar(
            modifier = Modifier.padding(bottom = 32.dp),
        )
        CircularLoadingBar(
            modifier = Modifier.padding(bottom = 32.dp),
            color = Color.Red,
            strokeWidth = 8.dp,
            trackColor = Color.Gray,
            strokeCap = StrokeCap.Butt
        )
        CircularLoadingBar(
            modifier = Modifier.padding(bottom = 32.dp),
            color = Color.Green,
            strokeWidth = 12.dp,
            trackColor = Color.DarkGray,
            strokeCap = StrokeCap.Square
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LinearLoadingBarPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearLoadingBar(
        )
        Spacer(Modifier.padding(bottom = 32.dp))
        LinearLoadingBar(

            color = Color.Red,
            trackColor = Color.Gray,
            strokeCap = StrokeCap.Square
        )
        Spacer(Modifier.padding(bottom = 32.dp))

        LinearLoadingBar(
            color = Color.Green,
            trackColor = Color.DarkGray,
            strokeCap = StrokeCap.Butt
        )
    }
}
