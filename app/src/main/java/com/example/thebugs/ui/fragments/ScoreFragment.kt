package com.example.thebugs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thebugs.App
import com.example.thebugs.databinding.FragmentScoreListBinding
import com.example.thebugs.ui.adaports.ScoreListAdapter
import com.example.thebugs.viewmodel.GameViewModel
import com.example.thebugs.viewmodel.GameViewModelFactory

class ScoreFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels {
        GameViewModelFactory(
            (activity?.application as App).database
                .scoreDao()
        )
    }

    private var _binding: FragmentScoreListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScoreListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ScoreListAdapter {
            val action =
                ScoreFragmentDirections.actionScoreFragmentToTittleFragment()

            this.findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
    }
}
