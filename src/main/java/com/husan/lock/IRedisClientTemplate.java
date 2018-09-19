package com.husan.lock;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * <p>
 * </p>
 * 
 */
public interface IRedisClientTemplate {



    Boolean exists(String key);

    Long expire(String key, int seconds);

    Long expireAt(String key, long unixTime);

    Long ttl(String key);

    Long decrBy(String key, long integer);

    Long decr(String key);

    Long incr(String key);

    Long incrBy(String key, long integer);

    Long append(String key, String value);

    String set(String key, String value);

    String get(String key);

    Long del(String... keys);

    String setex(String key, int seconds, String value);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    List<String> hmget(String key, String... fields);

    Long hdel(String key, String... fields);

    String hmset(String key, Map<String, String> hash);

    Boolean hexists(String key, String field);

    Map<String, String> hgetAll(String key);

    /**
     * @param key
     * @param values
     * @return
     */
    Long lpush(String key, String... values);

    Long rpush(String key, String... values);

    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lrange(String key, long start, long end);

    /**
     * <p>
     * 限制列表保存数量
     * </p>
     * 
     * @param key
     * @param start
     * @param end
     * @return
     */
    String ltrim(String key, long start, long end);

    /**
     * <p>
     * 向名称为key的set中添加元素member
     * </p>
     * 
     * @param key
     * @param members
     * @return
     */
    Long sadd(String key, String... members);

    /**
     * <p>
     * 返回名称为key的set的所有元素
     * </p>
     * 
     * @param key
     * @return
     */
    Set<String> smembers(String key);

    /**
     * <p>
     * 删除名称为key的set中的元素member
     * </p>
     * 
     * @param key
     * @param members
     * @return
     */
    Long srem(String key, String... members);

    /**
     * <p>
     * 随机返回并删除名称为key的set中一个元素
     * </p>
     * 
     * @param key
     * @return
     */
    String spop(String key);

    /**
     * <p>
     * 返回名称为key的set的元素个数
     * </p>
     * 
     * @param key
     * @return
     */
    Long scard(String key);

    /**
     * <p>
     * 测试member是否是名称为key的set的元素
     * </p>
     * 
     * @param key
     * @param member
     * @return
     */
    Boolean sismember(String key, String member);

    /**
     * <p>
     * 随机返回名称为key的set的一个元素，但不删除元素
     * </p>
     * 
     * @param key
     * @return
     */
    String srandmember(String key);

    /**
     * <p>
     * 向名称为key的zset中添加元素member，score用于排序。如果该元素已经存在，则根据score更新该元素的顺序
     * </p>
     * 
     * @param key
     * @param score
     * @param member
     * @return
     */
    Long zadd(String key, double score, String member);

    /**
     * <p>
     * 返回名称为key的zset（元素已按score从小到大排序）中的index从start到end的所有元素
     * </p>
     * 
     * @param key
     * @param start
     *            从0开始
     * @param end
     *            -1表示所有
     * @return
     */
    Set<String> zrange(String key, int start, int end);

    /**
     * <p>
     * 删除有序集合中的一个元素
     * </p>
     * 
     * @param key
     * @param members
     * @return
     */
    Long zrem(String key, String... members);

    /**
     * <p>
     * 如果在名称为key的zset中已经存在元素member，则改元素的score 增加increment（顺序号）否则向该集合中添加元素，其score的值为increment
     * </p>
     * 
     * @param key
     * @param score
     * @param member
     * @return
     */
    Double zincrby(String key, double score, String member);

    /**
     * <p>
     * 返回名称为key的zset中member元素的排名（按score从小到大排序）即下标,和zrevrank顺序相反
     * </p>
     * 
     * @param key
     * @param member
     * @return
     */
    Long zrank(String key, String member);

    /**
     * <p>
     * 返回名称为key的zset中member元素的排名（按score从大到小排序）即下,和zrank顺序相反标
     * </p>
     * 
     * @param key
     * @param member
     * @return
     */
    Long zrevrank(String key, String member);

    /**
     * <p>
     * 值按降序排序（score从大到小排序）,与zrange顺序相反
     * </p>
     * 
     * @param key
     * @param start
     *            从0开始
     * @param end
     *            -1 表示全部
     * @return
     */
    Set<String> zrevrange(String key, int start, int end);



    /**
     * <p>
     * 返回集合元素中的个数
     * </p>
     * 
     * @param key
     * @return
     */
    Long zcard(String key);

    Double zscore(String key, String member);

    /**
     * <p>
     * 返回集合中score在给定区域的数量
     * </p>
     * 
     * @param key
     * @param min
     * @param max
     * @return
     */
    Long zcount(String key, double min, double max);

    /**
     * <p>
     * 返回集合中score在给定区域的元素,从小到大返回
     * </p>
     * 
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<String> zrangeByScore(String key, double min, double max);

    /**
     * <p>
     * 返回集合中score在给定区域的元素,从大到小返回
     * </p>
     * 
     * @param key
     * @param max
     * @param min
     * @return
     */
    Set<String> zrevrangeByScore(String key, double max, double min);

    Set<String> zrangeByScore(String key, double min, double max, int offset, int count);

    Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);


    /**
     * <p>
     * 删除集合中排名在给定区间的元素（按照索引删除）
     * </p>
     * 
     * @param key
     * @param start
     * @param end
     * @return
     */
    Long zremrangeByRank(String key, int start, int end);

    /**
     * <p>
     * 删除集合中score给定区间的元素（按照顺序号删除）
     * </p>
     * 
     * @param key
     * @param start
     * @param end
     * @return
     */
    Long zremrangeByScore(String key, double start, double end);

    /**
     * <p>
     * 批量向名称为key的zset中添加元素member，score用于排序。
     * </p>
     * 
     * @param key
     * @param scoreMembers
     * @return
     */
    Long zadd(String key, Map<String, Double> scoreMembers);

    Long hincrBy(String key, String field, long value);

    /**
     * <p>
     * 返回hash表中field列表
     * </p>
     * 
     * @param key
     * @return
     */
    Set<String> hkeys(String key);

    List<String> hvals(String key);

    /**
     * <p>
     * 用于为哈希表中不存在的的字段赋值 。<br>
     * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。 如果字段已经存在于哈希表中，操作无效。<br>
     * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     * </p>
     * 
     * @param key
     * @param field
     * @param value
     * @return 设置成功，返回 1 。 如果给定字段已经存在且没有操作被执行，返回 0
     */
    Long hsetnx(String key, String field, String value);

    Long setnx(String key, String value);

    Map<String, Map<String, String>> hgetAllMultiMap(List<String> keys);

    String getAndSet(String key, String value);

    void close();

}