package com.udacity.shoestore.screens.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.models.User
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        binding.buttonLogin.setOnClickListener {
            setUser()
            if (authenticated()) {
                findNavController().navigate(LoginFragmentDirections.actionWelcomeFragment())
            } else {
                Toast.makeText(context, "Não autorizado",Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonNewAccount.setOnClickListener {
            setUser()

            if (authenticated()) {
                findNavController().navigate(LoginFragmentDirections.actionWelcomeFragment())
            } else {
                Toast.makeText(context, "Não autorizado",Toast.LENGTH_SHORT).show()
            }
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.login)

        return binding.root
    }

    private fun authenticated(): Boolean {
        Timber.i("Fake Authentication!")

        // Note: only validating email/password are not empty
        return !binding.user?.email?.isEmpty()!! &&
                !binding.user?.password?.isEmpty()!!
    }

    private fun setUser() {
        binding.user = User(binding.inputEmail.text.toString(),
            binding.inputPassword.text.toString())
    }
}