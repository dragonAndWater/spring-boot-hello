package com.example.demo.base.model.baseModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BaseModel {
    /**
     * 上游系统唯一系统标识号 12位系统号
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist=false)
    private String upSystemId;
    /**
     * 上游系统唯一流水号 21位
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist=false)
    private String upSystemSeqNo;
    /**
     * 上游系统 请求日期 2020-10-20
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist=false)
    private Date tranDate;
    /**
     * 上游系统 操作人 12位
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist=false)
    private String userId;
    /**
     * 主键ID
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 创建时间
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 当前页，需要查询的页数
     * pageNo从1开始
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist=false)
    private Integer pageNo;

    /**
     * 每页显示的条数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist=false)
    private Integer pageSize;

    /**
     * 当前页，需要查询的页数
     * pageNo从1开始
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist=false)
    private Integer startNum;

    /**
     * 设置分页查询数据
     */
    public void setPageQuery() {
        this.startNum = this.getPageNo() > 0 ? (this.getPageNo() - 1) * this.getPageSize() : 0;
    }
}
