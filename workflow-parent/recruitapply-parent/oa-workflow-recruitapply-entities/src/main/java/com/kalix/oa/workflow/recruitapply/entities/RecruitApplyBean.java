package com.kalix.oa.workflow.recruitapply.entities;


import com.kalix.middleware.workflow.api.model.WorkflowEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zangyanming on 2016/6/14.
 */

@Entity
@Table(name = "oa_workflow_recruit")
public class RecruitApplyBean extends WorkflowEntity {
    private String personType;// 人员类型
    private Integer reason;// 需求原因
    private String positionName;//职位名称
    private Integer recruitCount;// 拟聘人数
    private Integer allocationCount;// 定编人数
    private Integer existCount;// 现有人数
    private String leaderName;// 直接上级
    private String treatmentLevel;// 待遇标准
    private String coreRecruit;// 核心职责
    private String commonRecruit;// 常规职责
    private String baseCondition;// 任职基本素质条件
    private Integer recruitType;// 建议招聘方式
    private String depUser;// 申请部门负责人签字
    private String manpower;// 人力资源部长签字

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getRecruitCount() {
        return recruitCount;
    }

    public void setRecruitCount(Integer recruitCount) {
        this.recruitCount = recruitCount;
    }

    public Integer getAllocationCount() {
        return allocationCount;
    }

    public void setAllocationCount(Integer allocationCount) {
        this.allocationCount = allocationCount;
    }

    public Integer getExistCount() {
        return existCount;
    }

    public void setExistCount(Integer existCount) {
        this.existCount = existCount;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getTreatmentLevel() {
        return treatmentLevel;
    }

    public void setTreatmentLevel(String treatmentLevel) {
        this.treatmentLevel = treatmentLevel;
    }

    public String getCoreRecruit() {
        return coreRecruit;
    }

    public void setCoreRecruit(String coreRecruit) {
        this.coreRecruit = coreRecruit;
    }

    public String getCommonRecruit() {
        return commonRecruit;
    }

    public void setCommonRecruit(String commonRecruit) {
        this.commonRecruit = commonRecruit;
    }

    public String getBaseCondition() {
        return baseCondition;
    }

    public void setBaseCondition(String baseCondition) {
        this.baseCondition = baseCondition;
    }

    public Integer getRecruitType() {
        return recruitType;
    }

    public void setRecruitType(Integer recruitType) {
        this.recruitType = recruitType;
    }

    public String getDepUser() {
        return depUser;
    }

    public void setDepUser(String depUser) {
        this.depUser = depUser;
    }

    public String getManpower() {
        return manpower;
    }

    public void setManpower(String manpower) {
        this.manpower = manpower;
    }
}
