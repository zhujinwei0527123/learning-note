<a name="index">**Index**</a>

<a href="#0">Java并发应用</a>  
&emsp;<a href="#1">1. 生产者与消费者模型</a>  
&emsp;&emsp;<a href="#2">1.1. synchronize</a>  
&emsp;&emsp;&emsp;<a href="#3">1.1.1. 基于synchronize 方法</a>  
&emsp;&emsp;&emsp;<a href="#4">1.1.2. 使用synchronize 锁对象</a>  
&emsp;&emsp;&emsp;<a href="#5">1.1.3. 问题代码</a>  
&emsp;&emsp;<a href="#6">1.2. 基于ReentrantLock 结合 condition</a>  
&emsp;&emsp;<a href="#7">1.3. 基于BlockingQueue</a>  
&emsp;<a href="#8">2. 多线程顺序输出</a>  
&emsp;&emsp;<a href="#9">2.1. 基于Synchronize锁对象</a>  
&emsp;&emsp;<a href="#10">2.2. 基于Reentrant Lock</a>  
&emsp;<a href="#11">3. 使用String常量作为synchronized的锁 优化同步锁</a>  
&emsp;<a href="#12">4. 类加载中synchronized的应用</a>  
&emsp;<a href="#13">5. 单订单重复退款请求</a>  
&emsp;&emsp;<a href="#14">5.1. 分布式锁的处理方案</a>  
&emsp;<a href="#15">6. 消息批量发送设计</a>  
&emsp;&emsp;<a href="#16">6.1. 问题场景</a>  
&emsp;&emsp;<a href="#17">6.2. 解决方法</a>  
&emsp;&emsp;<a href="#18">6.3. 进阶问题处理</a>  
&emsp;&emsp;<a href="#19">6.4. 相关类似资料</a>  
&emsp;<a href="#20">7. future编程</a>  
# <a name="0">Java并发应用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">生产者与消费者模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="2">synchronize</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="3">基于synchronize 方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```java
public class Solution {
    public synchronized void produce() throws InterruptedException {
        while (present) {
            wait();
        }
        System.out.println(Thread.currentThread() + " Producer produce meal " + count);
        count++;
        this.setPresent(true);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (!present) {
            wait();
        }
        System.out.println(Thread.currentThread() + " consumer present meal" + count);
        this.setPresent(false);
        notifyAll();

    }
}
```

#### <a name="4">使用synchronize 锁对象</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```java
public class Solution {
    public void createByObject() throws InterruptedException {
        synchronized (this) {
            while (present) {
                wait();
            }
        }
        count++;
        System.out.println(Thread.currentThread() + " Producer produce meal " + count);
        this.setPresent(true);
        synchronized (this) {
            notifyAll();
        }
    }

    public void consumerByObject() throws InterruptedException {
        synchronized (this) {
            while (!present) {
                wait();
            }
        }
        System.out.println(Thread.currentThread() + " consumer present meal " + count);
        this.setPresent(false);
        synchronized (this) {
            notifyAll();
        }
    }
}
```

- 更进一步细分锁粒度，区分生产者与消费者对象。

#### <a name="5">问题代码</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```java
public class Solution {
    public void createByObject() throws InterruptedException {
        while (present) {
            // 轻量级锁等待的时候，如果另个线程先获取锁，并notifyAll
            // 那么可能两个线程都进入wait状态
            synchronized (this) {
                wait();
            }
        }
        count++;
        System.out.println(Thread.currentThread() + " Producer produce meal " + count);
        this.setPresent(true);
        synchronized (this) {
            notifyAll();
        }

    }

    public void consumerByObject() throws InterruptedException {
        while (!present) {
            // 轻量级锁等待的时候，如果另个线程先获取锁，并notifyAll
            // 那么可能两个线程都进入wait状态
            synchronized (this) {
                wait();
            }
        }
        System.out.println(Thread.currentThread() + " consumer present meal " + count);
        this.setPresent(false);
        synchronized (this) {
            notifyAll();
        }
    }

    // 正确做法 将synchronize放循环外
    public void createByObject() throws InterruptedException {
        synchronized (this) {
            while (present) {
                wait();
            }
        }
//        ... 
    }
}
```

### <a name="6">基于ReentrantLock 结合 condition</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 使用condition作为等待队列

```java
class Consumer implements Runnable {

    private ReentrantLock lock;

    private Condition condition;

