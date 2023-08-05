package com.example.updownload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    public static final String URL_PATH = "http://10.0.2.2:8080/web/login";
    private EditText name;
    private EditText pwd;
    private Button login;
    private TextView tvRes;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editText);
        pwd = findViewById(R.id.editText2);
        login = findViewById(R.id.button2);
        tvRes = findViewById(R.id.textView5);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                tvRes.setText(String.valueOf(msg.obj));
                return false;
            }
        });

    }

    public void login(View view) {
        final Map<String, String> params = new HashMap<>();
        params.put("name", name.getText().toString());
        params.put("password", pwd.getText().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
//                get(params);
                post(params);
            }
        }).start();
    }

    public void post(Map<String, String> params) {
        StringBuffer sb = new StringBuffer();
        if (!params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            sb.delete(sb.length() - 1, sb.length());
        }
        Log.i("TEST_URL", sb.toString());
        try {
            URL url = new URL(URL_PATH);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
//            把参数写进输出流里
            DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
            dataOutputStream.write(sb.toString().getBytes());
            dataOutputStream.flush();
            dataOutputStream.close();
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
//                使用dom解析返回的html文本
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                Document dom = documentBuilder.parse(inputStream);
                NodeList body = dom.getElementsByTagName("body");
                String res = body.item(0).getChildNodes().item(0).getNodeValue();
                Log.i("TEST", res);
                Message message = handler.obtainMessage();
                message.obj = res;
                handler.sendMessage(message);
                inputStream.close();
            }
            conn.disconnect();
        } catch (Exception e) {
            Log.i("TEST", "登陆失败");
            Message message = handler.obtainMessage();
            message.obj = "登陆失败";
            handler.sendMessage(message);
            e.printStackTrace();
        }
    }

    public void get(Map<String, String> params) {
        StringBuffer sb = new StringBuffer(URL_PATH);
        if (!params.isEmpty()) {
            sb.append("?");
            for (Map.Entry entry : params.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            sb.delete(sb.length() - 1, sb.length());
        }
        Log.i("TEST_URL", sb.toString());
        try {
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
//                使用dom解析返回的html文本
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                Document dom = documentBuilder.parse(inputStream);
                NodeList body = dom.getElementsByTagName("body");
                String res = body.item(0).getChildNodes().item(0).getNodeValue();
                Log.i("TEST", res);
                Message message = handler.obtainMessage();
                message.obj = res;
                handler.sendMessage(message);
                inputStream.close();
            }
            conn.disconnect();
        } catch (Exception e) {
            Log.i("TEST", "登陆失败");
            Message message = handler.obtainMessage();
            message.obj = "登陆失败";
            handler.sendMessage(message);
            e.printStackTrace();
        }
    }

    public void upload(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                uploadFile();
            }
        }).start();
    }

    public void download(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFile();
            }
        }).start();
    }

    private void downloadFileTime() {
        long start = System.currentTimeMillis();
        downloadFile();
        long end = System.currentTimeMillis();
        Log.i("TEST", "下载用时：" + (end - start) + "ms");
    }

    private void uploadFile() {  // 客户端通过流输入内容，服务端通过流读取内容
        final String URL_PATH = "http://10.0.2.2:8080/web/upload";
        final String CONTENT_TYPE = "text/plain";

//        Log.i("TEST", getFilesDir() + "/1.txt");
//        File file = new File(MainActivity.this.getFilesDir() + "/1.txt");
        Log.i("TEST", getFilesDir() + "/1.jpg");
        File file = new File(getFilesDir() + "/1.jpg");

        try {
            URL url = new URL(URL_PATH);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
//            准备协议头
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("content-Type", CONTENT_TYPE);
            if (file.exists() && file.isFile()) {
                DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
                InputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = inputStream.read(buffer)) > 0) {
                    dataOutputStream.write(buffer, 0, len);
                }
                inputStream.close();
                dataOutputStream.close();

                if (conn.getResponseCode() == 200) {
                    Log.i("TEST", "上传成功");
                } else {
                    Log.i("TEST", "上传失败");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFile() {  // 单线程下载直接开启流读写就行，这里演示多线程下载
//        final String URL_PATH = "https://image.huanghepiao.com/images/upload/20190708/15625564919361.jpg";
        final String URL_PATH = "https://cdn.mysql.com//Downloads/Connector-J/mysql-connector-java-8.0.27.zip";

        final int idx = URL_PATH.lastIndexOf("/");
        final String fileName = URL_PATH.substring(idx+1);

        try {
            URL url = new URL(URL_PATH);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 60 * 1000);
            conn.setReadTimeout(5 * 60 * 1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                final int contentLen = conn.getContentLength();
                final String filePath = getFilesDir() + "/" + fileName;
                Log.i("TEST", "文件内容的长度：" + contentLen);

                Log.i("TEST", "文件的路径：" + filePath);
                RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rwd");
                randomAccessFile.setLength(contentLen);

                int threadNum = 3;
                int block = (int) Math.ceil((float)contentLen / 3.0f);
                List<DownloadThread> list = new ArrayList<>();
                for (int i = 0; i < threadNum; i++) {
                    DownloadThread downloadThread = new DownloadThread(URL_PATH, filePath, i * block, Math.min((i+1) * block, contentLen));
                    list.add(downloadThread);
                    downloadThread.start();
                }
                if (conn.getResponseCode() == 200) {
                    Log.i("TEST", "开始多线程下载");
                } else {
                    Log.i("TEST", "下载失败");
                }
//                for (int i = 0; i < threadNum; i++) {
//                    list.get(i).join();
//                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class DownloadThread extends Thread{
        private String URL_PATH;
        private String filePath;
        private int start;
        private int end;

        public DownloadThread(String URL_PATH, String filePath, int start, int end) {
            this.URL_PATH = URL_PATH;
            this.filePath = filePath;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(URL_PATH);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 60 * 1000);
                conn.setReadTimeout(5 * 60 * 1000);
                conn.setDoInput(true);
                conn.setRequestMethod("GET");

                InputStream inputStream = conn.getInputStream();
                RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rwd");
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = inputStream.read(buffer)) > 0) {
                    randomAccessFile.write(buffer, 0, len);
                }
                inputStream.close();
                randomAccessFile.close();

                if (conn.getResponseCode() == 200) {
                    Log.i("TEST", start + "-" + end + "下载成功");
                } else {
                    Log.i("TEST", start + "-" + end + "下载失败");
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
