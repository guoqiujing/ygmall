package cn.myzqu.ygmall.utils;

import cn.myzqu.ygmall.exception.CustomException;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by 小奇冰 on 2018/9/28.
 */
public class ImgUtil {
    private final static Logger logger = LoggerFactory.getLogger(ImgUtil.class);
    /**
     * 删除
     * @param files
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 保存文件在本地服务器
     * @param file
     * @param request
     * @return 绝对地址
     * @throws Exception
     */
    public static File saveLocal(MultipartFile file, HttpServletRequest request) throws Exception{
        File saveFile = null;
        // 如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //控制图片大小
            if (file.getSize() >= 4 * 1024 * 1024) {
                throw new CustomException(1, "上传文件不能大于4M!");
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
            saveFile = new File(path, newFileName);
            // 判断路径是否存在，如果不存在就创建一个
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            // 将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + newFileName));
        }
        return saveFile;
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

    /**
     * 压缩图片
     * @param file
     * @param saveFilePath 目标文件地址
     * @return
     */
    public static void compress(File file,String saveFilePath) throws Exception{
        //
        //根据情况进行图片压缩
        if(file.length() >= 0.5*1024*1024){
            //大于500k的情况，压缩0.5
            Thumbnails.of(file).scale(1.0f).outputQuality(0.65f).toFile(saveFilePath);
        }else if(file.length()  >= 0.2*1024*1024){
            //大于200k的情况，压缩0.8
            Thumbnails.of(file).scale(0.8f).toFile(saveFilePath);
        }else if(file.length() >=0.1*1024*1024){
            //大于100k的情况，压缩0.9
            Thumbnails.of(file).scale(0.9f).toFile(saveFilePath);
        }
    }


    /**
     * 压缩图片
     * @param file
     * @throws Exception
     */
    public static void compress(File file) throws Exception{
        //根据情况进行图片压缩
        BufferedImage bim = ImageIO.read(file);
        //限制宽和高，循环压缩宽高，直至都小于1920
        if(bim.getWidth()>1920||bim.getHeight()>1920){
            while(bim.getWidth()>1920||bim.getHeight()>1920){
                Thumbnails.of(file).scale(0.6f).toFile(file);
                bim = ImageIO.read(file);
            }
        }
        bim = ImageIO.read(file);
        if(file.length() >= 1.0*1024*1024){
            //大于1M的情况，不断循环，压缩到1M以下
            while(file.length() >= 1.0*1024*1024) {
                Thumbnails.of(file).scale(1.00f).outputQuality(0.90f).toFile(file);
                System.out.println("大于1M，压缩一次");
            }
        }else if(file.length() >= 0.5*1024*1024){
            //大于500k的情况，压缩0.88
            //尺寸大，体积小的图片再压缩很可能会导致图片损坏,所以宽高都小于500才压缩
            if(bim.getWidth()<500&&bim.getHeight()<500){
                System.out.println("执行大于500k的情况");
                Thumbnails.of(file).scale(1.00f).outputQuality(0.85f).toFile(file);
            }
        }else if(file.length()  >= 0.2*1024*1024){
            //大于200k的情况，压缩0.90
            if(bim.getWidth()<500&&bim.getHeight()<500) {
                System.out.println("执行大于200k的情况");
                Thumbnails.of(file).scale(1.00f).outputQuality(0.90f).toFile(file);
            }
        }
        else if(file.length() >=0.1*1024*1024){
            //大于100k的情况，压缩0.95
            if(bim.getWidth()<500&&bim.getHeight()<500) {
                System.out.println("执行大于100k的情况");
                Thumbnails.of(file).scale(1.00f).outputQuality(0.95f).toFile(file);
            }
        }
    }
}
