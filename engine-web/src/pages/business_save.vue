<template>

  <div class="col-xs-12 col-sm-9 content">
    <div class="panel panel-default">
      <div class="panel-body">
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-body">

      <div class="content-row common" id="main_bdoy_id">
    <div class="alert alert-success alert-dismissable alert" v-show="showSuccessAlter">
      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
      <strong>保存成功!</strong>
    </div>
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
                  <div class="col-xs-8" v-for="(value, key) in typeto" v-show="key!='id'">
                    <label>{{key}}</label>
                    <input type="text" class="form-control" :name="value" v-bind:placeholder="value"
                           v-model="typeto[key]">
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-success" v-on:click="save()">save</button>
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
    name: "settings",
    data() {
      return {
        typeto: {},
        showSuccessAlter: false
      }
    },
    mounted: async function () {
      var key = this.$route.query.key;
      console.log(key);
      if (key != null) {
        var response = await otherApi.getBusiness(key);
        console.log(this.typeto);
        this.typeto = response['data'];
      }
    },
    methods: {
      save: async function () {
        var typeto = this.typeto;
        console.log("settings is " + JSON.stringify(typeto));
        var response = await otherApi.saveBusiness(typeto);
        this.showSuccessAlter = true;
      }
    }
  }

</script>

<style scoped>
  @import "../assets/css/common.css";

  .content-row-modal .modal-dialog {
    width: 1200px;
    margin: 20px;
  }
</style>
