package com.yunyun.financemanager.contract.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunyun.financemanager.common.entity.Contract;

import com.finance.system.contract.service.ContractService;
import com.github.pagehelper.Page;
import com.yunyun.financemanager.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author xlc
 * @ClassName: ContractController
 * @Description:
 * @date 2020/8/20 10:17
 * @see
 */
@Api(value = "/contract", tags = {"合同管理"})
@RestController
@CrossOrigin
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@RequestMapping("/contract")
public class ContractController {

   @Resource
    private ContractService contractService;


    @ApiOperation(value = "分页查询合同")
    @PostMapping("/getContractListByPage")
    public ApiResponse<List<Contract>> listContractByPage(@RequestBody PageRequest pageQuery){
        Page<Contract> page = contractService.listContractByPage(pageQuery);
        return ApiResponse.ok(page.getResult(), page.getTotal());
    }

    @ApiOperation(value = "根据id删除合同")
    @DeleteMapping("/deleteContractById/{id}")
    public ApiResponse<String> deleteContractById(@PathVariable("id") Integer id) {
        return contractService.deleteContractById(id);

    }


    @ApiOperation(value = "根据id查询合同")
    @GetMapping("/getContractById/{id}")
    public ApiResponse<Void>  getContractById(@PathVariable("id") Integer id) {
        return contractService.getContractById(id);
    }

    @ApiOperation(value = "修改合同")
    @PostMapping("/editContract")
    public ApiResponse<Void> editContract(@RequestBody Contract contract) {
        return contractService.editContract(contract);
    }

    @ApiOperation(value = "添加合同")
    @PostMapping("/addContract")
    public ApiResponse<Void> addContract(@RequestBody Contract contract) {
        return contractService.addContract(contract);

    }

    @ApiOperation(value = "模糊查询合同名")
    @GetMapping("/queryAllContractNames")
    public ApiResponse<Void> queryAllContractNames(String keyWord) {
        return contractService.queryAllContractNames(keyWord);
    }

//    @ApiOperation(value = "合同统计")
//    @GetMapping("/Statistics")
//    public ApiResponse<ContractStatisticsVO> ContractStatistics() {
//        return ApiResponse.buildSuccessResponse(contractService.contractStatistics());
//    }
}
