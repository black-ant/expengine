<template>
  <div class="content-row">
    <div class="row">
      <div class="col-md-6">
        <div class="selecter  cover closed" tabindex="0"><select name="selecter_basic"
                                                                 class="selecter_3 selecter-element"
                                                                 data-selecter-options="{&quot;cover&quot;:&quot;true&quot;}"
                                                                 tabindex="-1">
          <option value="1">One</option>
          <option value="2">Two</option>
          <option value="3">Three</option>
          <option value="4">Four</option>
          <option value="5">Five</option>
        </select><span class="selecter-selected">One</span>
          <div class="selecter-options" style="display: none;"><span class="selecter-item selected"
                                                                     data-value="1">One</span><span
            class="selecter-item" data-value="2">Two</span><span class="selecter-item"
                                                                 data-value="3">Three</span><span
            class="selecter-item" data-value="4">Four</span><span class="selecter-item" data-value="5">Five</span>
          </div>
        </div>
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
