package com.example.demo.executer.loseBook.controller;

import com.example.demo.base.Enum.Msg;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.exception.CheckException;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.loseBook.model.LoseBookModel;
import com.example.demo.executer.loseBook.service.LoseBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "loseBook")
public class LoseBookController {

    @Autowired
    private LoseBookService loseBookService;

    @BaseBeforeAnnotation
    @RequestMapping("insertOne")
    public BaseResponse insertLoseBook(@RequestBody LoseBookModel model) {
//        Boolean flag = loseBookService.insertOne(model);
//        if (flag) {
//            return new BaseResponse(ResultEnum.SUCCESS);
//        }
        return new BaseResponse(Msg.ERROR);
    }

    @BaseBeforeAnnotation
    @RequestMapping("updateOne")
    public BaseResponse updateLoseBook(@RequestBody LoseBookModel model) {
//        Boolean flag = loseBookService.updateOne(model);
//        if (flag) {
//            return new BaseResponse(ResultEnum.SUCCESS);
//        }
        return new BaseResponse(Msg.ERROR);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectLoseBook(@RequestBody LoseBookModel model) {
//        LoseBookModel bookInfoModel = loseBookService.selectOne(model.getId());
//        return new BaseResponse(ResultEnum.SUCCESS, bookInfoModel);
        return null;
    }

    /**
     * @Author longtao
     * @Date 2020/10/28
     * @Describe 根据书籍ID获取赔偿金额
     **/
    @BaseBeforeAnnotation
    @RequestMapping("getRealAmt")
    public BaseResponse getRealAmt(@RequestBody LoseBookModel model) {
        try {
            LoseBookModel loseBookModel = loseBookService.getRealAmt(model);
            return new BaseResponse(Msg.SUCCESS, loseBookModel);
        } catch (CheckException e) {
            return new BaseResponse(e);
        }


    }
}
