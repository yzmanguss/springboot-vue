package com.yunyun.financemanager.contract.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yunyun.financemanager.contract.service.PhaseService;
import com.yunyun.financemanager.common.entity.Phase;
import com.yunyun.financemanager.contract.mapper.PhaseMapper;
import org.springframework.stereotype.Service;

/**
 * @author xlc
 */
@Service
public class PhaseServiceImpl extends ServiceImpl<PhaseMapper, Phase> implements PhaseService {
}
