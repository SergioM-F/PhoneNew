package cl.samf.phonenew.data

import cl.samf.phonenew.data.remote.PhoneList
import org.hamcrest.Matchers.closeTo
import org.junit.Assert.*

import org.junit.Test

@Suppress("DEPRECATION")
class MapperKtTest {

    @Test
    fun convert() {
        //given
        val phone = PhoneList(1, "name", 123.0, "image")
        //when
        val result = phone.convert()

        //then

        assertEquals(result.id, phone.id)
        assertEquals(result.name, phone.name)
        assertThat(phone.price, closeTo(result.price,0.001))
        assertEquals(result.image, phone.image)

    }
}