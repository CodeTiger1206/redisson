/**
 * Copyright 2016 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;

import java.util.concurrent.TimeUnit;

import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.redisson.codec.CodecProvider;
import org.redisson.liveobject.provider.ResolverProvider;

/**
 * Main Redisson interface for access
 * to all redisson objects with sync/async interface.
 *
 * @author Nikita Koksharov
 *
 */
public interface RedissonClient {

    /**
     * Returns binary stream holder instance by <code>name</code>
     * 
     * @param name of binary stream
     * @return BinaryStream object 
     */
    RBinaryStream getBinaryStream(String name);
    
    /**
     * Returns geospatial items holder instance by <code>name</code>.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return Geo object
     */
    <V> RGeo<V> getGeo(String name);
    
    /**
     * Returns geospatial items holder instance by <code>name</code>
     * using provided codec for geospatial members.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for value
     * @return Geo object
     */
    <V> RGeo<V> getGeo(String name, Codec codec);
    
    /**
     * Returns set-based cache instance by <code>name</code>.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(String, Codec)}.</p>
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return SetCache object
     */
    <V> RSetCache<V> getSetCache(String name);

    /**
     * Returns set-based cache instance by <code>name</code>.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(String, Codec)}.</p>
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return SetCache object
     */
    <V> RSetCache<V> getSetCache(String name, Codec codec);

    /**
     * Returns map-based cache instance by <code>name</code>
     * using provided <code>codec</code> for both cache keys and values.
     * Supports entry eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getMap(String, Codec)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return MapCache object
     */
    <K, V> RMapCache<K, V> getMapCache(String name, Codec codec);

    /**
     * Returns map-based cache instance by name.
     * Supports entry eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getMap(String)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return MapCache object
     */
    <K, V> RMapCache<K, V> getMapCache(String name);

    /**
     * Returns object holder instance by name.
     *
     * @param <V> type of value
     * @param name - name of object
     * @return Bucket object
     */
    <V> RBucket<V> getBucket(String name);

    /**
     * Returns object holder instance by name
     * using provided codec for object.
     *
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return Bucket object
     */
    <V> RBucket<V> getBucket(String name, Codec codec);

    /**
     * Returns interface for mass operations with Bucket objects.
     *
     * @return Buckets
     */
    RBuckets getBuckets();

    /**
     * Returns interface for mass operations with Bucket objects
     * using provided codec for object.
     *
     * @param codec - codec for bucket objects
     * @return Buckets
     */
    RBuckets getBuckets(Codec codec);

    /**
     * Returns HyperLogLog instance by name.
     *
     * @param <V> type of value
     * @param name - name of object
     * @return HyperLogLog object
     */
    <V> RHyperLogLog<V> getHyperLogLog(String name);

    /**
     * Returns HyperLogLog instance by name
     * using provided codec for hll objects.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return HyperLogLog object
     */
    <V> RHyperLogLog<V> getHyperLogLog(String name, Codec codec);

    /**
     * Returns list instance by name.
     *
     * @param <V> type of value
     * @param name - name of object
     * @return List object
     */
    <V> RList<V> getList(String name);

    /**
     * Returns list instance by name
     * using provided codec for list objects.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return List object
     */
    <V> RList<V> getList(String name, Codec codec);

    /**
     * Returns List based Multimap instance by name.
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return ListMultimap object
     */
    <K, V> RListMultimap<K, V> getListMultimap(String name);

    /**
     * Returns List based Multimap instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return ListMultimap object
     */
    <K, V> RListMultimap<K, V> getListMultimap(String name, Codec codec);

    /**
     * Returns List based Multimap instance by name.
     * Supports key-entry eviction with a given TTL value.
     * 
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String)}.</p>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return ListMultimapCache object
     */
    <K, V> RListMultimapCache<K, V> getListMultimapCache(String name);
    
    /**
     * Returns List based Multimap instance by name
     * using provided codec for both map keys and values.
     * Supports key-entry eviction with a given TTL value.
     * 
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String, Codec)}.</p>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return ListMultimapCache object
     */
    <K, V> RListMultimapCache<K, V> getListMultimapCache(String name, Codec codec);
    
    /**
     * Returns local cached map instance by name.
     * Configured by parameters of options-object. 
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param options - local map options
     * @return LocalCachedMap object
     */
    <K, V> RLocalCachedMap<K, V> getLocalCachedMap(String name, LocalCachedMapOptions options);
    
