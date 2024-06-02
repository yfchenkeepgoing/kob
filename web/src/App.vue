<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from './components/NavBar.vue'
// 导入Bootstrap的css和js
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
import $ from 'jquery'

export default {
  components: {
    NavBar
  },
  setup() {
    $.ajax({
      url: "http://127.0.0.1:3000/user/account/token/",
      type: "post",
      data: {
        username: 'bb',
        password: "pbb",
      },
      success(resp) {
        console.log(resp);
      }, 
      error(resp) {
        console.log(resp);
      }
    });

    $.ajax({
      url: "http://127.0.0.1:3000/user/account/info/",
      type: "get",
      // 不需要传数据，但需要传表头
      headers: {
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjdkMjRjMmJhNjk0ODgwOGU1NGY2ZWVkZGRkMDFkMiIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTcxNzM0ODEwNSwiZXhwIjoxNzE4NTU3NzA1fQ.C0gNZ7EtZ8o9nZvwdKHq2BKyRSWY6ENy6bPPWYijgP0"
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    })
  }
}
</script>

<style>
body {
  background-image: url("./assets/background.png");
  background-size: cover; 
}
</style>

