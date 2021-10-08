package com.example.exam4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.exam4.databinding.FragmentStartPageBinding


class startPage : Fragment() {

private lateinit var binding:FragmentStartPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartPageBinding.inflate(inflater, container, false)
        binding.start.setOnClickListener{ startGame()}

        return binding.root
    }

fun startGame(){
    val bundle = bundleOf("row" to binding.etRows.text.toString())
    findNavController().navigate(R.id.action_startPage_to_gamePage, bundle)
}
}