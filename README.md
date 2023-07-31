
## Quick start

#### Maven 
    <dependency>
       <groupId>org.redisson</groupId>
       <artifactId>redisson</artifactId>
       <version>3.23.2</version>
    </dependency>  

#### Gradle
    compile 'org.redisson:redisson:3.23.2'  

#### SBT
    libraryDependencies += "org.redisson" % "redisson" % "3.23.2"

#### Java

```java
// 1. Create config object
Config config = new Config();
config.useClusterServers()
       // use "rediss://" for SSL connection
      .addNodeAddress("redis://127.0.0.1:7181");

// or read config from file
config = Config.fromYAML(new File("config-file.yaml")); 
```

```java
// 2. Create Redisson instance

// Sync and Async API
RedissonClient redisson = Redisson.create(config);

// Reactive API
RedissonReactiveClient redissonReactive = redisson.reactive();

// RxJava3 API
RedissonRxClient redissonRx = redisson.rxJava();
```

```java
// 3. Get Redis based implementation of java.util.concurrent.ConcurrentMap
RMap<MyKey, MyValue> map = redisson.getMap("myMap");

RMapReactive<MyKey, MyValue> mapReactive = redissonReactive.getMap("myMap");

RMapRx<MyKey, MyValue> mapRx = redissonRx.getMap("myMap");
```

```java
// 4. Get Redis based implementation of java.util.concurrent.locks.Lock
RLock lock = redisson.getLock("myLock");

RLockReactive lockReactive = redissonReactive.getLock("myLock");

RLockRx lockRx = redissonRx.getLock("myLock");
```

```java
// 4. Get Redis based implementation of java.util.concurrent.ExecutorService
RExecutorService executor = redisson.getExecutorService("myExecutorService");

// over 50 Redis based Java objects and services ...

```

Upgrade to __[Redisson PRO](https://redisson.pro)__ with **advanced features**.

## Downloads
   
[Redisson 3.23.2](https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=org.redisson&a=redisson&v=3.23.2&e=jar),
[Redisson node 3.23.2](https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=org.redisson&a=redisson-all&v=3.23.2&e=jar)  

## FAQs

[Q: What is the cause of RedisTimeoutException?](https://github.com/redisson/redisson/wiki/16.-FAQ#q-what-is-the-cause-of-redistimeoutexception)

[Q: When do I need to shut down a Redisson instance, at the end of each request or the end of the life of a thread?](https://github.com/redisson/redisson/wiki/16.-FAQ#q-when-do-i-need-to-shut-down-a-redisson-instance-at-the-end-of-each-request-or-the-end-of-the-life-of-a-thread)

[Q: In MapCache/SetCache/SpringCache/JCache, I have set an expiry time to an entry, why is it still in Redis when it should be disappeared?](https://github.com/redisson/redisson/wiki/16.-FAQ#q-in-mapcachesetcachespringcachejcache-i-have-set-an-expiry-time-to-an-entry-why-is-it-still-in-redis-when-it-should-be-disappeared)

[Q: How can I perform Pipelining/Transaction through Redisson?](https://github.com/redisson/redisson/wiki/16.-FAQ#q-how-can-i-perform-pipeliningtransaction-through-redisson)

[Q: Is Redisson thread safe? Can I share an instance of it between different threads?](https://github.com/redisson/redisson/wiki/16.-FAQ#q-is-redisson-thread-safe-can-i-share-an-instance-of-it-between-different-threads)

[Q: Can I use different encoder/decoders for different tasks?](https://github.com/redisson/redisson/wiki/16.-FAQ#q-can-i-use-different-encoderdecoders-for-different-tasks)

