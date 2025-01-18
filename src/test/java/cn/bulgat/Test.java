package cn.bulgat;

import cn.bulgat.codesandbox.sdk.model.Input;
import cn.hutool.http.*;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
            static String code="import java.util.*;\n" +
                "\n" +
                "public class Main{\n" +
                "    public static void main(String[] args){\n" +
                "        Scanner scanner=new Scanner(System.in);\n" +
                "        int a=scanner.nextInt();\n" +
                "        int b=scanner.nextInt();\n" +
                "        System.out.println(a+b);\n" +
                "        scanner.close();\n" +
                "    }\n" +
                "}";
            public static void main(String[] args) throws IOException {
                // 定义请求的URL
                String url = "http://localhost:8077/api/execute_code/execute_code/file";

                // 创建请求对象
                HttpRequest request = HttpUtil.createPost(url);

                // 设置请求头
                request.contentType("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
                // 添加文件参数
                File file1 = new File("/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/1.txt");
                File file2 = new File("/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/2.txt");
                if (file1.exists()) {
                    request.form("files", file1);
                }
                if (file2.exists()) {
                    request.form("files", file2);
                }
                List<Input> inputList=new ArrayList<>();
                inputList.add(new Input("file",null,"1.txt"));
                inputList.add(new Input("file",null,"2.txt"));
                // 添加JSON参数
                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put("code", code);
                jsonMap.put("language", "java");
                jsonMap.put("inputList", inputList);
                JSONObject jsonObject = JSONUtil.parseObj(jsonMap);
                HttpConnection connection = request.getConnection();
                System.out.println(connection);
                request.form("executeCodeRequest",JSONUtil.toJsonStr(jsonObject));
//                MultipartOutputStream multipartOutputStream=new MultipartOutputStream(
//                        request.getConnection().getOutputStream(),
//                        StandardCharsets.UTF_8,
//                        "------------efwfuewfghuwef");
//                multipartOutputStream.write("executeCodeRequest",JSONUtil.toJsonStr(jsonObject));
                // 发送请求并获取响应
                HttpResponse response = request.execute();

                HttpConnection connection1 = request.getConnection();
                System.out.println("cc"+connection1);
                // 处理响应
                if (response.isOk()) {
                    String body = response.body();
                    System.out.println("响应内容: " + body);
                } else {
                    System.out.println("请求失败，状态码: " + response.getStatus());
                }
            }
        }

