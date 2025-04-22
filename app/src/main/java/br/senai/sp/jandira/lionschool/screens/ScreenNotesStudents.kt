package br.senai.sp.jandira.lionschool.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import br.senai.sp.jandira.lionschool.model.Aluno
import br.senai.sp.jandira.lionschool.model.Disciplina
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

@Composable
fun StudentPerformanceScreen(
    navController: NavHostController,
    aluno: Aluno = mockStudentData()
) {
    LionSchoolTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color(0xFF3347B0)
                        )
                    }

                    Text(
                        text = "Desempenho",
                        fontSize = 18.sp,
                        color = Color(0xFF3347B0),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.size(40.dp))
                }

                // Informações do aluno
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Foto do aluno",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF7D87E4))
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = aluno.nome,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3347B0)
                    )

                    Text(
                        text = aluno.curso,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }

                Divider(
                    color = Color(0xFFFFC23D),
                    thickness = 2.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Quadrado azul com as disciplinas
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF3347B0).copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Notas por disciplina:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3347B0),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        aluno.disciplinas.forEach { disciplina ->
                            SubjectPerformanceItem(disciplina = disciplina)
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        // Rodapé "Concluído" no canto inferior direito
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Text(
                                text = "Concluído",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF27AE60)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectPerformanceItem(disciplina: Disciplina) {
    val progressColor = when {
        disciplina.media < 60 -> Color(0xFFE74C3C)
        disciplina.media < 80 -> Color(0xFFF39C12)
        else -> Color(0xFF27AE60)
    }

    val progressValue = disciplina.media / 100f

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = disciplina.sigla,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3347B0)
            )

            Text(
                text = "%.0f".format(disciplina.media),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = progressColor
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(Color.LightGray, RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(progressColor, RoundedCornerShape(4.dp))
            )
        }
    }
}

// Função mock para preview
private fun mockStudentData(): Aluno {
    return Aluno(
        id = 1,
        nome = "Luana Oliveira Dias",
        matricula = "001",
        curso = "Técnico em Desenvolvimento de Sistemas",
        foto = "",
        status = "Concluído",
        anoConclusao = "2024",
        disciplinas = listOf(
            Disciplina(1, "SIOP", 70.0, "Aprovado"),
            Disciplina(2, "INRI", 86.0, "Aprovado"),
            Disciplina(3, "INNU", 59.0, "Aprovado"),
            Disciplina(4, "HA", 100.0, "Aprovado"),
            Disciplina(5, "SERE", 44.0, "Reprovado"),
            Disciplina(6, "CAES", 52.0, "Reprovado")
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun StudentPerformanceScreenPreview() {
    LionSchoolTheme {
        StudentPerformanceScreen(
            navController = androidx.navigation.compose.rememberNavController(),
            aluno = mockStudentData()
        )
    }
}
