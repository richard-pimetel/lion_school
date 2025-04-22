package br.senai.sp.jandira.lionschool.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

@Composable
fun StudentsScreen(navController: NavHostController) {
    var search by remember { mutableStateOf("") }

    LionSchoolTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {

                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = stringResource(R.string.logo_descripition),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.title),
                            fontSize = 18.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Card(
                        modifier = Modifier.size(40.dp).clip(CircleShape),
                        colors = CardDefaults.cardColors(Color(0xFFFFC23D))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "DS",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF3347B0)
                            )
                        }
                    }
                }

                // Divider
                Divider(
                    color = Color(0xFFFFC23D),
                    thickness = 2.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Search bar zxdc-

                Spacer(modifier = Modifier.height(16.dp))

                // Filtros
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilterButton("Todos")
                    FilterButton("Cursando")
                    FilterButton("Finalizado")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Título com ícone
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.twitter),
                        contentDescription = "Students",
                        tint = Color(0xFFFFC23D),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Students List",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3347B0)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Cards de estudantes (mock layout)
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(5) { // mock de 5 cards
                        StudentCard()
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButton(text: String) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable { /* ação futura */ },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC23D))
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3347B0)
        )
    }
}

@Composable
fun StudentCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF7D87E4)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Student photo",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Nome do Aluno",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "RA 0000000",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun StudentsScreenPreview() {
    LionSchoolTheme {
        StudentsScreen(navController = androidx.navigation.compose.rememberNavController())
    }
}