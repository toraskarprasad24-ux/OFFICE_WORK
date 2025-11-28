//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.request.handler;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class HTTPRequester$1 implements X509TrustManager {
	HTTPRequester$1(HTTPRequester var1) {
		this.this$0 = var1;
	}

	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
