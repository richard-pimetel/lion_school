package br.senai.sp.jandira.lionschool.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

data class Student(
    val name: String,
    val ra: String,
    val year: String
)

@Composable
fun StudentsScreen(navController: NavHostController) {
    var search by remember { mutableStateOf("") }
    val students = getMockStudents()

    LionSchoolTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo Lion School",
                            modifier = Modifier.size(60.dp)
                        )
                        Text(
                            text = "Lion School",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1262BE),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }


                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = Color(0xFFFFC23D),
                                shape = CircleShape
                            ), // <-- Faltava esta vírgula
                        contentAlignment = Alignment.Center // <-- Faltava este fechamento
                    ) {
                        Text(
                            text = "DS",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1262BE)
                        )
                    }

                }

                // Divider
                Divider(
                    color = Color(0xFFFFC23D),
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                // Título Find a student


                // Barra de pesquisa
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = {
                        Text(text = stringResource(id = R.string.textblue2))
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.textblue2),
                            tint = Color.Gray
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    )
                )

                // Filtros
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterButton("Todos", selected = true)
                    FilterButton("Cursando")
                    FilterButton("Finalizado")
                }

                // Divider
                Divider(
                    color = Color(0xFFFFC23D),
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                // Título Students List com ícone
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.students),
                        contentDescription = "Students icon",
                        modifier = Modifier.size(36.dp)
                    )
                    Text(
                        text = "Students List",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1262BE),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                // Lista de estudantes
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(students) { student ->
                        StudentCard(student)
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButton(text: String, selected: Boolean = false) {
    val backgroundColor = if (selected) Color(0xFFFFC23D) else Color.Transparent
    val textColor = if (selected) Color(0xFF1262BE) else Color(0xFF1262BE).copy(alpha = 0.6f)

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = if (!selected) BorderStroke(1.dp, Color(0xFF1262BE).copy(alpha = 0.6f)) else null
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

@Composable
fun StudentCard(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF9FA9E1)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = student.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = student.ra,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = if (student.year == "2025") Color(0xFFFFC23D) else Color(0xFF1262BE),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = student.year,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

fun getMockStudents(): List<Student> {
    return listOf(
        Student("Luana Oliveira Dias", "20151001018", "2022"),
        Student("Pedro da Silva Borges", "20151001018", "2022"),
        Student("Ana Clara Gomes", "20151001018", "2025"),
        Student("Fabiana Luz Moraes", "20151001018", "2022"),
        Student("Carlos Augusto Nobre", "20151001018", "2025"),
        Student("Pedro Xavier", "20151001018", "2025"),
        Student("Pedro Xavier", "20151001018", "2025")
    )
}

@Preview(showSystemUi = true)
@Composable
fun StudentsScreenPreview() {
    LionSchoolTheme {
        StudentsScreen(navController = androidx.navigation.compose.rememberNavController())
    }
}