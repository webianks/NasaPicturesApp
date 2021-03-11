package com.webianks.nasapicturesapp.utils

import com.google.gson.Gson
import com.webianks.nasapicturesapp.data.NasaPicture
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilTest {

    @Test
    fun testJsonToNasaPicturesList(){

        val jsonText = "[\n" +
                "            {\n" +
                "                \"copyright\": \"ESA/HubbleNASA\",\n" +
                "                \"date\": \"2019-12-01\",\n" +
                "                \"explanation\": \"Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library\",\n" +
                "                \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg\",\n" +
                "                \"media_type\": \"image\",\n" +
                "                \"service_version\": \"v1\",\n" +
                "                \"title\": \"Starburst Galaxy M94 from Hubble\",\n" +
                "                \"url\": \"https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"copyright\": \"Steve Mazlin\",\n" +
                "                \"date\": \"2019-12-03\",\n" +
                "                \"explanation\": \"Is this what will become of our Sun? Quite possibly.  The first hint of our Sun's future was discovered inadvertently in 1764. At that time, Charles Messier was compiling a list of diffuse objects not to be confused with comets. The 27th object on Messier's list, now known as M27 or the Dumbbell Nebula, is a planetary nebula, the type of nebula our Sun will produce when nuclear fusion stops in its core. M27 is one of the brightest planetary nebulae on the sky, and can be seen toward the constellation of the Fox (Vulpecula) with binoculars. It takes light about 1000 years to reach us from M27, featured here in colors emitted by hydrogen and oxygen. Understanding the physics and significance of M27 was well beyond 18th century science. Even today, many things remain mysterious about bipolar planetary nebula like M27, including the physical mechanism that expels a low-mass star's gaseous outer-envelope, leaving an X-ray hot white dwarf.   APOD across world languages: Arabic, Catalan, Chinese (Beijing), Chinese (Taiwan), Croatian, Czech, Dutch, Farsi, French, French, German, Hebrew, Indonesian, Japanese, Korean, Montenegrin, Polish, Russian, Serbian, Slovenian,  Spanish and Ukrainian\",\n" +
                "                \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg\",\n" +
                "                \"media_type\": \"image\",\n" +
                "                \"service_version\": \"v1\",\n" +
                "                \"title\": \"M27: The Dumbbell Nebula\",\n" +
                "                \"url\": \"https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg\"\n" +
                "            }\n" +
                "        ]"

        val list = jsonText.toNasaPicturesList(Gson())
        assert(list.size == 2)
        assert(list is List<NasaPicture>)
        assert(list[0] is NasaPicture)
    }
}