package cl.samf.phonenew.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.samf.phonenew.data.Repository
import cl.samf.phonenew.data.local.PhoneDataBase
import cl.samf.phonenew.data.remote.PhoneRetrofit
import kotlinx.coroutines.launch

class PhoneViewModel(application: Application): AndroidViewModel(application) {

    private val repository : Repository

    fun phoneLiveData() = repository.getPhones()

    fun phoneIdLiveData (id: Int) = repository.getDetailsIdPhones(id)


    init {
        val phoneApi = PhoneRetrofit.getRetrofitClient()
        val phoneDataBase = PhoneDataBase.getDataBase(application).getPhoneDao()
        repository = Repository(phoneApi, phoneDataBase)
    }
    fun getPhone() = viewModelScope.launch {
        repository.loadPhone()
    }

    fun getDetailsPhone(id: Int) = viewModelScope.launch {
        repository.detailsPhone(id)
    }

}