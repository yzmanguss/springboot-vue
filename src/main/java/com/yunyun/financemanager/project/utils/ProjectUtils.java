package com.yunyun.financemanager.project.utils;

import com.yunyun.financemanager.project.mapper.MemberMapper;
import java.time.LocalDateTime;


/**
 * @author yangzhongming
 */
public class ProjectUtils {

    /**
     * 成员id转成员name
     * @param memberIds  id数组
     * @param memberMapper  查询memberName
     * @return  memberName 字符串
     */
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

    /**
     * 判断两段时间是否冲突
     * @param oldStartDate 原来开始时间
     * @param oldFinishDate 原来结束时间
     * @param newStartDate  现在开始时间
     * @param newFinishDate 现在结束时间
     * @return  true--冲突   false --不冲突
     */
    public static boolean isTimeConflict(LocalDateTime oldStartDate,LocalDateTime oldFinishDate,LocalDateTime newStartDate,LocalDateTime newFinishDate){
        boolean flag = false;
        if(oldStartDate.compareTo(newStartDate) >= 0 && oldStartDate.compareTo(newFinishDate) < 0){
            flag = true;
        }
        if(oldFinishDate.compareTo(newStartDate) >= 0 && oldStartDate.compareTo(newFinishDate) < 0){
            flag = true;
        }
        return flag;
    }
}
