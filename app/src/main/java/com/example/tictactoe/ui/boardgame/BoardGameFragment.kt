package com.example.tictactoe.ui.boardgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tictactoe.databinding.FragmentBoardGameBinding
import androidx.fragment.app.Fragment
import com.example.tictactoe.R
import com.example.tictactoe.utils.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardGameFragment : Fragment() {

    private var _binding: FragmentBoardGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var playerOneName: String
    private lateinit var playerTwoName: String

    private var TURN: Int = 1

    lateinit var sharedPref: SharedPreferencesHelper

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
        setPlayerTurn()
        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            boardGame.cellOne.cellButton.setOnClickListener {
                setPlayerTurn()
            }
        }
    }

    private fun setPlayerTurn() {
        with(binding) {
            if (TURN <= 9) {
                if (TURN % 2 != 0) {
                    playerTurn.playerNameTurn.text = playerOneName
                    playerTurn.playerIconTurn.setImageResource(R.drawable.player_one_icon_big)
                } else {
                    playerTurn.playerNameTurn.text = playerTwoName
                    playerTurn.playerIconTurn.setImageResource(R.drawable.player_two_icon_big)
                }
                TURN++
            } else {
                TURN = 1
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}