    /**
     * Returns local cached map instance by name
     * using provided codec. Configured by parameters of options-object.
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @param options - local map options
     * @return LocalCachedMap object
     */
    <K, V> RLocalCachedMap<K, V> getLocalCachedMap(String name, Codec codec, LocalCachedMapOptions options);
    
    /**
     * Returns map instance by name.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return Map object
     */
    <K, V> RMap<K, V> getMap(String name);

    /**
     * Returns map instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return Map object
     */
    <K, V> RMap<K, V> getMap(String name, Codec codec);

    /**
     * Returns Set based Multimap instance by name.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return SetMultimap object
     */
    <K, V> RSetMultimap<K, V> getSetMultimap(String name);
    
    /**
     * Returns Set based Multimap instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return SetMultimap object
     */
    <K, V> RSetMultimap<K, V> getSetMultimap(String name, Codec codec);

    /**
     * Returns Set based Multimap instance by name.
     * Supports key-entry eviction with a given TTL value.
     * 
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String)}.</p>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @return SetMultimapCache object
     */
    <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String name);

    /**
     * Returns Set based Multimap instance by name
     * using provided codec for both map keys and values.
     * Supports key-entry eviction with a given TTL value.
     * 
     * <p>If eviction is not required then it's better to use regular map {@link #getSetMultimap(String, Codec)}.</p>
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return SetMultimapCache object
     */
    <K, V> RSetMultimapCache<K, V> getSetMultimapCache(String name, Codec codec);

    /**
     * Returns semaphore instance by name
     *
     * @param name - name of object
     * @return Semaphore object
     */
    RSemaphore getSemaphore(String name);
    
    /**
     * Returns semaphore instance by name.
     * Supports lease time parameter for each acquired permit.
     * 
     * @param name - name of object
     * @return PermitExpirableSemaphore object
     */
    RPermitExpirableSemaphore getPermitExpirableSemaphore(String name);

    /**
     * Returns lock instance by name.
     * <p>
     * Implements a <b>non-fair</b> locking so doesn't guarantees an acquire order by threads.
     *
     * @param name - name of object
     * @return Lock object
     */
    RLock getLock(String name);

    /**
     * Returns lock instance by name.
     * <p>
     * Implements a <b>fair</b> locking so it guarantees an acquire order by threads.
     * 
     * @param name - name of object
     * @return Lock object
     */
    RLock getFairLock(String name);
    
    /**
     * Returns readWriteLock instance by name.
     *
     * @param name - name of object
     * @return Lock object
     */
    RReadWriteLock getReadWriteLock(String name);

    /**
     * Returns set instance by name.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return Lock object
     */
    <V> RSet<V> getSet(String name);

    /**
     * Returns set instance by name
     * using provided codec for set objects.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return Set object
     */
    <V> RSet<V> getSet(String name, Codec codec);

    /**
     * Returns sorted set instance by name.
     * This sorted set uses comparator to sort objects.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return SortedSet object
     */
    <V> RSortedSet<V> getSortedSet(String name);

    /**
     * Returns sorted set instance by name
     * using provided codec for sorted set objects.
     * This sorted set sorts objects using comparator.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return SortedSet object
     */
    <V> RSortedSet<V> getSortedSet(String name, Codec codec);

    /**
     * Returns Redis Sorted Set instance by name.
     * This sorted set sorts objects by object score.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return ScoredSortedSet object
     */
    <V> RScoredSortedSet<V> getScoredSortedSet(String name);

    /**
     * Returns Redis Sorted Set instance by name
     * using provided codec for sorted set objects.
     * This sorted set sorts objects by object score.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return ScoredSortedSet object
     */
    <V> RScoredSortedSet<V> getScoredSortedSet(String name, Codec codec);

    /**
     * Returns String based Redis Sorted Set instance by name
     * All elements are inserted with the same score during addition,
     * in order to force lexicographical ordering
     * 
     * @param name - name of object
     * @return LexSortedSet object
     */
    RLexSortedSet getLexSortedSet(String name);

    /**
     * Returns topic instance by name.
     * 
     * @param <M> type of message
     * @param name - name of object
     * @return Topic object
     */
    <M> RTopic<M> getTopic(String name);

    /**
     * Returns topic instance by name
     * using provided codec for messages.
     *
     * @param <M> type of message
     * @param name - name of object
     * @param codec - codec for message
     * @return Topic object
     */
    <M> RTopic<M> getTopic(String name, Codec codec);

    /**
     * Returns topic instance satisfies by pattern name.
     *
     *  Supported glob-style patterns:
     *    h?llo subscribes to hello, hallo and hxllo
     *    h*llo subscribes to hllo and heeeello
     *    h[ae]llo subscribes to hello and hallo, but not hillo
     * 
     * @param <M> type of message
     * @param pattern of the topic
     * @return PatterTopic object
     */
    <M> RPatternTopic<M> getPatternTopic(String pattern);

    /**
     * Returns topic instance satisfies by pattern name
     * using provided codec for messages.
     *
     *  Supported glob-style patterns:
     *    h?llo subscribes to hello, hallo and hxllo
     *    h*llo subscribes to hllo and heeeello
     *    h[ae]llo subscribes to hello and hallo, but not hillo
     * 
     * @param <M> type of message
     * @param pattern of the topic
     * @param codec - codec for message
     * @return PatterTopic object
     */
    <M> RPatternTopic<M> getPatternTopic(String pattern, Codec codec);

    /**
     * Returns unbounded fair queue instance by name.
     * 
     * @param name of queue
     * @return queue
     */
    <V> RBlockingFairQueue<V> getBlockingFairQueue(String name);
    
    <V> RBlockingFairQueue<V> getBlockingFairQueue(String name, Codec codec);
    
    /**
     * Returns unbounded queue instance by name.
     *
     * @param <V> type of value
     * @param name of object
     * @return queue object
     */
    <V> RQueue<V> getQueue(String name);
    
    /**
     * Returns unbounded delayed queue instance by name.
     * <p>
     * Could be attached to destination queue only.
     * All elements are inserted with transfer delay to destination queue.
     * 
     * @param destinationQueue - destination queue
     * @return delayed queue
     */
    <V> RDelayedQueue<V> getDelayedQueue(RQueue<V> destinationQueue);

    /**
     * Returns unbounded queue instance by name
     * using provided codec for queue objects.
     *
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for message
     * @return Queue object
     */
    <V> RQueue<V> getQueue(String name, Codec codec);

    /**
     * Returns unbounded blocking queue instance by name.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return BlockingQueue object
     */
    <V> RBlockingQueue<V> getBlockingQueue(String name);

    /**
     * Returns unbounded blocking queue instance by name
     * using provided codec for queue objects.
     * 
     * @param <V> type of value
     * @param name - name of queue
     * @param codec - queue objects codec
     * @return BlockingQueue object
     */
    <V> RBlockingQueue<V> getBlockingQueue(String name, Codec codec);

    /**
     * Returns bounded blocking queue instance by name.
     *
     * @param <V> type of value
     * @param name of queue
     * @return BoundedBlockingQueue object
     */
    <V> RBoundedBlockingQueue<V> getBoundedBlockingQueue(String name);

    /**
     * Returns bounded blocking queue instance by name
     * using provided codec for queue objects.
     * 
     * @param <V> type of value
     * @param name - name of queue
     * @param codec - codec for values
     * @return BoundedBlockingQueue object
     */
    <V> RBoundedBlockingQueue<V> getBoundedBlockingQueue(String name, Codec codec);

    /**
     * Returns unbounded deque instance by name.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return Deque object
     */
    <V> RDeque<V> getDeque(String name);

    /**
     * Returns unbounded deque instance by name
     * using provided codec for deque objects.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return Deque object
     */
    <V> RDeque<V> getDeque(String name, Codec codec);

    /**
     * Returns unbounded blocking deque instance by name.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return BlockingDeque object
     */
    <V> RBlockingDeque<V> getBlockingDeque(String name);

    /**
     * Returns unbounded blocking deque instance by name
     * using provided codec for deque objects.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @param codec - deque objects codec
     * @return BlockingDeque object
     */
    <V> RBlockingDeque<V> getBlockingDeque(String name, Codec codec);

    /**
     * Returns atomicLong instance by name.
     *
     * @param name - name of object
     * @return AtomicLong object
     */
    RAtomicLong getAtomicLong(String name);

    /**
     * Returns atomicDouble instance by name.
     *
     * @param name - name of object
     * @return AtomicDouble object
     */
    RAtomicDouble getAtomicDouble(String name);

    /**
     * Returns countDownLatch instance by name.
     *
     * @param name - name of object
     * @return CountDownLatch object
     */
    RCountDownLatch getCountDownLatch(String name);

    /**
     * Returns bitSet instance by name.
     *
     * @param name - name of object
     * @return BitSet object
     */
    RBitSet getBitSet(String name);

    /**
     * Returns bloom filter instance by name.
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return BloomFilter object
     */
    <V> RBloomFilter<V> getBloomFilter(String name);

    /**
     * Returns bloom filter instance by name
     * using provided codec for objects.
     *
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for values
     * @return BloomFilter object
     */
    <V> RBloomFilter<V> getBloomFilter(String name, Codec codec);

    /**
     * Returns script operations object
     *
     * @return Script object
     */
    RScript getScript();

    /**
     * Returns ScheduledExecutorService by name
     * 
     * @param name - name of object
     * @return ScheduledExecutorService object
     */
    RScheduledExecutorService getExecutorService(String name);

    /**
     * Returns ScheduledExecutorService by name 
     * using provided codec for task, response and request serialization
     * 
     * @param name - name of object
     * @param codec - codec for task, response and request
     * @return ScheduledExecutorService object
     */
    RScheduledExecutorService getExecutorService(Codec codec, String name);
    
    /**
     * Returns object for remote operations prefixed with the default name (redisson_remote_service)
     * 
     * @return RemoteService object
     */
    RRemoteService getRemoteService();
    
    /**
     * Returns object for remote operations prefixed with the default name (redisson_remote_service)
     * and uses provided codec for method arguments and result.
     * 
     * @param codec - codec for response and request
     * @return RemoteService object
     */
    RRemoteService getRemoteService(Codec codec);

    /**
     * Returns object for remote operations prefixed with the specified name
     *
     * @param name - the name used as the Redis key prefix for the services
     * @return RemoteService object
     */
    RRemoteService getRemoteService(String name);
    
    /**
     * Returns object for remote operations prefixed with the specified name
     * and uses provided codec for method arguments and result.
     *
     * @param name - the name used as the Redis key prefix for the services
     * @param codec - codec for response and request
     * @return RemoteService object
     */
    RRemoteService getRemoteService(String name, Codec codec);

    /**
     * Return batch object which executes group of
     * command in pipeline.
     *
     * See <a href="http://redis.io/topics/pipelining">http://redis.io/topics/pipelining</a>
     *
     * @return Batch object
     */
    RBatch createBatch();

    /**
     * Returns interface with methods for Redis keys.
     * Each of Redis/Redisson object associated with own key
     *
     * @return Keys object
     */
    RKeys getKeys();

    /**
     * Returns RedissonAttachedLiveObjectService which can be used to 
     * retrieve live REntity(s)
     * 
     * @return LiveObjectService object
     */
    RLiveObjectService getLiveObjectService();
    
    /**
     * Shutdown Redisson instance but <b>NOT</b> Redis server
     * 
     * This equates to invoke shutdown(2, 15, TimeUnit.SECONDS);
     */
    void shutdown();
    
    /**
     * Shuts down Redisson instance <b>NOT</b> Redis server
     * 
     * Shutdown ensures that no tasks are submitted for <i>'the quiet period'</i>
     * (usually a couple seconds) before it shuts itself down.  If a task is submitted during the quiet period,
     * it is guaranteed to be accepted and the quiet period will start over.
     * 
     * @param quietPeriod the quiet period as described in the documentation
     * @param timeout     the maximum amount of time to wait until the executor is {@linkplain #shutdown()}
     *                    regardless if a task was submitted during the quiet period
     * @param unit        the unit of {@code quietPeriod} and {@code timeout}
     */
    void shutdown(long quietPeriod, long timeout, TimeUnit unit);

    /**
     * Allows to get configuration provided
     * during Redisson instance creation. Further changes on
     * this object not affect Redisson instance.
     *
     * @return Config object
     */
    Config getConfig();

    /**
     * Returns the CodecProvider instance
     * 
     * @return CodecProvider object
     */
    public CodecProvider getCodecProvider();
    
    /**
     * Returns the ResolverProvider instance
     * 
     * @return ResolverProvider object
     */
    public ResolverProvider getResolverProvider();

    
    /**
     * Get Redis nodes group for server operations
     *
     * @return NodesGroup object
     */
    NodesGroup<Node> getNodesGroup();

    /**
     * Get Redis cluster nodes group for server operations
     *
     * @return ClusterNodesGroup object
     */
    ClusterNodesGroup getClusterNodesGroup();

    /**
     * Returns {@code true} if this Redisson instance has been shut down.
     *
     * @return code true} if this Redisson instance has been shut down overwise <code>false</code>
     */
    boolean isShutdown();

    /**
     * Returns {@code true} if this Redisson instance was started to be shutdown
     * or was shutdown {@link #isShutdown()} already.
     *
     * @return {@code true} if this Redisson instance was started to be shutdown
     * or was shutdown {@link #isShutdown()} already.
     */
    boolean isShuttingDown();

}
