<template>
  <div class="container-fluid">
    <div class="content-row common">
      <div class="row">
        <!-- 执行业务关联 Start  -->
        <div class="col-md-5">
          <div class="well">{{business.syncProduce}}</div>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-primary btn-block" v-on:click="runStart()">执行</button>
        </div>
        <div class="col-md-5">
          <div class="well">{{business.syncConsumer}}</div>
        </div>
        <!-- 执行业务关联 End -->

        <!-- 执行配置关联 Start  -->
        <div class="col-md-1 label_self" style="margin-left: 1em">
          生产者配置 :
        </div>
        <div class="col-md-4">
          <select name="selecter_basic" class="selecter_3 col-md-2" data-selecter-options='{"cover":"true"}'
                  v-model="originSetting" @change="changeConnect($event)">
            <option :value="item.id" v-for="item in settings">{{item.settingName}}</option>
          </select>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-primary btn-block" v-on:click="saveSetting()">保存</button>
        </div>
        <div class="col-md-1 label_self">
          消费者配置 :
        </div>
        <div class="col-md-4">
          <select name="selecter_basic" class="selecter_3 col-md-2" data-selecter-options='{"cover":"true"}'
                  v-model="targetSetting" @change="changeConnect($event)">
            <option :value="item.id" v-for="item in settings">{{item.settingName}}</option>
          </select>
        </div>
        <!-- 执行配置关联 End -->


      </div>
      <div class="panel panel-default">
        <div class="panel-heading" style="height: 4.3em;">
          <div class="panel-title f-right">
            属性列表
          </div>
          <div class="col-md-2 f-right" >
            <button type="button" class="btn btn-primary btn-block" v-on:click="showConnect()">新建属性映射</button>
          </div>
        </div>
        <div class="col-md-12" v-show="showNewField">
          <!-- 执行关联其他信息 Start  -->
          <div class="col-md-1 label_self">
            属性映射 :
          </div>
          <div class="col-md-2">
            <select name="selecter_basic" class="selecter_3 col-md-2" data-selecter-options='{"cover":"true"}'
                    v-model="fieldConnectSelect" @change="changeConnect($event)">
              <option :value="item.id" v-for="item in connectList">{{item.syncTypeCode}}</option>
            </select>
          </div>
          <div class="col-md-1 label_self">
            策略配置:
          </div>
          <div class="col-md-2">
            <select name="selecter_basic" class="selecter_3 col-md-2" data-selecter-options='{"cover":"true"}'
                    v-model="strategySelect" @change="changeStrategy($event)">
              <option :value="item.id" v-for="item in strategyList">{{item.strategyName}}</option>
            </select>
          </div>
          <div class="col-md-1 label_self">
            子类名称:
          </div>
          <div class="col-md-3">
            <input type="text" class="form-control" :name="itemSeletName" v-bind:placeholder="itemSeletName"
                   v-model="itemSeletName">
          </div>
          <div class="col-md-1">
            <button type="button" class="btn btn-primary btn-block" v-on:click="newConnect()">添加关联</button>
          </div>
          <div class="col-md-1">
            <button type="button" class="btn btn-primary btn-block" v-on:click="newConnect()">关闭</button>
          </div>
        </div>
        <div class="row">
          <table class="table">
            <thead>
            <tr>
              <th class="table_header">是否执行</th>
              <th>属性业务</th>
              <th>属性策略</th>
              <th>属性映射</th>
              <th class="table_after">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in businessList">
              <td width="10%" class="table_header">
                <label class="toggle">
                  <input type="checkbox" checked="">
                  <span class="handle"></span>
                </label>
              </td>
              <td>{{item.syncBusinessName}}</td>
              <td>{{item.syncStrategyName}}</td>
              <td>{{item.syncFieldName}}</td>
              <td class="table_after">
                <button type="button" class="btn btn-danger btn-block"
                        v-on:click="deleteBusinessItem(item.syncBusinessNam)">删除
                </button>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
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
        business: {},
        businessId: {},
        businessList: {},
        connectList: {},
        originSetting: {},
        targetSetting: {},
        strategyList: {},
        fieldConnectSelect: {},
        strategySelect: {},
        settings: {},
        itemSeletName: "",
        showNewField: false,
      }
    },
    methods: {
      async initConnect() {
        var key = this.businessId;
        var response = await otherApi.getBusiness(key);
        console.log(response);
        this.business = response['data'];

        var responseList = await otherApi.listBusinessItem(key);
        console.log(responseList);
        this.businessList = responseList['data'];

        this.originSetting = this.business['syncProduceSetting'];
        this.targetSetting = this.business['syncConsumerSetting'];
        this.targetSetting = this.business['syncConsumerSetting'];
      },
      changeConnect(event) {
        this.fieldConnectSelect = event.target.value;
        console.log("选择 :" + this.fieldConnectSelect);
      },
      changeStrategy(event) {
        this.strategySelect = event.target.value;
        console.log("选择 :" + this.strategySelect);
      },
      async selectFieldConnect() {
        var connectList = await otherApi.listFiledConnect();
        console.log("connectList is :%o", connectList);
        this.connectList = connectList.data;
      },
      async selectStrategy() {
        var strategyList = await otherApi.listStrategy();
        console.log("strategyList is :%o", strategyList);
        this.strategyList = strategyList.data;
      },
      newConnect() {
        var businessItem = {
          "itemName": this.itemSeletName,
          "syncFieldId": this.fieldConnectSelect,
          "syncBusinessId": this.businessId,
          "synStrategyId": this.strategySelect
        }
        var back = otherApi.saveBusinessItem(businessItem);
        console.log(back);

        this.initConnect()
      },
      deleteBusinessItem(key) {
        otherApi.deleteBusinessItem(key);
      },
      runStart() {
        var back = otherApi.startBusiness(this.businessId);
      },
      // 查询所有的配置
      async selectAllSetting() {
        var settingsBack = await otherApi.listSetting();
        this.settings = settingsBack.data;
        console.log("setting is :%o", settingsBack)
      },
      // 更新业务配置
      async saveSetting() {
        this.business['syncProduceSetting'] = this.originSetting;
        this.business['syncConsumerSetting'] = this.targetSetting;
        otherApi.saveBusiness(this.business);
      },
      // 展示属性映射
      showConnect() {
        this.showNewField = true;
      }
    },
    mounted: async function () {

      this.selectFieldConnect();
      this.selectStrategy();
      this.selectAllSetting();

      var key = this.$route.query.key;
      console.log(key);
      if (key != null) {
        this.businessId = key;
        this.initConnect();
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
