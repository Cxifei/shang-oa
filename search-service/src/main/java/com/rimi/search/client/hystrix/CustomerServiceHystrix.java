package com.rimi.search.client.hystrix;

import com.rimi.customer.api.vo.CustomerInfo;
import com.rimi.search.client.CustomerServiceClient;
import com.rimi.search.pojo.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定义接口调用失败，记录日志
 *
 * @author szf
 */
@Component
@Slf4j
public class CustomerServiceHystrix implements CustomerServiceClient {
    @Override
    public List<Customer> getAll() {
        log.error("调用{}异常", "getAll");
        return null;
    }
}
