package com.mgaetan89.showsrage.presenter

import com.mgaetan89.showsrage.model.History
import com.mgaetan89.showsrage.model.ImageType
import com.mgaetan89.showsrage.model.Indexer
import com.mgaetan89.showsrage.network.SickRageApi

class HistoryPresenter(val history: History?) {
	fun getEpisode() = if (this.isHistoryValid()) this.history!!.episode else 0

	fun getPosterUrl() = if (this.isHistoryValid()) SickRageApi.instance.getImageUrl(ImageType.POSTER, this.history!!.tvDbId, Indexer.TVDB) else ""

	fun getProvider() = if (this.isHistoryValid()) this.history!!.provider else ""

	fun getProviderQuality() = if (this.isHistoryValid() && "-1" == this.history!!.provider) this.history.quality else null

	fun getQuality() = if (this.isHistoryValid()) this.history!!.quality else ""

	fun getSeason() = if (this.isHistoryValid()) this.history!!.season else 0

	fun getShowName() = if (this.isHistoryValid()) this.history!!.showName else ""

	internal fun isHistoryValid() = this.history != null && this.history.isValid
}
