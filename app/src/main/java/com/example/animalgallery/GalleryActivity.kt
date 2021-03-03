package com.example.animalgallery

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animalgallery.model.Animal
import com.example.animalgallery.model.AnimalRepository
import com.example.animalgallery.ui.theme.AnimalGalleryTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class GalleryActivity : AppCompatActivity() {

    private val galleryViewModel: GalleryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalGalleryTheme {
                val animals: List<Animal> by galleryViewModel.animals.observeAsState(emptyList())
                AnimalGallery(animals, ::showDetail)
            }
        }
    }

    private fun showDetail(animal: Animal) {
        startActivity(AnimalDetailActivity.createIntent(this, animal))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimalGallery(animals: List<Animal>, onItemClick: (Animal) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(animals) { animal ->
            AnimalPortraitCard(animal, onItemClick)
        }
    }
}

@Composable
fun AnimalPortraitCard(animal: Animal, onItemClick: (Animal) -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 0.78F)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(onClick = { onItemClick(animal) })
        ) {
            CoilImage(
                data = animal.thumbnail,
                contentDescription = animal.name,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val animalRepository = AnimalRepository()
    val animals = animalRepository.getAll()
    AnimalGalleryTheme {
        AnimalGallery(animals) { /* do nothing */ }
    }
}