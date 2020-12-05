import Vue from 'vue'
import Router from 'vue-router'

import HelloWorld from '@/components/HelloWorld'
import login from '@/pages/login'
import test from '@/pages/test'
import index from '@/pages/index'

import setting from '@/pages/setting_save'
import settinglist from '@/pages/setting_list'
import setting_save from '@/pages/setting_save'

import business_list from '@/pages/business_list'
import business_save from '@/pages/business_save'

import filedList from '@/pages/filed_list'
import filedConnect from '@/pages/filed_connect'

import applist from '@/pages/app_list'
import appsave from '@/pages/app_save'

import business_rate from '@/pages/business_rate'
import business_details from '@/pages/business_details'

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
      path: '/setting',
      name: 'setting',
      component: setting
    }, {
      path: '/settinglist',
      name: 'settinglist',
      component: settinglist
    }, {
      path: '/setting_save',
      name: 'setting_save',
      component: setting_save
    }, {
      path: '/business_list',
      name: 'business_list',
      component: business_list
    }, {
      path: '/business_save',
      name: 'business_save',
      component: business_save
    }, {
      path: '/business_rate',
      name: 'business_rate',
      component: business_rate
    }, {
      path: '/business_details',
      name: 'business_details',
      component: business_details
    }, {
      path: '/filedList',
      name: 'filedList',
      component: filedList
    }, {
      path: '/filedConnect',
      name: 'filedConnect',
      component: filedConnect
    }, {
      path: '/applist',
      name: 'applist',
      component: applist
    }, {
      path: '/appsave',
      name: 'appsave',
      component: appsave
    }
  ]
})
