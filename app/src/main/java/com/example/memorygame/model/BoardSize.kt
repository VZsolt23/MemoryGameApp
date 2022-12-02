package com.example.memorygame.model

enum class BoardSize(val cards: Int) {
    EASY(8),
    MEDIUM(16),
    HARD(24),
    EXPERT(30);

    fun getWidth(): Int {
        return when(this) {
            EASY -> 2
            MEDIUM -> 4
            HARD -> 4
            EXPERT -> 5
        }
    }

    fun getHeight(): Int {
        return cards / getWidth()
    }

    fun getNumberOfPairs(): Int {
        return cards / 2
    }
}