package com.example.demo.executer.exportExcel.controller;

import com.example.demo.base.Enum.Msg;
import com.example.demo.base.annonation.BaseAroundAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.exportExcel.service.ExportExcelService;
import com.github.junrar.Junrar;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.ContentType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
/**
 * @Author longtao
 * @Date   2021/3/15
 * @Describe 实现压缩包上传到指定路劲，并解压成文件夹
 * 1：重复上传只保留最新的一个
 * 2：支持zip解压，rar解压
 **/
@Slf4j
@RestController
@RequestMapping("exportExcel")
public class ExportExcelController {
    @Autowired
    private ExportExcelService exportExcelService;
    /**
     * 上传压缩包，将压缩包复制到指定文件夹下
     **/
    @Value("${uploadUrl}")
    private String uploadUrl;
    @BaseAroundAnnotation
    @GetMapping("getFile")
    public void getFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String filePath = "E:/1.IDEA/Workspace/helloFile/龙涛寸照_蓝底.jpg";
        String filePath = "E:/1.IDEA/Workspace/helloFile/龙涛-员工信息表-.xlsx";
        File f = new File(filePath);
        FileInputStream inputStream = new FileInputStream(f);
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
//        response.setContentType("audio/wav");  //wav 录音文件
//        response.setContentType("image/jpeg");  //图片录音文件
        response.setContentType("application/vnd.ms-excel");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }

    @BaseAroundAnnotation
    @PostMapping("uploadPackageAndDeal")
    public BaseResponse uploadPackageAndDeal(@RequestPart(name = "file") MultipartFile file) throws Exception {
        //上传并解压 压缩包
        this.uploadPackage(file);
        //调用excel
        String packageName = file.getOriginalFilename();
        String folderName = packageName.substring(0, packageName.length() - 4);

        Long startTime = System.currentTimeMillis();
        MultipartFile multipartFile = new MockMultipartFile("企业资源信息表.xls", "企业资源信息表.xls", ContentType.APPLICATION_OCTET_STREAM.toString(), new FileInputStream(uploadUrl + folderName + "/企业资源信息表.xls"));
        //解析文件夹内指定的.xlsx文件
        log.debug("使用MultipartFile，转成straem再解析");
        BaseResponse r1 =  this.analysisExcel(multipartFile);
        Long endTime = System.currentTimeMillis();
        log.info("MultipartFile执行时间："+(endTime-startTime));
        if(!Msg.SUCCESS.getCode().equals(r1.getCode())){
            //解析第一个sheet失败,是否继续执行后续的解析
            return r1;
        }

        log.info("使用File, 直接解析");
        Long startTime1 = System.currentTimeMillis();
        File f = new File(uploadUrl + folderName + "/企业资源信息表.xls");
        exportExcelService.analysisFirstSheet(f);
        Long endTime1 = System.currentTimeMillis();
        log.info("File执行时间："+(endTime1-startTime1));
        return new BaseResponse(Msg.SUCCESS);
    }

    /**
     * @Author longtao
     * @Date   2021/3/15
     * @Describe 解析传入的excel文件
     **/
    @BaseAroundAnnotation
    @PostMapping("analysisExcel")
    public BaseResponse analysisExcel(@RequestPart(name = "file") MultipartFile file) {
        //解析excel的第一个sheet
        return exportExcelService.analysisFirstSheet(file);
    }

    @BaseAroundAnnotation
    @PostMapping("uploadPackage")
    public BaseResponse uploadPackage(@RequestPart(name = "file") MultipartFile file) throws Exception {
        String packageName = file.getOriginalFilename();
        String folderName = packageName.substring(0, packageName.length() - 4);
        //检查是否已存在
        log.debug("检查压缩包是否存在");
        File oldFile = new File(uploadUrl + packageName);
        if (oldFile != null) {
            oldFile.delete();
            deleteAllFile(uploadUrl + folderName);
        }
        //复制压缩包
        log.debug("复制压缩包到指定路劲");
        file.transferTo(new File(uploadUrl + packageName));
        log.debug("开始解压...");
        if (file.getOriginalFilename().indexOf(".rar") > 0) {
            File rar = new File(uploadUrl + packageName);
            File destinationFolder = new File(uploadUrl);
            Junrar.extract(rar, destinationFolder);
        }
        if (file.getOriginalFilename().indexOf(".zip") > 0) {
            unPackZip(packageName, uploadUrl);
        }
        //解压
        log.info("上传并解压成功");
        //解析excel
        log.info("使用MultipartFile，转成stream进行解析");
        MultipartFile multipartFile = new MockMultipartFile("企业资源信息表.xls", "企业资源信息表.xls", ContentType.APPLICATION_OCTET_STREAM.toString(), new FileInputStream(uploadUrl + folderName + "/企业资源信息表.xls"));
        return new BaseResponse(Msg.SUCCESS,"文件夹名："+multipartFile.getOriginalFilename());
    }
    /**
     * 递归实现 删除文件夹下所有目录及文件
     **/
    public boolean deleteAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                if (!f.delete()) {
                    return false;
                }
            } else {
                if (!this.deleteAllFile(f.getAbsolutePath())) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public void unPackZip(String fileName, String destPath) throws Exception {
        ZipFile zipFile = new ZipFile(destPath + fileName);
        zipFile.setFileNameCharset(getEncoding(destPath + fileName));
        zipFile.extractAll(destPath);
    }

    private static String getEncoding(String path) throws Exception {
        String encoding = "GBK";
        ZipFile zipFile = new ZipFile(path);
        zipFile.setFileNameCharset(encoding);
        List<FileHeader> list = zipFile.getFileHeaders();
        for (int i = 0; i < list.size(); i++) {
            FileHeader fileHeader = list.get(i);
            String fileName = fileHeader.getFileName();
            if (isMessyCode(fileName)) {
                encoding = "UTF-8";
                break;
            }
        }
        return encoding;
    }

    private static boolean isMessyCode(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 当从Unicode编码向某个字符集转换时，如果在该字符集中没有对应的编码，则得到0x3f（即问号字符?）
            // 从其他字符集向Unicode编码转换时，如果这个二进制数在该字符集中没有标识任何的字符，则得到的结果是0xfffd
            if ((int) c == 0xfffd) {
                // 存在乱码
                return true;
            }
        }
        return false;
    }




}
