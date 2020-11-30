<template>
  <div class="container-fluid">
    <div class="content-row">
      <div class="row">
        <div class="col-md-5">
          <div class="well">{{business.syncProduce}}</div>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn btn-primary btn-block">执行</button>
        </div>
        <div class="col-md-5">
          <div class="well">{{business.syncConsumer}}</div>
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-heading">执行列表 :</div>
        <div class="row" >
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
        businessList: {}
      }
    },
    methods: {},
    mounted: async function () {
      var key = this.$route.query.key;
      console.log(key);
      if (key != null) {
        var response = await otherApi.getBusiness(key);
        console.log(response);
        this.business = response['data'];

        var responseList = await otherApi.listBusinessItem(key);
        console.log(responseList);
        this.businessList = responseList['data'];
      }
    },
  }
</script>

<style scoped>
  @import "../assets/css/site.min.css";

  .panel-heading {
    margin-bottom: 10px;
  }
</style>
