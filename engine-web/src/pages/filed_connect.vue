<template>
  <div class="col-xs-12 col-sm-9 content">
    <div class="panel panel-default">
      <div class="panel-body">
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-body">


      <div class="container-fluid common">
    <div class="content-row">
      <div class="row">
        <div class="col-md-6">
          <div class="input-group form-search">
            <select name="selecter_basic" class="selecter_3 form-control search-query"
                    data-selecter-options='{"cover":"true"}'
                    v-model="fieldConnectSelect" @change="changeConnect($event)">
              <option :value="item.id" v-for="item in connectList">{{item.syncTypeCode}}</option>
            </select>
            <span class="input-group-btn">
                <button data-type="last" class="btn btn-primary" type="submit"
                        v-on:click="selectConnect()">选择</button>
            </span>
          </div>
        </div>
        <div class="col-md-6 top_title">
          <div class="col-md-2 button">
            <span class="glyphicon glyphicon-plus" v-on:click="selectBaseTO()">新建</span>
          </div>
        </div>
      </div>

      <div class="row table_backgroud_frame " v-show="show_create">

        <div class="col-md-4">
          <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}'
                  v-model="originSelect" @change="changeConnect($event)">
            <option :value="item.id" v-for="item in beanList">{{item.beanCode}}</option>
          </select>
        </div>
        <!--        <div class="col-md-2">-->
        <!--          <span class="glyphicon glyphicon-resize-horizontal"></span>-->
        <!--        </div>-->
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


      <div class="panel panel-default table_frame">
        <div class="row" v-for="(value,key) in connectObjBody">
          <div class="col-md-4">
            <input type="text" placeholder="Text input" class="form-control" :name="key" :value="key">
          </div>
          <div class="col-md-4">
            <input type="text" placeholder="Text input" class="form-control" :name="value" v-bind:placeholder="value"
                   v-model="connectObjBody[key]">
          </div>
          <div class="col-md-1">
            <button type="button" class="btn btn-primary btn-block"><span class="glyphicon-link">锁定</span></button>
          </div>
          <div class="col-md-1">
            <button type="button" class="btn btn-primary btn-block"><span class="glyphicon-remove-circle">删除</span>
            </button>
          </div>
        </div>
      </div>
      <div class="panel panel-default panel-self-footer">
        <div class="col-md-10"></div>
        <div class="col-md-2 panel-self-footer-button">
          <button type="button" class="btn btn-primary btn-block" v-on:click="save()">保存</button>
        </div>
      </div>

    </div>
  </div>
      </div>
    </div>
  </div>
</template>

<script>

  import otherApi from '../restful/otherApi.js';
  import selfJS from '../restful/properties.js';

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

        // 缓存上一次选择的类型
        if (fieldConnect != null) {
          localStorage.setItem(selfJS.constant.FIELD_CONNECT_SELECT, this.fieldConnectSelect);
        }
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
      var cacheSelect = localStorage.getItem(selfJS.constant.FIELD_CONNECT_SELECT);
      if (cacheSelect != null) {
        this.fieldConnectSelect = cacheSelect;
        this.selectConnect();
      }
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
