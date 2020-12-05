'use strict'

import common from './common.js';

export default {
  // 获取配置信息
  getSetting(key) {
    console.info("this is in getSetting");
    return common.get("/sync/setting/get/" + key, null)
  },
  // 获取配置列表
  listSetting() {
    console.info("this is in listSetting");
    return common.get("/sync/setting/list", null);
  },
  createSetting(data) {
    console.info("this is in createSetting");
    return common.post("/sync/setting/saveorupdate", data)
  },
  saveOrUpdate(data) {
    console.info("this is in createSetting");
    return common.post("/setting/saveorupdate", data)
  },
  listApp() {
    console.info("this is in listSetting");
    return common.get("/sync/type/list", null);
  },
  listStrategy(){
    console.info("this is in listSetting");
    return common.get("/sync/strategy/list", null);
  },
  saveBusinessItem(business){
    console.info("this is in listSetting");
    return common.post("/sync/business/item/saveorupdate", business);
  },
  listBusiness() {
    console.info("this is in listSetting");
    return common.get("/sync/business/list", null);
  },
  getBusiness(key) {
    console.info("this is in listSetting");
    return common.get("/sync/business/get/" + key, null);
  },
  saveBusiness(data) {
    console.info("this is in listSetting");
    return common.post("/sync/business/saveorupdate", data);
  },
  listBusinessItem(key){
    console.info("this is in listSetting");
    return common.get("/sync/business/item/listById/"+key, null);
  },
  getApp(key) {
    console.info("this is in listSetting");
    return common.get("/sync/type/get/" + key, null);
  },
  saveApp(data) {
    console.info("this is in listSetting");
    return common.post("/sync/type/saveorupdate", data);
  },
  getcfgto(code) {
    console.info("this is in listSetting");
    return common.get("/setting/getcfgto?code=" + code, null);
  },
  listBean() {
    console.info("this is in listSetting");
    return common.get("/sync/bean/list", null);
  },
  // 获取所有 TO 对象
  selectBeanTO() {
    console.info("this is in selectBaseTO");
    return common.get("/sync/bean/query?type=TO", null);
  },
  selectBeanSetting() {
    console.info("this is in selectBaseTO");
    return common.get("/sync/bean/query?type=SETTING", null);
  },
  listFiledConnect() {
    console.info("this is in listSetting");
    return common.get("/sync/field/list", null);
  },
  getFiledConnect(key) {
    console.info("this is in listSetting");
    return common.get("/sync/field/get/"+key, null);
  },
  saveFiledConnect(data) {
    console.info("this is in listSetting");
    return common.post("/sync/field/saveorupdate", data);
  },
  saveCfgTo(cfg) {
    console.info("this is in saveCfgTo");
    return common.post("/setting/saveorupdate", cfg);
  },
  // 扫描包
  scanBaseTO(type) {
    console.info("this is in saveCfgTo");
    var query = type == null ? "" : "?type=" + type;
    return common.get("/sync/bean/selectBaseTO" + query);
  },
  push() {
    var query = type == null ? "" : "?type=" + type;
    return common.get("/sync/field/selectBaseTO" + query);
    business/type/{typeId}
  },
  startBusiness(businessId) {
    return common.get("/excute/business/single/" + businessId);
  }


}
