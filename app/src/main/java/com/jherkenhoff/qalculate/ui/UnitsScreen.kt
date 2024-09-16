package com.jherkenhoff.qalculate.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.automirrored.outlined.ArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UnitsScreen(
    viewModel: UnitsViewModel = viewModel(),
    openDrawer: () -> Unit = {}
) {

    UnitsScreenContent(
        openDrawer = openDrawer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitsScreenContent(
    openDrawer: () -> Unit = {  }
) {

    val scrollState = rememberScrollState()

    var searchOpen by remember{ mutableStateOf(false) }

    AnimatedContent(
        targetState = searchOpen,
        transitionSpec = {
            fadeIn(animationSpec = tween(150, 150)) togetherWith
                    fadeOut(animationSpec = tween(150))
        }, label = "size transform"
    ) { targetExpanded ->
        if (targetExpanded) {
            SearchBar(
                query = "search units",
                active = true,
                leadingIcon = {
                    IconButton(onClick = { searchOpen = false }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back icon")
                    }
                },
                trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search icon") },
                onActiveChange = { searchOpen = it },
                onQueryChange = {},
                onSearch = {}
            ) {

            }
        } else {
            Scaffold(
                containerColor = MaterialTheme.colorScheme.surface,
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Units")
                        },
                        navigationIcon = {
                            IconButton(onClick = openDrawer) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }

                        },
                        actions = {
                            IconButton(onClick = { searchOpen = true }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search icon"
                                )
                            }
                            IconButton(onClick = { searchOpen = true }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.Sort,
                                    contentDescription = "Sort icon"
                                )
                            }
                        }

                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Add unit icon")
                    }
                },
                modifier = Modifier.imePadding(),
            ) { innerPadding ->

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                ) {

                    Row(
                        modifier = Modifier
                            .horizontalScroll(scrollState)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)

                    ) {
                        Box(modifier = Modifier.wrapContentSize(Alignment.BottomStart)) {

                            var expanded by remember { mutableStateOf(false) }

                            FilterChip(
                                selected = true,
                                onClick = { expanded = true },
                                label = { Text("Light") },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowDropDown,
                                        contentDescription = "Unit system icon"
                                    )
                                }
                            )
                            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                                DropdownMenuItem(
                                    text = { Text("Up") },
                                    onClick = { /* Handle edit! */ },
                                    leadingIcon = { Icon(Icons.AutoMirrored.Outlined.ArrowLeft, contentDescription = null) }
                                )
                                HorizontalDivider()
                                DropdownMenuItem(
                                    text = { Text("Settings") },
                                    onClick = { /* Handle settings! */ },
                                )
                                DropdownMenuItem(
                                    text = { Text("Radiance") },
                                    onClick = { /* Handle send feedback! */ },
                                )
                            }
                        }
                        FilterChip(
                            selected = false,
                            onClick = { /*TODO*/ },
                            label = { Text("System") },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "Unit system icon"
                                )
                            }
                        )
                        FilterChip(
                            selected = false,
                            onClick = { /*TODO*/ },
                            label = { Text("Favorite") }
                        )
                        FilterChip(
                            selected = false,
                            onClick = { /*TODO*/ },
                            label = { Text("Custom") }
                        )
                    }

                    ListItem(
                        headlineContent = { Text(text = "Ampere") },
                        supportingContent = { Text(text = "A, ampere, amperes, amp") },
                    )
                    ListItem(
                        headlineContent = { Text(text = "Ampere per Meter") },
                        supportingContent = { Text(text = "= 1 A ∕ m") },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    UnitsScreenContent(
    )
}
