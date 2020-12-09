package project.android.shoponlineproject.model

data class Comment_List(
    val listComment: MutableList<Comment>
)
data class Comment(
    val comment: String,
    val idproduct: String,
    val name: String
)