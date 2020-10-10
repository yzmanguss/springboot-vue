package com.yunyun.financemanager.project.controller;


import com.yunyun.financemanager.project.service.impl.EarlyWarningServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EarlyWarningController {


   private final EarlyWarningServiceImpl earlyWarningService;

    @GetMapping("/testEW")
    public void test(){
        Long lid = 2L;

   earlyWarningService.selectEarlyWarning(lid,1);
    }



}
