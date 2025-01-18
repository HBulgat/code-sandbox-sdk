//package cn.bulgat;
//
//import cn.bulgat.codesandbox.sdk.model.ExecuteCodeRequestByFileOrText;
//import cn.bulgat.codesandbox.sdk.model.Input;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectOutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RequestExampleHttpURLConnection {
//
//    static String code="import java.util.HashMap;\n" +
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
//    private static final String LINE_FEED = "\r\n";
//    private static final String BOUNDARY = "===" + System.currentTimeMillis() + "===";
//    private static final String CHARSET = "UTF-8";
//
//    public static void main(String[] args) {
//        String url = "http://localhost:8077/api/execute_code/execute_code/file";
//
//        // 创建 Input 对象列表
//        List<Input> inputList = new ArrayList<>();
//        // 假设文件路径为 "path/to/your/file.txt"
//        File file = new File("/home/bulgat/IdeaProjects/code-sandbox-sdk/docs/2.txt");
//        inputList.add(Input.builder()
//                .type("file")
//                .inputText(null)
//                .inputFile(file)
//                .build());
//
//        // 创建 ExecuteCodeRequestByFileOrText 对象
//        ExecuteCodeRequestByFileOrText request = ExecuteCodeRequestByFileOrText.builder()
//                .inputList(inputList)
//                .code(code)
//                .language("java")
//                .build();
//
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            // 设置请求为 POST 方法
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setUseCaches(false);
//            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
//
//            // 打开输出流，开始发送请求体
//            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
//                // 发送对象部分
//                outputStream.writeBytes("--" + BOUNDARY + LINE_FEED);
//                outputStream.writeBytes("Content-Disposition: form-data; name=\"executeCodeRequestByFileOrText\"" + LINE_FEED);
//                outputStream.writeBytes("Content-Type: multipart/form-data" + LINE_FEED);
//                outputStream.writeBytes(LINE_FEED);
//                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
//                    objectOutputStream.writeObject(request);
//                }
//                outputStream.writeBytes(LINE_FEED);
//
//                // 发送文件部分
//                for (Input input : inputList) {
//                    if (input.getInputFile()!= null) {
//                        outputStream.writeBytes("--" + BOUNDARY + LINE_FEED);
//                        outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + input.getInputFile().getName() + "\"" + LINE_FEED);
//                        outputStream.writeBytes("Content-Type: " + URLConnection.guessContentTypeFromName(input.getInputFile().getName()) + LINE_FEED);
//                        outputStream.writeBytes(LINE_FEED);
//                        try (FileInputStream fileInputStream = new FileInputStream(input.getInputFile())) {
//                            byte[] buffer = new byte[4096];
//                            int bytesRead;
//                            while ((bytesRead = fileInputStream.read(buffer))!= -1) {
//                                outputStream.write(buffer, 0, bytesRead);
//                            }
//                        }
//                        outputStream.writeBytes(LINE_FEED);
//                    }
//                }
//                outputStream.writeBytes("--" + BOUNDARY + "--" + LINE_FEED);
//            }
//
//            // 获取响应
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                    String inputLine;
//                    StringBuilder response = new StringBuilder();
//                    while ((inputLine = in.readLine())!= null) {
//                        response.append(inputLine);
//                    }
//                    System.out.println("Response: " + response.toString());
//                }
//            } else {
//                System.out.println("Error: " + responseCode);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
