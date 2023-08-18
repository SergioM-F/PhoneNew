package cl.samf.phonenew.data


import cl.samf.phonenew.data.local.PhoneEntity
import cl.samf.phonenew.data.remote.PhoneList

fun PhoneList.convert(): PhoneEntity =
    PhoneEntity(this.id, this.name, this.price, this.image)