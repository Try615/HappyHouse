<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="brown">
              <h4 slot="title" class="card-title">Login</h4>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-facebook-square"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-twitter"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-google-plus-g"></i>
              </md-button>
              <md-field class="md-form-group mt-5" slot="inputs">
                <md-icon>face</md-icon>
                <label>ID...</label>
                <md-input v-model.trim="id"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Password...</label>
                <md-input v-model.trim="password" type="password"></md-input>
              </md-field>
              <md-button slot="footer" class="md-simple md-brown md-lg" @click="login">
                로그인
              </md-button>
              <md-button slot="footer" class="md-simple md-brown md-lg" @click="signup">
                회원가입
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from '@/util/http-common';
import { LoginCard } from '@/components';
import jwt_decode from 'jwt-decode';
const storage = window.sessionStorage;

export default {
  components: {
    LoginCard,
  },
  bodyClass: 'login-page',
  data() {
    return {
      id: null,
      password: null,
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/note.jpg'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    login() {
      axios
        .post(
          '/member/login',
          {
            userid: this.id,
            userpwd: this.password,
          },
          {
            headers: {
              'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
            },
          }
        )
        .then((response) => {
          console.log(response);
          if (response.status == 200) {
            var token = response.headers['jwt-auth-token'];
            storage.setItem('jwt-auth-token', token);
            var decoded = jwt_decode(token);
            var id = decoded.Member.userid;
            this.$store.state.loginedId = id;
            this.$router.push('/');
          } else if (response.status == 204) {
            alert('아이디 혹은 비밀번호를 확인해주십시요.');
          } else {
            alert('로그인에 실패하셨습니다. 관리자에게 문의해주십시요.');
          }
        })
        .catch(() => {
          alert('로그인시 에러 발생');
        });
    },
    init() {
      if (storage.getItem('jwt-auth-token')) {
      } else {
        storage.setItem('jwt-auth-token', '');
      }
    },
    signup(){
      this.$router.push("signup");
    }
  },
  mounted() {
    this.init();
  },
};
</script>

<style lang="css"></style>
