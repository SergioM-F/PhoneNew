package cl.samf.phonenew.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.security.interfaces.DSAKey

@Entity(tableName = "table_details_phone")
data class DetailsPhoneEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val description: String,
    val lastPrice: Double,
    val credit: Boolean
)
