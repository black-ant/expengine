<template>
  <div class="content-row">
    <div class="row">
    </div>
    <div class="bs-example">
      <div class="col-md-12">
        <h2 class="content-row-title">同步类型</h2>
        <div class="row">
          <div class="col-md-3">
            <div class="selecter  closed" tabindex="0">
              <label>生产者</label>
              <select name="selecter_basic" class="selecter_1 selecter-element" tabindex="-1">
                <optgroup label="Group One">
                  <option value="1">One</option>
                  <option value="2">Two</option>
                  <option value="3">Three</option>
                </optgroup>
              </select>
              <span class="selecter-selected">One</span>
              <div class="selecter-options" style="display: none;">
                <span class="selecter-group">Group One</span>
                <span class="selecter-item selected" data-value="1">One</span>
                <span class="selecter-item" data-value="2">Two</span>
                <span class="selecter-item" data-value="3">Three</span>
                <span class="selecter-group">Group One</span>
              </div>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  closed" tabindex="0">
              <label>消费者</label>
              <select name="selecter_basic" class="selecter_2 selecter-element"
                      data-selecter-options="{&quot;label&quot;:&quot;Jump Sites&quot;,&quot;external&quot;:&quot;true&quot;}"
                      tabindex="-1">
                <option value="http://google.com">Google Search</option>
                <option value="http://boingboing.com">BoingBoing</option>
                <option value="http://cnn.com">CNN News</option>
              </select>
              <span class="selecter-selected placeholder">Google Search</span>
              <div class="selecter-options">
                <a class="selecter-item selected" href="http://google.com">Google Search</a>
                <a class="selecter-item" href="http://boingboing.com">BoingBoing</a>
                <a class="selecter-item" href="http://cnn.com">CNN News</a>
              </div>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  cover closed" tabindex="0">
              <label>映射模板</label>
              <select name="selecter_basic"
                      class="selecter_3 selecter-element"
                      data-selecter-options="{&quot;cover&quot;:&quot;true&quot;}"
                      tabindex="-1">
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
                <option value="4">Four</option>
                <option value="5">Five</option>
              </select><span class="selecter-selected">One</span>
              <div class="selecter-options"><span class="selecter-item selected" data-value="1">One</span><span
                class="selecter-item" data-value="2">Two</span><span class="selecter-item"
                                                                     data-value="3">Three</span><span
                class="selecter-item" data-value="4">Four</span><span class="selecter-item" data-value="5">Five</span>
              </div>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  closed disabled" tabindex="0">
              <label>操作</label>
              <button type="button" class="btn btn-primary btn-block">开始同步</button>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-3">
            <div class="selecter  closed" tabindex="0">
              <label>生产者</label>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  closed" tabindex="0">
              <label>消费者</label>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  cover closed" tabindex="0">
              <label>映射模板</label>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  closed disabled" tabindex="0">
              <button type="button" class="btn btn-primary btn-block">暂停推送</button>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-3">
            <div class="selecter  closed" tabindex="0">
              <label>生产者</label>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  closed" tabindex="0">
              <label>消费者</label>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  cover closed" tabindex="0">
              <label>映射模板</label>
            </div>
          </div>
          <div class="col-md-3">
            <div class="selecter  closed disabled" tabindex="0">
              <button type="button" class="btn btn-primary btn-block">暂停推送</button>
            </div>
          </div>
        </div>
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
                <dt>Apr 2014</dt>
                <dd class="pos-right clearfix">
                  <div class="circ"></div>
                  <div class="time">Apr 14</div>
                  <div class="events">
                    <div class="pull-left">
                      <img class="events-object img-rounded" src="dist/img/photo-1.jpg">
                    </div>
                    <div class="events-body">
                      <h4 class="events-heading">Bootstrap</h4>
                      <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                        aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                        helvetica.</p>
                    </div>
                  </div>
                </dd>
                <dd class="pos-right clearfix">
                  <div class="circ"></div>
                  <div class="time">Apr 10</div>
                  <div class="events">
                    <div class="pull-left">
                      <img class="events-object img-rounded" src="dist/img/photo-2.jpg">
                    </div>
                    <div class="events-body">
                      <h4 class="events-heading">Bootflat</h4>
                      <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                        aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                        helvetica.</p>
                    </div>
                  </div>
                </dd>
                <dt>Mar 2014</dt>
                <dd class="pos-right clearfix">
                  <div class="circ"></div>
                  <div class="time">Mar 15</div>
                  <div class="events">
                    <div class="pull-left">
                      <img class="events-object img-rounded" src="dist/img/photo-3.jpg">
                    </div>
                    <div class="events-body">
                      <h4 class="events-heading">Flat UI</h4>
                      <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                        aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                        helvetica.</p>
                    </div>
                  </div>
                </dd>
                <dd class="pos-right clearfix">
                  <div class="circ"></div>
                  <div class="time">Mar 8</div>
                  <div class="events">
                    <div class="pull-left">
                      <img class="events-object img-rounded" src="dist/img/photo-4.jpg">
                    </div>
                    <div class="events-body">
                      <h4 class="events-heading">UI design</h4>
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
