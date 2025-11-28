//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.jdbc.service;

public class DbProperty {
	private String driver;
	private String username;
	private String imp;
	private String url;

	public DbProperty(String driver, String username, String imp, String url) {
		this.driver = driver;
		this.username = username;
		this.imp = imp;
		this.url = url;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImp() {
		return this.imp;
	}

	public void setImp(String imp) {
		this.imp = imp;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
