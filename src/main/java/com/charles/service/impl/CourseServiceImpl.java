package com.charles.service.impl;

import com.charles.bean.Course;
import com.charles.mapper.CourseMapper;
import com.charles.service.ICourseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
