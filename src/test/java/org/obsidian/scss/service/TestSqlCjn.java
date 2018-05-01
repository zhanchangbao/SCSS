package org.obsidian.scss.service;

import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.obsidian.scss.dao.KeywordHeatMapper;
import org.obsidian.scss.dao.KeywordMapper;
import org.obsidian.scss.entity.Keyword;
import org.obsidian.scss.entity.KeywordAndHeat;
import org.obsidian.scss.entity.KeywordHeat;
import org.obsidian.scss.entity.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by mobing  on 2017/7/15.
 */
@ContextConfiguration("classpath:spring/spring-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSqlCjn {
    @Autowired
    private KeywordHeatService keywordHeatService;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private  ConversationService conversationService;
    @Autowired
    private KnowledgeService knowledgeService;
    @Test
    public void testGetByIdWithOrdersWithProducts() {
        keywordHeatService.getHeatWord().get(0);
    }
    
    @Test
    public void testPerson(){
//        customerServiceService.selectBySearchName("小");
//        knowledgeService.selectKnowledgeBySearchName("小");
    }
}
