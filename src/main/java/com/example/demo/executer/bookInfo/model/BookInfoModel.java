package com.example.demo.executer.bookInfo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.base.model.baseModel.BaseModel;
//import com.example.demo.executer.borrowBoook.model.BorrowBookModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_book_info")
public class BookInfoModel extends BaseModel {

    /**
     * 书名
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bookName;
    /**
     * 价格
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal bookPrice;
    /**
     * 作者
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bookAuther;
    /**
     * 类别（1-童书，2-文学，3-教辅，4-经管投资，5-社科，6-科技，7-生活育儿，8-艺术，9-动漫,10-其他）
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String bookType;
    /**
     * 珍本标识(rare-珍本，unra-非珍本)
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rareFlag;
    /**
     * 借阅次数
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long borrowTimes;
    /**
     * 借阅标识（in-未借出，out-已借出）
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String borrowFlag;
    /**
     * 遗失标志（0-未遗失，1-遗失）
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String loseFlag;
    /**
     * 出版社
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String press;
    /**
     * 出版时间
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date pressDate;
    /**
     * 备注
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String remark;

//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @TableField(exist=false)
//    private List<BorrowBookModel> borrowList;

    public BookInfoModel() {
    }

}
