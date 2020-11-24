//package com.example.demo.scheduler.borrowBook;
//
//import com.example.demo.base.annonation.BaseAroundAnnotation;
//import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
//import com.example.demo.executer.borrowBoook.service.BorrowBookService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//
//@Slf4j
//@Component
//@EnableScheduling
//public class OverdueBookScheduler {
//
//    @Autowired
//    private BorrowBookService borrowBookService;
//
//    //每5秒钟查询一次
//    @BaseAroundAnnotation
////    @Scheduled(fixedDelay = 5 * 1000, initialDelay = 2000)
////    @Scheduled(cron = "*/5 * * * * *")
//    public void getverdueBorrowBook(){
//        log.info("开始执行定时任务：查询所有借阅超期的书籍");
//        List<BorrowBookModel> borrowBookModelList = borrowBookService.selectOverdueBorrowList(new BorrowBookModel());
//        log.info("borrowBookModelList = {}",borrowBookModelList);
//    }
//}
