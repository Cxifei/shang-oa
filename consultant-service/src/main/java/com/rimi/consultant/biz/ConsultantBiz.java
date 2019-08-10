package com.rimi.consultant.biz;

import com.rimi.consultant.client.CustomerServiceClient;
import com.rimi.consultant.pojo.Consultant;
import com.rimi.consultant.service.IConsultantService;
import com.rimi.consultant.vo.ConsultantVO;
import com.rimi.customer.api.vo.CustomerInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shangzf
 */
@Component
@Transactional
public class ConsultantBiz {

    @Autowired
    private IConsultantService consultantService;
    @Autowired
    private CustomerServiceClient customerServiceClient;

    public Consultant saveNetworkConsultation(ConsultantVO network) {

        if (StringUtils.equals(network.getEffectiveness(), "无效")) {
            return null;
        }

        // 保存学生的信息
        CustomerInfo customerInfo = new CustomerInfo();
        // 把network对象中的学生数据拷贝到student中
        BeanUtils.copyProperties(network, customerInfo);

        if (StringUtils.isBlank(customerInfo.getTelephone())) {
            customerInfo.setTelephone("0");
        }

        //保存学生对象,获得保存成功的学生的ID
        String customerId = customerServiceClient.saveCustomer(customerInfo);

        // 如果学生ID为null时，这说明手机号码已存在或者手机号码没有填写
        if (customerId == null) {
            // throw new CommonException(ErrorCode.TELEPHONE_IS_NULL_OR_EXIST);
            return null;
        }

        Consultant consultation = new Consultant();
        BeanUtils.copyProperties(network, consultation);

        consultation.setCustomer(customerId);


        return consultantService.save(consultation);
    }

    public Consultant updateNetworkConsultation(ConsultantVO network) {
        // 获取原先的数据
        Consultant consultation = consultantService.selectById(network.getCid());

        // 保存学生的信息

        CustomerInfo customerInfo = customerServiceClient.getCustomerById(consultation.getCustomer());
        BeanUtils.copyProperties(network, customerInfo);
        customerServiceClient.updateCustomer(customerInfo);

        BeanUtils.copyProperties(network, consultation);

        return consultantService.update(consultation);
    }

    public int saveNetworkConsultationByList(List<ConsultantVO> list) {
        int sum = 0;
        for (ConsultantVO network : list) {
            Consultant consultant = this.saveNetworkConsultation(network);
            if (consultant != null) {
                sum++;
            }
        }
        return sum;
    }

    public PageImpl selectByParamForPage(int pageNum, int pageSize) {
        // 设置排序规则
        Sort sort = Sort.by(Sort.Direction.DESC, "updTime");
        // 设置分页信息，默认分页从0开始
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<Consultant> page = consultantService.selectByParamForPage(pageable);
        List<ConsultantVO> consultantVOList = getNetworkVOS(page.getContent());
        return new PageImpl<>(consultantVOList, page.getPageable(), page.getTotalElements());
    }

    public List<ConsultantVO> getAll() {
        List<Consultant> consultants = consultantService.selectListAll(Sort.by(Sort.Direction.DESC, "updTime"));
        return getNetworkVOS(consultants);
    }

    private List<ConsultantVO> getNetworkVOS(List<Consultant> consultants) {
        List<ConsultantVO> consultantVOList = new ArrayList<>();
        for (Consultant consultant : consultants) {
            ConsultantVO consultantVO = new ConsultantVO();
            CustomerInfo customerInfo = customerServiceClient.getCustomerById(consultant.getCustomer());
            // 如果学生信息没有查询到，则不做处理
            if (customerInfo != null) {
                BeanUtils.copyProperties(customerInfo, consultantVO);
            }
            BeanUtils.copyProperties(consultant, consultantVO);
            consultantVO.setCid(consultant.getId());
            consultantVOList.add(consultantVO);
        }
        return consultantVOList;
    }


    // 删除所有
    public void deleteAll() {
        consultantService.deleteAll();
    }
}
