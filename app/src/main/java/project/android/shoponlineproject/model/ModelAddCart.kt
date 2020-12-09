package project.android.shoponlineproject.model

data class ModelAddCart(
    val cost: List<GetCost>,
    val cost_post: String,
    val count: Int,
    val status: String
)
data class GetCost(
    val cost: String
)