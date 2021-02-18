package com.example.todayweather.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentSearchMainBinding

class SearchMainFragment : Fragment() {

    lateinit var binding : FragmentSearchMainBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchMainBinding>(inflater, R.layout.fragment_search_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.before.setOnClickListener {
            navController.navigate(R.id.action_searchMainFragment_to_mainFragment)
            // 키보드 내리기 함수
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.autoCompleteEditText.windowToken, 0)
        }
        binding.resetText.setOnClickListener {
            // 클릭시 안에 있는 글 지우기
            binding.autoCompleteEditText.setText("")
        }
    }

}