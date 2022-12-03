package com.example.memorygame

import android.content.Intent
import android.os.Bundle
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
import com.example.memorygame.model.MemoryGame
import com.example.memorygame.service.ActivityHandler
import com.example.memorygame.service.MemoryBoardAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_default_memory_game.*


class DefaultMemoryGame : AppCompatActivity() {

    /*companion object {
        private const val TAG = "DefaultMemoryGame"
    }*/

    private lateinit var memoryGame: MemoryGame
    private lateinit var clRoot: ConstraintLayout
    private lateinit var board: RecyclerView
    private lateinit var moves: TextView

    private lateinit var adapter: MemoryBoardAdapter

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

        val getSize: Intent = intent
        val boardSize: BoardSize = getSize.getSerializableExtra("SizeOfBoard") as BoardSize

        boardSetup(boardSize)

        val loadMainMenu = ActivityHandler()

        backBtn.setOnClickListener {
            loadMainMenu.switchActivity(this, this, MainMenu::class.java, 0)
        }

        resetBtn.setOnClickListener {
            if (memoryGame.getNumberOfMoves() > 0 && !memoryGame.haveWonGame()) {
                showAlert(
                    getString(R.string.message2),
                    null,
                    View.OnClickListener {
                        boardSetup(boardSize)
                    })
            } else {
                boardSetup(boardSize)
            }
        }

    }

    private fun showAlert(title: String, view: View?, positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton(getString(R.string.cncl), null)
            .setPositiveButton(getString(R.string.okay)) { _, _ ->
                positiveClickListener.onClick(null)
            }.show()
    }

    private fun updateGameWithFlip(position: Int) {
        if (memoryGame.haveWonGame()) {
            Snackbar.make(clRoot, getString(R.string.already_won), Snackbar.LENGTH_SHORT).show()
            return
        }
        if (memoryGame.isCardFaceUp(position)) {
            Snackbar.make(clRoot, getString(R.string.invalid), Snackbar.LENGTH_SHORT).show()
            return
        }
        if (memoryGame.flipCard(position)) {
            if (memoryGame.haveWonGame()) {
                Snackbar.make(clRoot, getString(R.string.won), Snackbar.LENGTH_SHORT).show()
            }
        }
        moves.text = getString(R.string.moves) + " " + memoryGame.getNumberOfMoves();
        adapter.notifyDataSetChanged()
    }

    private fun boardSetup(boardSize: BoardSize) {
        when (boardSize) {
            BoardSize.EASY -> {
                moves.text = getString(R.string.easy_4_x_2)
            }
            BoardSize.MEDIUM -> {
                moves.text = getString(R.string.medium_4_x_4)
            }
            BoardSize.HARD -> {
                moves.text = getString(R.string.hard_6_x_4)
            }
            BoardSize.EXPERT -> {
                moves.text = getString(R.string.expert_6_x_5)
            }
        }
        memoryGame = MemoryGame(boardSize)
        adapter = MemoryBoardAdapter(
            this,
            boardSize,
            memoryGame.cards,
            object : MemoryBoardAdapter.CardClickListener {
                override fun onCardClicked(position: Int) {
                    updateGameWithFlip(position)
                }

            })
        board.adapter = adapter
        board.setHasFixedSize(true)
        board.layoutManager = GridLayoutManager(this, boardSize.getWidth())
    }
}