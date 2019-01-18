package com.zhang.guava.concurrent;

import com.google.common.util.concurrent.Monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link Monitor}
 *
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-15 13:54
 * @since 1.0
 */
public class MonitorExample {

    /**
     * synchronized
     * <p>
     * 自从Java提供了多线程编程，我们经常需要处理这样的情况：在特定的时间，我们需要限制访问，确保只有一个线程访问我们的代码。
     * Java提供了同步关键字synchronized来实现这种访问控制，但是使用synchronized会存在一些问题。
     * 第一个问题是，当我们需要调用线程的wait()方法时，我们必须记得去使用while循环。
     * <p>
     * 在这个例子中获取一个值，当值不存在的时候，我们等待。。。有值的时候需要notifyAll()。
     * 这里需要注意的是，我们要在while循环中使用wait方法，而不是if。另外用notifyAll而不是notify
     */
    static class SafeBox<V> {
        private V value;

        public synchronized V get() throws InterruptedException {
            while (value == null) {
                wait();
            }
            V result = value;
            value = null;
            notifyAll();
            return result;
        }

        public synchronized void set(V newValue) throws InterruptedException {
            while (value != null) {
                wait();
            }
            value = newValue;
            notifyAll();
        }
    }

    /**
     * 我们依然需要使用while循环，但是有一个好处，我们可以定义两个Condition，
     * 这样我们就可以用signal来替代signalAll，这样可能会带来一点性能上的提升。
     */
    static class SafeBoxLock<V> {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition valuePresent = lock.newCondition();
        private final Condition valueAbsent = lock.newCondition();
        private V value;

        public V get() throws InterruptedException {
            lock.lock();
            try {
                while (value == null) {
                    valuePresent.await();
                }
                V result = value;
                value = null;
                valueAbsent.signal();
                return result;
            } finally {
                lock.unlock();
            }
        }

        public void set(V newValue) throws InterruptedException {
            lock.lock();
            try {
                while (value != null) {
                    valueAbsent.await();
                }
                value = newValue;
                valuePresent.signal();
            } finally {
                lock.unlock();
            }
        }
    }


    /**
     * Monitor是一个支持任意布尔条件的同步的抽象，Monitor类是作为ReentrantLock的一个替代，代码中使用Monitor比使用ReentrantLock更不易出错，
     * 可读性也更强，并且也没有显著的性能损失，使用Monitor甚至有潜在的性能得到优化，下面我们看看用guava中的Monitor怎么重写上面的代码。
     *
     * 可以发现使用guava之后，我们不需要使用while，使用Monitor.Guard定义进入代码快的条件即可，代码变得更加容易阅读，写起来也更加方便。
     * 当我们Monitor的方法返回boolean值的时候，我们在if块中包含try-finally块，确保锁能够释放
     * if(monitor.enterIf(guard)){
     *     try{
     *        ...work..
     *     }finally{
     *         monitor.leave();
     *     }
     * }else{
     *    .. monitor not available..
     * }
     *
     * 当monitor的方法不返回任何值的时候，我们的代码也需要在finally中释放锁
     * monitor.enter()
     * try{
     *     ...work..
     * }finally{
     *     monitor.leave();
     * }
     *
     * Monitor有几个常用的方法
     *
     * enter()：进入到当前Monitor，无限期阻塞，等待锁。
     * enter(long time, TimeUnit unit)：进入到当前Monitor，最多阻塞给定的时间，返回是否进入Monitor。
     * tryEnter()：如果可以的话立即进入Monitor，不阻塞，返回是否进入Monitor。
     * enterWhen(Guard guard)：进入当前Monitor，等待Guard的isSatisfied()为true后，继续往下执行 ，但可能会被打断。
     * enterIf(Guard guard)：如果Guard的isSatisfied()为true，进入当前Monitor。等待获得锁，不需要等待Guard satisfied。
     * tryEnterIf(Guard guard)：如果Guard的isSatisfied()为true并且可以的话立即进入Monitor，不等待获取锁，也不等待Guard satisfied。
     */
    static class SafeBoxMonitor<V> {

        private final Monitor monitor = new Monitor();

        private final Monitor.Guard valuePresent = new Monitor.Guard(monitor) {
            public boolean isSatisfied() {
                return value != null;
            }
        };
        private final Monitor.Guard valueAbsent = new Monitor.Guard(monitor) {
            public boolean isSatisfied() {
                return value == null;
            }
        };
        private V value;

        public V get() throws InterruptedException {
            monitor.enterWhen(valuePresent);
            try {
                V result = value;
                value = null;
                return result;
            } finally {
                monitor.leave();
            }
        }

        public void set(V newValue) throws InterruptedException {
            monitor.enterWhen(valueAbsent);
            try {
                value = newValue;
            } finally {
                monitor.leave();
            }
        }
    }
}
