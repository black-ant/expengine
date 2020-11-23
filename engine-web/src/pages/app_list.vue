<template>
  <div class="content-row">
    <div class="bs-example">
      <table class="table">
        <thead>
        <tr>
          <th>应用名称</th>
          <th>应用代码</th>
          <th>应用状态</th>
          <th>类型</th>
          <th>配置设置</th>
        </tr>
        </thead>
        <tbody>

        <tr class="active" v-for="item in apps">
          <td>{{item.supplierName}}</td>
          <td>{{item.typeCode}}</td>
          <td>{{item.typePart=='USER'?"人员":item.typePart=='ORG'?"组织":"配置"}}</td>
          <td>{{item.dataType=='OP'?"操作":"实体类"}}</td>
          <td>
            <button class="btn btn-info" v-on:click="" v-if="item.typePart=='CFG'">编辑</button>
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
    name: "applist",
    data() {
      return {
        apps: {}
      }
    },
    mounted: async function () {

      var settingsBack = await otherApi.listApp();
      this.apps = settingsBack.data;
      console.log("setting is :%o", settingsBack)
    },
  }
</script>

<style scoped>
  tr {
    font-size: 15px;
  }

  tr td {
    padding-top: 14px;
  }
</style>
