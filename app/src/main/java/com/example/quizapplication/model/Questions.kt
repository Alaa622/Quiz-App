package com.example.quizapplication.model

data class Questions (
    val questionId:Int,
    val questionTag:String,
    val firstChoice:String,
    val secondChoice:String,
    val thirdChoice:String,
    val correctAnswerId:Int

)