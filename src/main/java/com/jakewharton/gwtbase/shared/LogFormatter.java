package com.jakewharton.gwtbase.shared;

import java.util.Date;
import java.util.logging.LogRecord;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.logging.impl.FormatterImpl;

public class LogFormatter extends FormatterImpl {
	private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
	
	private final boolean showStackTraces;

	public LogFormatter(boolean showStackTraces) {
		this.showStackTraces = showStackTraces;
	}

	@Override
	protected String getRecordInfo(LogRecord event, String newline) {
		Date date = new Date(event.getMillis());
		StringBuilder s = new StringBuilder();
		s.append(LogFormatter.DATE_FORMAT.format(date));
		s.append(" [");
		s.append(event.getLevel().getName());
		s.append("] ");
		s.append(event.getLoggerName());
		s.append(newline);
		s.append(": ");
		return s.toString();
	}

	@Override
	public String format(LogRecord event) {
		StringBuilder message = new StringBuilder();
		message.append(this.getRecordInfo(event, ""));
		message.append(event.getMessage());
		if (this.showStackTraces) {
			message.append(this.getStackTraceAsString(event.getThrown(), "\n", "\t"));
		}
		return message.toString();
	}
}