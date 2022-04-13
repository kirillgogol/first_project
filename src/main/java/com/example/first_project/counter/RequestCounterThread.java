package com.example.first_project.counter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestCounterThread extends Thread{
    Logger logger = LogManager.getLogger(RequestCounterThread.class);

    public RequestCounterThread() {
        super();
        start();
    }

    public void run() {
        boolean flag = true;
        while (flag == true) {
            try {
                logger.info(Thread.currentThread().getName() + " is waiting for resolution");
                Synchronization.semaphore.acquire();
                RequestCounter.increment();
                flag = false;
                logger.info("Counter after increment " + RequestCounter.getCounter());
            } catch (InterruptedException e) {
                logger.error("Thread was interrupted");
            }
        }
    }
}
