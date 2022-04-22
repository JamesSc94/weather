package com.jamessc94.weather.ui.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jamessc94.weather.R
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragTest {

    @Test
    fun testNavigationToHomeScreen(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val titleScenario = launchFragmentInContainer<HomeFrag>()

        titleScenario.onFragment { frag ->
            navController.setGraph(R.navigation.main)

            Navigation.setViewNavController(frag.requireView(), navController)

        }

    }

}