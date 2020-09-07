package org.thraex.business.hbis.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.thraex.business.hbis.entity.News;
import org.thraex.business.hbis.mapper.NewsMapper;
import org.thraex.business.hbis.service.NewsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/09/07 09:02
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    private List<News> mock = Stream.of(
            "北京国金衡信到信达公司开展质量体系认证审核",
            "河钢邯钢信达科技成为河北省数字经济联合会会员单位",
            "信达科技维护人员远程解决客户难题",
            "信达科技有限公司加入FDT协会",
            "全员“岗位素质提升行动”在进行")
            .map(t -> new News().setTitle(t).setCreateTime(LocalDateTime.now())).collect(Collectors.toList());

    @Override
    public List<News> list(long size) {
        List<News> list = this.list(Wrappers.<News>lambdaQuery()
                .orderByDesc(News::getCreateTime).last(size > 0, "LIMIT " + size));
        return list.isEmpty() ? mock : list;
    }

}
