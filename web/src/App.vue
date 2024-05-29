<template>
  <div>
    <div>Bot昵称: {{ bot_name }}</div>
    <div>Bot战力: {{ bot_rating }}</div>
  </div>
  <router-view/>
</template>

<script>
import $ from 'jquery';
import { ref } from 'vue'; // 定义两个变量需要引入的包

export default {
  name: "App", // 名字随便起

  // setup是整个函数的入口
  setup: () => {
    let bot_name = ref("");
    let bot_rating = ref("");

    // 用ajax取出后端的值，放入bot_name和bot_rating中，然后再放入页面里
    $.ajax({
      url: "http://127.0.0.1:3000/pk/getbotinfo/",
      type: "get", // 两种请求：get: 获取数据; post: 创建数据
      success: resp => {
        // console.log(resp); // 调试用
        bot_name.value = resp.name;
        bot_rating.value = resp.rating;
      }
    });

    return {
      bot_name,
      bot_rating
    }
  }
}
</script>

<style>
body {
  background-image: url("./assets/background.png");
  background-size: cover; 
}
</style>

