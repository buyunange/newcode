package com.jbj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Set;


@Service
public class JedisAdapter implements InitializingBean {
    private JedisPool pool;
    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

    public static void main(String[] args) {
        Jedis jedis = new Jedis("redis://localhost:6379/9");
        jedis.set("hello", "world");
        System.out.println(jedis.get("hello"));
        jedis.setex("hello2", 10, "world2");  //设置过期时间为一秒
        jedis.set("num1", "100");
        jedis.incr("num1");  //将数值+1
        System.out.println(jedis.get("num1"));
        jedis.incrBy("num1", 5);
        System.out.println(jedis.get("num1"));
        jedis.decrBy("num1", 5);  //减5
        System.out.println(jedis.get("num1"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/10");
    }

    //redis 添加string 数据
    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
        return 0;
    }

    //redis 移除
    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
        return 0;
    }

    //获取数量
    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
        return 0;
    }

    //判断是不是成员之一
    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }
        return false;
    }

    public long lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return 0;
    }

    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public Jedis getJedis() {
        return pool.getResource();
    }

    public Transaction mulit(Jedis jedis) {
        try {
            return jedis.multi();
        } catch (Exception e) {

            logger.error("发生异常" + e.getMessage());
        }
        return null;
    }

    public List<Object> exec(Transaction tx, Jedis jedis) {
        try {
            return tx.exec();
        } catch (Exception e) {

            logger.error("发生异常" + e.getMessage());
        } finally {
            if (tx != null) {
                try {
                    tx.discard();
                } catch (Exception e) {
                    logger.error("发生异常" + e.getMessage());
                }
            }
            if (jedis != null) {
                jedis.close();
            }

        }
        return null;
    }

    public long zadd(String key, double score, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zadd(key, score, value);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return 0;
    }

    public Set<String> zrerange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return null;
    }

    public long zcard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zcard(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return 0;
    }

    public Double zscore(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zscore(key,member);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return null;
    }
}
