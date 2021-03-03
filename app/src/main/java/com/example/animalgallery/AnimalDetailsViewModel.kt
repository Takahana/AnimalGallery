package com.example.animalgallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalgallery.model.Animal
import com.example.animalgallery.model.AnimalRepository

class AnimalDetailsViewModel : ViewModel() {

    private val _animal = MutableLiveData<Animal>()
    val animal: LiveData<Animal> = _animal

    private val animalRepository = AnimalRepository()

    fun loadDetail(animalId: Int) {
        animalRepository.getById(animalId)?.let {
            _animal.value = it
        }
    }
}