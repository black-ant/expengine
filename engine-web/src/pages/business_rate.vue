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
              <h2 class="content-row-title">同步进度</h2>
              <div class="row content-row-progress">
                <div class="progress">
                  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                       style="width: 60%;">60%
                  </div>
                </div>
              </div>
              <h2 class="content-row-title">Logs</h2>
              <div class="row">
                <div class="col-md-12">
                  <div class="timeline">
                    <dl>
                      <dd class="pos-right clearfix">
                        <div class="circ"></div>
                        <div class="events">
                          <div class="pull-left">
                            <span class="glyphicon glyphicon-refresh"></span>
                          </div>
                          <div class="events-body">
                            <h4 class="events-heading">Bootstrap : 2021-02-27 23:49</h4>
                            <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                              aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                              helvetica.</p>
                          </div>
                        </div>
                      </dd>
                      <dd class="pos-right clearfix">
                        <div class="circ"></div>
                        <div class="events">
                          <div class="pull-left">
                            <span class="glyphicon glyphicon-ok"></span>
                          </div>
                          <div class="events-body">
                            <h4 class="events-heading">Bootfla : 2021-02-27 23:49t</h4>
                            <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                              aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                              helvetica.</p>
                          </div>
                        </div>
                      </dd>
                      <dd class="pos-right clearfix">
                        <div class="circ"></div>
                        <div class="events">
                          <div class="pull-left">
                            <span class="glyphicon glyphicon-ok"></span>
                          </div>
                          <div class="events-body">
                            <h4 class="events-heading">Flat UI : 2021-02-27 23:49</h4>
                            <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                              aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                              helvetica.</p>
                          </div>
                        </div>
                      </dd>
                      <dd class="pos-right clearfix">
                        <div class="circ"></div>
                        <div class="events">
                          <div class="pull-left">
                            <span class="glyphicon glyphicon-ok"></span>
                          </div>
                          <div class="events-body">
                            <h4 class="events-heading">UI design : 2021-02-27 23:49</h4>
                            <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                              aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                              helvetica.</p>
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
        business: {}
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
      var key = this.$route.query.key;
      console.log("show Busniess Rate : " + key);
      // 进度页面进来后做三件事 :
      // Step 1 : 获取 Business
      var response = await otherApi.getBusiness(key);
      console.log(this.typeto);
      this.business = response['data'];
    },
  }
</script>

<style scoped>
  @import "../assets/css/common.css";
</style>
