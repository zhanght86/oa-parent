package com.kalix.oa.workflow.tempapply.biz.form;


import com.kalix.middleware.workflow.biz.AbstractModifyFormHandler;

/**
 * Created by sunlf on 2015/8/4.
 * 审批页面实现类
 */
public class TempApplyModifyFormImpl extends AbstractModifyFormHandler {
    @Override
    public String getFormClass() {
        return "kalix.workflow.tempApply.view.TempApplyModifyForm";
    }

}
