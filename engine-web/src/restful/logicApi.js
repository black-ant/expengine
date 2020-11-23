'use strict'

import common from './common.js';

export default {
  getInfo(data) {
    console.info("tshis is in getInfo");
    return common.get("/start/getInfo", null)
  },


}
