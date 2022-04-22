package com.example.first_project.counter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class RequestCounterThread extends Thread {
    public final static Semaphore semaphore = new Semaphore(1, true);
    Logger logger = LogManager.getLogger(RequestCounterThread.class);

    public void run() {
        try {
            logger.info(Thread.currentThread().getName() + " is waiting for resolution");
            semaphore.acquire();
            RequestCounter.increment();
            semaphore.release();
            logger.info("Counter after increment " + RequestCounter.getCounter());
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted");
        }
    }
}
