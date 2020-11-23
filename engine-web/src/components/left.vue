<template>
  <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
    <ul class="list-group panel">
      <li class="list-group-item">
        <router-link to="/push"><i class="glyphicon glyphicon-play-circle"></i>推送</router-link>
      </li>
      <!--      <li class="list-group-item"><input type="text" class="form-control search-query"-->
      <!--                                         placeholder="Search Something"></li>-->
      <!--      <li class="list-group-item"><a href="index.html"><i class="glyphicon glyphicon-home"></i>Dashboard </a></li>-->
      <li class="list-group-item">
        <router-link to="/applist"><i class="glyphicon glyphicon-certificate"></i>应用管理</router-link>
      </li>
      <li class="list-group-item">
        <router-link to="/settinglist"><i class="glyphicon glyphicon-th-list"></i>同步配置
        </router-link>
      </li>
      <li class="list-group-item"><a href="forms.html"><i class="glyphicon glyphicon-list-alt"></i>数据字典</a></li>
      <li class="list-group-item">
        <router-link to="/filedInfo"><i class="glyphicon glyphicon-th-list"></i>关联关系
        </router-link>
      </li>
      <li class="list-group-item"><a href="calendars.html"><i
        class="glyphicon glyphicon-calendar"></i>同步记录</a></li>
      <li class="list-group-item"><a href="alerts.html"><i class="glyphicon glyphicon-bell"></i>问题告警</a></li>
      <!--      <li class="list-group-item"><a href="typography.html"><i class="glyphicon glyphicon-font"></i>Typography</a>-->
      <!--      </li>-->
      <!--      <li class="list-group-item"><a href="footers.html"><i class="glyphicon glyphicon-minus"></i>Footers</a></li>-->
      <li class="list-group-item"><a href="panels.html"><i class="glyphicon glyphicon-list-alt"></i>系统日志</a>
      </li>
      <!--      <li class="list-group-item"><a href="navs.html"><i class="glyphicon glyphicon-th-list"></i>Navs</a></li>-->
      <!--      <li class="list-group-item"><a href="colors.html"><i class="glyphicon glyphicon-tint"></i>Colors</a></li>-->
      <li class="list-group-item"><a href="flex.html"><i class="glyphicon glyphicon-th"></i>人员管理</a></li>
      <li class="list-group-item"><a href="login.html"><i class="glyphicon glyphicon-lock"></i>登录配置</a></li>
      <li>
        <a href="#demo3" class="list-group-item " data-toggle="collapse">Item 3 <span
          class="glyphicon glyphicon-chevron-right"></span></a>
        <div class="collapse" id="demo3">
          <a href="#SubMenu1" class="list-group-item" data-toggle="collapse">Subitem 1 <span
            class="glyphicon glyphicon-chevron-right"></span></a>
          <div class="collapse list-group-submenu" id="SubMenu1">
            <a href="#" class="list-group-item">Subitem 1 a</a>
            <a href="#" class="list-group-item">Subitem 2 b</a>
            <a href="#SubSubMenu1" class="list-group-item" data-toggle="collapse">Subitem 3 c <span
              class="glyphicon glyphicon-chevron-right"></span></a>
            <div class="collapse list-group-submenu list-group-submenu-1" id="SubSubMenu1">
              <a href="#" class="list-group-item">Sub sub item 1</a>
              <a href="#" class="list-group-item">Sub sub item 2</a>
            </div>
            <a href="#" class="list-group-item">Subitem 4 d</a>
          </div>
          <a href="javascript:;" class="list-group-item">Subitem 2</a>
          <a href="javascript:;" class="list-group-item">Subitem 3</a>
        </div>
      </li>
      <li>
        <a href="#demo4" class="list-group-item " data-toggle="collapse">Item 4 <span
          class="glyphicon glyphicon-chevron-right"></span></a>
      <li class="collapse" id="demo4">
        <a href="" class="list-group-item">Subitem 1</a>
        <a href="" class="list-group-item">Subitem 2</a>
        <a href="" class="list-group-item">Subitem 3</a>
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: "left"
  }
</script>

<style scoped>

</style>
