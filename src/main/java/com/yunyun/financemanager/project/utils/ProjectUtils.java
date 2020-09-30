package com.yunyun.financemanager.project.utils;

import com.yunyun.financemanager.project.mapper.MemberMapper;
/**
 * @author yangzhongming
 */
public class ProjectUtils {

    public static String memberIdToname(String[] memberIds,MemberMapper memberMapper){
        StringBuilder members = new StringBuilder();
        for (int j = 0; j < memberIds.length; j++) {
            String memberName = memberMapper.selectById(memberIds[j]).getMemberName();
            members.append(memberName);
            if (j != memberIds.length - 1) {
                members.append(",");
            }
        }
        return members.toString();
    }
}
