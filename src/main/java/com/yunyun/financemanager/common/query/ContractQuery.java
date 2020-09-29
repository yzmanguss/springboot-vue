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

    protected Integer contractStatus;

    protected String signDateStart;

    protected String signDateEnd;

    protected String keyWord;
}
