package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import com.example.android.guesstheword.viewmodel.GameViewModel


class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.endGame.observe(viewLifecycleOwner, Observer<Boolean>{
            hasFinished -> if(hasFinished) gameFinished()
        })

        return binding.root

    }

    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onEndGameCompleted()
    }
}
