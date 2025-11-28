//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.request.handler;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class HTTPRequester$2 implements HostnameVerifier {
	HTTPRequester$2(HTTPRequester var1) {
		this.this$0 = var1;
	}

	public boolean verify(String hostname, SSLSession session) {
		return true;
	}
}
