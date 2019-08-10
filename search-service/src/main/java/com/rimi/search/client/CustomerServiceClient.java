package com.rimi.search.client;

import com.rimi.search.client.hystrix.CustomerServiceHystrix;
import com.rimi.search.pojo.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author shangzf
 */
@FeignClient(value = "customer-server", fallback = CustomerServiceHystrix.class)
public interface CustomerServiceClient {

    /**
     * 获得客户（学生）信息
     *
     * @return
     */
    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    List<Customer> getAll();
}
