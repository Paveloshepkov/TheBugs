package com.example.thebugs.ui.fragments

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.thebugs.App
import com.example.thebugs.R
import com.example.thebugs.databinding.FragmentGameBinding
import com.example.thebugs.viewmodel.GameViewModel
import com.example.thebugs.viewmodel.GameViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class GameFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels {
        GameViewModelFactory(
            (activity?.application as App).database
                .scoreDao()
        )
    }

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var gameTime: Long = 60000
    private val gameTimer: CountDownTimer = object : CountDownTimer(gameTime, 1000) {
        override fun onTick(seconds: Long) {
            gameTime = seconds / 1000
            binding.time.text = gameTime.toString()
        }

        override fun onFinish() {
            binding.gameView.visibility = View.GONE;
            showScoreInformationDialog()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        viewModel.startGame()
        binding.gameViewModel = viewModel

        binding.stopGame.setOnClickListener {
            gameTimer.cancel()
            val action = GameFragmentDirections.actionGameFragmentToTittleFragment()
            this.findNavController().navigate(action)
        }

        binding.gameView.setOnTouchListener { v, event ->
            val tmp = viewModel.score.value
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    viewModel.check(x = event.x, y = event.y)
                }
            }
            if (viewModel.score.value!! > tmp!!) {
                smashSound()
            } else {
                missSound()
            }
            binding.score.text = viewModel.score.value.toString()
            v?.onTouchEvent(event) ?: true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameTimer.start()
    }

    private fun addScore() {
        val score = binding.score.text
        viewModel.addNewScore(score = score.toString())
        val action = GameFragmentDirections.actionGameFragmentToTittleFragment()
        this@GameFragment.findNavController().navigate(action)
    }

    private fun showScoreInformationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Ure Score is :" + viewModel.score.value)
            .setPositiveButton("Save") { _, _ ->
                addScore()
            }
            .setNeutralButton("ScoreTable") { _, _ ->
                val action = GameFragmentDirections.actionGameFragmentToScoreFragment()
                this@GameFragment.findNavController().navigate(action)
            }
            .setNegativeButton("Menu") { _, _ ->
                val action = GameFragmentDirections.actionGameFragmentToTittleFragment()
                this@GameFragment.findNavController().navigate(action)
            }
            .show()

    }

    private fun smashSound() {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.smash)
        mp.start()
    }

    private fun missSound() {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.miss)
        mp.start()
    }

}


