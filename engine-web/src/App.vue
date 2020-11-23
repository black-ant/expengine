<template>
  <div id="app">
    <vueHeader></vueHeader>
    <!--header-->
    <div class="container-fluid">
      <!--documents-->
      <div class="row row-offcanvas row-offcanvas-left">
        <vueLeft></vueLeft>
        <div class="col-xs-12 col-sm-9 content">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title"><a href="javascript:void(0);" class="toggle-sidebar"><span
                class="fa fa-angle-double-left" data-toggle="offcanvas" title="Maximize Panel"></span></a> 工作界面
              </h3>
            </div>
            <div class="panel-body">
              <router-view/>
            </div>
          </div>
        </div>

      </div>
    </div>


  </div>
</template>

<script>
  import vueHeader from "./components/header.vue";
  import vueLeft from "./components/left.vue";

  export default {
    name: 'App',
    components: {
      vueHeader,
      vueLeft
    }
  }
</script>

<style>
  @import "./assets/css/site.min.css";
</style>
