// =========================================================
// * Vue Material Kit - v1.2.2
// =========================================================
//
// * Product Page: https://www.creative-tim.com/product/vue-material-kit
// * Copyright 2019 Creative Tim (https://www.creative-tim.com)
// * Licensed under MIT (https://github.com/creativetimofficial/vue-material-kit/blob/master/LICENSE.md)
//
// * Coded by Creative Tim
//
// =========================================================
//
// * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

import Vue from 'vue';
import App from './App.vue';
import router from './router';

import MaterialKit from './plugins/material-kit';
import store from './store';

import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

import Vuesax from 'vuesax';

import 'vuesax/dist/vuesax.css'; //Vuesax styles
import './vuesax.css';
import axios from 'axios';
import http from './util/http-common';
import jwt_decode from 'jwt-decode';
import Notifications from 'vue-notification';

Vue.use(Vuesax);
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.config.productionTip = false;
Vue.use(MaterialKit);
Vue.use(Notifications);

const NavbarStore = {
  showNavbar: false,
};

Vue.mixin({
  data() {
    return {
      NavbarStore,
    };
  },
});

new Vue({
  router,
  store,
  render: (h) => h(App),
  beforeCreate() {
    if (sessionStorage.getItem('jwt-auth-token')) {
      if (
        sessionStorage.getItem('jwt-auth-token') != '' &&
        sessionStorage.getItem('jwt-auth-token') != null
      ) {
        http
          .get('/token', {
            headers: {
              'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
            },
          })
          .then((data) => {
            var token = data.headers['jwt-auth-token'];
            sessionStorage.setItem('jwt-auth-token', token);
            var decoded = jwt_decode(token);
            var id = decoded.Member.userid;
            this.$store.state.loginedId = id;
          })
          .catch((data) => {
            console.log(data);
            alert('다시 로그인해주세요.');
            sessionStorage.removeItem('jwt-auth-token');
            this.$store.state.loginedId = null;
            this.$router.push('login');
          });
      }
    } else {
      sessionStorage.setItem('jwt-auth-token', '');
    }
  },
  mounted() {
    this.$vs.setColor('brown', '#68453b');
  },
}).$mount('#app');
