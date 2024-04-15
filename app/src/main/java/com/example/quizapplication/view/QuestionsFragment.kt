package com.example.quizapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.quizapplication.R
import com.example.quizapplication.databinding.FragmentQuestionsBinding
import com.example.quizapplication.model.Questions

class QuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding
    private var questionArray = ArrayList<Questions>()
    private lateinit var question: Questions
    private var currentQuestion: Int = 0
    private var correctAnswer: Int = 0
    private var wrongAnswer: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentQuestionsBinding>(
            inflater,
            R.layout.fragment_questions, container, false
        )

        //fill Question Arraylist
        fillQuestionArray()
        //fill the views
        setQuestion()
        //submit button
        binding.questionBtnSubmit.setOnClickListener { view: View ->
            val choiceId = binding.radioGroup.checkedRadioButtonId
            question = questionArray[currentQuestion]
            var selectedChoice = 0

            //giving values to checked radio buttons
            when (choiceId) {
                R.id.firstChoice -> selectedChoice = 1
                R.id.secondChoice -> selectedChoice = 2
                R.id.thirdChoice -> selectedChoice = 3
            }

            //check the correct and wrong answers
            if (selectedChoice == question.correctAnswerId) {
                correctAnswer++
            } else {
                wrongAnswer++
            }
            currentQuestion++

            //set another question
            if (currentQuestion < questionArray.size) {
                binding.radioGroup.clearCheck()
                setQuestion()

            } else {

                // navigate to another fragment
                if (wrongAnswer >= 3) {
                    view.findNavController().navigate(
                        QuestionsFragmentDirections.actionQuestionsFragmentToGameOverFragment(
                            correctAnswer,
                            wrongAnswer
                        )
                    )

                } else {
                    view.findNavController().navigate(
                        QuestionsFragmentDirections.actionQuestionsFragmentToWinFragment(
                            correctAnswer,
                            wrongAnswer
                        )
                    )
                }
            }

        }

        return binding.root
    }

    private fun fillQuestionArray() {
        questionArray.add(
            Questions(
                1,
                "What does HTML stand for?",
                "Hyper Trainer Marking Language",
                "Hyper Text Marketing Language",
                "Hyper Text Markup Language",
                3
            )
        )
        questionArray.add(
            Questions(
                1,
                "One loop inside the body of another loop is called",
                "Loop in loop", "Nested", "Double loops",
                2
            )
        )
        questionArray.add(
            Questions(
                1, "is the process of finding errors and fixing them within a program.",
                "Compiling", "Debugging", "Executing",
                2
            )
        )
        questionArray.add(
            Questions(
                1,
                "A short sections of code written to complete a task.",
                "Function", "Buffer", "Array ",
                1
            )
        )
    }

    private fun setQuestion() {
        question = questionArray[currentQuestion]
        binding.questionTagTv.text = question.questionTag
        binding.firstChoice.text = question.firstChoice
        binding.secondChoice.text = question.secondChoice
        binding.thirdChoice.text = question.thirdChoice
    }

}
