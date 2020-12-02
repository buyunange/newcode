package com.jbj.dao;

import com.jbj.model.Comment;
import com.jbj.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageDao {
    String TABLE_NAME = "message";
    String INSERT_FIELDS = " from_id, to_id, content, created_date,has_read, conversation_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ") values(#{fromId},#{toId}," +
            "#{content},#{createdDate},#{hasRead},#{conversationId})"})
    int addMessage(Message message);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME,
            "where conversation_id=#{conversationId} order by created_date limit #{offset},#{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String conversationId,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);
//select *,count(id) as cnt from (select * from message t  ORDER BY t.created_date  desc)
// tt GROUP BY conversation_id desc  ORDER BY created_date desc limit 0,10


    @Select({"select", INSERT_FIELDS, ",count(id) as id from (select * from", TABLE_NAME,
            "ORDER BY  created_date desc) tt  where from_id = #{userId} or to_id=#{userId} " +
                    "GROUP BY conversation_id desc  ORDER BY created_date desc" +
                    " limit #{offset},#{limit}"})
    List<Message> getConversationList(@Param("userId") int userId,
                                      @Param("offset") int offset,
                                      @Param("limit") int limit);

    @Select({"select count(id) from ", TABLE_NAME, " where has_read=0 and to_id=#{userId} and conversation_id=#{conversationId}"})
    int getConvesationUnreadCount(@Param("userId") int userId, @Param("conversationId") String conversationId);

    @Update({"update", TABLE_NAME, "set has_read=#{has_read} where to_id=#{userId} and conversation_id=#{conversationId}"})
    void updateById(@Param("userId") int userId,
                    @Param("conversationId") String conversationId,
                    @Param("has_read") int has_read);
}