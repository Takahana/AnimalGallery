package com.example.animalgallery.model

class AnimalRepository {

    private val data = listOf(
        Animal(
            id = 1,
            name = "Hachi",
            age = 3,
            thumbnail = "https://cdn.pixabay.com/photo/2018/10/04/19/46/dog-3724261_1280.jpg"
        ),
        Animal(
            id = 2,
            name = "Mike",
            age = 5,
            thumbnail = "https://cdn.pixabay.com/photo/2016/10/23/14/49/cat-slave-1763057_1280.jpg"
        )
    )

    fun getAll(): List<Animal> = data

    fun getById(id: Int): Animal? {
        return data.find { it.id == id }
    }
}