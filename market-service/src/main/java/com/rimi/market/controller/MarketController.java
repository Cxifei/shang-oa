package com.rimi.market.controller;

import com.rimi.common.dto.RespDTO;
import com.rimi.common.exception.CommonException;
import com.rimi.common.exception.ErrorCode;
import com.rimi.common.util.ExcelUtils;
import com.rimi.market.biz.MarketBiz;
import com.rimi.market.pojo.Market;
import com.rimi.market.vo.MarketVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(description = "市场相关接口")
@RestController
@RequestMapping("/colleges")
public class MarketController {

    @Autowired
    private MarketBiz marketBiz;

    /**
     * 获取所有市场信息
     *
     * @return
     */
    @ApiOperation(value = "获取所有市场信息", notes = "获取所有市场信息")
    @GetMapping(value = "/getAll", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getAll() {
        List<MarketVO> list = marketBiz.getAll();
        return RespDTO.onSuc(list);
    }

    /**
     * 获取所有网络咨询信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取所有网络咨询信息", notes = "获取所有网络咨询信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "int", defaultValue = "15")})
    @GetMapping(value = "/getAllByPage", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getAllByPage(@RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "15") int pageSize) {
        Page page = marketBiz.selectByParamForPage(pageNum, pageSize);
        return RespDTO.onSuc(page);
    }

    /**
     * 保存市场信息
     *
     * @param info
     * @return
     */
    @ApiOperation(value = "保存市场信息", notes = "保存市场信息")
    @PostMapping(value = "/save", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO save(MarketVO info) {
        // 保存市场信息
        Market consultation = marketBiz.saveCollegesInfo(info);
        if (consultation == null) {
            throw new CommonException(ErrorCode.FAIL);
        }
        return RespDTO.onSuc(consultation);
    }

    /**
     * 根据ID查询市场信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询市场信息", notes = "根据ID查询市场信息")
    @ApiImplicitParam(name = "id", value = "市场ID", paramType = "path")
    @GetMapping(value = "/getCollegesInfoById/{id}", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO getCollegesInfoById(@PathVariable String id) {
        Market market = marketBiz.getCollegesInfoById(id);
        return RespDTO.onSuc(market);
    }

    /**
     * 更新市场信息
     *
     * @param info
     * @return
     */
    @ApiOperation(value = "更新市场信息", notes = "更新市场信息")
    @PostMapping(value = "/update", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO update(MarketVO info) {
        // TODO 更新市场信息,修改所有字段
        if (info == null && StringUtils.isBlank(info.getMid())) {
            throw new CommonException(ErrorCode.FAIL);
        }
        // 更新
        Market consultation = marketBiz.updateCollegesInfo(info);
        if (consultation == null) {
            throw new CommonException(ErrorCode.FAIL);
        }

        return RespDTO.onSuc(consultation);
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
        List<MarketVO> infoList = ExcelUtils.importExcel(file, 1, 1, MarketVO.class);
        int i = marketBiz.saveCollegesInfoByList(infoList);
        return RespDTO.onSuc("导入了" + i + "条");
    }

    /**
     *删除所有数据
     * @return
     */
    @ApiIgnore()
    @ApiOperation(value = "删除所有数据", notes = "删除所有数据（谨慎操作）")
    @GetMapping(value = "/deleteAll", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO deleteAll() {
        marketBiz.deleteAll();
        return new RespDTO();
    }

}
