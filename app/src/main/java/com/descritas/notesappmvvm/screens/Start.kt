package com.descritas.notesappmvvm.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.descritas.notesappmvvm.navigation.NavRoute
import com.descritas.notesappmvvm.ui.theme.NotesAppMVVMTheme

@Composable
fun StartScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddigPages: PaddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What base will we use")
            Button(
                onClick = {
                          navController.navigate(route = NavRoute.Main.route)
                          },
                modifier = Modifier
                    .width(200.dp)
                    .padding(paddigPages)
            )
            {
                Text(text = "Room database")
            }
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Main.route)
                          },
                modifier = Modifier
                    .width(200.dp)
                    .padding(paddigPages)
            )
            {
                Text(text = "Firebase database")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PrevStartScreen() {
    NotesAppMVVMTheme() {
        StartScreen(navController = rememberNavController())

    }
}
