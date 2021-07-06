package net.kyong.template.util;

import com.alibaba.fastjson.JSONObject;
import net.kyong.template.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.Set;


/**
 * http连接工具类
 */
public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	private static RequestConfig requestConfig = RequestConfig.custom()
			.setConnectTimeout(35000)
			.setConnectionRequestTimeout(35000)
			.setSocketTimeout(60000)
			.build();

	/**
	 * 使用utf-8编码的简单get请求
	 */
	public static String doGetByMap(String url, Map<String, String> paramMap, Map<String, String> headers) throws Exception {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		String result = "";
		// 拼接URL
		if (null != paramMap) {
			StringBuffer strBuf = new StringBuffer(url);
			Set<String> keySet = paramMap.keySet();
			strBuf.append("?");
			for (String key : keySet) {
				strBuf.append(key).append("=");
				strBuf.append(paramMap.get(key)).append("&");
			}
			url = StringUtils.substringBeforeLast(strBuf.toString(), "&");
		}
		try {
			httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			// 传入请求头数据
			if (null != headers) {
				httpGet.setHeader("Cookie", headers.get("Cookie"));
			}
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
			// 执行请求，得到返回数据
			response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			// 转换成字符串，最终返回
			result = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != httpclient) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送 POST 请求（HTTP），JSON形式
	 *
	 * @param apiUrl
	 * @param json   json对象
	 * @return
	 */
	public static String doPostJson(String apiUrl, Object json) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		JSONObject jsonObject=new JSONObject();
		try {
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			logger.info("接口返回response:"+response.toString());
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
		} catch (HttpHostConnectException e){
			jsonObject.put("msg","连接拒绝");
			jsonObject.put("code", Constants.CONNECT_EXCEPTION_CODE);
			logger.error("连接拒绝",e);
			httpStr=jsonObject.toString();
		}catch (SocketTimeoutException se){
			jsonObject.put("msg","连接超时");
			jsonObject.put("code",Constants.CONNECT_EXCEPTION_CODE);
			logger.error("连接超时",se);
			httpStr=jsonObject.toString();
		}catch (Exception e){
			jsonObject.put("msg","服务异常");
			jsonObject.put("code",Constants.CONNECT_EXCEPTION_CODE);
			logger.error("服务异常",e);
			httpStr=jsonObject.toString();
		}finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("服务异常",e);
				}
			}
		}
		return httpStr;
	}

}
