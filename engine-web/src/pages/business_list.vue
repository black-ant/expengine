<template>

  <div class="col-xs-12 col-sm-9 content">
    <div class="panel panel-default">
      <div class="panel-body">
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-body">


        <div class="parent_common">
          <div class="panel-heading">
            <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span
              class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a> 工作界面
            </h3>
          </div>
          <div class="content-row common">
            <div class="alert alert-success alert-dismissable alert" v-show="showSuccessAlter">
              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
              <strong>保存成功!</strong>
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
                    <button class="btn btn-info" v-on:click="detail(item.id)">运行</button>
                    <button type="button" class="btn btn-danger btn-info" v-on:click="deleteOne(item.id)">删除</button>
                  </td>
                </tr>

                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  import otherApi from '../restful/otherApi.js';

  export default {
    name: "applist",
    data() {
      return {
        apps: {},
        showSuccessAlter: false
      }
    },
    methods: {
      goTo(key) {
        //直接跳转
        this.$router.push('/business_save?key=' + key);
      },
      detail(key) {
        this.$router.push('/business_details?key=' + key);
      },
      async deleteOne(key) {
        await otherApi.deleteBusiness(key);
        this.selectAllBusiness();
        this.showSuccessAlter = true;
      },
      async selectAllBusiness() {
        var settingsBack = await otherApi.listBusiness();
        this.apps = settingsBack.data;
        console.log("setting is :%o", settingsBack)
      }
    },
    mounted: async function () {
      this.selectAllBusiness();
    },
  }
</script>

<style scoped>
  @import "../assets/css/common.css";
</style>
