package com.mgaetan89.showsrage.extension.preferences

import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.mgaetan89.showsrage.TestActivity
import com.mgaetan89.showsrage.extension.Fields
import com.mgaetan89.showsrage.extension.getPreferences
import com.mgaetan89.showsrage.extension.getShowsFilterState
import com.mgaetan89.showsrage.extension.saveShowsFilter
import com.mgaetan89.showsrage.model.ShowsFilters
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesExtension_GetShowsFilterStateTest {
	@JvmField
	@Rule
	val activityRule = ActivityTestRule(TestActivity::class.java, false, false)

	private lateinit var preference: SharedPreferences

	@Before
	fun before() {
		this.preference = InstrumentationRegistry.getTargetContext().getPreferences()
	}

	@Test
	fun getShowsFilterState_Active() {
		this.preference.saveShowsFilter(ShowsFilters.State.ACTIVE, 0)

		val showsFilterState = this.preference.getShowsFilterState()

		assertThat(showsFilterState).isEqualTo(ShowsFilters.State.ACTIVE)
	}

	@Test
	fun getShowsFilterState_All() {
		this.preference.saveShowsFilter(ShowsFilters.State.ALL, 0)

		val showsFilterState = this.preference.getShowsFilterState()

		assertThat(showsFilterState).isEqualTo(ShowsFilters.State.ALL)
	}

	@Test
	fun getShowsFilterState_Empty() {
		this.preference.edit().putString(Fields.SHOW_FILTER_STATE.field, "").apply()

		val showsFilterState = this.preference.getShowsFilterState()

		assertThat(showsFilterState).isEqualTo(ShowsFilters.State.ALL)
	}

	@Test
	fun getShowsFilterState_Missing() {
		assertThat(this.preference.contains(Fields.SHOW_FILTER_STATE.field)).isFalse()

		val showsFilterState = this.preference.getShowsFilterState()

		assertThat(showsFilterState).isEqualTo(ShowsFilters.State.ALL)
	}

	@Test
	fun getShowsFilterState_Null() {
		this.preference.edit().putString(Fields.SHOW_FILTER_STATE.field, null).apply()

		val showsFilterState = this.preference.getShowsFilterState()

		assertThat(showsFilterState).isEqualTo(ShowsFilters.State.ALL)
	}

	@Test
	fun getShowsFilterState_Paused() {
		this.preference.saveShowsFilter(ShowsFilters.State.PAUSED, 0)

		val showsFilterState = this.preference.getShowsFilterState()

		assertThat(showsFilterState).isEqualTo(ShowsFilters.State.PAUSED)
	}

	@After
	fun after() {
		this.preference.edit().clear().apply()
	}
}
