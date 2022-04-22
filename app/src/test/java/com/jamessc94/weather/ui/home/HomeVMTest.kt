package com.jamessc94.weather.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import com.jamessc94.weather.model.Weather
import com.jamessc94.weather.persistence.WeatherDAO
import com.jamessc94.weather.repo.HomeRepo
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

//@Config(manifest = "src/main/AndroidManifest.xml")
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
class HomeVMTest : TestCase() {

//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltrule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: HomeRepo

    private val dao: WeatherDAO = mockk(relaxed = true)
    private lateinit var viewModel: HomeVM

    @Before
    fun setup(){
        hiltrule.inject()

        repository = HomeRepo()
//        viewModel = HomeVM(dao, repository, ApplicationProvider.getApplicationContext())

    }

    @Test
    fun `Random Test`(){
        val input = "123"
        val expected: LiveData<List<Weather>> = mockk(relaxed = true)
        every { dao.getWeather(input) } returns expected

        val result = viewModel.getWeatherBy(input)

//        verify(exactly = 1){ dao.getWeather(input) }
//        confirmVerified(dao)
        Assert.assertEquals(expected.value,  result.value)

    }

}