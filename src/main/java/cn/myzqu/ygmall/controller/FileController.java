package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.qcloud.cos.CommentInformation;
import cn.myzqu.ygmall.qcloud.cos.GoodsInfomation;
import cn.myzqu.ygmall.qcloud.cos.Operate;
import cn.myzqu.ygmall.qcloud.cos.UserInformation;
import cn.myzqu.ygmall.utils.FileUtil;
import cn.myzqu.ygmall.utils.ImgUtil;
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
    public Result uploadToQCloud(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        //首先将MultipartFile转为File或InputStream
        File tempFile = FileUtil.saveLocal(file,request);
        //调用方法
        String result = Operate.upload(tempFile,new CommentInformation());
        //在服务器删除tempFile
        FileUtil.deleteFile(tempFile);
        return ResultVOUtil.success(result);

    }

    //用户头像上传到腾讯服务器
    @PostMapping("/qCloud/userInfo")
    public Result uploadUserIcon(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        //首先将MultipartFile转为File或InputStream
        File tempFile = ImgUtil.saveLocal(file,request);
        //压缩图片
        ImgUtil.compress(tempFile);
        //调用方法
        String result = Operate.upload(tempFile,new UserInformation());
        //在服务器删除tempFile
        ImgUtil.deleteFile(tempFile);
        return ResultVOUtil.success(result);
    }
    //评论图片上传到腾讯服务器
    @PostMapping("/qCloud/commentInfo")
    public Result uploadCommentImg(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        //首先将MultipartFile转为File或InputStream
        File tempFile = ImgUtil.saveLocal(file,request);
        //压缩图片
        ImgUtil.compress(tempFile);
        //调用方法
        String result = Operate.upload(tempFile,new CommentInformation());
        //在服务器删除tempFile
        ImgUtil.deleteFile(tempFile);
        return ResultVOUtil.success(result);
    }
    //商品货品图片上传到腾讯服务器（多文件）
    @PostMapping("/qCloud/goodsInfo")
    public Result uploadGoodsImg(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) throws Exception {
        //首先将MultipartFile转为File或InputStream
        if (files != null && files.length > 0) {
            //定义一个存放图片地址的数组
            String[] result=new String[files.length];
            //循环获取files数组中的文件
            for(int i = 0; i < files.length; i++) {
                File tempFile = ImgUtil.saveLocal(files[i],request);
                //压缩图片
                ImgUtil.compress(tempFile);
                //调用方法
                result[i] = Operate.upload(tempFile,new GoodsInfomation());
                //在服务器删除tempFile
                ImgUtil.deleteFile(tempFile);
            }
            System.out.println(result.toString());
            //把图片地址数组返回前端
            return ResultVOUtil.success(result);
        }
        return ResultVOUtil.error("接收不到图片");
    }

    //评论图片上传到腾讯服务器（多文件）
    @PostMapping("/qCloud/commentsInfo")
    public Result uploadCommentsImg(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) throws Exception {
        //首先将MultipartFile转为File或InputStream
        if (files != null && files.length > 0) {
            //定义一个存放图片地址的数组
            String[] result=new String[files.length];
            //循环获取files数组中的文件
            for(int i = 0; i < files.length; i++) {
                File tempFile = ImgUtil.saveLocal(files[i],request);
                //压缩图片
                ImgUtil.compress(tempFile);
                //调用方法
                result[i] = Operate.upload(tempFile,new CommentInformation());
                //在服务器删除tempFile
                ImgUtil.deleteFile(tempFile);
            }
            System.out.println(result.toString());
            //把图片地址数组返回前端
            return ResultVOUtil.success(result);
        }
        return ResultVOUtil.error("接收不到图片");
    }

}
