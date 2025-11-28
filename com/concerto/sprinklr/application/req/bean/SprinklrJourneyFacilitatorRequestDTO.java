//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.req.bean;

import java.util.List;

public class SprinklrJourneyFacilitatorRequestDTO {
	private RequestString requestString;
	private List<HeaderParam> headerParams;

	public SprinklrJourneyFacilitatorRequestDTO() {
	}

	public RequestString getRequestString() {
		return this.requestString;
	}

	public List<HeaderParam> getHeaderParams() {
		return this.headerParams;
	}

	public void setRequestString(RequestString requestString) {
		this.requestString = requestString;
	}

	public void setHeaderParams(List<HeaderParam> headerParams) {
		this.headerParams = headerParams;
	}

	public String toString() {
		return "SprinklrJourneyFacilitatorRequestDTO [requestString=" + this.requestString + ", headerParams=" + this.headerParams + "]";
	}
}
