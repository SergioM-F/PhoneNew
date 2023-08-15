package cl.samf.phonenew.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhoneDao {

    @Insert
    suspend fun insertPhone (phoneEntity: PhoneEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhones (phoneEntity: List<PhoneEntity>)

    @Query("select * from table_phone order by id ASC")
    fun getPhones(): LiveData<List<PhoneEntity>>

    @Query("select * from table_phone where id = :id")
    fun getPhones(id: Int): LiveData<PhoneEntity>

}