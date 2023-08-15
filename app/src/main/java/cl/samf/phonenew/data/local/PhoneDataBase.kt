package cl.samf.phonenew.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhoneEntity::class], version = 1)
abstract class PhoneDataBase : RoomDatabase() {

    abstract fun getPhoneDao() : PhoneDao

    companion object {
        private var INSTANCE: PhoneDataBase? = null

        fun getDataBase(context: Context): PhoneDataBase {
            val tempInstance = INSTANCE
            if (tempInstance !=null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDataBase::class.java,
                    "phone_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}