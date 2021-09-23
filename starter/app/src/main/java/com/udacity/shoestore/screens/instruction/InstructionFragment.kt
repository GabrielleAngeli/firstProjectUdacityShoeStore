package com.udacity.shoestore.screens.instruction

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionFragmentBinding
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.screens.login.LoginFragmentDirections

class InstructionFragment : Fragment() {

    private lateinit var binding: InstructionFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.instruction_fragment,
            container,
            false
        )

        binding.buttonInstructionNext.setOnClickListener {
            findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToListFragment())
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.bar_instructions)

        return binding.root
    }

}