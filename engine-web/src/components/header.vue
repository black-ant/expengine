<template id="head" v-if="ok">
  <div id="header">
    <nav role="navigation" class="navbar navbar-custom">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button data-target="#bs-content-row-navbar-collapse-5" data-toggle="collapse" class="navbar-toggle"
                  type="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="#" class="navbar-brand">ETL 管理平台</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div id="bs-content-row-navbar-collapse-5" class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active">
              <router-link to="document">快速使用</router-link>
            </li>
            <li class="active">
              <router-link to="document">使用文档</router-link>
            </li>
            <!-- <li class="disabled"><a href="#">Link</a></li> -->
            <li class="dropdown">
              <a data-toggle="dropdown" class="dropdown-toggle" href="#">更多 <b class="caret"></b></a>
              <ul role="menu" class="dropdown-menu">
                <li class="dropdown-header">Setting</li>
                <li><a href="#">Action</a></li>
                <li class="divider"></li>
                <li class="active"><a href="#">Separated link</a></li>
                <li class="divider"></li>
                <li class="disabled"><a href="#">Signout</a></li>
              </ul>
            </li>
          </ul>

        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
  </div>

</template>

<script>
  export default {
    name: 'vueHeader',
  }
</script>
