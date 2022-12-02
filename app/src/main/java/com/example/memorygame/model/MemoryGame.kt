package com.example.memorygame.model

import com.example.memorygame.utils.DEFAULT_ICONS

class MemoryGame (private val boardSize: BoardSize) {
    val cards: List<MemoryCard>
    var numberOfFoundPairs = 0

    private var numberOfCardFliped = 0
    private var indexOfSingleSelectedCard: Int? = null

    init {
        val images = DEFAULT_ICONS.shuffled().take(boardSize.getNumberOfPairs())
        val randomImages = (images + images).shuffled()
        cards = randomImages.map {MemoryCard(it)}
    }

    fun flipCard(position: Int): Boolean {
        numberOfCardFliped++
        val card = cards[position]
        var foundMatch = false
        if (indexOfSingleSelectedCard == null) {
            restoreCard()
            indexOfSingleSelectedCard = position
        } else {
            foundMatch = checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }

        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier) {
            return false
        }

        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numberOfFoundPairs++
        return true
    }

    private fun restoreCard() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numberOfFoundPairs == boardSize.getNumberOfPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }

    fun getNumberOfMoves(): Int {
        return numberOfCardFliped / 2
    }
}