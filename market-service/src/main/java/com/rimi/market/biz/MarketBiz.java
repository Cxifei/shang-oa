package com.rimi.market.biz;

import com.rimi.common.exception.CommonException;
import com.rimi.common.exception.ErrorCode;
import com.rimi.customer.api.vo.CustomerInfo;
import com.rimi.market.client.CustomerServiceClient;
import com.rimi.market.pojo.Market;
import com.rimi.market.service.IMarketService;
import com.rimi.market.vo.MarketVO;
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
public class MarketBiz {

    @Autowired
    private IMarketService collegesInfoService;
    @Autowired
    private CustomerServiceClient customerServiceClient;

    public Market saveCollegesInfo(MarketVO info) {
        // 保存学生的信息
        CustomerInfo customerInfo = new CustomerInfo();
        // 把collegesInfo对象中的学生数据拷贝到student中
        BeanUtils.copyProperties(info, customerInfo);

        if (StringUtils.isBlank(customerInfo.getTelephone())) {
            customerInfo.setTelephone("0");
        }

        Market market = new Market();
        // 把collegesInfo对象中的学生数据拷贝到info中
        BeanUtils.copyProperties(info, market);

        // 保存学生对象
        String customerId = customerServiceClient.saveCustomer(customerInfo);

        // 如果学生ID为null时，这说明手机号码已存在或者手机号码没有填写
        if (customerId == null) {
            throw new CommonException(ErrorCode.TELEPHONE_IS_NULL_OR_EXIST);
        }

        market.setCustomer(customerId);

        return collegesInfoService.save(market);
    }

    public Market updateCollegesInfo(MarketVO marketVO) {
        // 获取原先的数据
        Market market = collegesInfoService.selectById(marketVO.getMid());

        // 把collegesInfo对象中的学生数据拷贝到info中
        BeanUtils.copyProperties(marketVO, market);

        // 更新学生信息
        CustomerInfo customerInfo = customerServiceClient.getCustomerById(market.getCustomer());
        BeanUtils.copyProperties(marketVO, customerInfo);
        customerServiceClient.updateCustomer(customerInfo);

        return collegesInfoService.update(market);
    }

    public int saveCollegesInfoByList(List<MarketVO> list) {
        int sum = 0;
        for (MarketVO marketVO : list) {
            Market market = this.saveCollegesInfo(marketVO);
            if (market != null) {
                sum++;
            }
        }
        return sum;
    }

    public List<MarketVO> getAll() {
        List<Market> marketList = collegesInfoService.selectListAll(Sort.by(Sort.Direction.DESC, "updTime"));
        return getCollegesInfoVOS(marketList);
    }

    private List<MarketVO> getCollegesInfoVOS(List<Market> markets) {
        List<MarketVO> infoVOList = new ArrayList<>();
        for (Market market : markets) {
            MarketVO marketVO = new MarketVO();
            CustomerInfo customerInfo = customerServiceClient.getCustomerById(market.getCustomer());
            // 如果学生信息没有查询到，则不做处理
            if (customerInfo != null) {
                BeanUtils.copyProperties(customerInfo, marketVO);
            }
            BeanUtils.copyProperties(market, marketVO);
            marketVO.setMid(market.getId());
            infoVOList.add(marketVO);
        }
        return infoVOList;
    }

    public Market getCollegesInfoById(String id) {
        return collegesInfoService.selectById(id);
    }

    /**
     * 删除所有
     */
    public void deleteAll() {
        collegesInfoService.deleteAll();
    }

    public Page<MarketVO> selectByParamForPage(int pageNum, int pageSize) {
        // 设置排序规则
        Sort sort = Sort.by(Sort.Direction.DESC, "updTime");
        // 设置分页信息，默认分页从0开始
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<Market> page = collegesInfoService.selectByParamForPage(pageable);
        List<MarketVO> marketVOList = getCollegesInfoVOS(page.getContent());
        return new PageImpl<>(marketVOList, page.getPageable(), page.getTotalElements());
    }
}
