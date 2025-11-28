//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.req.bean;

public class RequestString {
	private String journeyId;
	private ContextParams contextParams;
	private UnifiedProfile unifiedProfile;

	public RequestString() {
	}

	public String getJourneyId() {
		return this.journeyId;
	}

	public ContextParams getContextParams() {
		return this.contextParams;
	}

	public UnifiedProfile getUnifiedProfile() {
		return this.unifiedProfile;
	}

	public void setJourneyId(String journeyId) {
		this.journeyId = journeyId;
	}

	public void setContextParams(ContextParams contextParams) {
		this.contextParams = contextParams;
	}

	public void setUnifiedProfile(UnifiedProfile unifiedProfile) {
		this.unifiedProfile = unifiedProfile;
	}

	public String toString() {
		return "RequestString [journeyId=" + this.journeyId + ", contextParams=" + this.contextParams + ", unifiedProfile=" + this.unifiedProfile + "]";
	}
}
