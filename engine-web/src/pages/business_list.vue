<template>
  <div class="content-row">
    <div class="row">
    </div>
    <div class="bs-example">
      <table class="table">
        <thead>
        <tr>
          <th>业务名称</th>
          <th>业务唯一编码</th>
          <th>业务描述</th>
          <th>业务类型</th>
          <th>生产者</th>
          <th>消费者</th>
          <th>配置设置
            <button type="button" class="btn btn-success" v-on:click="goTo('1')">创建</button>
          </th>
        </tr>
        </thead>
        <tbody>

        <tr class="active" v-for="item in apps">
          <td>{{item.businessName}}</td>
          <td>{{item.businessCode}}</td>
          <td>{{item.businessDesc}}</td>
          <td>{{item.businessCode}}</td>
          <td>{{item.syncProduce}}</td>
          <td>{{item.syncConsumer}}</td>
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
        this.$router.push('/business_save?key=' + key);
      },
      detail(key) {
        console("显示详情");
      }
    },
    mounted: async function () {

      var settingsBack = await otherApi.listBusiness();
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
