package com.fzn.pesystem.concase.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.fzn.pesystem.concase.util.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;

@Service
public class FileServiceImpl implements IFileService {
    @Override
    public String addAttachments(String type,Integer typeId, MultipartFile file){
        String fileName = generateRandomFilename();
        String fileType = getFileType(file);

        //新名称
        String newName = fileName + fileType;

        String fileUrl = type + "/" + typeId + "/" + newName;
        if(uploadFile(fileUrl,file)){
            String uploadUrl = "https://" + ConstantPropertiesUtil.BUCKET_NAME + "." + ConstantPropertiesUtil.END_POINT + "/" + fileUrl;
            return uploadUrl;
        }else {
            return "false";
        }
    }

    @Override
    public String addVideo(String type, Integer completionId, MultipartFile video) {
        String fileName = generateRandomFilename();
        String fileType = getFileType(video);
        //新名称
        String newName = fileName + fileType;

        String fileUrl = type + "/" + completionId + "/" + "Video/" + newName;
        if(uploadFile(fileUrl,video)){
            String uploadUrl = "https://" + ConstantPropertiesUtil.BUCKET_NAME + "." + ConstantPropertiesUtil.END_POINT + "/" + fileUrl;
            return uploadUrl;
        }else {
            return "false";
        }
    }

    @Override
    public String downloadFile(String downLoadUrl, String saveUrl) {
        try {
            String endpoint = ConstantPropertiesUtil.END_POINT;
            String accessKeyId = ConstantPropertiesUtil.KEY_ID;
            String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
            String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

            Integer totalLength = downLoadUrl.length();
            Integer preLength = ConstantPropertiesUtil.BUCKET_NAME.length()+ConstantPropertiesUtil.END_POINT.length()+10;
            String objectName =  downLoadUrl.substring(preLength,totalLength);
            saveUrl = saveUrl + "/"+downLoadUrl.substring(downLoadUrl.lastIndexOf("/"),totalLength);

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(saveUrl));
            // 关闭OSSClient。
            ossClient.shutdown();
        }catch (Exception e) {
            return e.getMessage();
        }
        return "true";
    }

    public Boolean uploadFile(String fileUrl,MultipartFile file){
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, fileUrl, inputStream);
            ossClient.shutdown();
        }catch (IOException e){
            return false;
        }
        return true;
    }


    /**
     * 生成随机文件名
     * @return
     */
    public String generateRandomFilename(){
        String RandomFilename = "";
        Random rand = new Random();
        int random = rand.nextInt();
        Calendar calCurrent = Calendar.getInstance();
        int intDay = calCurrent.get(Calendar.DATE);
        int intMonth = calCurrent.get(Calendar.MONTH) + 1;
        int intYear = calCurrent.get(Calendar.YEAR);
        String now = String.valueOf(intYear) + "_" + String.valueOf(intMonth) + "_" + String.valueOf(intDay) + "_";
        RandomFilename = now + String.valueOf(random > 0 ? random : ( -1) * random);
        return RandomFilename;
    }

    /**
     * 获取原始文件的文件类型
     * @param file
     * @return
     */
    public String getFileType(MultipartFile file){
        //原始文件名
        String original = file.getOriginalFilename();
        // Chrome 会获取到该文件的直接文件名称，IE/Edge会获取到文件上传时完整路径/文件名
        int unixSep = original.lastIndexOf('/');
        int winSep = original.lastIndexOf('\\');
        int pos = (winSep > unixSep ? winSep : unixSep);
        if (pos != -1) {
            original = original.substring(pos + 1);
        }
        String fileType = original.substring(original.lastIndexOf("."));
        return fileType;
    }
}
