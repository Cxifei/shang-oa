package com.rimi.market.service.impl;

import com.rimi.common.service.impl.MongoBaseServiceImpl;
import com.rimi.market.pojo.Market;
import com.rimi.market.repository.MarketRepository;
import com.rimi.market.service.IMarketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl extends MongoBaseServiceImpl<MarketRepository, Market, String> implements IMarketService {

    @Override
    public Market save(Market market) {
        super.insertSelective(market);
        return market;
    }

    @Override
    public Market update(Market market) {
        super.updateSelectiveById(market);
        return market;
    }

    @Override
    public Page<Market> selectByParamForPage(Pageable pageable) {
        return super.selectPage(pageable);
    }
}
