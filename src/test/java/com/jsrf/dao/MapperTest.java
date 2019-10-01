package com.jsrf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsrf.demo.entity.Person;
import com.jsrf.demo.mapper.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
    //这是mp的一个bug
//    @Autowired
    @Resource
    PersonMapper mapper;

    @Test
    public void insert(){
        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(80);
        Integer row = mapper.insert(person);
        System.out.println(row);

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
    }

    @Test
    public void delete(){
        Integer row = mapper.deleteById(5);
        System.out.println(row);
    }

    @Test
    public void query(){
        Person person = mapper.selectById(6);
        System.out.println(person);
    }

    @Test
    public void queryPage(){
        IPage<Person> personIPage = mapper.selectPageVo(new Page(), 90);
        List<Person> records = personIPage.getRecords();
        System.out.println(records);
    }


}
