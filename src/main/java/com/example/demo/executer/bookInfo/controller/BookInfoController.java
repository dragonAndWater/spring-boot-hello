package com.example.demo.executer.bookInfo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.base.Enum.LoseFlagEnum;
import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.annonation.BaseAroundAnnotation;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.bookInfo.excelListener.BookInfoExcelListener;
import com.example.demo.executer.bookInfo.model.BookInfoExcelModel;
import com.example.demo.executer.bookInfo.model.BookInfoModel;
import com.example.demo.executer.bookInfo.service.BookInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @Author longtao
 * @Date 2020/10/21
 * @Describe bookInfo 主要用于维护书籍信息
 * 如： 新增（批量\单笔）书籍信息、查询书籍信息、更新书籍信息、
 **/
@Slf4j
@RestController
@RequestMapping(value = "bookInfo")
public class BookInfoController {

    //按类型装配
    @Autowired
    private BookInfoService bookInfoService;

    //按照名称进行装配
    @Resource
    private ExecutorService executorService;

    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertBoookInfo(@RequestBody BookInfoModel model) {
        Boolean flag = bookInfoService.save(model);
        if (flag) {
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    /**
     * @Author longtao
     * @Date 2020/11/24
     * @Describe 更新指定字段，忽略其他字段
     **/
    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateBookInfo(@RequestBody BookInfoModel model) {
        Boolean flag = bookInfoService.update(new UpdateWrapper<BookInfoModel>()
                .lambda()
                .set(BookInfoModel::getBookName, model.getBookName())
                .eq(BookInfoModel::getId, model.getId())
        );
        if (flag) {
            return new BaseResponse(ResultEnum.SUCCESS);
        }
        return new BaseResponse(ResultEnum.FAIL);
    }

    /**
     * @Author longtao
     * @Date 2020/11/24
     * @Describe mybatis 实现简单的新增或更新
     **/
    @BaseAroundAnnotation
    @RequestMapping("saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody BookInfoModel model) {
        //saveOrUpdate 内部逻辑 !StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable)idVal)) ? this.updateById(entity) : this.save(entity)
        Boolean flag = bookInfoService.saveOrUpdate(model);
        return new BaseResponse(ResultEnum.SUCCESS, flag);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectBookInfo(@RequestBody BookInfoModel model) {
        BookInfoModel bookInfoModel = bookInfoService.getOne(new QueryWrapper<BookInfoModel>().lambda().eq(BookInfoModel::getId, model.getId()));
        return new BaseResponse(ResultEnum.SUCCESS, bookInfoModel);
    }

    /**
     * @Author longtao
     * @Date 2020/10/1
     * @Describe 栗子-线程池-线程无返回值
     **/
    @BaseBeforeAnnotation
    @RequestMapping("selectBookList")
    public BaseResponse selectBookList(@RequestBody BookInfoModel model) {
        model.setPageQuery();
        List<BookInfoModel> bookInfoList = bookInfoService.selectBookList(model);
//        bookInfoList.forEach(bookInfo -> {
//            executorService.execute(() -> {
//                try {
//                    log.info("调用线程池执行List的每一条数据：{}", bookInfo.getBookName());
//                    Thread.sleep(1000);
//                    log.info("线程睡眠1秒");
//                } catch (Exception e) {
//                    log.error("异常：" + e);
//                }
//
//            });
//        });
        return new BaseResponse(ResultEnum.SUCCESS, bookInfoList);
    }

    /**
     * @Author longtao
     * @Date 2020/10/1
     * @Describe 栗子-线程池-线程有返回值
     **/
    @BaseBeforeAnnotation
    @RequestMapping("getAll2")
    public BaseResponse getAll2(@RequestBody BookInfoModel model) {
        List<BookInfoModel> bookInfoList = bookInfoService.selectBookList(model);
        List<Future<Object>> resultList = new ArrayList<>();
        List<Object> retList = new ArrayList<>();
        bookInfoList.forEach(bookInfo -> {
            Future<Object> fal = executorService.submit(() -> {
                log.info("调用线程池执行List的每一条数据：{}", bookInfo.getBookName());
                Thread.sleep(1000);
                log.info("线程睡眠1秒");
                return bookInfo.getBookName();
            });
            resultList.add(fal);
        });
        //关闭线程  此处不能关闭。系统启动后，此线程池只能用一次
//        executorService.shutdown();
        //将每个线程的执行结果放到dataList中
        resultList.forEach(fs -> {
            try {
                retList.add(fs.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new BaseResponse(ResultEnum.SUCCESS, retList);
    }

    /**
     * @Author longtao
     * @Date 2020/10/10
     * @Describe 解析excel, 将书籍信息导入bookInfo表中
     **/
    @BaseAroundAnnotation
    @RequestMapping("importExcel")
    public BaseResponse importExcel(MultipartFile file) {
        try {
            log.info("------------importExcel start------------");
            //读取excel
            ExcelReader excelReader = EasyExcel.read(file.getInputStream(), BookInfoExcelModel.class, new BookInfoExcelListener(new BookInfoExcelModel(), bookInfoService, executorService)).build();
            //读取excel第一页内容
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
            log.info("------------importExcel end------------");
            return new BaseResponse(ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("导入表异常：" + e);
            return new BaseResponse(ResultEnum.FAIL, e.getMessage());
        }
    }

    @BaseAroundAnnotation
    @RequestMapping("selectBookInfoAndBorrowInfo")
    public BaseResponse selectBookInfoAndBorrowInfo(@RequestBody BookInfoModel model) {
        List<BookInfoModel> list = bookInfoService.selectBookInfoAndBorrowInfo(model);
        return new BaseResponse(ResultEnum.SUCCESS, list);
    }


    @BaseAroundAnnotation
    @RequestMapping("selectBookInfoByWrapper")
    public BaseResponse selectBookInfoByWrapper(@RequestBody BookInfoModel model) {
//        List<BookInfoModel> list = bookInfoService.selectBookInfoAndBorrowInfo(model);
//        List<BookInfoModel> list = bookInfoService.list(new QueryWrapper<BookInfoModel>().lambda().eq(BookInfoModel::getLoseFlag, 0));
        Page<BookInfoModel> page = new Page<BookInfoModel>(model.getPageNo(), model.getPageSize());
        //计数
        Integer count = bookInfoService.count(new QueryWrapper<BookInfoModel>()
                .lambda()
                .eq(BookInfoModel::getLoseFlag, LoseFlagEnum.UN_LOSE.getCode())
        );
        StringUtils.isEmpty("");
        //数据
        IPage<BookInfoModel> list = bookInfoService.page(page, new QueryWrapper<BookInfoModel>()
                .lambda()
                .eq(BookInfoModel::getLoseFlag, LoseFlagEnum.UN_LOSE.getCode())
                .like(BookInfoModel::getBookName, "php")
                .eq(BookInfoModel::getBookName, "php+").or().eq(BookInfoModel::getBookName, "php-")
                .orderByDesc(BookInfoModel::getCreateTime)
        );
        return new BaseResponse(ResultEnum.SUCCESS, count, list.getRecords());
    }

    @RequestMapping("testJSONObject")
    public void testJSONObject(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject = {} ", jsonObject);
    }
    /**
     * @Author longtao
     * @Date   2020/12/7
     * @Describe 使用stream 来操作集合
     **/
    public void testStream(@RequestBody BookInfoModel model){

    }

}
