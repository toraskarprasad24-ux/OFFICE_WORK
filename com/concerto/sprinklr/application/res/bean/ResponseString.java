//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.res.bean;

import java.util.List;

public class ResponseString {
	private Data data;
	private List<String> errors;

	public ResponseString() {
	}

	public Data getData() {
		return this.data;
	}

	public List<String> getErrors() {
		return this.errors;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
