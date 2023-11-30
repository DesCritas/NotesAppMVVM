package com.descritas.notesappmvvm.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.descritas.notesappmvvm.MainViewModel
import com.descritas.notesappmvvm.MainViewModelFactory
import com.descritas.notesappmvvm.navigation.NavRoute
import com.descritas.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.descritas.notesappmvvm.utils.Constants
import com.descritas.notesappmvvm.utils.Constants.Keys.CHOSE_BASE
import com.descritas.notesappmvvm.utils.Constants.Keys.ENTER_PASSWORD
import com.descritas.notesappmvvm.utils.Constants.Keys.FIREBASE_DATABASE
import com.descritas.notesappmvvm.utils.Constants.Keys.INPUT_EMAIL
import com.descritas.notesappmvvm.utils.Constants.Keys.LOG_IN
import com.descritas.notesappmvvm.utils.Constants.Keys.ROOM_DATABASE
import com.descritas.notesappmvvm.utils.LOGIN
import com.descritas.notesappmvvm.utils.PASSWORD
import com.descritas.notesappmvvm.utils.TYPE_FIREBASE
import com.descritas.notesappmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var login by remember {
        mutableStateOf(Constants.Keys.EMPTY)
    }
    var password by remember {
        mutableStateOf(Constants.Keys.EMPTY)
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {

                    Text(
                        text = LOG_IN,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = INPUT_EMAIL) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = ENTER_PASSWORD) },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {

                            LOGIN = login
                            PASSWORD = password
                            viewModel.initDatabase(TYPE_FIREBASE) {

                                Log.d("checkData", "Auth success")
                            }
                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = Constants.Keys.SIGN_IN)
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { paddigPages: PaddingValues ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = CHOSE_BASE)
                Button(
                    onClick = {
                        viewModel.initDatabase(TYPE_ROOM) {
                            navController.navigate(route = NavRoute.Main.route)
                        }

                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(paddigPages)
                )
                {
                    Text(text = ROOM_DATABASE)
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(paddigPages)
                )
                {
                    Text(text = FIREBASE_DATABASE)
                }
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun PrevStartScreen() {
    NotesAppMVVMTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        StartScreen(navController = rememberNavController(), viewModel = mViewModel)

    }
}
