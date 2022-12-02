package com.example.memorygame

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.model.BoardSize
import com.example.memorygame.model.MemoryCard
import com.example.memorygame.model.MemoryGame
import com.example.memorygame.service.ActivityHandler
import com.example.memorygame.service.FlipAnimation
import com.example.memorygame.service.MemoryBoardAdapter
import com.example.memorygame.service.Timer
import com.example.memorygame.utils.DEFAULT_ICONS
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_default_memory_game.*

class DefaultMemoryGame : AppCompatActivity() {

    companion object {
        private const val TAG = "DefaultMemoryGame"
    }

    private lateinit var memoryGame: MemoryGame
    private lateinit var clRoot: ConstraintLayout
    private lateinit var board: RecyclerView
    private lateinit var moves: TextView

    private lateinit var adapter: MemoryBoardAdapter

    private val getSize = intent
    private val size: BoardSize = getSize.getSerializableExtra("SizeOfBoard") as BoardSize
    private var boardSize: BoardSize = size

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_default_memory_game)

        board = findViewById(R.id.recyclerView)
        moves = findViewById(R.id.moves)
        val backBtn: Button = findViewById(R.id.backBtn)
        clRoot = findViewById(R.id.clRoot)

        boardSetup()

        val loadMainMenu: ActivityHandler = ActivityHandler()
        val fa: FlipAnimation = FlipAnimation()

        val tmr: Timer = Timer()

        backBtn.setOnClickListener {
            loadMainMenu.switchActivity(this, this, MainMenu::class.java, 0)
        }

        resetBtn.setOnClickListener {
            if (memoryGame.getNumberOfMoves() > 0 && !memoryGame.haveWonGame()) {
                showAlert("Are you sure you want to reset current game?", null, View.OnClickListener {
                    boardSetup()
                })
            } else {
                boardSetup()
            }
        }

    }

    private fun showAlert(title: String, view: View?, positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("OK") { _, _ ->
                positiveClickListener.onClick(null)
            }.show()
    }

    private fun updateGameWithFlip(position: Int) {
        if (memoryGame.haveWonGame()) {
            Snackbar.make(clRoot, "You already won!", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (memoryGame.isCardFaceUp(position)) {
            Snackbar.make(clRoot, "Invalid move!", Snackbar.LENGTH_SHORT).show()
            return
        }
        if (memoryGame.flipCard(position)) {
            if (memoryGame.haveWonGame()) {
                Snackbar.make(clRoot, "You won!", Snackbar.LENGTH_SHORT).show()
            }
        }
        moves.text = "Moves: " + memoryGame.getNumberOfMoves();
        adapter.notifyDataSetChanged()
    }

    private fun boardSetup()  {
        when (boardSize) {
            BoardSize.EASY -> {
                moves.text = "Easy: 4 x 2"
            }
            BoardSize.MEDIUM -> {
                moves.text = "Medium: 4 x 4"
            }
            BoardSize.HARD -> {
                moves.text = "Hard: 6 x 4"
            }
            BoardSize.EXPERT -> {
                moves.text = "Expert: 6 x 5"
            }
        }
        memoryGame = MemoryGame(boardSize)
        adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards, object: MemoryBoardAdapter.CardClickListener {
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
            }

        })
        board.adapter = adapter
        board.setHasFixedSize(true)
        board.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }
}