/**
 * @author zangyanming
 * checkDateTime函数检测会议室申请中的开始时间与结束时间范围内，会议室是否被占用
 */
package com.kalix.oa.workflow.meetingapply.biz;



import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.persistence.PersistentEntity;
import com.kalix.framework.core.util.BeanUtil;
import com.kalix.framework.core.util.SerializeUtil;
import com.kalix.middleware.workflow.biz.WorkflowGenericBizServiceImpl;
import com.kalix.oa.system.meetingroom.api.biz.IMeetingroomBeanService;
import com.kalix.oa.workflow.meetingapply.api.biz.IMeetingApplyBeanService;
import com.kalix.oa.workflow.meetingapply.api.dao.IMeetingApplyBeanDao;
import com.kalix.oa.workflow.meetingapply.entities.MeetingApplyBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MeetingApplyBeanServiceImpl extends WorkflowGenericBizServiceImpl<IMeetingApplyBeanDao, MeetingApplyBean> implements IMeetingApplyBeanService {
    private IMeetingroomBeanService meetingroomBeanService;

    public IMeetingroomBeanService getMeetingroomBeanService() {
        return meetingroomBeanService;
    }

    public void setMeetingroomBeanService(IMeetingroomBeanService meetingroomBeanService) {
        this.meetingroomBeanService = meetingroomBeanService;
    }

    @Override
    public String getProcessKeyName() {
        return PROCESS_KEY_NAME;
    }

    @Override
    public Map getVariantMap(Map map, MeetingApplyBean bean) {
        return map;
    }

    @Override
    public JsonData getAllEntityByQuery(Integer page, Integer limit, String jsonStr) {
        JsonData jsonData = new JsonData();
        jsonData = super.getAllEntityByQuery(page, limit, jsonStr);

        List beans = jsonData.getData();
        List ids = BeanUtil.getBeanFieldValueList(beans, "meetingroomId");
        List values = this.meetingroomBeanService.getFieldValuesByIds(ids.toArray(), "name");
        BeanUtil.setBeanListFieldValues(beans, "meetingroomName", values);

        jsonData.setTotalCount((long) beans.size());
        jsonData.setData(beans);

        return jsonData;
    }

    @Override
    public JsonStatus checkDateTime(String jsonStr) {
        MeetingApplyBean entity = SerializeUtil.unserializeJson(jsonStr, MeetingApplyBean.class);
        JsonStatus jsonStatus = new JsonStatus();
        jsonStatus.setMsg("0");
        long id;
        if (entity.getId() == 0) {
            id = -1;
        } else {
            id = entity.getId();
        }

        long meetingroomId = entity.getMeetingroomId();
        //Date meetingDate = entity.getMeetingDate();

        Date beginDateTime = new Date();
        Date beginTime = entity.getBeginTime();
        beginDateTime.setYear(entity.getMeetingDate().getYear());
        beginDateTime.setMonth(entity.getMeetingDate().getMonth());
        beginDateTime.setDate(entity.getMeetingDate().getDate());
        beginDateTime.setHours(beginTime.getHours());
        beginDateTime.setMinutes(beginTime.getMinutes());
        beginDateTime.setSeconds(beginTime.getSeconds());

        Date endDateTime = new Date();//entity.getMeetingDate();
        Date endTime = entity.getEndTime();
        endDateTime.setYear(entity.getMeetingDate().getYear());
        endDateTime.setMonth(entity.getMeetingDate().getMonth());
        endDateTime.setDate(entity.getMeetingDate().getDate());
        endDateTime.setHours(endTime.getHours());
        endDateTime.setMinutes(endTime.getMinutes());
        endDateTime.setSeconds(endTime.getSeconds());

        try {
            Date row_beginTime, row_endTime, row_beginDateTime, row_endDateTime;
            List<MeetingApplyBean> list = dao.find("select ob from MeetingApplyBean ob where ob.id <> ?1 and ob.meetingroomId=?2", id, meetingroomId);
            for (int i = 0; i < list.size(); i++) {
                row_beginTime = list.get(i).getBeginTime();
                row_endTime = list.get(i).getEndTime();

                row_beginDateTime = new Date();
                row_beginDateTime.setYear(list.get(i).getMeetingDate().getYear());
                row_beginDateTime.setMonth(list.get(i).getMeetingDate().getMonth());
                row_beginDateTime.setDate(list.get(i).getMeetingDate().getDate());
                row_beginDateTime.setHours(row_beginTime.getHours());
                row_beginDateTime.setMinutes(row_beginTime.getMinutes());
                row_beginDateTime.setSeconds(row_beginTime.getSeconds());

                row_endDateTime = new Date();
                row_endDateTime.setYear(list.get(i).getMeetingDate().getYear());
                row_endDateTime.setMonth(list.get(i).getMeetingDate().getMonth());
                row_endDateTime.setDate(list.get(i).getMeetingDate().getDate());
                row_endDateTime.setHours(row_endTime.getHours());
                row_endDateTime.setMinutes(row_endTime.getMinutes());
                row_endDateTime.setSeconds(row_endTime.getSeconds());

                if (beginDateTime.before(row_beginDateTime) && endDateTime.after(row_beginDateTime)) {
                    jsonStatus.setMsg("1");
                    break;
                }
                if (Math.abs(beginDateTime.getTime() - row_beginDateTime.getTime()) < 1000) {
                    jsonStatus.setMsg("1");
                    break;
                }
                if (beginDateTime.after(row_beginDateTime) && beginDateTime.before(row_endDateTime)) {
                    jsonStatus.setMsg("1");
                    break;
                }
            }

            jsonStatus.setSuccess(true);
        } catch (Exception e) {
            jsonStatus.setMsg("1");
            jsonStatus.setSuccess(false);
        }
        return jsonStatus;
    }

    @Override
    public JsonData reservation(Date date) {
        if (date == null) return null;
        JsonData jsonData = new JsonData();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(date);
        List<PersistentEntity> beans = dao.findByNativeSql("select ob from MeetingApplyBean ob where ob.meetingDate >=1? and ob.meetingDate <=2?", MeetingApplyBean.class, dateStr + "00:00:00", dateStr + "23:59:59");

        jsonData.setData(beans);
        jsonData.setTotalCount((long) beans.size());
        return jsonData;
    }

    @Override
    public JsonData reservation(long roomId, Date date) {
        if (date == null) return null;
        JsonData jsonData = new JsonData();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(date);
        List<PersistentEntity> beans = dao.findByNativeSql("select ob from MeetingApplyBean ob where ob.meetingRoomId = ?1 ob.meetingDate >= 2? and ob.meetingDate <= 3?", MeetingApplyBean.class, roomId, dateStr + "00:00:00", dateStr + "23:59:59");

        jsonData.setData(beans);
        jsonData.setTotalCount((long) beans.size());
        return jsonData;
    }
}
