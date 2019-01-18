package com.zhang.guava.concurrent;

/**
 * synchronized
 *
 * 自从Java提供了多线程编程，我们经常需要处理这样的情况：在特定的时间，我们需要限制访问，确保只有一个线程访问我们的代码。*
 * Java提供了同步关键字synchronized来实现这种访问控制，但是使用synchronized会存在一些问题。
 * 第一个问题是，当我们需要调用线程的wait()方法时，我们必须记得去使用while循环。
 *
 * 在这个例子中获取一个值，当值不存在的时候，我们等待。。。有值的时候需要notifyAll()。
 * 这里需要注意的是，我们要在while循环中使用wait方法，而不是if。另外用notifyAll而不是notify
 * @author <p>yuyang.zhang<p>
 * @date 2019-01-15 13:55
 * @since 1.0
 */
public class SafeBox<V> {
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