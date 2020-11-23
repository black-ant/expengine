<template>
  <div class="content-row" id="main_bdoy_id">
    <div class="row content-row-modal">
      <div class="col-md-12">
        <h2 class="content-row-title">Modals</h2>
        <div class="modal">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title">Contact</h4>
              </div>
              <div class="modal-body">
                <p>配置连接信息</p>
                <div class="row">
                  <div class="col-xs-8" v-for="(value, key) in setting">
                    <label>{{key}}</label>
                    <input type="text" class="form-control" :name="value" v-bind:placeholder="value"
                           v-bind:value="value">
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-success" v-on:click="save()">Send</button>
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
    name: "settings",
    data() {
      return {
        setting: {},
        cfgtos: {}
      }
    },
    mounted: async function () {

      var cfgtos = await otherApi.getcfgto("WORK_WECHAT_CFG_TO");
      this.setting = JSON.parse(cfgtos.data.settingBody);
      this.cfgtos = cfgtos.data;
      console.log("cfgtos is :%o", cfgtos.data.settingBody);
    }, methods: {
      save: async function () {
        var cfgtos = this.cfgtos;
        var settings = this.setting;

        console.log("settings is " + JSON.stringify(settings));

        // var cfgtos = await otherApi.saveCfgTo("WORK_WECHAT_CFG_TO");
      }
    }
  }

</script>

<style scoped>
  .content-row-modal .modal-dialog {
    width: 1200px;
    margin: 20px;
  }
</style>
