package cl.samf.phonenew.data.remote

data class DetailsPhone (
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val description: String,
    val lastPrice: Double,
    val credit: Boolean
)
