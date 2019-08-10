package com.rimi.consultant.controller;

import com.rimi.common.dto.RespDTO;
import com.rimi.common.exception.CommonException;
import com.rimi.common.exception.ErrorCode;
import com.rimi.common.util.ExcelUtils;
import com.rimi.consultant.biz.ConsultantBiz;
import com.rimi.consultant.pojo.Consultant;
import com.rimi.consultant.vo.ConsultantVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author shangzf
 */
@Api(description = "网络咨询相关接口")
@RestController
@RequestMapping("/network")
public class ConsultantController {

    @Autowired
    private ConsultantBiz consultantBiz;

    /**
     * 获取所有网络咨询信息
     *
     * @return
     */
    @ApiOperation(value = "获取所有网络咨询信息", notes = "获取所有网络咨询信息")
    @GetMapping(value = "/getAll", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getAll() {
        List<ConsultantVO> list = consultantBiz.getAll();
        return RespDTO.onSuc(list);
    }

    /**
     * 获取所有网络咨询信息（分页）
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取所有网络咨询信息（分页）", notes = "获取所有网络咨询信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "int", defaultValue = "15")})
    @GetMapping(value = "/getAllByPage", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getAllByPage(@RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Page page = consultantBiz.selectByParamForPage(pageNum, pageSize);
        return RespDTO.onSuc(page);
    }

    /**
     * 保存网络咨询信息
     *
     * @param network
     * @return
     */
    @ApiOperation(value = "保存网络咨询信息", notes = "保存网络咨询信息")
    @PostMapping(value = "/save", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO save(ConsultantVO network) {
        Consultant consultant = consultantBiz.saveNetworkConsultation(network);
        if (consultant == null) {
            throw new CommonException(ErrorCode.FAIL);
        }
        return RespDTO.onSuc(consultant);
    }

    /**
     * 更新网络咨询信息
     *
     * @param network
     * @return
     */
    @ApiOperation(value = "更新网络咨询信息", notes = "更新网络咨询信息")
    @PostMapping(value = "/update", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO update(ConsultantVO network) {
        // TODO 更新市场信息,无法确定需要修改的字段
        if (network == null && StringUtils.isBlank(network.getCid())) {
            throw new CommonException(ErrorCode.FAIL);
        }
        Consultant consultant = consultantBiz.updateNetworkConsultation(network);
        if (consultant == null) {
            throw new CommonException(ErrorCode.FAIL);
        }

        return RespDTO.onSuc(consultant);
    }

    /**
     * 表格上传
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "表格上传", notes = "表格上传")
    @PostMapping(value = "/upload", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO upload(MultipartFile file) {
        //导入
        List<ConsultantVO> consultantVOList = ExcelUtils.importExcel(file, 0, 1, ConsultantVO.class);
        int i = consultantBiz.saveNetworkConsultationByList(consultantVOList);
        return RespDTO.onSuc("导入了" + i + "条");
    }

    /**
     * 删除所有数据
     *
     * @return
     */
//    @ApiIgnore()
    @ApiOperation(value = "删除所有数据", notes = "删除所有数据（谨慎操作）")
    @GetMapping(value = "/deleteAll", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO deleteAll() {
        consultantBiz.deleteAll();
        return new RespDTO();
    }

}
