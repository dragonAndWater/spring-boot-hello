package com.example.demo.executer.readerBlackList.excelListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.executer.readerBlackList.model.BlackListExcel;
import com.example.demo.executer.readerBlackList.model.ReaderBlackListModel;
import com.example.demo.executer.readerBlackList.service.ReaderBlackListService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

@Slf4j
public class BlackListExcelListener extends AnalysisEventListener<BlackListExcel> {

    private BlackListExcel blackListExcel;

    private ReaderBlackListService readerBlackListService;


    /**
     * 自定义用于暂时存储data
     * 可以通过实例获取该值
     */
    private LinkedList<BlackListExcel> datas = new LinkedList<BlackListExcel>();


    public BlackListExcelListener(BlackListExcel blackListExcel, ReaderBlackListService readerBlackListService) {
        this.blackListExcel = blackListExcel;
        this.readerBlackListService = readerBlackListService;
    }

    public BlackListExcelListener() {
    }

    @Override
    public void invoke(BlackListExcel blackListExcel, AnalysisContext analysisContext) {
        //readerId由 a-z|A-Z|0-9|_ 组成的是正确数据
//        if(Pattern.matches("\\w*",blackListExcel.getReaderId())){
        datas.add(blackListExcel);
//        }
        //根据自己业务做处理
    }

    @SneakyThrows
    @Override
    @Transactional
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        datas.forEach(data -> {
            readerBlackListService.insertByExcelModel(data);
        });
    }
}
