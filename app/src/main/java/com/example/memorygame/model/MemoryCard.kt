package com.example.memorygame.model

data class MemoryCard (
    val identifier: Int,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)