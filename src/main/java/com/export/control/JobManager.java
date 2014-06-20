package com.export.control;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.export.base.log.Logger;

public class JobManager {
	private Queue<Job> jobQueue = new ConcurrentLinkedQueue<Job>();
	private boolean switcher = false;
	private Thread jobScheduleThread;
	private ExecutorService executorService;
	private final int executorThreadCount = 5;
	
	public JobManager() {
		executorService = Executors.newFixedThreadPool(executorThreadCount);
		jobScheduleThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(switcher) {
						Job job = jobQueue.poll();
						if(job != null) {
							JobExecutor jobExecutor = new JobExecutor(job);
							executorService.submit(jobExecutor);
						}
					}
				} catch(Exception e) {
					Logger.log(e);
				}
			}
		});
	}
	
	public void add(Job job) {
		jobQueue.add(job);
	}
	
	public void remove(Job job) {
		jobQueue.remove(job);
	}
	
	public void clear() {
		jobQueue.clear();
	}
	
	public void start() {
		if(!switcher) {
			switcher = true;
			jobScheduleThread.start();
		}
	}
	
	public void stop() {
		if(switcher) {
			switcher = false;
			executorService.shutdown();
		}
	}
}
