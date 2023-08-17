package cl.samf.phonenew.data

import android.util.Log
import androidx.lifecycle.LiveData
import cl.samf.phonenew.data.local.DetailsPhoneEntity
import cl.samf.phonenew.data.local.PhoneDao
import cl.samf.phonenew.data.local.PhoneEntity
import cl.samf.phonenew.data.remote.DetailsPhone
import cl.samf.phonenew.data.remote.PhoneApi
import cl.samf.phonenew.data.remote.PhoneList

class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {

    fun getPhones(): LiveData<List<PhoneEntity>> = phoneDao.getPhones()
    fun getDetailsIdPhones(id: Int): LiveData<DetailsPhoneEntity> = phoneDao.getDetailsIdPhones(id)


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

    suspend fun detailsPhone(id: Int) {
        try {


            val response = phoneApi.getDetailsPhone(id)

            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let { phonesDetails ->
                    val detailsPhoneEntity = phonesDetails.convertDetails()
                    phoneDao.insertDetailsPhones(detailsPhoneEntity)
                }

            } else {
                Log.d("Error", "${response.errorBody()} ${response.code()}")
            }
        }catch (e:Exception){Log.d("Error", "${e.message}")}
    }


}

fun PhoneList.convert(): PhoneEntity =
    PhoneEntity(this.id, this.name, this.price, this.image)

fun DetailsPhone.convertDetails(): DetailsPhoneEntity =
    DetailsPhoneEntity(
        this.id,
        this.name,
        this.price,
        this.image,
        this.description,
        this.lastPrice,
        this.credit
    )