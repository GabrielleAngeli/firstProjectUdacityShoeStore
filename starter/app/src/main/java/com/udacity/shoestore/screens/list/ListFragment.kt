package com.udacity.shoestore.screens.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer


import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListFragmentBinding
import com.udacity.shoestore.screens.addItem.AddItemViewModel


class ListFragment : Fragment() {

    private lateinit var binding: ListFragmentBinding
    private val sharedViewModel: AddItemViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_fragment,
            container,
            false
        )

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToAddItemFragment())

        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Note: create ViewModel and associate to this Fragment
        //       also handles configuration changes such as device rotation
        val showDetailsViewModel = ViewModelProvider(requireActivity())
            .get(AddItemViewModel::class.java)

        // Note: setup binding for LiveData to know to observe this LifecycleOwner
        binding.lifecycleOwner = this

        // Note: ViewModel field(s) can now Observer Lifecycle changes
        showDetailsViewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            if (shoes.isNotEmpty()) {
                createShoes(shoes)
            }
        })

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.shoe_list)
    }

    private fun createShoes(shoes: List<Shoe>) {
        context?.let { context ->
            val shoeContainer = binding.linearLayout
            shoes.forEach { shoe ->
                val shoeLayout = ShoeItem(context)
                shoeLayout.loadShoe(shoe)
                shoeContainer.addView(shoeLayout)
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logOutId -> {
                // if the logout option was selected the user will navigate to the Login Option
                navigateToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToLogin() {
        findNavController().popBackStack(R.id.loginFragment, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }
}