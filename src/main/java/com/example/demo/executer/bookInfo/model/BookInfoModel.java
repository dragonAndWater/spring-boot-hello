package com.example.demo.executer.bookInfo.model;

import com.example.demo.base.model.baseModel.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookInfoModel extends BaseModel {

    /**
     * 书名
     **/
    private String bookName;
    /**
     * 价格
     **/
    private BigDecimal bookPrice;
    /**
     * 作者
     **/
    private String bookAuther;
    /**
     * 类别（1-童书，2-文学，3-教辅，4-经管投资，5-社科，6-科技，7-生活育儿，8-艺术，9-动漫,10-其他）
     **/
    private String bookType;
    /**
     * 珍本标识(rare-珍本，unra-非珍本)
     **/
    private String rareFlag;
    /**
     * 借阅次数
     **/
    private Long borrowTimes;
    /**
     * 借阅标识（in-未借出，out-已借出）
     **/
    private String borrowFlag;
    /**
     * 遗失标志（0-未遗失，1-遗失）
     **/
    private String loseFlag;
    /**
     * 出版社
     **/
    private String press;
    /**
     * 出版时间
     **/
    private Date pressDate;
    /**
     * 备注
     **/
    private String remark;

    public BookInfoModel() {
    }

}
