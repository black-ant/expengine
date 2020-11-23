<template>
  <div>
    <button v-on:click="testAPI()">点击测试</button>
    <div id="test">this is ok</div>
  </div>

</template>

<script>
  import $ from 'jquery'
  import logicApi from '../restful/logicApi.js';

  export default {
    name: "test",
    methods: {
      testAPI: function () {
        console.log("info testAPI");
        var info = logicApi.getInfo("ok");
        console.log("info is :{}", info);
      }
    }, beforeCreate: function () {
      console.log('vue实例被创建前，data和el还未被初始化');
    },
    created: function () {
      console.log('vue实例被创建成功了，data被初始化了，el还没有');
    },
    beforeMount: function () {
      console.log('这个时候el和data都被初始化了');
    },
    mounted: function () {
      console.log('现在html也被渲染完成');
      $("#test").html("ok");
    },
    beforeUpdate: function () {
      console.log('view层的数据变化前，不是data，可以将我的msg注释掉，这个函数就不会被执行');
    },
    updated: function () {
      console.log('更新之后执行');
    },
    beforeDestroy: function () {
      console.log('beforeDestroy');
    },
    destroyed: function () {
      console.log('destroyed');
    }
  }
</script>

<style scoped>
  @import "../assets/css/site.min.css";
</style>
