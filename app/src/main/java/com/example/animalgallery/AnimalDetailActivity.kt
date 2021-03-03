package com.example.animalgallery

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animalgallery.model.Animal
import com.example.animalgallery.ui.theme.AnimalGalleryTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class AnimalDetailActivity : AppCompatActivity() {

    companion object {
        private const val KEY_ANIMAL_ID = "animal_id"

        fun createIntent(activity: AppCompatActivity, animal: Animal): Intent {
            return Intent(activity, AnimalDetailActivity::class.java).apply {
                putExtra(KEY_ANIMAL_ID, animal.id)
            }
        }
    }

    private val detailsViewModel by viewModels<AnimalDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalGalleryTheme {
                val animal: Animal? by detailsViewModel.animal.observeAsState()
                AnimalDetail(animal)
            }
        }
        getExtras()
    }

    private fun getExtras() {
        val animalId = intent.getIntExtra(KEY_ANIMAL_ID, -1).takeIf { it >= 0 }
        animalId?.let { detailsViewModel.loadDetail(it) }
    }
}

@Composable
fun AnimalDetail(animal: Animal?) {
    animal ?: return
    Column {
        CoilImage(
            data = animal.thumbnail,
            contentDescription = animal.name,
            loading = {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = animal.name)
            Text(text = "Age ${animal.age}")
        }
    }
}