    public Consumer(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                try {
                    lock.lock();
                    while (!ProConDemo.flag) {
                        condition.await();
                    }
                    System.out.println(Thread.currentThread() + " consumer shout !!!!");
                    ProConDemo.flag = false;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private ReentrantLock lock;

    private Condition condition;

    public Producer(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                try {
                    lock.lock();
                    while (ProConDemo.flag) {
                        condition.await();
                    }
                    System.out.println(Thread.currentThread() + " producer shout~~~~");
                    ProConDemo.flag = true;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```

- 进阶：细分生产者队列与消费者队列

### <a name="7">基于BlockingQueue</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

使用阻塞队列实现生产者与消费者模型

- 消费者：`queue.take();`
- 生产者：`queue.put(obj)`

## <a name="8">多线程顺序输出</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="9">基于Synchronize锁对象</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```java
class Thread1 implements Runnable {

    private Object obj;

    public Thread1(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                synchronized (obj) {
                    while (!SynchronizeObject.flag) {
                        obj.wait();
                    }
                    System.out.println(Thread.currentThread() + " this is thread1");
                    SynchronizeObject.flag = false;
                    obj.notify();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread2 implements Runnable {

    private Object obj;

    public Thread2(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                synchronized (obj) {
                    while (SynchronizeObject.flag) {
                        obj.wait();
                    }
                    System.out.println(Thread.currentThread() + " this is thread2~~");
                    SynchronizeObject.flag = true;
                    obj.notify();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### <a name="10">基于Reentrant Lock</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

方法1：设立一个flag，防止非公平锁抢锁输出，导致的顺序混乱问题。

方法2： 使用Reentrant Lock 公平锁

```java
public class Solution extends Thread {
    ReentrantLock lock = new ReentrantLock(true);

    public void run() {

        while (!Thread.interrupted()) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread() + " consumer shout !!!!");
            } finally {
                lock.unlock();
            }
        }
    }
}
```

## <a name="11">使用String常量作为synchronized的锁 优化同步锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

String是final的，每次对它的操作都会产生新的String，这很大程度上是安全性的考虑，但是产生大量的String也是会有一些问题的。

1. 大量的String会对gc产生影响；
2. 两次 new String（“aa”）操作，产生的String不一样，如果用这两个去做synchronized（String）操作就达不到想要的效果，因为synchronized必须是对同一个对象进行加锁才有效果。

以下为demo：

1. `synchronized (lock)` 输出为乱序
2. `synchronized (lock.intern()) ` 输出为顺序
3. `synchronized (pool.intern(lock)) ` 输出为顺序

```java

public class StringSynchronized {

    public static void main(String[] args) throws InterruptedException {
        Interner<String> pool = Interners.newWeakInterner();

        for (int i = 1; i < 10; i++) {
            TestString billno123 = new TestString("billNo:123123123", i, pool);
            Thread thread = new Thread(billno123);
            thread.start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("finish");
    }
}

@Slf4j
@AllArgsConstructor
@Data
class TestString implements Runnable {
    private final String lock;
    private int workingNo;
    private Interner<String> pool;

    @Override
    public void run() {
//        synchronized (lock) {
//        synchronized (lock.intern()) {
        synchronized (pool.intern(lock)) {
            log.info(lock + " ==>" + workingNo);
        }
    }
}

```

区别是：
1. interns常量池有限，存储在hashtable中，数据多了之后，碰撞厉害，而且容易加重full gc负担
2. Interners内部基于ConcurrentHashMap实现，而且可以设置引用类型，不会加重full gc负担，但有一个问题就是如果gc回收了存储在Interners里面的String，那么pool.intern(lock)
   可能也会返回不同的引用，总之，还是建议使用Interners，效率和内存使用率更高

## <a name="12">类加载中synchronized的应用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

类加载过程中为了控制并发的情况，使用synchronized控制。而synchronized控制并发主要使用锁对象的方式

1. 使用ConcurrentHashMap存储加锁的对象。
2. 获取锁对象的时候，先去ConcurrentHashMap获取对象，由于ConcurrentHashMap添加操作是并发安全的，最后保证不同线程加锁的时候获取到同一个对象。
3. synchronized加锁后，进入到类加载流程。

```java
public class ClassLoader {
    // ...

    private final ConcurrentHashMap<String, Object> parallelLockMap;

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    protected Object getClassLoadingLock(String className) {
        Object lock = this;
        if (parallelLockMap != null) {
            Object newLock = new Object();
            lock = parallelLockMap.putIfAbsent(className, newLock);
            if (lock == null) {
                lock = newLock;
            }
        }
        return lock;
    }
}
```

## <a name="13">单订单重复退款请求</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

1. synchronize修饰退款方法。
2. 缩小synchronize锁范围，使用对象锁。对象锁，创建弱引用的一个订单ID对象，放到统一的锁对象资源池中。
    - 清理锁对象可以使用守护线程的方法，基于Unsafe的包操作去清除。
3. 分布式应用，使用分布式锁来处理。

### <a name="14">分布式锁的处理方案</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

1. 数据库锁，数据库乐观锁，数据库悲观锁。
2. redis 锁 或者 ZooKeeper锁
3. 使用消息队列顺序消费，保证不重复退款

## <a name="15">消息批量发送设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="16">问题场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

某个活动需要对平台的客户进行短信的推销发送，假设对平台的10w用户推送某个活动。推送的用户数据由数据仓库已经推送到表t_user_promotion总共10w条数据。

而在调用短信批量发送服务的时候，经常有限制批量发送的手机号数目的，比如限制100个手机号。

1. 如何对10w条消息进行发送
2. 假设推送由运营人员进行触发，如何防止10w条消息出现重复触发的情况。

进阶问题：假如10w条消息，消息模板是不一致的，如何设计？

设计点：

1. 表结构设计
2. 查询效率问题

### <a name="17">解决方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

方法1： 设计一张中间表作为批次表，每100个用户打一个批次。用户表中增加批次id。

整体发送的步骤如下：

1. 用户表增加批次号字段，每100个号码打成一个批次，记录插入批次表。
2. 调用消息队列异步处理该批次的短信消息。
3. 消息队列消费，批次消息进行短信发送的调用，发送成功更新批次表状态。\
   好处为：短信发送与数据处理逻辑分离。

方法2：

1. 用户表使用状态代表发送和未发送。

---

问题点： 该问题的难点在于如何处理多线程的分工及互斥问题。多线程三大核心问题：分工、协作、互斥。

- 分工：每个批量发送的接口只能固定发送100个手机号，因此每个线程负责100条记录的处理。
- 协作：该场景的线程协作，只用通知短信发送的主线线程这个数据处理完了。比如countDownLaunch，子线程完成之后countDown
- 互斥：每个线程负责处理100条数据，如何避免互斥问题。

解决分工及互斥几种方式：

1. 避免互斥。
    1. 如果取10个线程，那么每个线程可以默认取主键id%10=y的记录进行处理，避免了互斥。
    2. 查询出所需要处理的记录，使用id排序，每个线程根据pageIndex+pageNo，进行数据的处理。`limit pageIndex pageNo`
        > sql可能会因为pageIndex+pageNo 导致慢查询，需要使用延迟关联的方式进行sql优化
2. 加锁不推荐，悲观锁（使用mysql的行锁），乐观锁（使用mysql的version乐观锁）

### <a name="18">进阶问题处理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 如何解决重复发送问题，短信批量发送的请求更新为中间状态发送中。
2. 消息模版不一致问题，使用数据分组，相同消息模版的数据统一处理。

### <a name="19">相关类似资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

[批量任务体现多线程的威力！](https://juejin.cn/post/6844903774234869774) \
[JAVA实现多线程处理批量发送短信、APP推送](https://blog.csdn.net/weixin_30443747/article/details/95104128)

## <a name="20">future编程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```java
public class Solution {
    public void test() {
        List<Future<String>> futureList = new ArrayList<>();
        for (ChannelModel channel : channels) {
            Future<String> future = executorService
                    .submit(() -> load(channel.getChannel()));
            futureList.add(future);
        }

        final long deadline = System.currentTimeMillis() + MAX_LOAD_SECONDS * 1000;
        for (int i = 0; i < futureList.size(); i++) {
            Future<RateModel> future = futureList.get(i);
            try {
                long timeLeft = deadline - System.currentTimeMillis();
                if (timeLeft > 0) {
                    // 设定时间取出任务
                    String model = future.get(timeLeft, TimeUnit.MILLISECONDS);
//            ...
                } else {
                    if (future.isDone()) {
                        RateModel rateModel = future.get();
//                ...
                    } else {
                        future.cancel(true);
                    }
                }
            } catch (InterruptedException e) {
//      ...
            }
        }
    }
}
```