package com.jbj.util;

import org.omg.CORBA.PUBLIC_MEMBER;

public class RedisKeyUtil {
    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_DISLIKE = "DISLIKE";
    private static String BIZ_EVENTQUEUE = "EVENT_QUEUE";
    // 获取粉丝
    private static String BIZ_FOLLOWER = "FOLLOWER";
    // 关注对象
    private static String BIZ_FOLLOWEE = "FOLLOWEE";
    //构造redis的key
    public  static  String getLikeKey(int entityType,int entityId){
        return BIZ_LIKE+SPLIT+String.valueOf(entityType)+SPLIT+String.valueOf(entityId);
    }
    public  static  String getDisLikeKey(int entityType,int entityId){
        return BIZ_DISLIKE+SPLIT + String.valueOf(entityType)+SPLIT+String.valueOf(entityId);
    }
    public static String getEventQueueKey(){
        return BIZ_EVENTQUEUE;
    }
    // 某个实体的粉丝key
    public static String getFollowerKey(int entityType, int entityId) {
        return BIZ_FOLLOWER + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    // 每个用户对某类实体的关注key
    public static String getFolloweeKey(int userId, int entityType) {
        return BIZ_FOLLOWEE + SPLIT + String.valueOf(userId) + SPLIT + String.valueOf(entityType);
    }
}
