package com.example.thebugs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thebugs.databinding.FragmentTittleBinding

class TittleFragment : Fragment() {

    private var _binding: FragmentTittleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTittleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            startGame.setOnClickListener {
                val action =
                    TittleFragmentDirections.actionTittleFragmentToGameFragment()
                this@TittleFragment.findNavController().navigate(action)

            }
            gameScore.setOnClickListener {
                val action =
                    TittleFragmentDirections.actionTittleFragmentToScoreFragment()
                this@TittleFragment.findNavController().navigate(action)
            }
            exit.setOnClickListener {
                requireActivity().finish()
            }
        }
    }
}