package com.example.animalgallery.model

class AnimalRepository {

    fun getAll(): List<Animal> = listOf(
        Animal(
            name = "Hachi",
            age = 3,
            thumbnail = "https://cdn.pixabay.com/photo/2018/10/04/19/46/dog-3724261_1280.jpg"
        ),
        Animal(
            name = "Mike",
            age = 5,
            thumbnail = "https://cdn.pixabay.com/photo/2016/10/23/14/49/cat-slave-1763057_1280.jpg"
        )
    )
}