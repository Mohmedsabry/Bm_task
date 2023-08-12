package com.example.bmapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.bmapp.Mydatabase
import com.example.bmapp.dao.ProfileDao
import com.example.bmapp.roomDatabases.objects.ProfileTask

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task8() {
    val context = LocalContext.current
    val database =
        Room.databaseBuilder(context, Mydatabase::class.java, "mydatabase").allowMainThreadQueries()
            .build()
    val dao = database.ProfileDao()
    val navController = rememberNavController()
    var list by remember {
        mutableStateOf(emptyList<ProfileTask>())
    }
    list = dao.getAllProfile()

    NavHost(navController = navController, startDestination = Screens.listScreen) {
        composable(Screens.listScreen) { navBackStackEntry ->
            list = dao.getAllProfile()
            println("${list.size} back")
            ListScreen(list, navController)

        }
        composable(Screens.insertScreen) {
            insertScreen(navHostController = navController, dao = dao)
        }
        composable("DetailsScreen/{id}", listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )) {
            val id = it.arguments?.getInt("id") ?: -1
            println("id is $id")
            val profileTask = dao.getItemByID(id)
            println("$dao $profileTask")
            DetailsScreen(
                navHostController = navController,
                id = id ,
                dao = dao
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ListScreen(list: List<ProfileTask>, navHostController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            IconButton(onClick = {
                navHostController.navigate(Screens.insertScreen)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add",Modifier.background(
                    Color.DarkGray))
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        LazyColumn(Modifier.padding(it)) {
            if (list.isEmpty()) {
                item {
                    Text(text = "empty List")
                }
            } else {
                items(list.size) {
                    Text(text = "${list[it].name} ${list[it].age}", Modifier.clickable {
                        navHostController.navigate("DetailsScreen/${list[it].id}")
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun insertScreen(navHostController: NavHostController, dao: ProfileDao) {
    var name by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = name, onValueChange = {
            name = it
        })
        Spacer(modifier = Modifier
            .height(10.dp)
            .background(Color.LightGray))
        OutlinedTextField(value = age, onValueChange = {
            age = it
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.height(7.dp))
        Button(onClick = {
            val profileTask = ProfileTask(name = name, age = age.toInt())
            dao.insertProfile(profileTask)
            navHostController.navigate(Screens.listScreen){
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop
            }
        }) {
            Text(text = "insert")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navHostController: NavHostController, id :Int, dao: ProfileDao) {
    var  profileTask = dao.getItemByID(id)
    var name by remember {
        mutableStateOf(profileTask.name)
    }
    var age by remember {
        mutableStateOf(profileTask.age.toString())
    }
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = name, onValueChange = {
            name = it
        })
        Spacer(modifier = Modifier.height(7.dp))
        OutlinedTextField(value = age, onValueChange = {
            age = it
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.height(7.dp))
        Button(onClick = {
            profileTask.name = name
            profileTask.age = age.toInt()
            dao.updateProfile(profileTask)
            navHostController.navigate(Screens.listScreen){
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop
            }
        }, Modifier.padding(7.dp)) {
            Text(text = "update")
        }
        Button(onClick = {
            dao.deleteProfile(profileTask)
            navHostController.navigate(Screens.listScreen){
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop
            }
        }, Modifier.padding(7.dp)) {
            Text(text = "delete")
        }
    }
}

object Screens {
    val listScreen = "ListScreen"
    val insertScreen = "InsertScreen"
}