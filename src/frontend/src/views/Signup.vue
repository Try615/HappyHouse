<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
          <notifications group="notifyApp" position="bottom center" />
            <login-card header-color="brown">
              <h4 slot="title" class="card-title">회원가입</h4>
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
                <label>아이디</label>
                <md-input v-model.trim="id"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>비밀번호</label>
                <md-input v-model.trim="password" type="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>비밀번호 확인</label>
                <md-input v-model.trim="passwordCheck" type="password"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>label</md-icon>
                <label>이름</label>
                <md-input v-model.trim="name"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>phone</md-icon>
                <label>전화번호</label>
                <md-input v-model.trim="phone"></md-input>
              </md-field>
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
  methods:{
    signup(){
      if(this.id == null){
        this.$notify({
          group: 'notifyApp',
          duration: 3000,
          title: '아이디를 입력해주세요.',
          width: 500,
          position: 'bottom left',
        });
      }else if(this.password == null){
        this.$notify({
          group: 'notifyApp',
          duration: 3000,
          title: '비밀번호를 입력해주세요.',
          width: 500,
          position: 'bottom left',
        });
      }else if(this.passwordCheck == null){
        this.$notify({
          group: 'notifyApp',
          duration: 3000,
          title: '비밀번호 확인을 입력해주세요.',
          width: 500,
          position: 'bottom left',
        });
      }else if(this.name == null){
        this.$notify({
          group: 'notifyApp',
          duration: 3000,
          title: '이름을 입력해주세요.',
          width: 500,
          position: 'bottom left',
        });
      }else if(this.phone == null){
        this.$notify({
          group: 'notifyApp',
          duration: 3000,
          title: '전화번호를 입력해주세요.',
          width: 500,
          position: 'bottom left',
        });
      }else if(this.password != this.passwordCheck){
        this.$notify({
          group: 'notifyApp',
          duration: 3000,
          title: '비밀번호가 일치하지 않습니다.',
          width: 500,
          position: 'bottom left',
        });
      }else{
        axios.post(`member/signup`,{
          userid: this.id,
          username: this.name,
          userpwd: this.password,
        }).then(() => {
          alert("회원가입 완료");
          this.$router.push('/');
        }).catch((data) => {
          alert(data);
        })
      }
    }
  }
};
</script>

<style lang="css"></style>
