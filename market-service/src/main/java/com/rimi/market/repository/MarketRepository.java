package com.rimi.market.repository;

import com.rimi.market.pojo.Market;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends MongoRepository<Market, String> {
}
