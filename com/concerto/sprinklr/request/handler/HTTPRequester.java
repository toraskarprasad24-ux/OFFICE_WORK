//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.request.handler;

import com.concerto.sprinklr.application.init.ServerInitializer;
import com.concerto.sprinklr.application.util.MaskSensitiveFields;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPRequester {
	static Logger logger = LoggerFactory.getLogger("AppLogger");

	public HTTPRequester() {
	}

	public String postData(String externalRequest, String maskExternalRequest, String messageId) {
		String line = "";
		HttpURLConnection httpCon = null;
		HttpsURLConnection httpsCon = null;
		StringBuffer sb = null;
		OutputStream os = null;
		BufferedReader bufferedReader = null;
		DataInputStream inputStream = null;
		if (ServerInitializer.reqLogMaskingEnable == 1) {
			logger.info("Masked Request for message id : " + messageId + " --> " + maskExternalRequest);
		} else {
			logger.info("Actual Request for message id : " + messageId + " --> " + externalRequest);
		}

		String var76;
		try {
			if (!ServerInitializer.requestURL.startsWith("http://")) {
				if (!ServerInitializer.requestURL.startsWith("https://")) {
					return line;
				}

				if (ServerInitializer.CertificateBypassEnable == 1) {
					TrustManager x509 = new X509TrustManager() {
						public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
						}

						public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
						}

						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}
					};
					SSLContext ctx = null;
					ctx = SSLContext.getInstance("SSL");
					ctx.init((KeyManager[])null, new TrustManager[]{x509}, (SecureRandom)null);
					HostnameVerifier allHostsValid = new HostnameVerifier() {
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					};
					HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
					HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
				}

				URL url = new URL(ServerInitializer.requestURL);
				httpsCon = (HttpsURLConnection)url.openConnection();
				httpsCon.setRequestMethod(ServerInitializer.httprequestMethod);
				httpsCon.setConnectTimeout(ServerInitializer.connectionTimeOut);
				httpsCon.setReadTimeout(ServerInitializer.readTimeOut);
				httpsCon.setDoOutput(true);
				httpsCon.setDoInput(true);
				httpsCon.setAllowUserInteraction(false);
				Map<String, String> map = new HashMap();
				String[] entries = ServerInitializer.requestHeaders.split(ServerInitializer.headerSeparator);

				for(String entry : entries) {
					String[] keyValue = entry.split(":::");
					map.put(keyValue[0], keyValue[1]);
				}

				for(Map.Entry<String, String> entry1 : map.entrySet()) {
					httpsCon.setRequestProperty((String)entry1.getKey(), (String)entry1.getValue());
				}

				logger.trace("Request Sent to API for message id : " + messageId);
				os = httpsCon.getOutputStream();
				os.write(externalRequest.getBytes());
				os.flush();
				if (httpsCon.getResponseCode() != 200) {
					logger.error("Failed : HTTP error code : " + httpsCon.getResponseCode() + " for message id : " + messageId);
					var76 = "Failed : HTTP error code : " + httpsCon.getResponseCode();
					return var76;
				}

				if (!httpsCon.getContentType().equalsIgnoreCase("application/json")) {
					logger.error("Failed : Invalid Content-Type : " + httpsCon.getContentType() + " for message id : " + messageId);
					var76 = "Failed : Invalid Content-Type : " + httpsCon.getContentType();
					return var76;
				}

				inputStream = new DataInputStream(httpsCon.getInputStream());
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				sb = new StringBuffer();
				String line1 = "";

				while((line1 = bufferedReader.readLine()) != null) {
					sb.append(line1);
				}

				line = httpsCon.getResponseCode() + sb.toString();
				logger.trace("Response received from API for message id : " + messageId);
				if (ServerInitializer.resLogMaskingEnable == 1) {
					if (!ServerInitializer.responseSensitiveFields.isEmpty()) {
						logger.info("Masked Acknowledgement for message id : " + messageId + " --> " + MaskSensitiveFields.getKeysAndPosition(line.substring(line.indexOf("{"), line.length()), messageId, ServerInitializer.responseSensitiveFieldsList));
					} else {
						logger.info("Actual Acknowledgement for message id : " + messageId + " --> " + line);
					}

					return line;
				} else {
					logger.info("Actual Acknowledgement for message id : " + messageId + " --> " + line);
					return line;
				}
			}

			URL url = new URL(ServerInitializer.requestURL);
			httpCon = (HttpURLConnection)url.openConnection();
			httpCon.setRequestMethod(ServerInitializer.httprequestMethod);
			httpCon.setDoOutput(true);
			httpCon.setDoInput(true);
			httpCon.setAllowUserInteraction(false);
			httpCon.setConnectTimeout(ServerInitializer.connectionTimeOut);
			httpCon.setReadTimeout(ServerInitializer.readTimeOut);
			Map<String, String> map = new HashMap();
			String[] entries = ServerInitializer.requestHeaders.split(ServerInitializer.headerSeparator);

			for(String entry : entries) {
				String[] keyValue = entry.split(":::");
				map.put(keyValue[0], keyValue[1]);
			}

			for(Map.Entry<String, String> entry1 : map.entrySet()) {
				httpCon.setRequestProperty((String)entry1.getKey(), (String)entry1.getValue());
			}

			os = httpCon.getOutputStream();
			os.write(externalRequest.getBytes());
			os.flush();
			logger.trace("Request Sent to API for message id : " + messageId);
			if (httpCon.getResponseCode() == 200) {
				if (!httpCon.getContentType().equalsIgnoreCase("application/json")) {
					logger.error("Failed : Invalid Content-Type : " + httpCon.getContentType() + " for message id : " + messageId);
					var76 = "Failed : Invalid Content-Type : " + httpCon.getContentType();
					return var76;
				}

				inputStream = new DataInputStream(httpCon.getInputStream());
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				sb = new StringBuffer();
				String line1 = "";

				while((line1 = bufferedReader.readLine()) != null) {
					sb.append(line1);
				}

				line = httpCon.getResponseCode() + sb.toString();
				logger.trace("Response received from API for message id : " + messageId);
				if (ServerInitializer.resLogMaskingEnable == 1) {
					if (!ServerInitializer.responseSensitiveFields.isEmpty()) {
						logger.info("Masked Acknowledgement for message id : " + messageId + " --> " + MaskSensitiveFields.getKeysAndPosition(line.substring(line.indexOf("{"), line.length()), messageId, ServerInitializer.responseSensitiveFieldsList));
					} else {
						logger.info("Actual Acknowledgement for message id : " + messageId + " --> " + line);
					}

					return line;
				} else {
					logger.info("Actual Acknowledgement for message id : " + messageId + " --> " + line);
					return line;
				}
			}

			logger.error("Failed : HTTP error code : " + httpCon.getResponseCode() + " for message id : " + messageId);
			var76 = "Failed : HTTP error code : " + httpCon.getResponseCode();
		} catch (MalformedURLException e) {
			logger.error("Malformed URL Exception has occured for message id : " + messageId + ",", e);
			e.printStackTrace();
			var76 = e.getMessage();
			return var76;
		} catch (ProtocolException e) {
			logger.error("Protocol Exception has occured for message id : " + messageId + ",", e);
			e.printStackTrace();
			var76 = e.getMessage();
			return var76;
		} catch (SocketTimeoutException e) {
			logger.error("Socket Timeout Exception has occured for message id : " + messageId + ",", e);
			e.printStackTrace();
			var76 = e.getMessage();
			return var76;
		} catch (IOException e) {
			logger.error("IO Exception has occured for message id : " + messageId + ",", e);
			e.printStackTrace();
			var76 = e.getMessage();
			return var76;
		} catch (Exception e) {
			logger.error("Exception has occured for message id : " + messageId + ",", e);
			e.printStackTrace();
			var76 = e.getMessage();
			return var76;
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error("IOException has occured for message id : " + messageId + ",", e);
				e.printStackTrace();
				return e.getMessage();
			}

			if (httpCon != null) {
				HttpURLConnection var46 = null;
			} else if (httpsCon != null) {
				HttpsURLConnection var47 = null;
			}

		}

		return var76;
	}
}
