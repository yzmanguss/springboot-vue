package com.yunyun.financemanager.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunyun.financemanager.common.entity.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangzhongming
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    String selectMemberById(Long id);
}