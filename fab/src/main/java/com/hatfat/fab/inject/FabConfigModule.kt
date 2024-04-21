package com.hatfat.swccg.inject

import com.hatfat.fab.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object FabConfigModule {
    @Provides
    @Named("about resource list")
    fun providesAboutAdapterResources(): List<Int> {
        return ArrayList<Int>().apply {
            this.add(R.string.cards_about_desc_line)
            this.add(R.string.cards_about_contact_line)
            this.add(R.string.cards_about_swccg)
            this.add(R.string.cards_about_affiliation)
        }
    }
}
