<template>
  <div id="app">
    <vueHeader></vueHeader>
    <!--header-->
    <div class="container-fluid">
      <!--documents-->
      <div class="row row-offcanvas row-offcanvas-left">
        <vueLeft></vueLeft>
        // Top
        <router-view/>
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
