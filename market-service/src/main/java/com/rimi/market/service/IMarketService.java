package com.rimi.market.service;

import com.rimi.common.service.IBaseService;
import com.rimi.market.pojo.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMarketService extends IBaseService<Market,String> {

    /**
     * 添加院校信息
     * @param market
     * @return
     */
    Market save(Market market);

    Market update(Market market);

    Page<Market> selectByParamForPage(Pageable pageable);


}
