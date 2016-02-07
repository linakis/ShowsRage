package com.mgaetan89.showsrage.db_model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Quality extends RealmObject {
	private RealmList<RealmString> archive = null;
	private RealmList<RealmString> initial = null;

	public RealmList<RealmString> getArchive() {
		return this.archive;
	}

	public RealmList<RealmString> getInitial() {
		return this.initial;
	}

	public void setArchive(RealmList<RealmString> archive) {
		this.archive = archive;
	}

	public void setInitial(RealmList<RealmString> initial) {
		this.initial = initial;
	}
}