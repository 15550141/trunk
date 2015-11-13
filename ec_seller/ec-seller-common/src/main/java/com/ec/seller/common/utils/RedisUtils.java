package com.ec.seller.common.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
	private static JedisPool pool;
	
	static {  
	    JedisPoolConfig config = new JedisPoolConfig();  
	    config.setMaxActive(1024);  
	    config.setMaxIdle(200);  
	    config.setMaxWait(5000);  
	    config.setTestOnBorrow(true);  
	    config.setTestOnReturn(true);  
	    pool = new JedisPool(config, "124.202.141.219", 6379);
	} 
	
	public static String get(String key){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.get(key);
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
	}
	
	public static void set(String key, int seconds, String value){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			jedis.setex(key, seconds, value);
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
	}
	
	public static void set(String key, String value){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			jedis.set(key, value);
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
	}
	
	public static boolean exists(String key){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.exists(key);
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
	}
	
	public static void del(String key){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			jedis.del(key);
		}finally{
			if(jedis != null){
				pool.returnResource(jedis);
			}
		}
	}
	
	public static void main(String[] args) {
		RedisUtils.set("ceshi", "1");
		System.out.println(RedisUtils.get("ceshi"));
	}
}
