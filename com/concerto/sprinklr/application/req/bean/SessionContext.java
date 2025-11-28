//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.req.bean;

import org.codehaus.jackson.annotate.JsonProperty;

public class SessionContext {
	private String channel;
	@JsonProperty("bankCode")
	private String bankCode;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("externalReferenceNo")
	private String externalReferenceNo;
	@JsonProperty("transactionBranch")
	private String transactionBranch;
	@JsonProperty("transactingPartyCode")
	private String transactingPartyCode;

	public SessionContext() {
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExternalReferenceNo() {
		return this.externalReferenceNo;
	}

	public void setExternalReferenceNo(String externalReferenceNo) {
		this.externalReferenceNo = externalReferenceNo;
	}

	public String getTransactionBranch() {
		return this.transactionBranch;
	}

	public void setTransactionBranch(String transactionBranch) {
		this.transactionBranch = transactionBranch;
	}

	public String getTransactingPartyCode() {
		return this.transactingPartyCode;
	}

	public void setTransactingPartyCode(String transactingPartyCode) {
		this.transactingPartyCode = transactingPartyCode;
	}

	public String toString() {
		return "SessionContext [channel=" + this.channel + ", bankCode=" + this.bankCode + ", userId=" + this.userId + ", externalReferenceNo=" + this.externalReferenceNo + ", transactionBranch=" + this.transactionBranch + ", transactingPartyCode=" + this.transactingPartyCode + "]";
	}
}
