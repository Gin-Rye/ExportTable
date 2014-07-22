package com.export.control.batch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.export.base.exception.BusinessException;
import com.export.base.log.Logger;
import com.export.control.Job;
import com.export.control.JobExecutor;
import com.export.control.JobManager;

public class BatchJobManager implements JobManager {
	public static final int BATCH_THREAD_COUNT = 5;
	
	private Queue<Job> jobQueue = new ConcurrentLinkedQueue<Job>();
	private Thread jobScheduleThread;
	private boolean started = false;
	
	public BatchJobManager() {
		jobScheduleThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Logger.log("BatchJobManager start: " + jobQueue.size() + " jobs");
					ExecutorService executorService = 
						Executors.newFixedThreadPool(BatchJobManager.BATCH_THREAD_COUNT);
					List<Future<?>> futureList = new LinkedList<Future<?>>();
					while(!jobQueue.isEmpty()) {
						Thread.sleep(100);
						Job job = jobQueue.poll();
						if(job != null) {
							JobExecutor jobExecutor = new JobExecutor(job);
							Future<?> future = executorService.submit(jobExecutor);
							futureList.add(future);
						}
					}
					while(!futureList.isEmpty()) {
						Thread.sleep(100);
						Iterator<Future<?>> futureListIterator = futureList.iterator();
						while(futureListIterator.hasNext()) {
							Future<?> future = futureListIterator.next();
							if(future.isDone()) {
								futureListIterator.remove();
							}
						}
					}
					executorService.shutdown();
					executorService = null;
					Logger.log("BatchJobManager stop");
				} catch(Exception e) {
					Logger.log(e);
				}
			}
		});
	}
	
	@Override
	public synchronized void add(Job job) throws BusinessException {
		if(!started) {
			jobQueue.add(job);
		} else {
			throw new BusinessException("BatchJobManager has started");
		}
	}

	@Override
	public synchronized void remove(Job job) throws BusinessException {
		if(!started) {
			jobQueue.remove(job);
		} else {
			throw new BusinessException("BatchJobManager has started");
		}
	}

	@Override
	public synchronized void start() throws BusinessException {
		if(!started) {
			started = true;
			jobScheduleThread.start();
		}
	}
}
