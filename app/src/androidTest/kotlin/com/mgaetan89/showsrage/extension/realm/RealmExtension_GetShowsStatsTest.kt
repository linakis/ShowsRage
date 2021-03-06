package com.mgaetan89.showsrage.extension.realm

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
	fun getShowsStats() {
		this.realm.getShowsStats(RealmChangeListener {
			it.removeAllChangeListeners()

			assertThat(it).hasSize(1)
			assertThat(it[0]!!.episodesDownloaded).isEqualTo(4977)
			assertThat(it[0]!!.episodesSnatched).isEqualTo(786)
			assertThat(it[0]!!.episodesTotal).isEqualTo(7978)
			assertThat(it[0]!!.showsActive).isEqualTo(42)
			assertThat(it[0]!!.showsTotal).isEqualTo(83)
		})
	}
}
