package com.rimi.search.utils;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public class PageUtils {
    private PageUtils() {
    }

    public static NativeSearchQuery getSearchQueryByPage(String param, int pageSize, int pageNum) {
        QueryStringQueryBuilder stringQueryBuilder = new QueryStringQueryBuilder(param);
        //如果页码为0，则查询所有
        if (pageSize != 0) {
            Pageable pageable = PageRequest.of(pageSize, pageNum,new Sort(Sort.Direction.DESC,"updTime"));

            return new NativeSearchQueryBuilder().withQuery(stringQueryBuilder).withPageable(pageable).build();
        }

        return new NativeSearchQueryBuilder().withQuery(stringQueryBuilder).build();
    }

    public static NativeSearchQuery getSearchQuery(String param) {
        return getSearchQueryByPage(param,0,10);
    }
}
