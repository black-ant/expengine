//package com.gang.etl.dingtalk.service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.dingtalk.api.DefaultDingTalkClient;
//import com.dingtalk.api.DingTalkClient;
//import com.dingtalk.api.request.OapiUserCreateRequest;
//import com.dingtalk.api.request.OapiUserDeleteRequest;
//import com.dingtalk.api.request.OapiUserGetRequest;
//import com.dingtalk.api.request.OapiUserListbypageRequest;
//import com.dingtalk.api.request.OapiUserUpdateRequest;
//import com.dingtalk.api.response.OapiUserCreateResponse;
//import com.dingtalk.api.response.OapiUserDeleteResponse;
//import com.dingtalk.api.response.OapiUserGetResponse;
//import com.dingtalk.api.response.OapiUserListbypageResponse;
//import com.dingtalk.api.response.OapiUserUpdateResponse;
//import com.gang.etl.dingtalk.to.DingTalkUserTO;
//import com.gang.etl.dingtalk.type.DingTalkRestAPI;
//import com.gang.etl.engine.api.annotation.SyncCreate;
//import com.gang.etl.engine.api.annotation.SyncDelete;
//import com.gang.etl.engine.api.annotation.SyncSearch;
//import com.gang.etl.engine.api.annotation.SyncUpdate;
//import com.gang.etl.engine.api.exception.SyncException;
//import com.gang.etl.engine.api.type.BaseErrorEnum;
//import com.taobao.api.ApiException;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Classname DingtalkUserService
// * @Description TODO
// * @Date 2020/9/12 20:49
// * @Created by zengzg
// */
//@Component
//public class DingtalkUserService {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @SyncCreate
//    public String create(DingTalkUserTO createTO) {
//        logger.info("------>DingTalkUserLogic create :{} <-------", JSONObject.toJSONString(createTO));
//        DingTalkClient client = new DefaultDingTalkClient(DingTalkRestAPI.USER_CREATE);
//
//        OapiUserCreateRequest request = getOapiUserCreateRequest(createTO);
//        OapiUserCreateResponse execute = null;
//        try {
//            execute = client.execute(request, token);
//        } catch (ApiException e) {
//            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
//            throw new SyncException("API ERROR");
//        }
//        baseLogic.checkErrcode(execute.getErrcode(), execute.getErrmsg(), BaseErrorEnum.CREATE_ERR_EXT);
//        logger.debug("User create response {}", execute.getBody());
//        return execute.getUserid();
//    }
//
//    @Override
//    @SyncUpdate
//    public String update(DingTalkUserTO updateTO) {
//        logger.info("------>DingTalkUserLogic update :{} <-------", JSONObject.toJSONString(updateTO));
//        if (StringUtils.isBlank(updateTO.getUserid())) {
//            throw new SyncException(BaseErrorEnum.UPDATE_ERR_EXT, "userid is null");
//        }
//        DingTalkClient client = new DefaultDingTalkClient(DingTalkRestAPI.USER_UPDATE);
//
//        OapiUserUpdateRequest request = getOapiUserUpdateRequest(updateTO);
//        OapiUserUpdateResponse execute = null;
//        try {
//            execute = client.execute(request, token);
//        } catch (ApiException e) {
//            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
//            throw new SyncException("API ERROR");
//        }
//        baseLogic.checkErrcode(execute.getErrcode(), execute.getErrmsg(), BaseErrorEnum.UPDATE_ERR_EXT);
//        logger.debug("User update response {}", execute.getBody());
//        return updateTO.getUserid();
//    }
//
//    @Override
//    @SyncDelete
//    public Boolean delete(String userid) {
//        logger.info("------>DingTalkUserLogic delete :{} <-------", userid);
//        if (StringUtils.isBlank(userid)) {
//            throw new SyncException(BaseErrorEnum.DELETE_ERR_EXT, "userid is null");
//        }
//        DingTalkClient client = new DefaultDingTalkClient(DingTalkRestAPI.USER_DELETE);
//
//        OapiUserDeleteRequest request = new OapiUserDeleteRequest();
//        request.setUserid(String.valueOf(userid));
//        request.setHttpMethod(HttpMethodEnum.GET.name());
//
//        OapiUserDeleteResponse execute = null;
//        try {
//            execute = client.execute(request, token);
//        } catch (ApiException e) {
//            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
//            throw new SyncException("API ERROR");
//        }
//        baseLogic.checkErrcode(execute.getErrcode(), execute.getErrmsg(), BaseErrorEnum.DELETE_ERR_EXT);
//        logger.debug("User delete response {}", execute.getBody());
//        if (execute != null && execute.getErrcode() != null && execute.getErrcode() == 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    @SyncSearch(type = "one")
//    public DingTalkUserTO get(String userid) {
//        logger.info("------>DingTalkUserLogic getOne :{} <-------", userid);
//        if (StringUtils.isBlank(userid)) {
//            throw new SyncException(BaseErrorEnum.QUERY_ERR_EXT, "userid is null");
//        }
//
//        DingTalkClient client = new DefaultDingTalkClient(DingTalkRestAPI.USER_GET);
//
//        OapiUserGetRequest request = new OapiUserGetRequest();
//        request.setUserid(userid);
//        request.setHttpMethod(HttpMethodEnum.GET.name());
//        OapiUserGetResponse execute = null;
//        try {
//            execute = client.execute(request, token);
//        } catch (ApiException e) {
//            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
//            throw new SyncException("API ERROR");
//        }
//        baseLogic.checkErrcode(execute.getErrcode(), execute.getErrmsg(), BaseErrorEnum.QUERY_ERR_EXT);
//        logger.debug("User update response {}", execute.getBody());
//        return convertOapiUserGetResponseToDingTalkUserTO(execute);
//    }
//
//    @Override
//    @SyncSearch(type = "list")
//    public List<DingTalkUserTO> list(DingTalkUserSearchTO searchKey) {
//        logger.info("------>DingTalkUserLogic getList :{} <-------", searchKey);
//        Long departmentId = searchKey.getDepartmentId();
//        Long page = searchKey.getPage();
//        Long size = searchKey.getSize();
//        DingTalkOrderByEnum orderBy = searchKey.getOrderby();
//        if (departmentId == null || page == null || size == null) {
//            throw new SyncException(BaseErrorEnum.QUERY_ERR_EXT,
//                    "Required attribute is empty,check attribute : DepartmentId,Page,Size");
//        }
//        DingTalkClient client = new DefaultDingTalkClient(DingTalkRestAPI.USER_PAGE_LIST);
//        OapiUserListbypageRequest request = new OapiUserListbypageRequest();
//
//        request.setDepartmentId(departmentId);
//
//        request.setOffset(page);
//        request.setSize(size);
//        request.setOrder(orderBy.getMsg());
//
//        request.setHttpMethod(HttpMethodEnum.GET.name());
//
//        OapiUserListbypageResponse execute = null;
//        try {
//            execute = client.execute(request, token);
//        } catch (ApiException e) {
//            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
//            throw new SyncException("API ERROR");
//        }
//        baseLogic.checkErrcode(execute.getErrcode(), execute.getErrmsg(), BaseErrorEnum.QUERY_ERR_EXT);
//        logger.debug("User getUserList create response {}", execute.getBody());
//        List<DingTalkUserTO> dingTalkUserTOList = new ArrayList<>();
//        if (execute != null && execute.getUserlist() != null && !execute.getUserlist().isEmpty()) {
//            List<Userlist> list = execute.getUserlist();
//            for (Userlist userlist : list) {
//                DingTalkUserTO dingTalkUserTO = convertUserlistToDingTalkUserTO(userlist);
//                dingTalkUserTOList.add(dingTalkUserTO);
//            }
//        }
//        return dingTalkUserTOList;
//    }
//
//    private OapiUserCreateRequest getOapiUserCreateRequest(DingTalkUserTO user) {
//        OapiUserCreateRequest request = new OapiUserCreateRequest();
//
//        BeanUtils.copyProperties(user, request);
//
//        request.setDepartment(JSON.toJSONString(user.getDepartment()));
//
//        if (null == user.getExtattr()) {
//            request.setExtattr(new JSONObject().toJSONString());
//        } else {
//            // request.setExtattr(user.getExtattr().toJSONString());
//            request.setExtattr(JSON.toJSONString(user.getExtattr()));
//        }
//
//        if (null == user.getOrderInDepts()) {
//            request.setOrderInDepts(new JSONObject().toJSONString());
//        } else {
//            //            request.setOrderInDepts(user.getOrderInDepts());
//            request.setOrderInDepts(JSON.toJSONString(user.getOrderInDepts()));
//        }
//
//        return request;
//    }
//
//    public OapiUserUpdateRequest getOapiUserUpdateRequest(DingTalkUserTO userTO) {
//
//        OapiUserUpdateRequest request = new OapiUserUpdateRequest();
//
//        BeanUtils.copyProperties(userTO, request);
//
//        request.setDepartment(userTO.getDepartment());
//
//        if (null == userTO.getExtattr()) {
//            request.setExtattr(new JSONObject().toJSONString());
//        } else {
//            // request.setExtattr(userTO.getExtattr().toJSONString());
//            request.setExtattr(JSON.toJSONString(userTO.getExtattr()));
//        }
//
//        if (null == userTO.getOrderInDepts()) {
//            request.setOrderInDepts(new JSONObject().toJSONString());
//        } else {
//            // request.setOrderInDepts(userTO.getOrderInDepts().toJSONString());
//            request.setOrderInDepts(JSON.toJSONString(userTO.getOrderInDepts()));
//        }
//
//        return request;
//    }
//
//    public DingTalkUserTO convertOapiUserGetResponseToDingTalkUserTO(OapiUserGetResponse response) {
//        DingTalkUserTO dingTalkUserTO = null;
//        if (response != null && response.getErrcode() == 0) {
//            dingTalkUserTO = new DingTalkUserTO();
//            dingTalkUserTO.setUserid(response.getUserid());
//            dingTalkUserTO.setRemark(response.getRemark());
//            dingTalkUserTO.setIsSenior(response.getIsSenior());
//            dingTalkUserTO.setTel(response.getTel());
//            dingTalkUserTO.setDepartment(response.getDepartment());
//            dingTalkUserTO.setWorkPlace(response.getWorkPlace());
//            dingTalkUserTO.setEmail(response.getEmail());
//            // dingTalkUserTO.setOrderInDepts(JSONObject.parseObject(response.getOrderInDepts()));
//            dingTalkUserTO.setOrderInDepts(prcessOrderInDepts(response.getOrderInDepts()));
//            dingTalkUserTO.setMobile(response.getMobile());
//            dingTalkUserTO.setExtattr(JSONObject.parseObject(response.getExtattr()));
//            dingTalkUserTO.setIsHide(response.getIsHide());
//            dingTalkUserTO.setJobnumber(response.getJobnumber());
//            dingTalkUserTO.setName(response.getName());
//            dingTalkUserTO.setPosition(response.getPosition());
//        }
//        return dingTalkUserTO;
//
//    }
//
//    public Map<String, Long> prcessOrderInDepts(String json) {
//        Map<String, Long> orderInDepts = null;
//        if (json != null) {
//            orderInDepts = new HashMap<>();
//            Map map = (Map) JSONObject.parse(json);
//            for (Object obj : map.keySet()) {
//                orderInDepts.put(String.valueOf(obj), Long.parseLong(String.valueOf(map.get(obj))));
//            }
//        }
//
//        return orderInDepts;
//    }
//
//    public DingTalkUserTO convertUserlistToDingTalkUserTO(OapiUserListbypageResponse.Userlist response) {
//        DingTalkUserTO dingTalkUserTO = null;
//        if (response != null) {
//            dingTalkUserTO = new DingTalkUserTO();
//            dingTalkUserTO.setUserid(response.getUserid());
//            dingTalkUserTO.setRemark(response.getRemark());
//            dingTalkUserTO.setTel(response.getTel());
//            dingTalkUserTO.setDepartment(convertDepartment(response.getDepartment()));
//            dingTalkUserTO.setWorkPlace(response.getWorkPlace());
//            dingTalkUserTO.setEmail(response.getEmail());
//            dingTalkUserTO.setMobile(response.getMobile());
//            dingTalkUserTO.setExtattr(JSONObject.parseObject(response.getExtattr()));
//            dingTalkUserTO.setIsHide(response.getIsHide());
//            dingTalkUserTO.setJobnumber(response.getJobnumber());
//            dingTalkUserTO.setName(response.getName());
//            dingTalkUserTO.setPosition(response.getPosition());
//        }
//        return dingTalkUserTO;
//    }
//
//    public List<Long> convertDepartment(String departmentString) {
//        List<Long> list = new ArrayList<>();
//        if (StringUtils.isNoneBlank(departmentString)) {
//            if (departmentString.contains(",")) {
//                String[] split = departmentString.split(",");
//                for (String department : split) {
//                    list.add(Long.parseLong(department));
//                }
//            } else {
//                list.add(Long.parseLong(departmentString.replace("[", "").replace("]", "")));
//            }
//        }
//
//        return list;
//
//    }
//}
