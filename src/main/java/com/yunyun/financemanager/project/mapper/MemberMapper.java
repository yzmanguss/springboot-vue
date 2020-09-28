package com.yunyun.financemanager.project.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunyun.financemanager.common.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@TableName("member")
public interface MemberMapper extends BaseMapper<Member> {

    void setMemberEnable(Long id);

    void setMemberDisable(Long id);

}