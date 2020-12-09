package project.android.ecommers.model

data class ModelDetails(
    val post: List<Post>,
    val slider: List<Slider>
)
data class Post(
    val code: String,
    val color: String,
    val cost: String,
    val description: String,
    val id: String,
    val image: String,
    val name: String
)
data class Slider(
    val image: String
)