package com.rimi.market.client;

import com.rimi.customer.api.vo.CustomerInfo;
import com.rimi.market.client.hystrix.CustomerServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author shangzf
 */
@FeignClient(value = "customer-service", fallback = CustomerServiceHystrix.class)
public interface CustomerServiceClient {

    /**
     * 根据ID获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    CustomerInfo getCustomerById(@PathVariable("id") String id);

    /**
     * 保存信息
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    String saveCustomer(@RequestBody CustomerInfo info);

    /**
     * 更新
     *
     * @param info
     */
    @RequestMapping(value = "/api/customer", method = RequestMethod.PUT)
    void updateCustomer(@RequestBody CustomerInfo info);
}
