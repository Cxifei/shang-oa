package com.rimi.download.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@Api(description = "下载表格模版")
public class DownloadController {

    @ApiOperation(value = "下载", notes = "根据url的type来指定下载对象")
    @ApiImplicitParam(name = "type", value = "类别 1-市场，2-网络", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/download/{type}",method = RequestMethod.GET, headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<InputStreamResource> download(@PathVariable int type) throws IOException {

        //获取根目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
        System.out.println("path:" + path.getAbsolutePath());

        String fileName = "";
        switch (type) {
            case 1:
                fileName = "市场";
                break;
            case 2:
                fileName = "网络";
                break;
            default:
                break;
        }

        // 类别为空或者类别错误
        if ("".equals(fileName)) {
            return null;
        }

        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(), "file");
        FileSystemResource file = new FileSystemResource(new File(upload.getPath() + "/" + fileName + ".xlsx"));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getFilename(), "UTF-8"));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
