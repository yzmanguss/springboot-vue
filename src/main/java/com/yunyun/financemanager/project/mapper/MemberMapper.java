package com.yunyun.financemanager.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunyun.financemanager.common.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper extends BaseMapper<Member> {

    void setMemberEnable(Long id);

    void setMemberDisable(Long id);

}