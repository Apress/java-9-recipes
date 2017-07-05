package org.java9recipes.chapter21.recipe21_06;

import java.io.IOException;

import java.net.URL;

/**
 * Recipe 23-6
 * 
 * Parsing a URL
 * 
 * @author juneau
 */
public class ParseUrl {

	public static void main(String[] args) {
		URL url1 = null;
		URL url2 = null;
		try {
			// Generate absolute URL
			url1 = new URL("http://www.apress.com/catalogsearch/result/?q=juneau");

			String host = url1.getHost();
			String path = url1.getPath();
			String query = url1.getQuery();
			String protocol = url1.getProtocol();
			String authority = url1.getAuthority();
			String ref = url1.getRef();

			System.out.println("The URL " + url1.toString() + " parses to the following:\n");
			System.out.println("Host: " + host + "\n");
			System.out.println("Path: " + path + "\n");
			System.out.println("Query: " + query + "\n");
			System.out.println("Protocol: " + protocol + "\n");
			System.out.println("Authority: " + authority + "\n");
			System.out.println("Reference: " + ref + "\n");

			url2 = new URL(protocol + "://" + host + path + "?q=java");
			System.out.println(url2);

		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}
}
