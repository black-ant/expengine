<template>
  <div class="container-fluid">
    <div class="content-row">
      <div class="row">
        <div class="col-md-6">
          <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}'
                  v-model="fieldConnectSelect" @change="changeConnect($event)">
            <option :value="item.id" v-for="item in connectList">{{item.fieldTypeCode}}</option>
          </select>
        </div>
        <div class="col-md-3">
          <button type="button" class="btn btn-primary btn-block">新建</button>
        </div>
        <div class="col-md-3">
          <button type="button" class="btn btn-primary btn-block" v-on:click="selectConnect()">选择</button>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">配置映射 :</div>
        <div class="row" v-for="(key,value) in connectObj">
          <div class="col-md-5">
            <input type="text" placeholder="Text input" class="form-control" :name="key" v-bind:placeholder="key">
          </div>
          <div class="col-md-5">
            <input type="text" placeholder="Text input" class="form-control" :name="value" v-bind:placeholder="value"
                   v-model="connectObj[key]">
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
        <button type="button" class="btn btn-primary btn-block">保存</button>
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
        connectObj: {},
        fieldConnectSelect: {},
      }
    },
    methods: {
      changeConnect(event) {
        this.fieldConnectSelect = event.target.value;
        console.log("选择 :" + this.fieldConnectSelect);
      },
      async selectConnect() {
        var fieldConnect = await otherApi.getFiledConnect(this.fieldConnectSelect);
        console.log("connectObj is :%o", fieldConnect);
        this.connectObj = JSON.parse(fieldConnect.data.fieldBody);
      },
      detail(key) {
        console.log("显示详情");
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

  .panel-heading {
    margin-bottom: 10px;
  }
</style>
