package com.export.base.log;

public class Logger {
	public synchronized static void log(String message) {
		System.out.println(message);
	}
	public synchronized static void log(Exception e) {
		e.printStackTrace();
	}
}
