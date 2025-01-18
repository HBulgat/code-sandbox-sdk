package cn.bulgat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MultipartRequestExample {
    private static final String LINE_FEED = "\r\n";
    private static final String BOUNDARY = "===" + System.currentTimeMillis() + "===";
    private static final String CHARSET = "UTF-8";

    public static void main(String[] args) {
        String url = "http://localhost:8077/api/execute_code/execute_code/file";
        String filePath = "/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/2.txt";
        try {
            File uploadFile = new File(filePath);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            // 设置请求为 POST 方法
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            // 打开输出流，开始发送请求体
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                // 发送文件部分
                outputStream.writeBytes("--" + BOUNDARY + LINE_FEED);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + uploadFile.getName() + "\"" + LINE_FEED);
                outputStream.writeBytes("Content-Type: " + URLConnection.guessContentTypeFromName(uploadFile.getName()) + LINE_FEED);
                outputStream.writeBytes(LINE_FEED);

                // 读取文件内容
                try (FileInputStream inputStream = new FileInputStream(uploadFile)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer))!= -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                outputStream.writeBytes(LINE_FEED);
                outputStream.writeBytes("--" + BOUNDARY + "--" + LINE_FEED);
            }

            // 获取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine())!= null) {
                        response.append(inputLine);
                    }
                    System.out.println("Response: " + response.toString());
                }
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
