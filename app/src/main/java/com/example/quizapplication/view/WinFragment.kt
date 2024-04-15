package com.example.quizapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.quizapplication.R
import com.example.quizapplication.databinding.FragmentWinBinding


class WinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding=DataBindingUtil.inflate<FragmentWinBinding>(inflater,
            R.layout.fragment_win,container,false)

        binding.winTryAgainBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_winFragment_to_questionsFragment)
        }
        val args= WinFragmentArgs.fromBundle(requireArguments())
        binding.correctTv.text=getString(R.string.correctAnswers,args.numCorrect.toString())
        binding.wrongTv.text=getString(R.string.wrongAnswers,args.numWrong.toString())
        return binding.root

    }

}