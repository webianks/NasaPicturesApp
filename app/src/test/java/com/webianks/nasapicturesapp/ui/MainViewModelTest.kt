package com.webianks.nasapicturesapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.data.PictureRepository
import com.webianks.nasapicturesapp.ui.main.MainViewModel
import com.webianks.nasapicturesapp.utils.Error
import com.webianks.nasapicturesapp.utils.Success
import com.webianks.nasapicturesapp.utils.UiState
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pictureRepository: PictureRepository

    @Mock
    private lateinit var pictureListUiStateObserver: Observer<UiState>

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {

        mainViewModel = MainViewModel(pictureRepository)
        mainViewModel.picturesListState.observeForever(pictureListUiStateObserver)
    }

    @Test
    fun testNull() {
        assertNotNull(mainViewModel.picturesListState)
        assertTrue(mainViewModel.picturesListState.hasObservers())
    }

    @Test
    fun testCallGetPicturesList() {

        `when`(pictureRepository.getPicturesList()).then {  }
        mainViewModel.getPicturesList()

        verify(pictureRepository).getPicturesList(any(), any())
    }

    @Test
     fun `test success state set`() {

        val nasaPicture = NasaPicture(
            copyright = "ESA/HubbleNASA",
            date = "2019-12-01",
            explanation = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
            hdUrl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
            mediaType = "image",
            serviceVersion = "v1",
            title = "Starburst Galaxy M94 from Hubble",
            url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
        )

        val success: ((List<NasaPicture>) -> Unit) = {

        }
        val error: ((Exception) -> Unit) = {

        }

        `when`(pictureRepository.getPicturesList(success, error)).then {
            val successInside = it.getArgument<(((List<NasaPicture>) -> Unit))>(0)
            successInside.invoke(listOf(nasaPicture))
        }

        mainViewModel.getPicturesList()

        verify(pictureRepository).getPicturesList(any(), any())
        //assert(mainViewModel.picturesListState.value == Success(listOf(nasaPicture)))
        verify(pictureListUiStateObserver).onChanged(Success(listOf(nasaPicture)))

     }

    @Test
    fun `test error state set`() {

        val success: ((List<NasaPicture>) -> Unit) = {

        }
        val error: ((Exception) -> Unit) = {

        }

        `when`(pictureRepository.getPicturesList(success, error)).then {
            val errorInside = it.getArgument<(((java.lang.Exception) -> Unit))>(1)
            errorInside.invoke(java.lang.Exception())
        }

        mainViewModel.getPicturesList()

        verify(pictureRepository).getPicturesList(any(), any())
        //assert(mainViewModel.picturesListState.value == Error(anyInt()))
        verify(pictureListUiStateObserver).onChanged(Error(anyInt()))

    }


    @After
    fun tearDown() {
        mainViewModel.picturesListState.removeObserver(pictureListUiStateObserver)
    }

}