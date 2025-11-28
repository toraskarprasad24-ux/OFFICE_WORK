//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.res.bean;

import java.util.List;

public class Status {
	private boolean isOverriden;
	private int replyCode;
	private String replyText;
	private String memo;
	private String externalReferenceNo;
	private String internalReferenceNumber;
	private PostingDate postingDate;
	private String errorCode;
	private ExtendedReply extendedReply;
	private List<ValidationError> validationErrors;
	private String userReferenceNumber;

	public Status() {
	}

	public boolean isOverriden() {
		return this.isOverriden;
	}

	public int getReplyCode() {
		return this.replyCode;
	}

	public String getReplyText() {
		return this.replyText;
	}

	public String getMemo() {
		return this.memo;
	}

	public String getExternalReferenceNo() {
		return this.externalReferenceNo;
	}

	public String getInternalReferenceNumber() {
		return this.internalReferenceNumber;
	}

	public PostingDate getPostingDate() {
		return this.postingDate;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public ExtendedReply getExtendedReply() {
		return this.extendedReply;
	}

	public List<ValidationError> getValidationErrors() {
		return this.validationErrors;
	}

	public String getUserReferenceNumber() {
		return this.userReferenceNumber;
	}

	public void setOverriden(boolean isOverriden) {
		this.isOverriden = isOverriden;
	}

	public void setReplyCode(int replyCode) {
		this.replyCode = replyCode;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setExternalReferenceNo(String externalReferenceNo) {
		this.externalReferenceNo = externalReferenceNo;
	}

	public void setInternalReferenceNumber(String internalReferenceNumber) {
		this.internalReferenceNumber = internalReferenceNumber;
	}

	public void setPostingDate(PostingDate postingDate) {
		this.postingDate = postingDate;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setExtendedReply(ExtendedReply extendedReply) {
		this.extendedReply = extendedReply;
	}

	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public void setUserReferenceNumber(String userReferenceNumber) {
		this.userReferenceNumber = userReferenceNumber;
	}
}
