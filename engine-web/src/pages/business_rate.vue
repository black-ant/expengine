<template>

  <div class="col-xs-12 col-sm-9 content common">
    <div class="panel panel-default">
      <div class="panel-body panel-body-top">
        <div class="col-md-8">
          <ol class="breadcrumb breadcrumb-arrow">
            <li><a href="#"><i class="glyphicon glyphicon-home"></i> Home</a></li>
            <li><a href="#"><i class="glyphicon glyphicon-list-alt"></i> Library</a></li>
            <li class="active"><span><i class="glyphicon glyphicon-calendar"></i> Data</span></li>
          </ol>
        </div>
        <div class="col-md-1 button-top">
          <button type="button" class="btn btn-success" v-on:click="goTo('1')">创建</button>
        </div>
        <div class="col-md-1 button-top">
          <button type="button" class="btn btn-success" v-on:click="goTo('1')">创建</button>
        </div>

      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-body">
        <div class="col-md-12">
          <div class="col-md-6">业务名称</div>
          <div class="col-md-6"> {{business.businessName}}</div>
        </div>
        <div class="col-md-12">
          <div class="col-md-6">业务唯一编码</div>
          <div class="col-md-6"> {{business.businessCode}}</div>
        </div>
        <div class="col-md-12">
          <div class="col-md-6">业务描述</div>
          <div class="col-md-6"> {{business.businessDesc}}</div>
        </div>
        <div class="col-md-12">
          <div class="col-md-6">业务类型</div>
          <div class="col-md-6"> {{business.businessCode}}</div>
        </div>
        <div class="col-md-12">
          <div class="col-md-6">生产者</div>
          <div class="col-md-6"> {{business.syncProduce}}</div>
        </div>
        <div class="col-md-12">
          <div class="col-md-6">消费者</div>
          <div class="col-md-6"> {{business.syncConsumer}}</div>
        </div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-body">

        <div class="content-row ">
          <div class="row">
          </div>
          <div class="bs-example">
            <div class="col-md-12">
              <h2 class="content-row-title">
                <span class="text-title">同步进度 :</span>
                <span class=" float-right">手动刷新</span>
                <span class="glyphicon glyphicon-refresh float-right"></span>
              </h2>
              <div class="row content-row-progress">
                <div class="progress">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                       style="width: 60%;">60%
                  </div>
                </div>
              </div>
              <h2 class="content-row-title">
                <span class="text-title">同步日志 :</span>
                <span class=" float-right">查看所有</span>
                <span class="float-right glyphicon glyphicon-th"></span>
              </h2>
              <div class="row">
                <div class="col-md-12">
                  <div class="timeline">
                    <dl>

                      <dd class="pos-right clearfix" v-for="item in logs">
                        <div class="circ"></div>
                        <div class="events">
                          <div class="pull-left">
                            <span v-show="item.logStatus==1" class="glyphicon glyphicon-refresh"></span>
                            <span v-show="item.logStatus==0" class="glyphicon glyphicon-ok"></span>
                          </div>
                          <div class="events-body">
                            <h4 class="events-heading">{{item.logDomain}} : {{item.createDate}}</h4>
                            <p>{{item.logData}}</p>
                          </div>
                        </div>
                      </dd>

                    </dl>
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
        logs: {},
        business: {},
        time: {},
        updateNum: 0
      }
    },
    methods: {
      goTo(key) {
        //直接跳转
        this.$router.push('/business_save?key=' + key);
      },
      detail(key) {
        console("显示详情");
      },
      updateLog: async function (key) {
        var response = await otherApi.listLogsPage(key, 0, 6);
        console.log(response);
        if (response != null) {
          this.logs = response['data']['records'];
          this.updateNum = 0;
        } else if (this.updateNum > 5) {
          clearInterval(this.timer);
        } else {
          this.updateNum = +1;
        }
      }
    },
    mounted: async function () {
      var key = this.$route.query.key;
      console.log("show Busniess Rate : " + key);
      // 进度页面进来后做三件事 :
      // Step 1 : 获取 Business
      var response = await otherApi.getBusiness(key);
      console.log(this.typeto);
      this.business = response['data'];

      // Step 2 : 查询 logs
      this.time = setInterval(this.updateLog(key), 5000);
    },

  }
</script>

<style scoped>
  @import "../assets/css/common.css";
</style>
