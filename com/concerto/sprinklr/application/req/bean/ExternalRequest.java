//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.req.bean;

import org.codehaus.jackson.annotate.JsonProperty;

public class ExternalRequest {
	private SprinklrJourneyFacilitatorRequestDTO SprinklrJourneyFacilitatorRequestDTO;
	private SessionContext sessionContext;

	public ExternalRequest() {
	}

	public SprinklrJourneyFacilitatorRequestDTO getSprinklrJourneyFacilitatorRequestDTO() {
		return this.SprinklrJourneyFacilitatorRequestDTO;
	}

	@JsonProperty("SprinklrJourneyFacilitatorRequestDTO")
	public void setSprinklrJourneyFacilitatorRequestDTO(SprinklrJourneyFacilitatorRequestDTO SprinklrJourneyFacilitatorRequestDTO) {
		this.SprinklrJourneyFacilitatorRequestDTO = SprinklrJourneyFacilitatorRequestDTO;
	}

	public SessionContext getSessionContext() {
		return this.sessionContext;
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	public String toString() {
		return "ExternalRequest [SprinklrJourneyFacilitatorRequestDTO=" + this.SprinklrJourneyFacilitatorRequestDTO + ", sessionContext=" + this.sessionContext + "]";
	}
}
