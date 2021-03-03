package com.example.animalgallery.model

class AnimalRepository {

    private val data = generateData(100)

    fun getAll(): List<Animal> = data

    fun getById(id: Int): Animal? {
        return data.find { it.id == id }
    }

    private fun generateData(size: Int): List<Animal> {
        return (1..size).map {
            when (it % 4) {
                1 -> Animal(
                    id = it,
                    name = "Hachi",
                    age = 3,
                    weight = 5,
                    thumbnail = "https://cdn.pixabay.com/photo/2018/10/04/19/46/dog-3724261_1280.jpg"
                )
                2 -> Animal(
                    id = it,
                    name = "Mike",
                    age = 5,
                    weight = 6,
                    thumbnail = "https://cdn.pixabay.com/photo/2017/09/25/13/12/dog-2785074_1280.jpg"
                )
                3 -> Animal(
                    id = it,
                    name = "Ken",
                    age = 9,
                    weight = 15,
                    thumbnail = "https://cdn.pixabay.com/photo/2018/05/07/10/48/husky-3380548_1280.jpg"
                )
                else -> Animal(
                    id = it,
                    name = "Kuro",
                    age = 7,
                    weight = 4,
                    thumbnail = "https://cdn.pixabay.com/photo/2016/07/15/15/55/dachshund-1519374_1280.jpg"
                )
            }
        }
    }
}