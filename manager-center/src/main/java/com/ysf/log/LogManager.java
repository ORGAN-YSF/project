package com.ysf.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LogManager {
	public static LogManager logManager = new LogManager();

	//异步操作记录日志的线程池
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

	//日志记录操作延时
	private final int OPERATE_DELAY_TIME = 10;

	public static LogManager me() {
		return logManager;
	}

	public void executeLog(TimerTask task) {
		executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}
}
