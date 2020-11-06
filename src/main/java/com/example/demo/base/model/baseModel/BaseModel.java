package com.example.demo.base.model.baseModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
    /**
     * 上游系统唯一系统标识号 12位系统号
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String upSystemId;
    /**
     * 上游系统唯一流水号 21位
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String upSystemSeqNo;
    /**
     * 上游系统 请求日期 2020-10-20
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date tranDate;
    /**
     * 上游系统 操作人 12位
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userId;
    /**
     * 主键ID
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    /**
     * 创建时间
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;
    /**
     * 更新时间
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updateTime;

    /**
     * 当前页，需要查询的页数
     * pageNo从1开始
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageNo;

    /**
     * 每页显示的条数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;

    /**
     * 当前页，需要查询的页数
     * pageNo从1开始
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer startNum;

    /**
     * 设置分页查询数据
     */
    public void setPageQuery() {
        this.startNum = this.getPageNo() > 0 ? (this.getPageNo() - 1) * this.getPageSize() : 0;
    }
}
