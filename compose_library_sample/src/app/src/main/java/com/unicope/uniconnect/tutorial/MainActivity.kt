package com.unicope.uniconnect.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.unicope.uniconnect.tutorial.ui.theme.TutorialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.unicope.uniconnect.library.button.GapButton
import com.unicope.uniconnect.library.button.GapButtonInterface
import com.unicope.uniconnect.library.textfield.GapTextField
import com.unicope.uniconnect.library.textfield.GapTextFieldInterface
import com.unicope.uniconnect.library.user_item.GapUserItem
import com.unicope.uniconnect.tutorial.ui.theme.CustomTextFieldTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()

        setContent {
            TutorialTheme {
                MainLayout(viewModel = viewModel)
            }
        }
    }

    private fun initializeViewModel() {
        val factory = MainViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }
}

@Composable
fun MainLayout(viewModel: MainViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (nameTextField, addButton, addMultipleButton, titleAndClearLayout, titleText, clearButton, userLazyColumn) = createRefs()
        var name by remember { mutableStateOf("") }
        val users: List<String> by viewModel.users.observeAsState(listOf())

        CustomTextFieldTheme {
            GapTextField(
                text = name,
                customInterface = object : GapTextFieldInterface {
                    override fun onTextChange(text: String) {
                        name = text
                    }
                },
                modifier = Modifier
                    .constrainAs(nameTextField) {
                        top.linkTo(parent.top, margin = 12.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                    }
                    .width(150.dp)
            )
        }

        GapButton(
            text = stringResource(R.string.add),
            icon = Icons.Default.Add,
            customInterface = object : GapButtonInterface {
                override fun onButtonClick() {
                    viewModel.addUser(name)
                    name = ""
                }
            },
            modifier = Modifier
                .constrainAs(addButton) {
                    start.linkTo(nameTextField.end, margin = 12.dp)
                    centerVerticallyTo(nameTextField)
                }
        )

        GapButton(
            text = stringResource(R.string.add_3x),
            icon = Icons.Default.Add,
            customInterface = object : GapButtonInterface {
                override fun onButtonClick() {
                    viewModel.addMultipleUser(name)
                    name = ""
                }
            },
            modifier = Modifier
                .constrainAs(addMultipleButton) {
                    start.linkTo(addButton.end, margin = 12.dp)
                    centerVerticallyTo(nameTextField)
                }
        )

        ConstraintLayout(
            modifier = Modifier
                .constrainAs(titleAndClearLayout) {
                    top.linkTo(nameTextField.bottom, margin = 20.dp)
                    centerHorizontallyTo(parent)
                }
        ) {
            Text(
                text = stringResource(R.string.database_content),
                modifier = Modifier.constrainAs(titleText) {

                }
            )

            GapButton(
                text = stringResource(R.string.clear),
                icon = Icons.Default.Delete,
                customInterface = object : GapButtonInterface {
                    override fun onButtonClick() {
                        viewModel.deleteAllUsers()
                    }
                },
                modifier = Modifier.constrainAs(clearButton) {
                    start.linkTo(titleText.end, margin = 12.dp)
                    centerVerticallyTo(titleText)
                }
            )
        }

        LazyColumn(
            modifier = Modifier
                .constrainAs(userLazyColumn) {
                    top.linkTo(titleAndClearLayout.bottom, margin = 12.dp)
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                }
                .height(400.dp)
        ) {
            items(users) { user ->
                GapUserItem(name = user, avatar = painterResource(R.drawable.avatar))
            }
        }
    }
}