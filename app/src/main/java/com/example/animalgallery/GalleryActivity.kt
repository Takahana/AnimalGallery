package com.example.animalgallery

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun AnimalGallery(animals: List<Animal>, onItemClick: (Animal) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(animals) { animal ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable(onClick = { onItemClick(animal) }),
            ) {
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
            Spacer(modifier = Modifier.height(8.dp))
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