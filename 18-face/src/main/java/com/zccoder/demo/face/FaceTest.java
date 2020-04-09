package com.zccoder.demo.face;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.CompareFaceRequest;
import com.aliyuncs.facebody.model.v20191230.CompareFaceResponse;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 人脸对比测试
 * <p>接口文档地址：<a href="https://help.aliyun.com/document_detail/151891.html">人脸对比</a></p>
 *
 * @author zc 2020-04-09
 */
public class FaceTest {

    private static IAcsClient client;

    public static void main(String[] args) throws Exception {
        // 地域 ID
        String regionId = "cn-shanghai";
        // Access Key ID
        String accessKeyId = "you accessKeyId";
        // Access Key Secret
        String secret = "you secret";

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        client = new DefaultAcsClient(profile);
        testCompareFace();
    }

    /**
     * 人脸比对
     */
    private static void testCompareFace() throws Exception {
        System.out.println("--------  人脸比对 --------------");
        CompareFaceRequest req = new CompareFaceRequest();
        // 注意：下面的链接换成自有的oss链接
        req.setImageURLA("https://zccoder-face.oss-cn-shanghai.aliyuncs.com/test1.jpeg");
        req.setImageURLB("https://zccoder-face.oss-cn-shanghai.aliyuncs.com/test2.jpg");

        CompareFaceResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
    }

    private static <R extends RpcAcsRequest<T>, T extends AcsResponse> T getAcsResponse(R req) throws Exception {
        try {
            return client.getAcsResponse(req);
        } catch (ServerException e) {
            // 服务端异常
            System.out.println(String.format("ServerException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (ClientException e) {
            // 客户端错误
            System.out.println(String.format("ClientException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            throw e;
        }
    }

    private static void printResponse(String actionName, String requestId, AcsResponse data) {
        System.out.println(String.format("actionName=%s, requestId=%s, data=%s", actionName, requestId, JSON.toJSONString(data)));
    }
}
