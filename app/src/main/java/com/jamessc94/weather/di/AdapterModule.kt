package com.jamessc94.weather.di

import android.content.Context
import com.jamessc94.weather.ui.adapter.AdapDash
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

//@Module
//@InstallIn(ActivityComponent::class)
//object AdapterModule {
//
//    @Provides
//    @ActivityScoped
//    fun provideAdapterFragmentState(@ActivityContext context: Context) : AdapDash {
//        return AdapDash(context)
//
//    }
//
//}