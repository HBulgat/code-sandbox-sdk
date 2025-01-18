//package cn.bulgat;
//
//import cn.bulgat.codesandbox.sdk.constant.CodeSandboxConstant;
//import cn.bulgat.codesandbox.sdk.model.ExecuteCodeRequest;
//import cn.bulgat.codesandbox.sdk.model.ExecuteCodeRequestByFileOrText;
//import cn.bulgat.codesandbox.sdk.model.Input;
//import cn.hutool.json.JSONUtil;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.junit.Test;
//import cn.hutool.http.ContentType;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class FileTest {
//    public static void main(String[] args) {
//        String url = "http://localhost:8077/api/execute_code/execute_code/file";
//        String filePath1 = "/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/111.txt";
//        String filePath2 = "/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/222.txt";
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
//
//            String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";
//            try (OutputStream outputStream = connection.getOutputStream();
//                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream))) {
//
//                // 发送文件部分
//                sendFile(outputStream, writer, boundary, filePath1, "files", "file1.txt");
//                sendFile(outputStream, writer, boundary, filePath2, "files", "file2.txt");
//
//                // 发送 ExecuteCodeRequest 部分，将其作为 JSON 数据发送
//                sendExecuteCodeRequestAsJson(writer, boundary);
//
//                // 结束请求体
//                writer.append("--").append(boundary).append("--").append("\r\n");
//                writer.flush();
//            }
//
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                    String inputLine;
//                    StringBuilder response = new StringBuilder();
//                    while ((inputLine = in.readLine())!= null) {
//                        response.append(inputLine);
//                    }
//                    System.out.println(response.toString());
//                }
//            } else {
//                System.out.println("POST request failed with response code: " + responseCode);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void sendFile(OutputStream outputStream, PrintWriter writer, String boundary, String filePath, String paramName, String fileName) throws IOException {
//        writer.append("--").append(boundary).append("\r\n");
//        writer.append("Content-Disposition: form-data; name=\"").append(paramName).append("\"; filename=\"").append(fileName).append("\"").append("\r\n");
//        writer.append("Content-Type: ").append(Files.probeContentType(Paths.get(filePath))).append("\r\n");
//        writer.append("\r\n");
//        writer.flush();
//
//        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = fileInputStream.read(buffer))!= -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//            outputStream.flush();
//        }
//
//        writer.append("\r\n");
//        writer.flush();
//    }
//
//    private static void sendExecuteCodeRequestAsJson(PrintWriter writer, String boundary) {
//
//        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
//        // 设置 ExecuteCodeRequest 的属性
//        executeCodeRequest.setCode("your_code");
//        executeCodeRequest.setLanguage("java");
//        executeCodeRequest.setInputList(null);
//
//        String json = JSONUtil.toJsonStr(executeCodeRequest);
//
//        writer.append("--").append(boundary).append("\r\n");
//        writer.append("Content-Disposition: form-data; name=\"executeCodeRequest\"").append("\r\n");
//        writer.append("Content-Type: application/json").append("\r\n");
//        writer.append("\r\n");
//        writer.append(json).append("\r\n");
//    }
//
////    public static void main(String[] args) throws IOException {
////        String url = "http://localhost:8077/api/execute_code/execute_code/file";
////        // 创建一个 HttpClient 实例
////        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
////            // 创建一个 HttpPost 请求对象
////            HttpPost httpPost = new HttpPost(url);
//////            // 创建 MultipartEntityBuilder 用于构建多部分请求体
//////            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//////            // 设置边界，避免使用默认边界，可提高兼容性
//////            builder.setBoundary("----WebKitFormBoundary7MA4YWxkTrZu0gW");
//////            // 添加文件部分
//////            builder.addBinaryBody("files", new File(filePath1), ContentType.DEFAULT_BINARY, "file1.txt");
//////            builder.addBinaryBody("files", new File(filePath2), ContentType.DEFAULT_BINARY, "file2.txt");
//////            // 添加 ExecuteCodeRequest 部分，将其作为 JSON 数据发送
//////            String executeCodeRequestJson = "{\"code\":\"your_code\",\"language\":\"java\",\"inputList\":null}";
//////            builder.addTextBody("executeCodeRequest", executeCodeRequestJson, ContentType.APPLICATION_JSON);
//////            // 构建请求实体
//////            HttpEntity entity = builder.build();
////            // 将请求实体设置到 HttpPost 请求中
//////            httpPost.setEntity(entity);
////            // 执行请求
////            HttpResponse response = httpClient.execute(httpPost);
////            // 获取响应状态码
////            int statusCode = response.getStatusLine().getStatusCode();
////            if (statusCode == 200) {
////                // 获取响应内容
////                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
////                System.out.println(responseBody);
////            } else {
////                System.out.println("POST request failed with response code: " + statusCode);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        ExecuteCodeRequestByFileOrText executeCodeRequest=new ExecuteCodeRequestByFileOrText();
////        List<Input> inputList=new ArrayList<>();
////
////        Input input1=new Input();
////        input1.setType("text");
////        input1.setInputText("4 9\n2 7 11 15");
////        inputList.add(input1);
////
////        String filePath = "/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/2.txt";
////        Input input2=new Input();
////        input2.setType("file");
////        inputList.add(input2);
////
////        Input input3=new Input();
////        input3.setType("text");
////        input3.setInputText("2 6\n3 3");
////        inputList.add(input3);
////
////        executeCodeRequest.setInputList(inputList);
////        executeCodeRequest.setCode(code);
////        executeCodeRequest.setLanguage("java");
////
////
////        HttpRequest httpRequest = HttpRequest.post(url);
////
////        // 设置请求头为 multipart/form-data
//////        httpRequest.contentType("multipart/form-data");
////        // 添加文件
//////        httpRequest.form("files", null, "2.txt");
////
////        httpRequest.contentType(ContentType.MULTIPART.getValue());
//////        httpRequest.form("executeCodeRequest",JSONUtil.toJsonStr(executeCodeRequest));
////        httpRequest.body(JSONUtil.toJsonStr(executeCodeRequest));
////        System.out.println(httpRequest);
////        // 发送请求
////        HttpResponse httpResponse = httpRequest.execute();
////        // 处理响应
////        if (httpResponse.isOk()) {
////            System.out.println(httpResponse.body());
////        } else {
////            System.out.println("POST request failed with status code: " + httpResponse.getStatus());
////        }
////    }
//
//   static String code="import java.util.HashMap;\n" +
//            "import java.util.Map;\n" +
//            "import java.util.Scanner;\n" +
//            "\n" +
//            "public class Main {\n" +
//            "    public static void main(String[] args) {\n" +
//            "        Scanner scanner = new Scanner(System.in);\n" +
//            "        int n = scanner.nextInt();\n" +
//            "        int[] nums = new int[n];\n" +
//            "        int target = scanner.nextInt();\n" +
//            "        for (int i = 0; i < nums.length; i++) {\n" +
//            "            nums[i] = scanner.nextInt();\n" +
//            "        }\n" +
//            "        int[] ints = twoSum(nums, target);\n" +
//            "        if (ints!= null) {\n" +
//            "            System.out.println(ints[0] + \" \" + ints[1]);\n" +
//            "        }\n" +
//            "        scanner.close();\n" +
//            "    }\n" +
//            "\n" +
//            "    public static int[] twoSum(int[] nums, int target) {\n" +
//            "        Map<Integer, Integer> map = new HashMap<>();\n" +
//            "        for (int i = 0; i < nums.length; i++) {\n" +
//            "            if (map.containsKey(target - nums[i])) {\n" +
//            "                return new int[]{map.get(target - nums[i]), i};\n" +
//            "            }\n" +
//            "            map.put(nums[i], i);\n" +
//            "        }\n" +
//            "        return null;\n" +
//            "    }\n" +
//            "}\n";
//
////    @Test
////    public void test(){
////        String url = "http://localhost:8077/api/execute_code/execute_code/file";
////        HttpRequest httpRequest=HttpRequest.post(url);
////        httpRequest.contentType(ContentType.MULTIPART.getValue());
////        ExecuteCodeRequestByFileOrText executeCodeRequestByFileOrText=new ExecuteCodeRequestByFileOrText();
////        List<Input> inputList=new ArrayList<>();
////        Input input1=new Input();
////        input1.setType("text");
////        input1.setInputText("4 9\n2 7 11 15\n");
////        inputList.add(input1);
////
////        String filePath = "/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/2.txt";
////        Input input2=new Input();
////        input2.setType("file");
////        input2.setInputFileName("2.txt");
////        inputList.add(input2);
////
////        executeCodeRequestByFileOrText.setInputList(inputList);
////        executeCodeRequestByFileOrText.setCode(code);
////        executeCodeRequestByFileOrText.setLanguage("java");
////        httpRequest.contentType(ContentType.MULTIPART.getValue());
////        httpRequest.form("files",FileUtil.file(filePath),"2.txt");
////        httpRequest.contentType(ContentType.JSON.getValue());
////        httpRequest.form("executeCodeRequest",JSONUtil.toJsonStr(executeCodeRequestByFileOrText));
////        HttpResponse httpResponse = httpRequest.execute();
////        System.out.println(httpResponse.getStatus());
////        System.out.println(httpResponse.body());
////    }
//
//}
