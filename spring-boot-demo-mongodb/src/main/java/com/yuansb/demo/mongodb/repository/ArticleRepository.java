package com.yuansb.demo.mongodb.repository;

import com.yuansb.demo.mongodb.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 文章 Dao
 */
public interface ArticleRepository extends MongoRepository<Article, Long> {
    /**
     * 根据标题模糊查询
     *
     * @param title 标题
     * @return 满足条件的文章列表
     */
    List<Article> findByTitleLike(String title);
}
