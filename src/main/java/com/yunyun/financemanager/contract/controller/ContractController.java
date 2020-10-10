package com.yunyun.financemanager.contract.controller;

import com.yunyun.financemanager.common.entity.Contract;

import com.github.pagehelper.Page;
import com.yunyun.financemanager.common.query.ContractQuery;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.vo.ContractVO;
import com.yunyun.financemanager.contract.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xlc
 */
@Validated
@Api(value = "/contract", tags = {"合同管理"})
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Resource
    private ContractService contractService;

    @ApiOperation(value = "分页查询合同")
    @PostMapping("/getContractListByPage")
    public ApiResponse<List<ContractVO>> listContractByPage(@RequestBody ContractQuery contractQuery) {
        Page<ContractVO> page = contractService.listContractByPage(contractQuery);
        return ApiResponse.ok(page.getResult(), page.getTotal());
    }

    @ApiOperation(value = "根据id删除合同")
    @DeleteMapping("/deleteContractById/{id}")
    public ApiResponse<Void> deleteContractById(@PathVariable("id") @NotNull Long id) {
        return contractService.deleteContractById(id);

    }

    @ApiOperation(value = "根据id查询合同")
    @GetMapping("/getContractById/{id}")
    public ApiResponse<Contract> getContractById(@PathVariable("id") @NotNull Long id) {
        return contractService.getContractById(id);
    }

    @ApiOperation(value = "修改合同")
    @PostMapping("/editContract")
    public ApiResponse<Void> editContract(@Validated @RequestBody Contract contract) {
        return contractService.editContract(contract);
    }

    @ApiOperation(value = "添加合同")
    @PostMapping("/addContract")
    public ApiResponse<Void> addContract(@Validated @RequestBody Contract contract) {
        return contractService.addContract(contract);
    }

    @ApiOperation(value = "查询最新10个合同")
    @GetMapping("/projectNames")
    public ApiResponse<List<Contract>> selectContractNames(@RequestParam(required = false) String name) {
        return ApiResponse.ok(contractService.selectContractNames(name));
    }

}
