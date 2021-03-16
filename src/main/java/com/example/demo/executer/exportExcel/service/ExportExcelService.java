package com.example.demo.executer.exportExcel.service;

import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.exportExcel.model.CustEnterpriseExcel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface ExportExcelService {
    BaseResponse analysisFirstSheet(MultipartFile file);
    BaseResponse analysisFirstSheet(File file);
    BaseResponse writerCustExcel(List<CustEnterpriseExcel> list);
}
