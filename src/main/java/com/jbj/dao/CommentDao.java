package com.jbj.dao;


import com.jbj.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CommentDao {
    String TABLE_NAME = "comment";
    String INSERT_FIELDS = " user_id, content, entity_id, entity_type, created_date,status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{userId},#{content}," +
            "#{entityId},#{entityType},#{createdDate},#{status})"})
    int addComment(Comment comment);
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,
            "where entity_type=#{entityType} and entity_id=#{entityId} order by id desc"})
    List<Comment> selectCommentByEntity(@Param("entityId") int entityId,
                                        @Param("entityType") int enetityType);
    //获取某个类型评论总数
    @Select({"select count(id) from",TABLE_NAME,"where entity_type=#{entityType} and " +
            "entity_id=#{entityId} order by created_date desc"})
    int getCommentCount(@Param("entityId") int entityId,
                        @Param("entityType") int enetityType);

    //删除评论，讲status置为1
    @Update({"update",TABLE_NAME,"set status = #{status} where id = #{id}"})
    int update(@Param("status") int status,
                @Param("id") int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Comment getCommentById(int id);

    @Select({"select count(id) from ", TABLE_NAME, " where user_id=#{userId}"})
    int getUserCommentCount(int userId);
}
