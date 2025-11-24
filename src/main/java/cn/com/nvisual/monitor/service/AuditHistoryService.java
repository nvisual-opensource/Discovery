package cn.com.nvisual.monitor.service;


import cn.com.nvisual.monitor.model.JwtRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import cn.com.nvisual.monitor.config.AppConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

/**
 * Created by DanielTog on Jun, 2021
 */
@Service
public class AuditHistoryService {
    public void clearCache() {
        cache.clear();
    }

    @Autowired
    private AppConfig appConfig;
    public JSONObject setAuditHistory(Long instanceId, Long parentId, String tableName, Long fieldId, String fieldAlias, String fieldValue, String oldValue, String action, String username){

        JSONObject response = new JSONObject();

        response.put("instanceId", instanceId);
        response.put("parentId", parentId);
        response.put("tableName", tableName);
        response.put("fieldId", fieldId);
        response.put("fieldAlias", fieldAlias);
        response.put("fieldValue", fieldValue);
        response.put("oldValue", oldValue);
        response.put("action", action);
        response.put("username", username);

        return response;

    }
    public Map<String, Object> getTokenReturn(String url, Map<String, Object> request){
        //获取token
        Map<String, Object> data = new HashMap<>();
        JwtRequest map = new JwtRequest();
         String username = (String) request.get("username");
         String password = (String) request.get("password");
        map.setUsername(username);
        map.setPassword(password);
        map.setNoexp(true);
        map.setNoexppassword("no_exp_token");
        String jsonString = JSON.toJSONString(map);
        try {
            String responseToken = postJSON(url,jsonString);
            Map responseTokenMap = JSONObject.parseObject(responseToken,Map.class);
            System.err.println("responseTokenMap:"+responseTokenMap);
            if("200".equals(responseTokenMap.get("code").toString())){
                 data = (Map<String, Object>)responseTokenMap.get("data");
//                token = (String) data.get("access_token");
            }else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return data;
    }
    public  Map<String, Object> getPostReturnObj(String url,String token,Map<String, Object> requestBody){
        //获取token
        Map<String, Object> data = new HashMap<>();
        Map<String, String> map = new HashMap<String, String>();
        String name = (String) requestBody.get("name");
        map.put("name",name);

        String jsonString = JSON.toJSONString(map);
//        try {
            String responseToken = postJSONToken(url,token,jsonString);
            Map responseTokenMap = JSONObject.parseObject(responseToken,Map.class);
            if("200".equals(responseTokenMap.get("code").toString())){
                data = (Map<String, Object>) responseTokenMap.get("data");
//                data = (Map<String, Object>)responseTokenMap.get("data");
//                token = (String) data.get("access_token");
                return data;
            }else {
                return null;
            }
//        } catch (Exception e) {
//            return null;
//        }
    }
    public  void getPostNoReturn(String url,String token,Map<String, Object> requestBody){

        String jsonString = JSON.toJSONString(requestBody);
//        try {
            String responseToken = postJSONToken(url,token,jsonString);

    }
    public  void getPostNoReturnList(String url,String token,List<Map> requestBody){

        String jsonString = JSON.toJSONString(requestBody);
        String responseToken = postJSONToken(url,token,jsonString);

    }

    public static String postJSON(String url, String paramjson) throws NoSuchAlgorithmException {
        HttpPost post = new HttpPost(url);

        // 设置请求超时配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)    // 设置连接超时为10秒
                .setSocketTimeout(10000)     // 设置读取超时为10秒
                .build();
        try {
        post.setConfig(requestConfig);
        CloseableHttpClient httpClient;
        if (url.startsWith("https")) {

            // 创建 SSLContext，禁用证书验证
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        } else {
            httpClient = HttpClients.createDefault();
        }

            StringEntity stringEntity = new StringEntity(paramjson, ContentType.APPLICATION_JSON);
            post.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取响应的内容对象
                HttpEntity httpEntity = response.getEntity();
                // 转换结果为字符串
                return EntityUtils.toString(httpEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e);
        } catch (KeyStoreException e) {
            System.err.println("https问题："+e);

            throw new RuntimeException(e);

        } catch (KeyManagementException e) {
            System.err.println("https问题："+e);

            throw new RuntimeException(e);
        }
        return null;
    }
    /*
     * json格式的参数 post请求
     * 获取json数据
     * */
    public static String postJSON2(String url,String paramjson,String token){
        HttpPost post = new HttpPost(url);
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            StringEntity stringEntity = new StringEntity(paramjson, ContentType.APPLICATION_JSON);
            post.setEntity(stringEntity);
            post.addHeader("Authorization", token);
            // 传输的类型
            post.addHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                //获取响应的内容对象
                HttpEntity httpEntity = response.getEntity();
                //转换结果位字符串
                return EntityUtils.toString(httpEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static String postJSONToken(String url,String token,String paramjson){
        HttpPost post = new HttpPost(url);
        post.setHeader(HttpHeaders.AUTHORIZATION,  token);
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            StringEntity stringEntity = new StringEntity(paramjson, ContentType.APPLICATION_JSON);
            post.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                //获取响应的内容对象
                HttpEntity httpEntity = response.getEntity();
                //转换结果位字符串
                return EntityUtils.toString(httpEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static void postJSONToken2(String u,String token,Map<String, Object> paramjson){
        try {
            // URL of the API
            URL url = new URL(u);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", token); // Assuming token is a Bearer token

            // Enable input/output streams
            connection.setDoOutput(true);

            // Convert Map to JSON string
            Gson gson = new Gson();
            String jsonInputString = gson.toJson(paramjson);


            // Write the body of the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("nvisual post 接口code: " + responseCode);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object postJSONArrayToken(String url, String token, List<?> data) {
        HttpURLConnection connection = null;
        try {
            // 创建连接
            URL apiUrl = new URL(url);
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization", token);
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(30000);

            // 将List数据转换为JSON字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(data);

            // 发送请求体
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 获取响应码
            int responseCode = connection.getResponseCode();
            String responseBody = "";

            try {
                // 尝试读取正常响应流
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    responseBody = response.toString();
                }
            } catch (IOException e) {
                // 如果正常流读取失败，尝试读取错误流
                InputStream errorStream = connection.getErrorStream();
                if (errorStream != null) {
                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(errorStream, StandardCharsets.UTF_8))) {
                        StringBuilder errorResponse = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            errorResponse.append(line);
                        }
                        responseBody = errorResponse.toString();
                    }
                }
            }

            // 处理响应
            if (responseCode == HttpURLConnection.HTTP_OK) {
                if (!responseBody.isEmpty()) {
                    Object responseObject = objectMapper.readValue(responseBody, Object.class);

                    if (responseObject instanceof List) {
                        return responseObject;
                    } else if (responseObject instanceof Map) {
                        return responseObject;
                    } else {
                        Map<String, Object> errorMap = new HashMap<>();
                        errorMap.put("code", 500);
                        errorMap.put("message", "未知的响应格式");
                        return errorMap;
                    }
                } else {
                    Map<String, Object> successMap = new HashMap<>();
                    successMap.put("code", 200);
                    successMap.put("message", "请求成功，但无响应体");
                    return successMap;
                }
            } else {
                // 错误响应处理
                Map<String, Object> errorMap = new HashMap<>();
                errorMap.put("code", responseCode);
                errorMap.put("message", "请求失败，状态码: " + responseCode);

                if (!responseBody.isEmpty()) {
                    try {
                        Object errorObject = objectMapper.readValue(responseBody, Object.class);
                        if (errorObject instanceof List || errorObject instanceof Map) {
                            errorMap.put("data", errorObject);
                        } else {
                            errorMap.put("response", responseBody);
                        }
                    } catch (Exception e) {
                        errorMap.put("response", responseBody);
                    }
                }

                return errorMap;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("code", 500);
            errorMap.put("message", "转发请求异常: " + e.getMessage());
            return errorMap;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public static void postJSONTokenJob(String u,String token,JSONArray paramjson){
        try {
            // URL of the API
            URL url = new URL(u);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Set headers
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", token); // Assuming token is a Bearer token

            // Enable input/output streams
            connection.setDoOutput(true);

            // Convert JSONArray to JSON string
            String jsonInputString = paramjson.toString();

            // Write the body of the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code job: " + responseCode);

            // Optional: read the response if needed
            // InputStream responseStream = new BufferedInputStream(connection.getInputStream());
            // String response = new BufferedReader(new InputStreamReader(responseStream)).lines().collect(Collectors.joining("\n"));
            // System.out.println("Response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public JSONArray getJsonArray(String paramValue,String token){
        JSONArray ret = new JSONArray();
        try {
            String urlString = appConfig.getnVisual() + paramValue;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",  token); // 添加Token头部

            int responseCode = connection.getResponseCode();
            System.out.println("获取nVisual接口 Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // 关闭连接
                in.close();
                connection.disconnect();
                // 将响应转换为JSONArray
                ret = JSON.parseArray(content.toString());
            } else {
                System.out.println("GET request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public JSONObject getJsonObject(String paramValue, String token) {
        JSONObject ret = new JSONObject();
        try {
            String urlString = appConfig.getnVisual() + paramValue;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", token); // 添加Token头部

            int responseCode = connection.getResponseCode();
            System.out.println("获取nVisual接口 Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // 关闭连接
                in.close();
                connection.disconnect();
                // 将响应转换为JSONObject
                ret = JSON.parseObject(content.toString());
            } else {
                System.out.println("GET request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    public JSONArray getJsonArrayProm(String paramValue){
        JSONArray ret = new JSONArray();
        String url = appConfig.getPrometheus()  + paramValue;
        // 创建HttpClient实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpGet请求
            System.err.println("url:"+url);

            HttpGet request = new HttpGet(url);

            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // 检查响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("普罗米修斯接口 code:" + statusCode);

                // 获取响应实体
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    // 将响应内容转换为字符串
                    String resultString = EntityUtils.toString(entity);

                    // 使用fastjson解析结果字符串
                    JSONObject jsonObject = JSON.parseObject(resultString);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    JSONArray resultArray = dataObject.getJSONArray("result");

                    // 输出结果
                    ret = resultArray;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
    public JSONArray getJsonArrayJob(String paramValue){
        JSONArray ret = new JSONArray();
        String url = paramValue;
        // 创建HttpClient实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpGet请求
            HttpGet request = new HttpGet(url);

            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // 检查响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("获取普罗米修斯接口 code:" + statusCode);

                // 获取响应实体
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    // 将响应内容转换为字符串
                    String resultString = EntityUtils.toString(entity);

                    // 使用fastjson解析结果字符串
                    JSONObject jsonObject = JSON.parseObject(resultString);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    JSONArray resultArray = dataObject.getJSONArray("scrapePools");

                    // 输出结果
                    ret = resultArray;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
    public JSONArray getJsonArrayGrafa(String paramValue) {
        JSONArray ret = new JSONArray();
        String url = paramValue;
        try {
            CloseableHttpClient httpClient;
            if (url.startsWith("https")) {
                // 创建 SSLContext，禁用证书验证
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
                SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
                httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
            } else {
                httpClient = HttpClients.createDefault();
            }

            // 创建HttpGet请求
            HttpGet request = new HttpGet(url);
            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // 检查响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("获取grafana接口 code:" + statusCode);
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 将响应内容转换为字符串
                    String resultString = EntityUtils.toString(entity);
                    // 使用fastjson解析结果字符串
                    JSONArray resultArray = JSON.parseArray(resultString);
                    // 输出结果
                    ret = resultArray;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    public Map getHelloold(String paramValue){
        Map m = new HashMap();
        String url = paramValue;
        System.err.println("验证地址:" + url);
        // 创建HttpClient实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建HttpGet请求
            HttpGet request = new HttpGet(url);

            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // 检查响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("验证响应码:"+statusCode);
                // 获取响应实体
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    // 将响应内容转换为字符串
//                    resultString = EntityUtils.toString(entity);
                    m.put("code",200);
                    m.put("message","verification successful");
                    return m;
                }
            }catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
                m.put("code",500);
                m.put("message","verification failed");
                return m;
            }
        } catch (Exception e) {
            System.err.println(e);

            e.printStackTrace();
            m.put("code",500);
            m.put("message","verification failed");
            return m;
        }

        return m;
    }
    public Map<String, Object> getHello(String paramValue) {
        Map<String, Object> m = new HashMap<>();
        String url = paramValue;
        System.err.println("验证地址:" + url);

        try {
            CloseableHttpClient httpClient;
            if (url.startsWith("https")) {
                // 创建 SSLContext，禁用证书验证
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
                SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
                httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
            } else {
                httpClient = HttpClients.createDefault();
            }

            // 创建 HttpGet 请求
            HttpGet request = new HttpGet(url);

            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // 检查响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("验证响应码:" + statusCode);
                // 获取响应实体
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    m.put("code", 200);
                    m.put("message", "verification successful");
                } else {
                    m.put("code", 500);
                    m.put("message", "verification failed: empty entity");
                }
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
                m.put("code", 500);
                m.put("message", "verification failed");
            }
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            m.put("code", 500);
            m.put("message", "verification failed");
        }

        return m;
    }

    public Long getIdByIp(String urlString, String token) {
        Long id = 0l; // 初始化回参类型
        try {
            // 构建接口 URL，ip 是 Path 参数
            URL url = new URL(urlString);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", token); // 添加Token头部

            // 获取响应码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code getidByip: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应内容
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();

                // 解析返回的 Long 类型ID
                id = Long.parseLong(content.toString());
                System.out.println("getIdByIp ID: " + id);
            } else {
                System.out.println("GET getIdByIp request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

}
