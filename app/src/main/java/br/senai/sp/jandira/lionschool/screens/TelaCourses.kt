package br.senai.sp.jandira.lionschool.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.model.Course
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import br.senai.sp.jandira.lionschool.components.LionCourseComponent

data class Course(
    val id: String,
    val icone: Int,
    val sigla: String,
    val nome: String,
    val descricao: String,
    val cargaHoraria: String
)


@Composable
fun CoursesScreen(navController: NavHostController) {
    val courses = listOf(
        Course(
            id = "1",
            icone = R.drawable.programming1,
            sigla = "DS",
            nome = stringResource(R.string.course_ds_name),
            descricao = stringResource(R.string.course_ds_description),
            cargaHoraria = stringResource(R.string.course_ds_duration)
        ),
        Course(
            id = "2",
            icone = R.drawable.internet1,
            sigla = "RDS",
            nome = stringResource(R.string.course_rds_name),
            descricao = stringResource(R.string.course_rds_description),
            cargaHoraria = stringResource(R.string.course_rds_duration)
        ),
        Course(
            id = "3",
            icone = R.drawable.lion_chip1,
            sigla = "ELE",
            nome = stringResource(R.string.course_ele_name),
            descricao = stringResource(R.string.course_ele_description),
            cargaHoraria = stringResource(R.string.course_ele_duration)
        )
    )

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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(R.string.logo_descripition),
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        text = stringResource(R.string.title),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1262BE),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                // Divider
                Divider(
                    color = Color(0xFFFFC23D),
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                // Search field
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = {
                        Text(text = stringResource(R.string.search_placeholder))
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_placeholder),
                            tint = Color.Gray
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    )
                )

                // Courses title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.list),
                        contentDescription = "List icon",
                        modifier = Modifier.size(36.dp)
                    )
                    Text(
                        text = stringResource(R.string.courses_section_title),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1262BE),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                // Courses list
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(courses) { course ->
                        LionCourseComponent(
                            icone = painterResource(id = course.icone),
                            sigla = course.sigla,
                            nome = course.nome,
                            descricao = course.descricao,
                            cargaHoraria = course.cargaHoraria,
                            isFilled = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("course_details/${course.id}")
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CourseCard(
    course: Course,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = course.icone),
                    contentDescription = course.nome,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = course.sigla,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1262BE)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = course.nome,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = course.descricao,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = course.cargaHoraria,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFC23D)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CoursesScreenPreview() {
    LionSchoolTheme {
        CoursesScreen(navController = androidx.navigation.compose.rememberNavController())
    }
}