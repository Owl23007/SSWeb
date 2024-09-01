package com.SSweb.blog.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.InputStream;


public class AliOssUtil {
    // 节点域名
    private static final String EndPoint = "";
    // 阿里云KEY_ID
    private static final String ID = "";
    // 阿里云KEY_SECRET
    private static final String SECRET = "";
    // 存储空间名
    private static final String Bucket_Name = "";

    public static String uploadFile(String fileName, InputStream in) throws Exception {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(EndPoint, ID, SECRET);
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(Bucket_Name, fileName, in);
            // 上传文件
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 返回url
            return "https://" + Bucket_Name + "." + EndPoint.substring(EndPoint.lastIndexOf("/") + 1) + "/" + fileName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}
