package com.example.demo.executer.readerInfo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.base.Enum.Check;
import com.example.demo.base.Enum.Msg;
import com.example.demo.base.annonation.BaseAroundAnnotation;
import com.example.demo.base.annonation.BaseBeforeAnnotation;
import com.example.demo.base.annonation.CheckVisitTimesAroundAnnotation;
import com.example.demo.base.exception.CheckException;
import com.example.demo.base.model.baseModel.BaseModel;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.executer.readerCardInfo.model.ReaderCardInfoModel;
import com.example.demo.executer.readerCardInfo.service.ReaderCardInfoService;
import com.example.demo.executer.readerInfo.model.ReaderInfoModel;
import com.example.demo.executer.readerInfo.service.ReaderInfoService;
import com.example.demo.util.commonUtil.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "readerInfo")
public class ReaderInfoController {

    @Autowired
    private ReaderInfoService readerInfoService;
    @Autowired
    private ReaderCardInfoService readerCardInfoService;

//    /**
//     * @Author longtao
//     * @Date 2020/10/21
//     * @Describe 新建读者信息
//     **/
//    @BaseBeforeAnnotation
//    @RequestMapping("saveOrUpdate")
//    public BaseResponse saveOrUpdate(@RequestBody ReaderInfoModel model) throws CheckException {
//        if(StringUtil.isBlank(model.getReaderName())){
//            throw new CheckException(Check.CHECK_CARD);
//        }
//        if(StringUtil.isBlank(model.getReaderPhone())){
//            throw new CheckException(Check.CHECK_PHONE);
//        }
//       Boolean flag =  readerInfoService.saveOrUpdate(model);
//        if(flag){
//            return new BaseResponse(Msg.SUCCESS);
//        }
//        return new BaseResponse(Msg.ERROR);
//    }


    @BaseBeforeAnnotation
    @CheckVisitTimesAroundAnnotation
    @RequestMapping("selectOne")
    public BaseResponse selectReaderInfo(@RequestBody ReaderInfoModel model) {
        ReaderInfoModel readerInfoModel = readerInfoService.getById(model.getId());
        return new BaseResponse(Msg.SUCCESS, readerInfoModel);
    }

    @BaseBeforeAnnotation
    @RequestMapping("selectReaderInfoList")
    public BaseResponse selectReaderInfoList(@RequestBody ReaderInfoModel model) {
//        List<ReaderInfoModel> bookInfoModel = readerInfoService.selectReaderInfoList(model);
        List<ReaderInfoModel> readerInfoModelList = readerInfoService.list();
        return new BaseResponse(Msg.SUCCESS, readerInfoModelList);
    }

    @BaseAroundAnnotation
    @PostMapping("saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody ReaderInfoModel model) throws CheckException {
        //暂无业务检查
        if (StringUtil.isBlank(model.getId())) {
            if (StringUtil.isBlank(model.getCardId())) {
                throw new CheckException(Check.CHECK_CARD);
            } else {
                ReaderCardInfoModel cardModel = readerCardInfoService.getOne(new QueryWrapper<ReaderCardInfoModel>()
                        .lambda()
                        .eq(ReaderCardInfoModel::getUsableFlag, "0")
                        .eq(ReaderCardInfoModel::getId, model.getCardId())
                );
                if (null == cardModel) {
                    throw new CheckException(Check.CHECK_CARD_NOT_EXIST);
                }
            }
            if (StringUtil.isBlank(model.getReaderName())) {
                throw new CheckException(Check.CHECK_NAME);
            }
            if (StringUtil.isBlank(model.getReaderPhone())) {
                throw new CheckException(Check.CHECK_PHONE);
            }
        }
        Boolean flag = readerInfoService.saveOrUpdate(model);
        if (flag) {
            return new BaseResponse(Msg.SUCCESS);
        }
        return new BaseResponse(Msg.ERROR);
    }

    @BaseAroundAnnotation
    @PostMapping("getPageList")
    public BaseResponse getPageList(@RequestBody ReaderInfoModel model) {
        //设置分页参数
        if (!StringUtil.isBlank(model.getPageNo()) && !StringUtil.isBlank(model.getPageSize())) {
            PageHelper.startPage(model.getPageNo(), model.getPageSize());
        }
        //设置查询条件
        QueryWrapper<ReaderInfoModel> queryWrapper = new QueryWrapper();
        if (!StringUtil.isBlank(model.getId())) {
            queryWrapper.lambda().eq(ReaderInfoModel::getId, model.getId());
        }
        if (!StringUtil.isBlank(model.getCardId())) {
            queryWrapper.lambda().eq(ReaderInfoModel::getCardId, model.getCardId());
        }
        if (!StringUtil.isBlank(model.getReaderName())) {
            queryWrapper.lambda().eq(ReaderInfoModel::getReaderName, model.getReaderName());
        }
        if (!StringUtil.isBlank(model.getReaderCardNo())) {
            queryWrapper.lambda().eq(ReaderInfoModel::getReaderCardNo, model.getReaderCardNo());
        }
        if (!StringUtil.isBlank(model.getReaderPhone())) {
            queryWrapper.lambda().eq(ReaderInfoModel::getReaderPhone, model.getReaderPhone());
        }
        if (!StringUtil.isBlank(model.getReaderCardType())) {
            queryWrapper.lambda().eq(ReaderInfoModel::getReaderCardType, model.getReaderCardType());
        }
        queryWrapper.lambda().orderByDesc(BaseModel::getCreateTime);
        //列表查询
        List<ReaderInfoModel> retList = readerInfoService.list(queryWrapper);
        if (null != retList) {
            PageInfo pageInfo = new PageInfo(retList);
            return new BaseResponse(Msg.SUCCESS, pageInfo.getTotal(), pageInfo.getList());
        }
        return new BaseResponse(Msg.SUCCESS);

    }

    /**
     * @Author longtao
     * @Date 2021/3/12
     * @Describe 根据条件查询单条数据
     **/
    @BaseAroundAnnotation
    @PostMapping("getOne")
    public BaseResponse getOne(@RequestBody ReaderInfoModel model) {

        ReaderInfoModel readerInfoModel = readerInfoService.getOne(new QueryWrapper<ReaderInfoModel>()
                .lambda()
                .eq(ReaderInfoModel::getId, model.getId())
                .eq(ReaderInfoModel::getReaderPhone, model.getReaderPhone())
                .eq(ReaderInfoModel::getReaderCardNo, model.getReaderCardNo())
        );

        return new BaseResponse(Msg.SUCCESS, readerInfoModel);
    }

    /**
     * @Author longtao
     * @Date 2021/3/12
     * @Describe 批量删除
     **/
    @BaseAroundAnnotation
    @PostMapping("deleteList")
    public BaseResponse deleteList(@RequestBody List<String> ids) {
        //ps：只要有一条数据update 成功，那么flag = true.
        Boolean flag = readerInfoService.removeByIds(ids);
        if (flag) {
            return new BaseResponse(Msg.SUCCESS);
        }
        return new BaseResponse(Msg.ERROR, ":批量删除失败");

    }

}


























