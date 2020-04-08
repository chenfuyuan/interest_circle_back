package com.cfy.interestback;

import com.cfy.interestback.mapper.ArticleMapper;
import com.cfy.interestback.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ArticleTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void getList() {
        List<Article> list = articleMapper.getList();
        log.info("list");
        for (Article article : list) {
            log.info("article = " + article);
        }
    }
}
