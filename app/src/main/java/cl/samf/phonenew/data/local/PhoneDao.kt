package cl.samf.phonenew.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhoneDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhones (phoneEntity: List<PhoneEntity>)

    @Query("select * from table_phone order by id ASC")
    fun getPhones(): LiveData<List<PhoneEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailsPhones (detailPhoneEntity: DetailsPhoneEntity)

   //@Query("select * from table_details_phone order by id ASC")
   //fun getDetailsPhones(): LiveData<List<DetailsPhoneEntity>>

    @Query("select * from table_details_phone where id = :id")
    fun getDetailsIdPhones(id: Int): LiveData<DetailsPhoneEntity>

}