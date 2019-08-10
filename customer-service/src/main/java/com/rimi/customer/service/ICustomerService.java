package com.rimi.customer.service;

import com.rimi.common.service.IBaseService;
import com.rimi.customer.api.vo.CustomerInfo;
import com.rimi.customer.pojo.Customer;
import org.springframework.data.domain.Page;

/**
 * @author shangzf
 */
public interface ICustomerService extends IBaseService<Customer, String> {

    /**
     * 分页获取用户信息
     *
     * @param pageNum  页码
     * @param pageSize 每页显示条数
     * @return
     */
    Page<Customer> getCustomerByPage(int pageNum, int pageSize);

    /**
     * 保存客户信息
     *
     * @param info
     * @return
     */
    String saveCustomer(CustomerInfo info);

    /**
     * 更新客户信息
     *
     * @param info
     */
    void updateCustomer(CustomerInfo info);

    /**
     * 根据ID获取客户信息
     *
     * @param id
     * @return
     */
    CustomerInfo findById(String id);

    /**
     * 检查手机号码是否存在,如果 true 说明手机号码存在，false不存在
     * null 说明手机号码为空
     *
     * @param telephone 手机号码
     * @return
     */
    Boolean checkTelephone(String telephone);
}


