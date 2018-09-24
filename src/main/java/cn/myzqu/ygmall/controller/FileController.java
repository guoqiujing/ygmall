package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.qcloud.cos.CommentInformation;
import cn.myzqu.ygmall.qcloud.cos.Operate;
import cn.myzqu.ygmall.utils.FileUtil;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by 的川 on 2018/9/24.
 * 文件上传控制器
 */
@RestController
@RequestMapping("/files")
public class FileController {

    //测试上传文件在本地服务器器
    @PostMapping("/local")
    public Result uploadToLocalHost(HttpServletRequest request,  @RequestParam("file") MultipartFile file) throws Exception {
        String filePath = FileUtil.uploadToLocalHost(file,request);
        return ResultVOUtil.success(filePath);

    }

    //测试上传文件在腾讯服务器
    @PostMapping("/qcloud")
    public Result uploadToQCloud(HttpServletRequest request, @RequestParam("file") MultipartFile files) throws Exception {
        //首先将MultipartFile转为File或InputStream
        CommonsMultipartFile cf = (CommonsMultipartFile) files;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        //会在项目的根目录的临时文件夹下生成一个临时文件 *.tmp
        File tempFile = fi.getStoreLocation();
        //调用方法
        String result = Operate.upload(tempFile,new CommentInformation());
        return ResultVOUtil.success(result);

    }



}
