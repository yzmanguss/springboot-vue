package com.yunyun.financemanager.common.query;


import lombok.Getter;
import lombok.Setter;

/**
 * 用于封装合同的查询条件
 *
 * @author xlc
 */
@Getter
@Setter
public class ContractQuery {

    /**
     * 当前页码
     */
    protected int pageNum = 1;
    /**
     * 每页数量
     */
    protected int pageSize = 5;

    /**
     * 合同状态
     */
    protected Integer contractStatus;
    /**
     * 合同签订的开始日期
     */
    protected String signDateStart;
    /**
     * 合同签订的结束日期
     */
    protected String signDateEnd;
    /**
     * 查询关键字，与合同的标题匹配
     */
    protected String keyWord;
}
