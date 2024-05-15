package com.alex.springmongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	public static String decodeParam(String toDecode) {
		try {
			return URLDecoder.decode(toDecode, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return (" ");
		}
	}
}
