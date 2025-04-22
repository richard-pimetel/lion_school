package br.senai.sp.jandira.lionschool.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R

@Composable
fun LionCourseComponent(
    icone: Painter = painterResource(id = R.drawable.programming1),
    sigla: String = "",
    nome: String = "",
    descricao: String = "",
    cargaHoraria: String = "",
    isFilled: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(322.dp)
            .height(190.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, colorResource(R.color.lion_color_yellow))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorResource(R.color.lion_color_blue),
                            colorResource(R.color.lion_color_light_white)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = if (isFilled) icone else painterResource(id = R.drawable.image_not_found),
                        contentDescription = "",
                        modifier = Modifier
                            .height(102.dp)
                            .width(101.dp)
                    )
                    Text(
                        text = sigla,
                        fontSize = 64.sp,
                        color = colorResource(R.color.lion_color_yellow),
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Column {
                    Text(
                        text = nome,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = descricao,
                        color = Color.White,
                        fontWeight = FontWeight.Thin
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.watch_later),
                        contentDescription = "",
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp)
                    )
                    Text(
                        text = cargaHoraria,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LionCourseComponentPreview() {
    LionCourseComponent(
        sigla = "DS",
        nome = "Desenvolvimento de Sistemas",
        descricao = "Curso técnico em desenvolvimento de sistemas",
        cargaHoraria = "3 semestres",
        isFilled = true
    )
}