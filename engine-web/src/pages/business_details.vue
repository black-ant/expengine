<template>
  <div class="container-fluid">
    <div class="content-row">
      <div class="row">
        <div class="col-md-5">
          <div class="well">{{business.syncProduce}}</div>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-primary btn-block"  v-on:click="runStart()">执行</button>
        </div>
        <div class="col-md-5">
          <div class="well">{{business.syncConsumer}}</div>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">
          <span>执行列表 </span>
        </div>
        <div class="col-md-12">
          <div class="col-md-1">关联关系</div>
          <select name="selecter_basic" class="selecter_3 col-md-2" data-selecter-options='{"cover":"true"}'
                  v-model="fieldConnectSelect" @change="changeConnect($event)">
            <option :value="item.id" v-for="item in connectList">{{item.fieldTypeCode}}</option>
          </select>
          <div class="col-md-1">策略关系</div>
          <select name="selecter_basic" class="selecter_3 col-md-2" data-selecter-options='{"cover":"true"}'
                  v-model="strategySelect" @change="changeStrategy($event)">
            <option :value="item.id" v-for="item in strategyList">{{item.strategyName}}</option>
          </select>
          <div class="col-md-1">名称</div>
          <div class="col-md-2">
            <input type="text" class="form-control" :name="itemSeletName" v-bind:placeholder="itemSeletName"
                   v-model="itemSeletName">
          </div>


          <div class="col-md-3">
            <button type="button" class="btn btn-primary btn-block" v-on:click="newConnect()">添加关联</button>
          </div>
        </div>
        <div class="row">
          <table class="table">
            <thead>
            <tr>
              <th></th>
              <th>属性名</th>
              <th>属性策略</th>
              <th>属性映射</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in businessList">
              <td>
                <div class="checkbox">
                  <input type="checkbox" id="flat-checkbox-2" checked>
                  <label for="flat-checkbox-2">执行</label>
                </div>
              </td>
              <td>{{item.itemName}}</td>
              <td>{{item.itemName}}</td>
              <td>{{item.itemName}}</td>
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
        strategyList: {},
        fieldConnectSelect: {},
        strategySelect: {},
        itemSeletName: ""
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
      },
      changeConnect(event) {
        this.fieldConnectSelect = event.target.value;
        console.log("选择 :" + this.fieldConnectSelect);
      },
      changeStrategy(event) {
        this.strategySelect = event.target.value;
        console.log("选择 :" + this.strategySelect);
      },
      async selectConnect() {
        var connectList = await otherApi.listFiledConnect();
        console.log("connectList is :%o", connectList);
        this.connectList = connectList.data;

        var strategyList = await otherApi.listStrategy();
        console.log("connectList is :%o", connectList);
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
      runStart(){
        var back = otherApi.startBusiness(this.businessId);
      }
    },
    mounted: async function () {
      var key = this.$route.query.key;
      console.log(key);
      if (key != null) {
        this.businessId = key;
        this.initConnect();
      }

      this.selectConnect();
    },
  }
</script>

<style scoped>
  @import "../assets/css/site.min.css";

  .panel-heading {
    margin-bottom: 10px;
  }
</style>
