//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.util;

import com.concerto.sprinklr.application.init.ServerInitializer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaskSensitiveFields {
	public static Logger logger = LoggerFactory.getLogger("AppLogger");

	public MaskSensitiveFields() {
	}

	public static String getKeysAndPosition(String externalRequest, String messageId, List<String> sensitiveFieldsList) {
		JSONObject jsonObject = new JSONObject(externalRequest);

		try {
			List<String> keysAndPositionsToMask = new ArrayList();

			for(String field : sensitiveFieldsList) {
				String[] splitedValues = field.trim().split(ServerInitializer.fieldSeprator);
				String OrignalField = splitedValues[0];
				int Startposition = Integer.parseInt(splitedValues[1]);
				int endPosition = Integer.parseInt(splitedValues[2]);
				keysAndPositionsToMask.add(OrignalField + ":" + Startposition + "-" + endPosition);
			}

			maskKeys(jsonObject, keysAndPositionsToMask, messageId);
		} catch (Exception e) {
			logger.error("Exception occured while masking fields for message id : " + messageId + ",", e);
		}

		return jsonObject.toString();
	}

	private static void maskKeys(JSONObject jsonObject, List<String> keysAndPositionsToMask, String messageId) throws Exception {
		for(String keyAndPosition : keysAndPositionsToMask) {
			String[] parts = keyAndPosition.split(":");
			if (parts.length != 2) {
				logger.error("Invalid format for key and position: " + keyAndPosition + " for message id : " + messageId);
			} else {
				String[] keyParts = parts[0].split("\\.");
				String key = keyParts[keyParts.length - 1];
				traverseJSONObject(jsonObject, keyParts, 0, key, parts[1]);
			}
		}

	}

	private static void traverseJSONObject(Object object, String[] keyParts, int index, String targetKey, String positions) throws Exception {
		if (index == keyParts.length - 1) {
			maskValue(object, targetKey, positions);
		} else if (object instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject)object;
			if (jsonObject.has(keyParts[index])) {
				traverseJSONObject(jsonObject.get(keyParts[index]), keyParts, index + 1, targetKey, positions);
			}
		} else if (object instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray)object;

			for(int i = 0; i < jsonArray.length(); ++i) {
				traverseJSONObject(jsonArray.get(i), keyParts, index, targetKey, positions);
			}
		}

	}

	private static void maskValue(Object object, String key, String positions) throws Exception {
		Object value = null;

		try {
			if (object instanceof JSONObject) {
				JSONObject jsonObject = (JSONObject)object;
				if (jsonObject.has(key)) {
					value = jsonObject.get(key);
					if (value instanceof String) {
						String originalValue = (String)value;
						String maskedValue = maskSubstring(originalValue, positions);
						jsonObject.put(key, maskedValue);
					} else if (value instanceof Integer) {
						Integer originalValue = (Integer)value;
						String maskedValue = maskSubstring(originalValue.toString(), positions);
						jsonObject.put(key, maskedValue);
					} else if (value instanceof JSONArray) {
						JSONArray originalValue = (JSONArray)value;
						String maskedValue = maskSubstring(originalValue.toString(), positions);
						jsonObject.put(key, maskedValue);
					}
				}
			} else if (object instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray)object;

				for(int i = 0; i < jsonArray.length(); ++i) {
					maskValue(jsonArray.get(i), key, positions);
				}
			}

		} catch (Exception var7) {
			throw new Exception("Exception occured for field : " + key + " Value : " + value);
		}
	}

	private static String maskSubstring(String originalValue, String positions) {
		String[] positionArray = positions.split("-");
		int start = Integer.parseInt(positionArray[0]);
		int end = Integer.parseInt(positionArray[1]);
		StringBuilder maskedValue = null;
		if (originalValue != null && originalValue.length() > start + end) {
			maskedValue = new StringBuilder(originalValue);

			for(int i = start; i < originalValue.length() - end; ++i) {
				maskedValue.setCharAt(i, '*');
			}

			return maskedValue.toString();
		} else {
			return originalValue;
		}
	}
}
