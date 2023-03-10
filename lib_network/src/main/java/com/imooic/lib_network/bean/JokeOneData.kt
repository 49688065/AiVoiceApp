package com.imooic.lib_network.bean

//

data class JokeOneData(
    val error_code: Int,
    val reason: String,
    val results: List<Results>
)

data class Result(
    val content: String,
    val hashId: String,
    val unixtime: Int
)
