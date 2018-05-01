package org.obsidian.scss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by mobing  on 2017/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/spring-*.xml")
public class GroupWordServiceTest {

    @Autowired
    private GroupWordService groupWordService;

    @Test
    public void getTrie() throws Exception {
        String s = "abc";
        String s1 = new String("abc");
        String s2 = new String("cba");
        String s3 = "cbd";
        System.out.println(s == s1);
        System.out.println(s2 == s3);
    }

    @Test
    public void getServiceGroupIdByContent() throws Exception {
        Integer integer = groupWordService.getServiceGroupIdByContent("sdsadsa");
        System.out.println(integer );
    }

}