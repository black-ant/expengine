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
            <div class="row">
              <div class="col-md-3">
                <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span
                  class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a> 工作界面
                </h3>
              </div>
              <div class="col-md-6">
              </div>
              <div class="col-md-3">
                <div class="col-md-6 right">
                  <button type="button" class="btn btn-primary btn-block" v-on:click="query()">刷新</button>
                </div>
                <div class="col-md-6 right">
                  <button type="button" class="btn btn-success btn-block" v-on:click="scan()">拉取</button>
                </div>
              </div>
            </div>
          </div>
          <div class="content-row">
            <div class="col-md-12">
              <div class="panel-group panel-group-lists collapse in" id="accordion2" style="">
                <div class="panel" v-for="(item,index) in filedList">
                  <div class="glyphicon-class">

                  </div>
                  <div class="panel-heading">
                    <h4 class="panel-title">
                      <a data-toggle="collapse" data-parent="#accordion2" :href="'#collapse'+item.beanName"
                         class="collapsed" v-on:click="select(index)">
                        {{item.beanType}} - {{item.beanAppCode}} - {{item.beanName}}
                      </a>
                    </h4>
                  </div>
                  <div :id="'collapse'+item.beanName" class="panel-collapse collapse table_in" style="height: 0px;">
                    <div class="panel-body">
                      <div class="table-responsive">
                        <table class="table table-bordered table-striped">
                          <colgroup>
                            <col class="col-xs-1">
                            <col class="col-xs-7">
                          </colgroup>
                          <thead>
                          <tr>
                            <th>属性名</th>
                            <th>含义</th>
                          </tr>
                          </thead>
                          <tbody>
                          <tr class="active" v-for="param in params">
                            <td>{{param.key}}</td>
                            <td>{{param.desc}}</td>
                          </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
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
        filedList: {},
        params: {}
      }
    },
    methods: {
      select(key) {
        console.log("选择 :" + key);
        this.params = JSON.parse(this.filedList[key].beanBody);
      },
      detail(key) {
        console.log("显示详情");
      },
      async scan() {
        var settingsBack = await otherApi.scanBaseTO()
      },
      async query() {
        var settingsBack = await otherApi.listBean();
        this.filedList = settingsBack.data;
        this.params = JSON.parse(this.filedList[0].beanBody);
        console.log("setting is :%o", settingsBack)
        console.log("params is :%o", this.params)
      }
    },
    mounted: async function () {
      this.query();
    },
  }
</script>

<style scoped>
  @import "../assets/css/common.css";
</style>
