package cl.samf.phonenew.data.local

import android.content.Context

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException



class PhoneDataBaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var productsDao: PhoneDao
    private lateinit var db: PhoneDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhoneDataBase::class.java).build()
        productsDao = db.getPhoneDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertBreeds_empty() = runBlocking {
        // Given
        val phoneList = listOf<PhoneEntity>()

        // When
        productsDao.insertPhones(phoneList)

        // Then A
        val it = productsDao.getPhones().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        productsDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertBreeds_happyCase_1element() = runBlocking {
        // Given
        val phoneList = listOf(PhoneEntity(1,"name",1.0,"image"))

        // When
        productsDao.insertPhones(phoneList)

        // Then
        productsDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertBreeds_happyCase_3elements() = runBlocking {
        // Given
        val phoneList = listOf(PhoneEntity(1,"name",1.0,"image"), PhoneEntity(2,"name2",2.0,"image2"), PhoneEntity(3,"name3",3.0,"image"))

        // When
        productsDao.insertPhones(phoneList)

        // Then
        productsDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}