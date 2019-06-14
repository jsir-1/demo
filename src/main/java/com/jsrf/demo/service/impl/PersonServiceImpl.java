package com.jsrf.demo.service.impl;

import com.jsrf.demo.entity.Person;
import com.jsrf.demo.mapper.PersonMapper;
import com.jsrf.demo.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsrf
 * @since 2019-06-03
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
