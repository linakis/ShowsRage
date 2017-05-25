package com.mgaetan89.showsrage.extension.realm

import android.support.test.annotation.UiThreadTest
import android.support.test.runner.AndroidJUnit4
import com.mgaetan89.showsrage.extension.getShowsStats
import io.realm.RealmChangeListener
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RealmExtension_GetShowsStatsTest : RealmTest() {
    @Before
    fun before() {
        this.realm.isAutoRefresh = true
    }

    @Test
    @UiThreadTest
    fun getShowsStats() {
        this.realm.getShowsStats(RealmChangeListener {
            it.removeAllChangeListeners()

            assertThat(it).hasSize(1)
            assertThat(it.first().episodesDownloaded).isEqualTo(4977)
            assertThat(it.first().episodesSnatched).isEqualTo(786)
            assertThat(it.first().episodesTotal).isEqualTo(7978)
            assertThat(it.first().showsActive).isEqualTo(42)
            assertThat(it.first().showsTotal).isEqualTo(83)
        })
    }
}