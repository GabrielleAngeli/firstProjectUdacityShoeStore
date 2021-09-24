package com.udacity.shoestore.screens.addItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.AddShoeBinding
import com.udacity.shoestore.models.Shoe

class AddItemFragment : Fragment() {

    private lateinit var binding: AddShoeBinding
    private lateinit var addViewModel: AddItemViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_shoe,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addViewModel = ViewModelProvider(requireActivity())
            .get(AddItemViewModel::class.java)

        binding.lifecycleOwner = this

        binding.shoe = Shoe("Set Name","2","Set Company","Give Description")


        binding.buttonAdd.setOnClickListener { view ->
            val shoe = binding.shoe

            addViewModel.saveValue(shoe)

            // Note: leverage Navigation via graph to find correct destination to
            view?.findNavController()
                ?.navigate(AddItemFragmentDirections.actionAddItemFragmentToListFragment())
        }

        binding.buttonCancel.setOnClickListener{
            it?.findNavController()?.navigate(AddItemFragmentDirections.actionAddItemFragmentToListFragment())
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.bar_add)
    }
    
}