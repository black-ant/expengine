<template>
  <div class="container-fluid common">
    <div class="content-row">
      <div class="row">
        <div class="col-md-6">
          <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}'
                  v-model="fieldConnectSelect" @change="changeConnect($event)">
            <option :value="item.id" v-for="item in connectList">{{item.syncTypeCode}}</option>
          </select>
        </div>
        <div class="col-md-3">
          <button type="button" class="btn btn-primary btn-block" v-on:click="selectBaseTO()">新建</button>
        </div>
        <div class="col-md-3">
          <button type="button" class="btn btn-primary btn-block" v-on:click="selectConnect()">选择</button>
        </div>
      </div>

      <div class="row" v-show="show_create">
        <div class="col-md-4">
          <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}'
                  v-model="originSelect" @change="changeConnect($event)">
            <option :value="item.id" v-for="item in beanList">{{item.beanCode}}</option>
          </select>
        </div>
        <div class="col-md-4">
          <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}'
                  v-model="targetSelect" @change="changeConnect($event)">
            <option :value="item.id" v-for="item in beanList">{{item.beanCode}}</option>
          </select>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-primary btn-block" v-on:click="ensureBaswTO()">确定</button>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-primary btn-block" v-on:click="cancle_create()">取消</button>
        </div>
      </div>


      <div class="panel panel-default">
        <div class="panel-heading">配置映射 :</div>
        <div class="row" v-for="(value,key) in connectObjBody">
          <div class="col-md-5">
            <input type="text" placeholder="Text input" class="form-control" :name="key" :value="key">
          </div>
          <div class="col-md-5">
            <input type="text" placeholder="Text input" class="form-control" :name="value" v-bind:placeholder="value"
                   v-model="connectObjBody[key]">
          </div>
          <div class="col-md-1">
            <button type="button" class="btn btn-primary btn-block">锁定</button>
          </div>
          <div class="col-md-1">
            <button type="button" class="btn btn-primary btn-block">删除</button>
          </div>
        </div>
      </div>

      <div class="col-md-12">
        <button type="button" class="btn btn-primary btn-block" v-on:click="save()">保存</button>
      </div>

    </div>
  </div>
</template>

<script>

  import otherApi from '../restful/otherApi.js';

  export default {
    name: "filedInfo",
    data() {
      return {
        connectList: {},
        beanList: {},
        beanMap: {},
        originSelect: {},
        targetSelect: {},
        connectObj: {},
        connectObjBody: {},
        fieldConnectSelect: {},
        show_create: false
      }
    },
    methods: {
      async save() {
        console.log(this.connectObjBody);
        var connectObjItem = {};
        if (this.connectObj['id'] == null) {
          connectObjItem["fieldBody"] = JSON.stringify(this.connectObjBody);
          connectObjItem["fieldOriginFormat"] = this.beanMap[this.originSelect].beanBody;
          connectObjItem["fieldSourceFormat"] = this.beanMap[this.targetSelect].beanBody;
          connectObjItem["originCode"] = this.beanMap[this.originSelect].beanCode;
          connectObjItem["targetCode"] = this.beanMap[this.targetSelect].beanCode;
          connectObjItem["syncTypeCode"] = connectObjItem["originCode"] + "_" + connectObjItem["targetCode"];
        } else {
          this.connectObj['fieldBody'] = JSON.stringify(this.connectObjBody);
          connectObjItem = this.connectObj;
        }
        var fieldConnect = await otherApi.saveFiledConnect(connectObjItem);
      },
      // 获取 TO 列表
      async selectBaseTO() {
        var beanList = await otherApi.selectBeanTO();
        this.beanList = beanList.data;
        var beanMap = {};
        for (var objKey in this.beanList) {
          var obj = this.beanList[objKey];
          beanMap[obj.id] = obj;
        }
        this.beanMap = beanMap;
        console.log(this.beanList)
        this.show_create = true;
      },
      // 确定新建 TO 关联
      async ensureBaswTO() {
        var body = this.beanMap[this.originSelect].beanBody;
        var json = JSON.parse(body);
        var newObj = {};
        for (var item in json) {
          newObj[json[item]['key']] = null;
        }
        this.connectObjBody = newObj;
        this.show_create = false;
      },
      changeConnect(event) {
        this.fieldConnectSelect = event.target.value;
        console.log("选择 :" + this.fieldConnectSelect);
      },
      // 查询所有管理关系
      async selectConnect() {
        var fieldConnect = await otherApi.getFiledConnect(this.fieldConnectSelect);
        console.log("connectObj is :%o", fieldConnect);
        this.connectObj = fieldConnect.data;
        this.connectObjBody = JSON.parse(fieldConnect.data.fieldBody);
      },
      detail(key) {
        console.log("显示详情");
      },
      cancle_create() {
        this.show_create = false
      }
    },
    mounted: async function () {
      var connectList = await otherApi.listFiledConnect();
      console.log("connectList is :%o", connectList);
      this.connectList = connectList.data;
    },
  }
</script>

<style scoped>
  @import "../assets/css/site.min.css";
  @import "../assets/css/common.css";

  .panel-heading {
    margin-bottom: 10px;
  }
</style>
