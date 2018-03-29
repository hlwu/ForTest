package fortest.hlwu.com.fortest.thread_synchronized;

import android.util.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hlwu on 3/23/18.
 */


public class MyThreadTest {
    private Lock lock = new ReentrantLock();
    private Integer i = 0;
    private void increase() {
        i++;
    }

    private class MyThreadWithLock extends Thread {
        public MyThreadWithLock(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int j = 0; j < 100; j++) {
                increaseWithLock();
                Log.d("flaggg", "run at " + getName() + "; i: " + i + "; lock: " + lock);
            }
        }
    }

    public void run3ThreadWithLock() {
        new MyThreadWithLock("thread1111").start();
        new MyThreadWithLock("thread2222").start();
        new MyThreadWithLock("thread3333").start();
    }

    private void increaseWithLock() {
        lock.lock();
        i++;
        lock.unlock();
    }


/////////////////////////////////////////////////////////////////////////////////////

    private class MyThreadWithLock2 extends Thread {
        public MyThreadWithLock2(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
//                    lock.lock();
                    for (int j = 0; j < 100; j++) {
                        increase();
                        Log.d("flaggg", "run at " + getName() + "; i: " + i + "; lock: " + lock);
                    }
//                    lock.unlock();
                } else {
                    Log.d("flaggg", "can't get lock, no wait any more; lock: " + lock);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Log.d("flaggg", "release the lock; lock: " + lock);
                lock.unlock();
            }
        }
    }

    public void run3ThreadWithLock2() {
        new MyThreadWithLock2("thread1111").start();
        new MyThreadWithLock2("thread2222").start();
        new MyThreadWithLock2("thread3333").start();
    }

/////////////////////////////////////////////////////////////////////////////////////

    private class MyThreadWithSynchronized extends Thread {
        public MyThreadWithSynchronized(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            for (int j = 0; j < 100; j++) {
                increaseSynchronized();
                Log.d("flaggg", "run at " + getName() + "; i: " + i);
            }
        }
    }

    private synchronized void increaseSynchronized() {
        i++;
    }

    public void run3ThreadWithSynchronized() {
        new MyThreadWithSynchronized("thread4444").start();
        new MyThreadWithSynchronized("thread5555").start();
        new MyThreadWithSynchronized("thread6666").start();
    }

/////////////////////////////////////////////////////////////////////////////////////

    private class MyThreadWithSynchronized2 extends Thread {
        public MyThreadWithSynchronized2(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            synchronized (MyThreadTest.this) {
                for (int j = 0; j < 100; j++) {
                    increase();
                    Log.d("flaggg", "run at " + getName() + "; i: " + i);
                }
            }
        }
    }

    public void run3ThreadWithSynchronized2() {
        new MyThreadWithSynchronized2("thread4444").start();
        new MyThreadWithSynchronized2("thread5555").start();
        new MyThreadWithSynchronized2("thread6666").start();
    }
}