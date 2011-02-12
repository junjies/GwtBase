package com.jakewharton.gwtbase.shared;

import java.util.logging.Logger;

public final class LogUtility {
	private LogUtility() {}
	
	/**
	 * <p>Gets an instance of Logger using only the class name.</p>
	 * <p>E.g., <code>com.jakewharton.gwtbase.SomeClass</code> becomes <code>SomeClass</code>.</p>
	 * <p>This operates under the assumption that your logging can tolerate two classes receiving
	 * the same <code>Logger</code> instance if they have the same class name but exist in two
	 * separate packages. If your implementation cannot tolerate this then call <code>Logger</code>'s
	 * <code>getLogger()</code> method directly.</p>
	 * 
	 * @param cls Target class.
	 * @return Logger instance.
	 */
	public static Logger get(Class<?> cls) {
		String className = cls.getName();
		try {
			int lastDot = className.lastIndexOf('.');
			return Logger.getLogger(className.substring(lastDot + 1));
		} catch (IndexOutOfBoundsException e) {
			return Logger.getLogger(className);
		}
	}
}
