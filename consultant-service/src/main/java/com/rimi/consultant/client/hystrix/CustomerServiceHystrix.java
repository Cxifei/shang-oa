package com.rimi.consultant.client.hystrix;

import com.rimi.consultant.client.CustomerServiceClient;
import com.rimi.customer.api.vo.CustomerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定义接口调用失败，记录日志
 *
 * @author szf
 */
@Component
@Slf4j
public class CustomerServiceHystrix implements CustomerServiceClient {
    @Override
    public CustomerInfo getCustomerById(String id) {
        log.error("调用{}异常{}", "getCustomerById", id);
        return null;
    }

    @Override
    public String saveCustomer(CustomerInfo info) {
        log.error("调用{}异常{}", "saveCustomer", info);
        return null;
    }

    @Override
    public void updateCustomer(CustomerInfo info) {
        log.error("调用{}异常{}", "updateCustomer", info);
    }
}
