package com.github.aarjavp.brick.core.init

import com.github.aarjavp.brick.core.model.Item

class ItemGenerator() {
    val itemConstructors = mutableSetOf<() -> Item>()


    fun getItems(numItems: Int): List<Item> {
        val listOfItems = mutableListOf<Item>()
        for (i in (0..numItems)) {
            val rand = itemConstructors.random()
            val newItem = rand()
            listOfItems.add(newItem)
        }
        return listOfItems
    }

}