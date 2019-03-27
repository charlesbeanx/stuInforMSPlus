package com.charles.service.impl;

import com.charles.bean.Score;
import com.charles.mapper.ScoreMapper;
import com.charles.service.IScoreService;
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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

}
