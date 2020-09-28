package com.example.readfromjson.network

/**
 * Data class for each list item from the json
 */
data class Item(
    val id : Long,
    val listId : Int,
    val name : String
) {
}