package com.example.nafis.nf.organizetestcenter.Model

data class TestPaper(
    val id: Int = 0,
    val title: String = "",
    val noOfQues: String = "",
    val totalMark: String = "",
    val totalTime: Long = 0,
    val classname: String = "",
    val subname: String = "",
    val chapname: String = "",
    val questions: List<QuizModel> = listOf()
)


data class QuizModel(
    val quizId: String = "",
    val quizQues: String = "",
    val quizop1: String = "",
    val quizop2: String = "",
    val quizop3: String = "",
    val quizop4: String = "",
    val correctop: String = ""
)
