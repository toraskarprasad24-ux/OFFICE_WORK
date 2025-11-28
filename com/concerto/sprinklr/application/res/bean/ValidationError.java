//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.res.bean;

import java.math.BigDecimal;
import java.util.List;

public class ValidationError {
	private String objectName;
	private String attributeName;
	private String attributeValue;
	private String errorCode;
	private String errorMessage;
	private String methodName;
	private String applicableAttributes;
	private List<String> errorMessageParams;
	private BigDecimal associatedSeverity;

	public ValidationError() {
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return this.attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getApplicableAttributes() {
		return this.applicableAttributes;
	}

	public void setApplicableAttributes(String applicableAttributes) {
		this.applicableAttributes = applicableAttributes;
	}

	public List<String> getErrorMessageParams() {
		return this.errorMessageParams;
	}

	public void setErrorMessageParams(List<String> errorMessageParams) {
		this.errorMessageParams = errorMessageParams;
	}

	public BigDecimal getAssociatedSeverity() {
		return this.associatedSeverity;
	}

	public void setAssociatedSeverity(BigDecimal associatedSeverity) {
		this.associatedSeverity = associatedSeverity;
	}
}
