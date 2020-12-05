<template>
  <div class="content-row common" id="main_bdoy_id">
    <div class="row content-row-modal">
      <div class="col-md-12">
        <div class="row">

          <div class="col-xs-12">
            <label>配置类型</label>
            <input type="text" class="form-control" :name="value" v-bind:placeholder="setting.settingType"
                   v-bind:value="setting.settingType">
          </div>
          <div class="col-xs-12">
            <label>配置名称</label>
            <input type="text" class="form-control" :name="value" v-bind:placeholder="setting.settingName"
                   v-bind:value="setting.settingName">
          </div>
          <div class="col-xs-12">
            <label>配置代码</label>
            <input type="text" class="form-control" :name="value" v-bind:placeholder="setting.settingCode"
                   v-bind:value="setting.settingCode">
          </div>

          <div class="col-xs-12">
            <label>配置信息</label>
          </div>
          <div class="col-md-9">
            <select name="selecter_basic" class="selecter_3" data-selecter-options='{"cover":"true"}'
                    v-model="targetSelect">
              <option :value="item.id" v-for="item in beanList">{{item.beanCode}}</option>
            </select>
          </div>

          <div class="col-md-3">
            <button type="button" class="btn btn-primary btn-block" v-on:click="ensureNew()">确定重置</button>
          </div>
        </div>
        <div class="modal">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title">配置连接信息</h4>
              </div>
              <div class="modal-body">
                <div class="modal-footer">
                  <div class="row">
                    <div class="col-xs-8" v-for="(value, key) in settingBody">
                      <label>{{key}}</label>
                      <input type="text" class="form-control" :name="value" v-bind:placeholder="value"
                             v-model="settingBody[key]">
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" v-on:click="save()">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  import otherApi from '../restful/otherApi.js';

  export default {
    name: "settings",
    data() {
      return {
        setting: {},
        settingBody: {},
        cfgtos: {},
        beanList: {},
        beanMap: {},
        targetSelect: {},
      }
    },
    mounted: async function () {
      var key = this.$route.query.key;
      console.log(key);

      var beanList = await otherApi.selectBeanSetting();
      this.beanList = beanList.data;
      var beanMap = {};
      for (var objKey in this.beanList) {
        var obj = this.beanList[objKey];
        beanMap[obj.id] = obj;
      }
      this.beanMap = beanMap;

      var setting = await otherApi.getSetting(key);
      this.setting = setting.data;
      this.settingBody = JSON.parse(this.setting['settingBody']);
    }, methods: {
      save: async function () {
        var settings = this.setting;
        var settingsBody = this.settingBody;
        settings['settingBody'] = JSON.stringify(settingsBody);
        console.log("settings is " + JSON.stringify(settings));
        await otherApi.createSetting(settings);
      },
      changeConnect(event) {
        this.fieldConnectSelect = event.target.value;
        console.log("选择 :" + this.fieldConnectSelect);
      },
      ensureNew: async function () {
        var body = this.beanMap[this.targetSelect].beanBody;
        var json = JSON.parse(body);
        var newObj = {};
        for (var item in json) {
          newObj[json[item]['key']] = "";
        }
        this.settingBody = newObj;
      }
    }
  }

</script>

<style scoped>
  @import "../assets/css/common.css";

  .content-row-modal .modal-dialog {
    width: 1200px;
    margin: 20px;
  }
</style>
