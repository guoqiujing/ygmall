package cn.myzqu.ygmall.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 网络请求工具类
 * Created by 的川 on 2018/5/10.
 */
public class HttpRequestUtil {

    /**
     * GET请求
     * @param urlParam
     * @param params
     * @return
     * @throws IOException
     */
    public static String get(String urlParam, Map<String, Object> params)  {

        StringBuffer resultBuffer = null;
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
        }
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (sbParams != null && sbParams.length() > 0) {
                osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                osw.flush();
            }
            resultBuffer = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭连接
            if(br!=null) try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(osw!=null) try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(con!=null) con.disconnect();
        }
        return resultBuffer.toString();

    }
}
