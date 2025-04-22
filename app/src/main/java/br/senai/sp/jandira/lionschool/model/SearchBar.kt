package br.senai.sp.jandira.lionschool.model

import androidx.compose.ui.graphics.Color

data class SearchBar(
    var text: String,
    var isSelected: Boolean = false,
    var color: Color
)