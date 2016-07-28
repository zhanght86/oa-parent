/**
 * 审计查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.workflow.sealApply.view.SealApplySearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.sealApplySearchForm',
    xtype: 'sealApplySearchForm',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '申请部门',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'department'
        },
        {
            xtype: 'textfield',
            fieldLabel: '经办人',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'createBy'
        },
        {
            xtype: 'datefield',
            format: 'Y-m-d',
            fieldLabel: '申请时间',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'creationDate:begin:gt'
        }
    ]
});