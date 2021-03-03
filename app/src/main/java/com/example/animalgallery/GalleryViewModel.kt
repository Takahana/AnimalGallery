package com.example.animalgallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalgallery.model.Animal
import com.example.animalgallery.model.AnimalRepository

class GalleryViewModel : ViewModel() {

    private val _animals = MutableLiveData<List<Animal>>()
    val animals: LiveData<List<Animal>> = _animals

    val animalRepository = AnimalRepository()

    init {
        getAnimals()
    }

    private fun getAnimals() {
        animalRepository.getAll().let {
            _animals.value = it
        }
    }
}