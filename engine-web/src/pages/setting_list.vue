<template>
  <div class="content-row">
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
            <button v-on:click="">编辑</button>
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
    }, mounted: async function () {

      var settingsBack = await otherApi.listSetting();
      this.settings = settingsBack.data;
      console.log("setting is :%o", settingsBack)
    },
  }
</script>

<style scoped>

</style>
