package com.example.demo.executer.bookInfo.excelListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.executer.bookInfo.model.BookInfoExcelModel;
import com.example.demo.executer.bookInfo.service.BookInfoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

/**
 * @author long_tao
 */
@Slf4j
public class BookInfoExcelListener extends AnalysisEventListener<BookInfoExcelModel> {

    private BookInfoExcelModel bookInfoExcelModel;

    private BookInfoService bookInfoService;

    private ExecutorService executorService;


    /**
     * 自定义用于暂时存储data
     * 可以通过实例获取该值
     */
    private LinkedList<BookInfoExcelModel> datas = new LinkedList<BookInfoExcelModel>();
    private Integer count = 0;


    public BookInfoExcelListener(BookInfoExcelModel bookInfoExcelModel, BookInfoService bookInfoService,ExecutorService executorService) {
        this.bookInfoExcelModel = bookInfoExcelModel;
        this.bookInfoService = bookInfoService;
        this.executorService = executorService;
    }

    public BookInfoExcelListener() {
    }

    @Override
    public void invoke(BookInfoExcelModel bookInfoExcelModel, AnalysisContext analysisContext) {
        //readerId由 a-z|A-Z|0-9|_ 组成的是正确数据
//        if(Pattern.matches("\\w*",blackListExcel.getReaderId())){
        datas.add(bookInfoExcelModel);
//        }
        //根据自己业务做处理
    }


    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        datas.forEach(data -> {
            //考虑到导入书籍数量可能上十万，此处使用线程池
            executorService.execute(()->{
                bookInfoService.insertByExcel(data);
            });
        });
    }

}
