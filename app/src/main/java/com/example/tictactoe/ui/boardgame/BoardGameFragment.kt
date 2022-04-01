package com.example.tictactoe.ui.boardgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.tictactoe.databinding.FragmentBoardGameBinding
import androidx.fragment.app.Fragment
import com.example.tictactoe.R
import com.example.tictactoe.utils.SharedPreferencesHelper
import com.example.tictactoe.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardGameFragment : Fragment() {

    private var _binding: FragmentBoardGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var playerOneName: String
    private lateinit var playerTwoName: String

    lateinit var sharedPref: SharedPreferencesHelper

    private var TURN = 0
    private var playerIconTurn: Int = R.drawable.player_one_icon_big
    private var board = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var winner: Boolean = false
    private var EMPTY_STRING = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = SharedPreferencesHelper(context)
        playerOneName = if (sharedPref.getPlayerOneNameValue() != null)
            sharedPref.getPlayerOneNameValue()!!
        else
            getString(R.string.player_one_name)
        playerTwoName = if (sharedPref.getPlayerTwoNameValue() != null)
            sharedPref.getPlayerTwoNameValue()!!
        else
            getString(R.string.player_two_name)
        with(binding) {
            playerTurn.playerNameTurn.text = playerOneName
            playerTurn.playerIconTurn.setImageResource(playerIconTurn)
        }
        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            boardGame.cellOne.cellButton.setOnClickListener {
                if (emptyCell(board[0])) setMovementPlayer(boardGame.cellOne.cellButton, 0)
            }
            boardGame.cellTwo.cellButton.setOnClickListener {
                if (emptyCell(board[1])) setMovementPlayer(boardGame.cellTwo.cellButton, 1)
            }
            boardGame.cellThree.cellButton.setOnClickListener {
                if (emptyCell(board[2])) setMovementPlayer(boardGame.cellThree.cellButton, 2)
            }
            boardGame.cellFour.cellButton.setOnClickListener {
                if (emptyCell(board[3])) setMovementPlayer(boardGame.cellFour.cellButton, 3)
            }
            boardGame.cellFive.cellButton.setOnClickListener {
                if (emptyCell(board[4])) setMovementPlayer(boardGame.cellFive.cellButton, 4)
            }
            boardGame.cellSix.cellButton.setOnClickListener {
                if (emptyCell(board[5])) setMovementPlayer(boardGame.cellSix.cellButton, 5)
            }
            boardGame.cellSeven.cellButton.setOnClickListener {
                if (emptyCell(board[6])) setMovementPlayer(boardGame.cellSeven.cellButton, 6)
            }
            boardGame.cellEight.cellButton.setOnClickListener {
                if (emptyCell(board[7])) setMovementPlayer(boardGame.cellEight.cellButton, 7)
            }
            boardGame.cellNine.cellButton.setOnClickListener {
                if (emptyCell(board[8])) setMovementPlayer(boardGame.cellNine.cellButton, 8)
            }
        }
    }

    private fun emptyCell(numCell: Int): Boolean {
        if (numCell == 0)
            return true
        return false
    }

    private fun checkMove() {
        if (TURN >= 5) {
            for (i in 1..2) {
                if (board[0] == i && board[1] == i && board[2] == i) showWinner()
                if (board[3] == i && board[4] == i && board[5] == i) showWinner()
                if (board[6] == i && board[7] == i && board[8] == i) showWinner()
                if (board[0] == i && board[3] == i && board[6] == i) showWinner()
                if (board[1] == i && board[4] == i && board[7] == i) showWinner()
                if (board[2] == i && board[5] == i && board[8] == i) showWinner()
                if (board[0] == i && board[4] == i && board[8] == i) showWinner()
                if (board[2] == i && board[4] == i && board[6] == i) showWinner()
            }
        }
        if (TURN == 9 && !winner)
            showTie()
    }

    private fun setMovementPlayer(button: ImageButton, position: Int) {
        if (TURN % 2 == 0)
            board[position] = 1
        else
            board[position] = 2
        button.setImageResource(playerIconTurn)
        TURN++
        checkMove()
        setPlayerTurn()
    }

    private fun setPlayerTurn() {
        with(binding) {
            if (TURN < 9) {
                if (TURN % 2 == 0) {
                    playerTurn.playerNameTurn.text = playerOneName
                    playerIconTurn = R.drawable.player_one_icon_big
                    playerTurn.playerIconTurn.setImageResource(playerIconTurn)
                } else {
                    playerTurn.playerNameTurn.text = playerTwoName
                    playerIconTurn = R.drawable.player_two_icon_big
                    playerTurn.playerIconTurn.setImageResource(playerIconTurn)
                }
            } else {
                checkMove()
            }
        }
    }

    private fun showWinner() {
        with(binding) {
            requireContext().showDialog(
                title = getString(R.string.winner),
                message = (playerTurn.playerNameTurn.text.toString() + EMPTY_STRING + getString(R.string.text_winner_message)),
                positiveButtonText = getString(R.string.restart),
                positiveButtonAction = { _, _ -> restartGame() },
                negativeButtonText = getString(R.string.ok)
            )
        }
        winner = true
        for (i in 0..8) {
            board[i] = 3
        }
    }

    private fun showTie() {
        requireContext().showDialog(
            title = getString(R.string.tie),
            message = (getString(R.string.text_tie_message)),
            positiveButtonText = getString(R.string.restart),
            positiveButtonAction = { _, _ -> restartGame() },
            negativeButtonText = getString(R.string.ok)
        )
    }

    private fun restartGame() {
        for (i in 0..8) {
            board[i] = 0
        }
        with(binding) {
            restartCell(boardGame.cellOne.cellButton)
            restartCell(boardGame.cellTwo.cellButton)
            restartCell(boardGame.cellThree.cellButton)
            restartCell(boardGame.cellFour.cellButton)
            restartCell(boardGame.cellFive.cellButton)
            restartCell(boardGame.cellSix.cellButton)
            restartCell(boardGame.cellSeven.cellButton)
            restartCell(boardGame.cellEight.cellButton)
            restartCell(boardGame.cellNine.cellButton)
        }
        TURN = 0
        winner = false
        setPlayerTurn()
    }

    private fun restartCell(cell: ImageButton) {
        cell.setImageResource(R.drawable.empty_cell_icon)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}