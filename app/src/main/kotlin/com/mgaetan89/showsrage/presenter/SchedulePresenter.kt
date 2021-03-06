package com.mgaetan89.showsrage.presenter

import android.content.Context
import android.text.format.DateUtils
import com.mgaetan89.showsrage.extension.getLocalizedTime
import com.mgaetan89.showsrage.extension.toRelativeDate
import com.mgaetan89.showsrage.model.ImageType
import com.mgaetan89.showsrage.model.Indexer
import com.mgaetan89.showsrage.model.Schedule
import com.mgaetan89.showsrage.network.SickRageApi

class SchedulePresenter(val schedule: Schedule?, val context: Context?) {
	private fun getAirDate(): CharSequence? {
		val schedule = this._getSchedule() ?: return null
		val airDate = schedule.airDate

		if (airDate.isEmpty()) {
			return null
		}

		return airDate.toRelativeDate("yyyy-MM-dd", DateUtils.DAY_IN_MILLIS)
	}

	fun getAirDateTime(): CharSequence? {
		val airDate = this.getAirDate()
		val airTime = this.getAirTime()

		if (airDate == null && airTime == null) {
			return null
		}

		if (airDate != null) {
			return if (airTime != null) "$airDate, $airTime" else airDate
		}

		return airTime
	}

	private fun getAirTime(): CharSequence? {
		val schedule = this._getSchedule() ?: return null

		if (this.context == null) {
			return null
		}

		val airDate = schedule.airDate
		val airTime = this.getAirTimeOnly()

		if (airDate.isEmpty() || airTime.isNullOrEmpty()) {
			return null
		}

		return this.context.getLocalizedTime("$airDate $airTime", "yyyy-MM-dd K:mm a")
	}

	fun getEpisode() = this._getSchedule()?.episode ?: 0

	fun getNetwork() = this._getSchedule()?.network ?: ""

	fun getPosterUrl(): String {
		val schedule = this._getSchedule() ?: return ""

		return SickRageApi.instance.getImageUrl(ImageType.POSTER, schedule.tvDbId, Indexer.TVDB)
	}

	fun getQuality() = this._getSchedule()?.quality ?: ""

	fun getSeason() = this._getSchedule()?.season ?: 0

	fun getShowName() = this._getSchedule()?.showName ?: ""

	internal fun getAirTimeOnly(): String? {
		val schedule = this._getSchedule() ?: return null
		val airTime = schedule.airs

		if (airTime.isEmpty()) {
			return null
		}

		return airTime.replaceFirst("(?i)^(monday|tuesday|wednesday|thursday|friday|saturday|sunday) ".toRegex(), "")
	}

	internal fun _getSchedule() = if (this.schedule?.isValid == true) this.schedule else null
}
