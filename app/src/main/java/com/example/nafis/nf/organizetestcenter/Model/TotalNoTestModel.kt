package com.example.nafis.nf.organizetestcenter.Model

data class TotalNoTestModel(
    var id: Int? = null,
    var title: String? = null,
    var noOfQues: String? = null,
    var totalMark: String? = null,
    var totalTime: Long = 0,
    var classname: String? = null,
    var subname: String? = null,
    var chapname: String? = null
)

