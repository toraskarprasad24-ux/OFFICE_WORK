//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.res.bean;

import javax.net.ssl.SSLEngineResult;

public class Response {
	private SSLEngineResult.Status status;
	private String maintenanceType;
	private String configVersionId;
	private ResponseString responseString;

	public Response() {
	}

	public SSLEngineResult.Status getStatus() {
		return this.status;
	}

	public String getMaintenanceType() {
		return this.maintenanceType;
	}

	public String getConfigVersionId() {
		return this.configVersionId;
	}

	public ResponseString getResponseString() {
		return this.responseString;
	}

	public void setStatus(SSLEngineResult.Status status) {
		this.status = status;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public void setConfigVersionId(String configVersionId) {
		this.configVersionId = configVersionId;
	}

	public void setResponseString(ResponseString responseString) {
		this.responseString = responseString;
	}
}
