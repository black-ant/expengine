var apiPrefix = "http://127.0.0.1:8088";

import axios from 'axios'
import qs from 'qs'

axios.interceptors.request.use(config => {
  // loading
  return config
}, error => {
  return Promise.reject(error)
})

axios.interceptors.response.use(response => {
  return response
}, error => {
  return Promise.resolve(error.response)
})

function checkStatus(response) {
  // loading
  // 如果http状态码正常，则直接返回数据
  if (response && (response.status === 200 || response.status === 304 || response.status === 400)) {
    if (response.status === 200) {
      return response.data;
    } else {
      return response;
    }

    // 如果不需要除了data之外的数据，可以直接 return response.data
  }
  // 异常状态下，把错误信息返回去
  return {
    status: -404,
    msg: '网络异常'
  }
}

function checkCode(res) {
  // 如果code异常(这里已经包括网络错误，服务器错误，后端抛出的错误)，可以弹出一个错误提示，告诉用户
  if (res.status === -404) {
    alert(res.msg)
  }
  if (res.data && (!res.data.success)) {
    alert(res.data.error_msg)
  }
  return res
}

export default {
  post(url, data) {
    return axios.post(apiPrefix + url, qs.stringify(data), {
      timeout: 10000,
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json; charset=UTF-8'
      },
      withCredentials: true
    }).then(
      (response) => {
        return checkStatus(response)
      }
    ).catch(
      (res) => {
        return checkCode(res)
      }
    )
  },
  get(url, params) {
    return axios.get(apiPrefix + url, params, {
      timeout: 10000,
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json; charset=UTF-8'
      },
      withCredentials: true
    }).then(
      (response) => {
        console.log("response" + response);
        return checkStatus(response)
      }
    ).catch((res) => {
        console.log("response" + res);
        return checkCode(res)
      }
    )
  },
  test() {

  }
}
