package com.example.qr_codescan;


import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HttpConnection异步请求实用类
 * 使用方法:
 * HttpUtil.sendHttpRequest(address, new HttpCallbackListener(){
 *
 * @Override public void onFinish(String response){
 * // 根据返回内容执行具体的逻辑
 * }
 * @Override public void onError(Exception e){
 * // 处理异常
 * }
 * }
 * Note:上面的实现也都是在子线程中,如果想要更新ui,要使用异步消息
 * Created by phnix on 12/3/2014.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address,
                                       final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }

    /**
     * android 4.0以后,不能在主线程使用网络访问功能
     *
     * @param address
     * @return
     */
    public static String sendHttpRequestSync(String address) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    public static void setImageViewWithUrl(final ImageView imageView, String url) {
        ImageLoadedCallback callback = new ImageLoadedCallback() {
            @Override
            public void loaded(Bitmap bitMap, String url) {
                imageView.setImageBitmap(bitMap);
            }
        };
        new DownloadImageTask(callback).execute(url);
    }
}
