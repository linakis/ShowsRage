package com.mgaetan89.showsrage.fragment

import com.mgaetan89.showsrage.R
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SettingsServerApiKeyFragmentTest {
	@Test
	fun getTitleResourceId() {
		assertThat(SettingsServerApiKeyFragment.newInstance().getTitleResourceId()).isEqualTo(R.string.api_key)
	}

	@Test
	fun getXmlResourceFile() {
		assertThat(SettingsServerApiKeyFragment.newInstance().getXmlResourceFile()).isEqualTo(R.xml.settings_server_api_key)
	}
}
