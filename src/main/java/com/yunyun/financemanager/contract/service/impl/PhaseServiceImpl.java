package com.finance.system.contract.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.system.common.dojo.PhaseDO;
import com.finance.system.contract.mapper.PhaseMapper;
import com.finance.system.contract.service.PhaseService;
import org.springframework.stereotype.Service;

/**
 * @author xlc
 */
@Service
public class PhaseServiceImpl extends ServiceImpl<PhaseMapper, PhaseDO> implements PhaseService {
}
