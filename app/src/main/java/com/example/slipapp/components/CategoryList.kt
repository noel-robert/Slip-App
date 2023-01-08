package com.example.slipapp.components

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CategoryList(
    categories: List<Category>
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut()
    ) {
        LazyColumn {
            itemsIndexed(categories) { index, item ->
                CategoryCard(
                    category = item,
                    modifier = Modifier
                        .padding(
                            end = 16.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                        .animateEnterExit(
                            enter = slideInHorizontally(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetX = { it * -(index + 1) }
                            )
                        )
                )
            }
        }
    }
}