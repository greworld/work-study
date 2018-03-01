package com.greworld.edu.transcation;

import com.alibaba.fastjson.JSON;
import com.greworld.edu.transcation.entity.Member;
import com.greworld.edu.transcation.service.MemberService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Ignore
    public void testQuery(){
        try{

            List<Member> list = memberService.query();
            System.out.println(JSON.toJSONString(list,true));

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test
    //@Ignore
    public void testAdd(){
        try{
            boolean r = memberService.add("james");
            System.out.println(r);

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Test
    @Ignore
    public void testRemove(){
        try{

            boolean r = memberService.remove(15L);
            System.out.println(r);

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Test
	@Ignore
    public void testLogin(){

        try{
            memberService.login(3L, "james");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}

