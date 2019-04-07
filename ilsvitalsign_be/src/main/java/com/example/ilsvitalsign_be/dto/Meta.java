package com.example.ilsvitalsign_be.dto;

import java.io.Serializable;

public class Meta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String versionId;
	private String lastUpdated;

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "Meta [versionId=" + versionId + ", lastUpdated=" + lastUpdated + "]";
	}

}
