package com.bulgat.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.bulgat.constant.CodeSandboxConstant;
import com.bulgat.model.ExecuteCodeRequest;
import com.bulgat.model.ExecuteCodeResponse;
import com.bulgat.utils.SignUtils;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CodeSandboxClient {
    private String accessKey;
    private String secretKey;
    private String url;
    public CodeSandboxClient(String accessKey,String secretKey){
        this(accessKey,secretKey, CodeSandboxConstant.DEFAULT_REMOTE_SERVER_URL);
    }
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest){
        HttpRequest httpRequest=buildHttpRequest(executeCodeRequest);
        HttpResponse httpResponse = httpRequest.execute();
        if(!httpResponse.isOk()){
            throw new RuntimeException("http调用异常");
        }
        String responseStr = httpResponse.body();
        Map responseBody = JSONUtil.toBean(responseStr, Map.class);
        System.out.println(responseBody);
        String dataStr=null;
        Integer code=null;
        try {
            dataStr= (String) responseBody.get("data");
            code=(Integer) responseBody.get("code");
        }catch (Exception e){
            throw new RuntimeException("代码沙箱调用异常");
        }
        if (code==null||code.intValue()!=0){
            throw new RuntimeException("代码沙箱调用异常,response code="+code);
        }
        ExecuteCodeResponse executeCodeResponse=JSONUtil.toBean(dataStr, ExecuteCodeResponse.class);
        return executeCodeResponse;
    }

    private Map<String,String> buildHeaders(String body){
        Map<String,String> headers=new HashMap<>();
        headers.put(CodeSandboxConstant.AUTH_HEADER_ACCESS_KEY,accessKey);
        headers.put(CodeSandboxConstant.AUTH_HEADER_NONCE, RandomUtil.randomNumbers(4));
        headers.put(CodeSandboxConstant.AUTH_HEADER_BODY,body);
        headers.put(CodeSandboxConstant.AUTH_HEADER_TIMESTAMP,String.valueOf(System.currentTimeMillis()));
        headers.put(CodeSandboxConstant.AUTH_HEADER_SIGN, SignUtils.getSign(headers,secretKey));
        return headers;
    }

    private HttpRequest buildHttpRequest(ExecuteCodeRequest executeCodeRequest) {
        String requestBody = JSONUtil.toJsonStr(executeCodeRequest);
        Map<String, String> headers = buildHeaders(requestBody);
        return HttpRequest.post(url)
                .addHeaders(headers)
                .body(requestBody);
    }
}