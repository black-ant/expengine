<template>
  <div class="content-row">
    <div class="row">
    </div>
    <div class="bs-example">
      <table class="table">
        <thead>
        <tr>
          <th>应用名称</th>
          <th>应用代码</th>
          <th>应用状态</th>
          <th>类型</th>
          <th>应用类</th>
          <th>配置设置
            <button type="button" class="btn btn-success" v-on:click="goTo('1')">创建</button>
          </th>
        </tr>
        </thead>
        <tbody>

        <tr class="active" v-for="item in apps">
          <td>{{item.supplierName}}</td>
          <td>{{item.typeCode}}</td>
          <td>{{item.typePart=='USER'?"人员":item.typePart=='ORG'?"组织":"配置"}}</td>
          <td>{{item.dataType=='OP'?"操作":"实体类"}}</td>
          <td>{{item.typeClass}}</td>
          <td>
            <button class="btn btn-info" v-on:click="goTo(item.id)">编辑</button>
            <button class="btn btn-info" v-on:click="detail()">详情</button>
            <button type="button" class="btn btn-danger btn-info">删除</button>
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
    methods: {
      goTo(key) {
        //直接跳转
        this.$router.push('/appsave?key=' + key);
      },
      detail(key) {
        console("显示详情");
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
