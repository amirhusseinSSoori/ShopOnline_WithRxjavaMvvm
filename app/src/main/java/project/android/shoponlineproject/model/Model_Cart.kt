package project.android.shoponlineproject.model

data class Model_Cart(
    val Buy: ArrayList<Buy>
)
data class Buy(
    val code: String,
    val color: String,
    val cost: String,
    val count: String,
    val description: String,
    val id: String,
    val idproduct: String,
    val iduser: String,
    val image: String,
    val name: String
)