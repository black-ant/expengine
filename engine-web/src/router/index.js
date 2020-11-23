import Vue from 'vue'
import Router from 'vue-router'

import HelloWorld from '@/components/HelloWorld'
import login from '@/pages/login'
import test from '@/pages/test'
import index from '@/pages/index'
import document from '@/pages/document'
import start from '@/pages/start'

import setting from '@/pages/setting_save'
import settinglist from '@/pages/setting_list'
import filedInfo from '@/pages/filedInfo'

import applist from '@/pages/app_list'
import push from '@/pages/push'

Vue.use(Router)

export default new Router({

  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }, {
      path: '/login',
      name: 'login',
      component: login
    }, {
      path: '/test',
      name: 'test',
      component: test
    }, {
      path: '/index',
      name: 'index',
      component: index
    }, {
      path: '/document',
      name: 'document',
      component: document
    }, {
      path: '/start',
      name: 'start',
      component: start
    }, {
      path: '/setting',
      name: 'setting',
      component: setting
    }, {
      path: '/settinglist',
      name: 'settinglist',
      component: settinglist
    }, {
      path: '/filedInfo',
      name: 'filedInfo',
      component: filedInfo
    }, {
      path: '/applist',
      name: 'applist',
      component: applist
    }, {
      path: '/list',
      name: 'applist',
      component: applist
    }, {
      path: '/push',
      name: 'push',
      component: push
    }
  ]
})
