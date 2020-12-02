package com.jbj.dao;

import com.jbj.model.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewsDao {
    String TABLE_NAME = "news";
    String INSERT_FIELDS = "user_id ,title, link, image, like_count, created_date, comment_count";
    String SELECT_FIELDS = INSERT_FIELDS+",id";
    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{user_id},#{title},#{link},#{image},#{like_count}," +
            "#{created_date},#{comment_count})"})
    int  addNews(News news);


    List<News> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset,
                                       @Param("limit") int limit);
}
