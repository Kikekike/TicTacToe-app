package com.example.tictactoe.ui.playernames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tictactoe.databinding.FragmentPlayerNamesBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.tictactoe.R
import com.example.tictactoe.utils.SharedPreferencesHelper
import com.example.tictactoe.utils.inputText
import com.example.tictactoe.utils.isNameValid
import com.example.tictactoe.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerNamesFragment : Fragment() {

    private var _binding: FragmentPlayerNamesBinding? = null
    private val binding get() = _binding!!

    lateinit var sharedPref: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SharedPreferencesHelper(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        with(binding) {
            startGameButton.setOnClickListener {
                val playerOneName = namePlayerOne.inputText()
                val playerTwoName = namePlayerTwo.inputText()
                if (areNamesValid(playerOneName, playerTwoName)) {
                    sharedPref.setPlayerOneNameValue(playerOneName)
                    sharedPref.setPlayerTwoNameValue(playerTwoName)
                    handleSuccessNames()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        requireContext().showDialog(
            title = getString(R.string.error),
            message = getString(R.string.text_error_message)
        )
    }

    private fun areNamesValid(playerOneName: String, playerTwoName: String): Boolean {
        return isNameValid(playerOneName) && isNameValid(playerTwoName)
    }

    private fun handleSuccessNames() {
        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val action = PlayerNamesFragmentDirections.actionPlayerNamesFragmentToBoardGameFragment()
        navController.navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}