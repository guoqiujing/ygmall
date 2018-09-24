package cn.myzqu.ygmall.utils;

import cn.myzqu.ygmall.exception.CustomException;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by 的川 on 2018/8/5.
 */
public class FileUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);


    /**
     * 删除
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
    /**
     * 保存文件到本地服务器
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    public static String uploadToLocalHost(MultipartFile file, HttpServletRequest request) throws Exception{

        String result = null;
        // 如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //控制图片大小
            if (file.getSize() >= 1*1024*1024)
            {
                throw new CustomException(1,"上传文件或图片不能大于1M!");
            }
            String files = "/upload/temp/";
            // 上传文件路径
            String path = request.getServletContext().getRealPath(files);
            //获取上传原文件名
            String fileName = file.getOriginalFilename();
            //获取原文件的后缀
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //生成随机文件名称
            String newFileName = KeyUtil.getUUID() + suffix;
            File filepath = new File(path, newFileName);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            // 将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + newFileName));
            // result = path + File.separator + newFileName;
            //获取保存后的文件的绝对地址
            String saveFilePath = filepath.getAbsolutePath();
            //根据情况进行图片压缩
            if(file.getSize() >= 0.5*1024*1024){
                //大于500k的情况，压缩0.5
                Thumbnails.of(saveFilePath).scale(0.5f).toFile(saveFilePath);
            }else if(file.getSize() >= 0.2*1024*1024){
                //大于200k的情况，压缩0.8
                Thumbnails.of(saveFilePath).scale(0.8f).toFile(saveFilePath);
            }else if(file.getSize()>=0.1*1024*1024){
                //大于100k的情况，压缩0.9
                Thumbnails.of(saveFilePath).scale(0.9f).toFile(saveFilePath);
            }
            //其他情况不压缩
            //获取保存文件的服务器地址
            String contextPath = request.getContextPath();
            //如果地址是ip地址，则加上端口号
            String server = request.getServerName();
            if(IPUtil.ipCheck(server)||"localhost".equals(server)){
                result = request.getScheme()+"://"+request.getServerName()+
                        ":"+request.getServerPort()+
                        contextPath+files+newFileName;
            }else{
                //如果地址是域名，则不需要
                result = request.getScheme()+"://"+request.getServerName()+
                        contextPath+files+newFileName;
            }
            logger.info("上传文件成功:"+ result);
        } else {
            logger.info("上传文件失败");
        }
        return result;
    }

}
