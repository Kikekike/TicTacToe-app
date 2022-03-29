package com.example.tictactoe.utils

import javax.inject.Inject
import android.content.Context

class SharedPreferencesHelper
@Inject constructor(val mContext: Context?){

    private val sharedPref = "sharedPrefs"
    private val sharedPreferences = mContext?.getSharedPreferences(sharedPref,Context.MODE_PRIVATE)
    private val PLAYER_ONE_NAME_KEY = "player_one_name_key_sp"
    private val PLAYER_TWO_NAME_KEY = "player_two_name_key_sp"

    fun setPlayerOneNameValue(playerOneName: String){
        val editor = sharedPreferences?.edit()
        editor?.apply{
            putString(PLAYER_ONE_NAME_KEY,playerOneName)
        }?.apply()
    }

    fun setPlayerTwoNameValue(playerTwoName: String){
        val editor = sharedPreferences?.edit()
        editor?.apply{
            putString(PLAYER_TWO_NAME_KEY,playerTwoName)
        }?.apply()
    }

    fun getPlayerOneNameValue(): String? {
        val playerOneName = sharedPreferences?.getString(PLAYER_ONE_NAME_KEY,null)
        return playerOneName
    }
    fun getPlayerTwoNameValue(): String? {
        val playerTwoName = sharedPreferences?.getString(PLAYER_TWO_NAME_KEY,null)
        return playerTwoName
    }

}

