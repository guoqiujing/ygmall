package cn.myzqu.ygmall.utils.mybatisRe;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public  void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String pathString = "/mybatisGenerator.xml";
        String filePath = Generator.class.getResource(pathString).getFile();
        File configFile = new File(filePath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   try {
			    Generator generator = new Generator();
	            generator.generator();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
}
