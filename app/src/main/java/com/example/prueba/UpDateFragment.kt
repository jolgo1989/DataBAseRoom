package com.example.prueba

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.prueba.data.User
import com.example.prueba.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_up_date.*
import kotlinx.android.synthetic.main.fragment_up_date.view.*


class UpDateFragment : Fragment() {
    private val args by navArgs<UpDateFragmentArgs>()


    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_up_date, container, false)


        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //Metodo para matener los datos en el recyclerView
        view.textInputUpDateFirstName.editText?.setText(args.CurrentUser.firstName)
        view.textInputUpDateLastName.editText?.setText(args.CurrentUser.lastName)
        view.textInputUpDateAge.editText?.setText(args.CurrentUser.age.toString())

        view.buttonUpdate.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem(){
        val firstName = textInputUpDateFirstName.editText?.text.toString()
        val lastName = textInputUpDateLastName.editText?.text.toString()
        val age = textInputUpDateAge.editText?.text.toString()
        if(inputCheck(firstName, lastName, age)){
            // Create User Object
            val updatedUser = User(args.CurrentUser.id, firstName, lastName, age)
            // Update Current User
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_upDateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}

