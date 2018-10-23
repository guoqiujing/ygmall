package cn.myzqu.ygmall.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091700531987";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCMbCO522MhJKdEY27H2B8ePsL6o3t26gkmCcpwxkxaIh+5A+vcIIGFDvRQze8nQ1SKjY0ZT91ZYq7XYXon58A9Le6/b6DTCsQRIC0ClNPogsBvi9bs/Y1nTiHhxjGwnYYZUY1+XjogtRT9PMntJUTJJ8wxl53IxejCP2mgkAG1151aDNrrbnF3eCTNlMq0/Rhl5Yd+/NacelecqSkwehagvOqfiVCztYoTTmZokFTJGTZevk/gVlv21R+Mjp4fSUot5hN7lk36SeY5peKru1qXHXuKeLqqhfjrqZjjYizkjGVYwmMcrvxi2MKQs+VYvdjj5HmjQzc0IgsjpHRdX0IzAgMBAAECggEAJv50oUzp5y89f8E45yf95DE7hcxWIbx10+lOHSkD1Ne7BW6wVY11ShTQBYxaSAXreRo7v3atGgBMWyUHfgNeH98ceG6wM0q/DZ5owVnAysVcWN11TpROawyIXiDidZAYx/kcmKXj8Gc4r+XskP0Tb+SsUG0w9Kr4qtBfLaYvw2X12eUwxFj9mCoyDajNhxsmYMlyv4bWmk7+0tc5rHsVqs822gBxJAGsWKZNnv6BF2D1+qoCRBXL5+Pf/eaErLj/XD9PvobeCMXIckfIbbu6i+d1QERJN1IuK/yrWA3WlVSE53rje/mcuSYDtj5yknkNlToYvXF/15CysE0zm19M2QKBgQDQMGJlkBoqiJnT4C91mXcAvq28ee4QiKc+Sa6TzLAXtjgKXAytxpwYCI2UiEq62zz+BdazcxCDFX7QdajM0eHp4UisHpDaOBIy2nBKFkDPROTi6wLFc+/zqs5153U7Nxk3pOx9esMxGmJHajcoOc7EtxYGcgLrjYc4BAqmtLfwPwKBgQCsq7KqADj5c5OiBiCLKspr8RlGW2QSisAM/WyoRX4lYD63T/lV9f3/Ef0JHKFfDCxaZNyR1VLJ51GOWb/mVCV49GvVMtAE2QW0Ww5z1ZtJnumm0fBKCn7RTXnf8jBXvkIYs242OPtpkKSNLiMmhhcgPYWu8K59dckkjjyrQfQxDQKBgFVq1KRJCINVApBM1gwRCCN1GIFRIZ0FBGFpB8i6XbGM4NdXRxhxeY/eyoAbn1WlKBQtPxbnaenugtZyv+wxXNiBxzhfXh06jjTxLMso8DRaVvLHa56p39SAyy7S5WWDSMRs1V7yZG2cHqCgcrr9ZNmPKOlCE/KjYcxY1Cnr0eAvAoGAC0zdT3TeRa0Dr2nZPWJahv/rWIhonzIHaZI4RzbtD4Mh95s7RR3pNoU12x/Ni8vfJzdMnBqq5V3HH5rODUPag/TzDavao0aUsf33jysek43b2rgIkr/nFsmMPVS7RzLDXpx3jQLjqT/3FOo9EKtb3hdIpW9I7BsGQ+cvH0Icug0CgYBpaKCRsikVZV1UPQk/dYB0x3An9f0G5/bjD5Xg97k2sVgepaVa+55ivkfAanQ1c0/Ur9wkJSDGYZ7JXKi4PYo2JtviAB269lcu82Y9F3Car9SC7IIKMhIpbkkCbwMBEG5uQnQ84nlBLp9KpqiNPik4f8tDZt5ICrDhtXCunO591A==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArdkxlfGhY7KYWEmSg1++2bwLot2DQ7Q2aqA9MyJQkKHN2WRBPQtmT0lGX6bJSfiJwJnXzUdNuGU0INbBcq5bPBOvTepGHZpoeIWOBnOfGclOZEKk/mXagRjgxhuf1DbwhqGwtcH6zrcLUzuLdNxAgkeOMHUiwmQAh2SbSegCHDbI34TgS9Od0npdtDsgfuaZ/DAbg1gW2AcY3FYGTl9LCsy/rQfoa+MY7s9rtUTA9vXJFdODOYbzLhgcRT3n6w4NuMR4HH+VuxG4/jrRVf4IUkU4XpdvZBvbO/QcS2JDHKoCKl09tRBry/Ifcte8pos6u5uIDmIZ1kzxH4UrQEIPCwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://http://sx3ht5.natappfree.cc/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/page/user/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

