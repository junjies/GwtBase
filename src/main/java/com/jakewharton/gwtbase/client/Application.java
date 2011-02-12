package com.jakewharton.gwtbase.client;

public class Application {
	protected static boolean isMobile = false;
	
	public static boolean isMobile() {
		return isMobile;
	}
	
	public void run() {}
	
	/**
	 * Redirect entire browser window to another URL.
	 * 
	 * @param url URL
	 */
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;
}