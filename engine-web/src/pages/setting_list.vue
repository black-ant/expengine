<template>
  <div class="content-row common">
    <div class="col-md-12 container-fluid">
      <div class="collapse navbar-collapse" id="bs-content-row-navbar-collapse-8">

      </div>
      <nav class="navbar navbar-inverse color_top_self" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
          </div>
          <div class="collapse navbar-collapse col-md-2 f-right" id="bs-content-row-navbar-collapse-8">
            <button type="button" class="btn btn-primary navbar-btn" v-on:click="newSetting()">新建</button>
          </div>
        </div>
      </nav>
    </div>
    <div class="bs-example">
      <table class="table">
        <thead>
        <tr>
          <th>配置名</th>
          <th>配置类型</th>
          <th>配置状态</th>
          <th>配置策略</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>

        <tr class="active" v-for="item in settings">
          <td>{{item.settingName}}</td>
          <td>{{item.settingType}}</td>
          <td>{{item.settingTypeCode}}</td>
          <td>{{item.settingPolicy}}</td>
          <td>
            <button v-on:click="edit(item.id)">编辑</button>
            <button v-on:click="deleteOne(item.id)">删除</button>
          </td>
        </tr>

        </tbody>
      </table>
    </div>
  </div>
</template>

<script>

  import otherApi from '../restful/otherApi.js';

  export default {
    name: "settinglist",
    data() {
      return {
        settings: {}
      }
    },
    methods: {
      async deleteOne(key) {
        await otherApi.deleteSetting(key);
        this.selectAll();
      },
      edit(key) {
        this.$router.push('/setting_save?key=' + key);
      },
      newSetting() {
        this.$router.push('/setting_save');
      },
      async selectAll(){
        var settingsBack = await otherApi.listSetting();
        this.settings = settingsBack.data;
        console.log("setting is :%o", settingsBack)
      }
    },
    mounted: async function () {
      this.selectAll();
    },
  }
</script>

<style scoped>
  @import "../assets/css/common.css";
</style>
