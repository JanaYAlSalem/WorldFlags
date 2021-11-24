package com.example.worldflags.network

data class FlagPhoto(val name: String, val flag: String)
data class ArrayOfData(val error: Boolean, val msg: String, val data:List<FlagPhoto>)