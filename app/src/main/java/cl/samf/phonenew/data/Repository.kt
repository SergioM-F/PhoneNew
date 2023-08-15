package cl.samf.phonenew.data

import androidx.lifecycle.LiveData
import cl.samf.phonenew.data.local.PhoneDao
import cl.samf.phonenew.data.local.PhoneEntity
import cl.samf.phonenew.data.remote.PhoneApi
import cl.samf.phonenew.data.remote.PhoneList

class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun getPhones(): LiveData<List<PhoneEntity>> = phoneDao.getPhones()

    suspend fun loadPhone() {

        val response = phoneApi.getData()

        if (response.isSuccessful) {
            val resp = response.body()
            resp?.let { phones ->
                val phoneEntity = phones.map { it.convert() }
                phoneDao.insertPhones(phoneEntity)
            }

        }
    }

    fun getPhones(id: Int): LiveData<PhoneEntity> = phoneDao.getPhones(id)
}
fun PhoneList.convert(): PhoneEntity =
    PhoneEntity(this.id, this.name, this.price, this.image)