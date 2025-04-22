package br.senai.sp.jandira.lionschool.model

data class Course(
    val id: Int,
    val sigla: String,
    val icone: Int,
    val nome: String,
    val descricao: String,
    val cargaHoraria: String
)
