<template>
  <div class="content-row">
    <div class="row">
      <div class="col-md-6">
        <input type="text" placeholder="Text input" class="form-control">
      </div>
      <div class="col-md-6">
        <input type="text" placeholder="Text input" disabled="" class="form-control">
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "filedInfo",
    data() {
      return {
        filed: {}
      }
    },
    mounted: async function () {

      var filedData = await otherApi.listSetting();
      this.filed = settingsBack.data.data;
      console.log("setting is :%o", settingsBack)
    },
  }
</script>

<style scoped>

</style>
