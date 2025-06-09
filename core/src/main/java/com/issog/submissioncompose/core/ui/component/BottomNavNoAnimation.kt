package com.issog.submissioncompose.core.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.issog.submissioncompose.core.ui.navigation.NavigationItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BottomNavNoAnimation(
    screens: List<NavigationItem>,
    onClick: (NavigationItem) -> Unit
) {
    var selectedScreen by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    Box(
        Modifier
            .border(
                width = 0.5.dp,
                color = Color.LightGray
            )
            .shadow(5.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (screen in screens) {
                val isSelected = screen == screens[selectedScreen]
                val animatedWeight by animateFloatAsState(
                    targetValue = if (isSelected) 1.5f else 1f
                )
                Box(
                    modifier = Modifier.weight(animatedWeight),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    val interactionSource = remember { MutableInteractionSource() }
                    BottomNavItem(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            selectedScreen = screens.indexOf(screen)
                            coroutineScope.launch {
                                delay(300)
                                onClick(screen)
                            }
                        },
                        screen = screen,
                        isSelected = isSelected
                    )
                }
            }
        }
    }
}
