package com.rimi.customer.controller;

import com.rimi.common.dto.RespDTO;
import com.rimi.common.exception.CommonException;
import com.rimi.common.exception.ErrorCode;
import com.rimi.customer.api.vo.CustomerInfo;
import com.rimi.customer.pojo.Customer;
import com.rimi.customer.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shangzf
 */
@Api(description = "客户相关接口")
@RestController
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    /**
     * 根据客户ID查询客户
     *
     * @param id 客户ID
     * @return
     */
    @ApiOperation(value = "根据客户ID查询客户", notes = "根据客户ID查询客户")
    @ApiImplicitParam(name = "id", value = "客户ID", paramType = "path")
    @GetMapping(value = "/customer/{id}", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getCustomerById(@PathVariable String id) {
        Customer customer = customerService.selectById(id);
        if (customer == null) {
            throw new CommonException(ErrorCode.FAIL);
        }
        return RespDTO.onSuc(customer);
    }

    /**
     * 获取所有客户信息
     *
     * @return
     */
    @ApiOperation(value = "获取所有客户信息", notes = "获取所有客户信息")
    @GetMapping(value = "/customers", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getAll() {
        // 查询所以的数据
        List<Customer> customerList = customerService.selectListAll();
        return RespDTO.onSuc(customerList);
    }

    /**
     * 分页获取客户信息
     *
     * @param pageNum  页码
     * @param pageSize 页数
     * @return
     */
    @ApiOperation(value = "分页获取客户信息", notes = "分页获取所有客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "0", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "int", defaultValue = "15", paramType = "path")})
    @GetMapping(value = "/customers/{pageNum}/{pageSize}", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getAllByPage(@PathVariable int pageNum, @PathVariable int pageSize) {
        // 分页获取用户的数据
        Page<Customer> customerByPage = customerService.getCustomerByPage(pageNum, pageSize);
        return RespDTO.onSuc(customerByPage);
    }

    /**
     * 保存客户信息
     *
     * @param info
     * @return
     */
    @ApiOperation(value = "保存客户信息", notes = "保存客户信息")
    @PostMapping(value = "/customer/save", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO saveCustomer(CustomerInfo info) {
        // 保存成功后返回用户的ID
        String id = customerService.saveCustomer(info);
        if (id == null) {
            throw new CommonException(ErrorCode.FAIL);
        }
        return RespDTO.onSuc(id);
    }

    /**
     * 修改客户信息
     *
     * @param info
     * @return
     */
    @ApiOperation(value = "修改客户信息", notes = "修改客户信息")
    @PostMapping(value = "/customer/update", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO updateCustomer(CustomerInfo info) {
        // 更新用户
        customerService.updateCustomer(info);
        return new RespDTO();
    }

    /**
     * 删除所以数据
     *
     * @return
     */
//    @ApiIgnore()
    @ApiOperation(value = "删除所有数据", notes = "删除所有数据（谨慎操作）")
    @GetMapping(value = "/customers/delete", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO deleteAll() {
        // 删除所以的数据
        customerService.deleteAll();
        return new RespDTO();
    }
}
