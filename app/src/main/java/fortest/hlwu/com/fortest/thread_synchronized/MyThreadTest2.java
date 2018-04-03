package fortest.hlwu.com.fortest.thread_synchronized;

import android.util.Log;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hlwu on 4/3/18.
 */

public class MyThreadTest2 {
    private int i = 0;
    private Lock lock = new ReentrantLock();

    public void startIncreaseTest() {
        new MyThread1("111").start();
        new MyThread2().start();
        new MyThread3().start();
    }

    private class MyThread1 extends Thread {
        public MyThread1(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                lock.lock();
                if (i >= 200) {
                    break;
                }
                if (i % 3 == 0) {
                    i++;
                    Log.d("flaggg", "increase at thread " + getName() + "; " + i);
//                    Log.d("flaggg", "increase at thread A ");
                }
                lock.unlock();
            }
        }
    }

    private class MyThread2 extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                lock.lock();
                if (i >= 200) {
                    break;
                }
                if (i % 3 == 1) {
                    i++;
                    Log.d("flaggg", "increase at thread2 " + i);
//                    Log.d("flaggg", "increase at thread B ");
                }
                lock.unlock();
            }
        }
    }

    private class MyThread3 extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                lock.lock();
                if (i >= 200) {
                    break;
                }
                if (i % 3 == 2) {
                    i++;
                    Log.d("flaggg", "increase at thread333333333333 " + i);
//                    Log.d("flaggg", "increase at thread C ");
                }
                lock.unlock();
            }
        }
    }

}
