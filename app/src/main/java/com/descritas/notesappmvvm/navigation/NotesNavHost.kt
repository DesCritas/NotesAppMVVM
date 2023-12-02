package com.descritas.notesappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.descritas.notesappmvvm.MainViewModel
import com.descritas.notesappmvvm.screens.AddScreen
import com.descritas.notesappmvvm.screens.MainScreen
import com.descritas.notesappmvvm.screens.NoteScreen
import com.descritas.notesappmvvm.screens.StartScreen
import com.descritas.notesappmvvm.utils.Constants.Keys.ID
import com.descritas.notesappmvvm.utils.Constants.Screens.ADD_SCREEN
import com.descritas.notesappmvvm.utils.Constants.Screens.MAIN_SCREEN
import com.descritas.notesappmvvm.utils.Constants.Screens.NOTE_SCREEN
import com.descritas.notesappmvvm.utils.Constants.Screens.START_SCREEN

sealed class NavRoute(val route: String) {
    object Start : NavRoute(START_SCREEN)
    object Main : NavRoute(MAIN_SCREEN)
    object Add : NavRoute(ADD_SCREEN)
    object Note : NavRoute(NOTE_SCREEN)
}


@Composable
fun NotesNavHost(mViewModel: MainViewModel, navController: NavHostController) {


    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Note.route + "/{${ID}}") { backStackEntry ->
            NoteScreen(
                navController = navController,
                viewModel = mViewModel,
                noteId = backStackEntry.arguments?.getString(ID)) }
    }
}
