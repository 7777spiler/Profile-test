package com.example.profilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter


@Composable
fun ProfileListPage(navController: NavController, users: MutableList<User>) {
    LazyColumn {
        itemsIndexed(users) { index, item ->
            GetCard( navController = navController, user = item, index = index)
        }
    }
}

@Composable
fun GetCard(navController: NavController, user: User, index: Int) {
    return Card(
        elevation = 6.dp, modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))



    ) {
        ConstraintLayout {
            val (image, nameText, secondText, rowStats, buttonFollow, buttonMessage) = createRefs()
            val guideline = createGuidelineFromTop(0.02f)

            Image(
                painter = rememberAsyncImagePainter(user.picture),
                contentDescription = "image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = CircleShape
                    )
                    .constrainAs(image) {
                        top.linkTo(guideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )

            Text(text = user.name,

                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Text(text = user.country,
                modifier = Modifier.constrainAs(secondText) {
                    top.linkTo(nameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(rowStats) {
                    top.linkTo(secondText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            {
                ProfileStats(count = user.email, title = "Email")
                ProfileStats(count = user.gender, title = "Gender")
                ProfileStats(count = user.phone, title = "Phone")
            }

            Button(onClick = { navController.navigate("profile/"+index) },
                modifier= Modifier.constrainAs(buttonFollow) {
                    top.linkTo(rowStats.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(buttonMessage.start)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.wrapContent
                }
            ) {
                Text(text = "Details")
            }

            Button(onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(buttonMessage) {
                    top.linkTo(rowStats.bottom, margin = 16.dp)
                    start.linkTo(buttonFollow.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.wrapContent
                }
            ) {
                Text(text = "Message")
            }
        }
    }
}

@Composable
fun ProfileStats(count: String, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = count,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = title
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileListPage() {
    var userList: MutableList<User> = mutableListOf()

    userList.add(
        User(
            "Tom",
            "man",
            "adam7@gmail.com",
            "https://api.randomuser.me/portraits/men/20.jpg",
            "06304993567", "USA"
        )
    )

    userList.add(
        User(
            "Mat",
            "man",
            "adam7@gmail.com",
            "https://api.randomuser.me/portraits/men/22.jpg",
            "06304993567", "USA"
        )
    )

    userList.add(
        User(
            "David",
            "man",
            "adam7@gmail.com",
            "https://randomuser.me/api/portraits/med/men/41.jpg",
            "06304993567", "USA"
        )
    )

    ProfileListPage(CustomNavHost(users = userList), userList)
}











