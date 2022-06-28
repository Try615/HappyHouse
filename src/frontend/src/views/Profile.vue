<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main member-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto">
              <div class="profile">
                <div class="avatar">
                  <img :src="img" alt="Circle Image" class="img-raised rounded-circle img-fluid" />
                </div>
                <div class="name">
                  <h3 class="title">{{ item.username }}</h3>
                  <h6>일반회원</h6>
                  <md-button href="javascript:void(0)" class="md-just-icon md-simple md-dribbble"
                    ><i class="fab fa-dribbble"></i
                  ></md-button>
                  <md-button href="javascript:void(0)" class="md-just-icon md-simple md-twitter"
                    ><i class="fab fa-twitter"></i
                  ></md-button>
                  <md-button href="javascript:void(0)" class="md-just-icon md-simple md-pinterest"
                    ><i class="fab fa-pinterest"></i
                  ></md-button>
                </div>
              </div>
            </div>
          </div>
          <div class="description text-center">
            <p>
              {{ item.usertell }}
            </p>
          </div>
          <div class="profile-tabs">
            <tabs
              :tab-name="['관심지역', '회원정보 수정']"
              :tab-icon="['favorite', 'palette']"
              plain
              nav-pills-icons
              color-button="brown"
            >
              <!-- here you can add your content for tab-content -->
              <template slot="tab-pane-1">
                <interest-section
                  v-for="(item, index) in interestItem"
                  :key="index"
                  :dong="item.dong"
                  :gugun="item.gugun"
                  :sido="item.sido"
                  class="mb-1"
                ></interest-section>
              </template>
              <template slot="tab-pane-2">
                <div class="md-layout">
                  <div class="md-layout-item md-size-25 m-auto">
                    <md-field class="md-form-group">
                      <md-icon>face</md-icon>
                      <div class="readonlyItem">{{ item.userid }}</div>
                    </md-field>
                    <md-field class="md-form-group">
                      <i class="fas fa-address-card fa-lg"></i>
                      <md-input v-model="item.username"></md-input>
                    </md-field>
                    <md-field class="md-form-group">
                      <i class="fas fa-key fa-lg"></i>
                      <md-input v-model="password" type="password" placeholder="현재 비밀번호 입력"></md-input>
                    </md-field>
                    <md-field class="md-form-group">
                      <i class="fas fa-key fa-lg"></i>
                      <md-input v-model="newpassword" type="password" placeholder="새 비밀번호 입력"></md-input>
                    </md-field>
                    <md-field class="md-form-group">
                      <md-icon>phone</md-icon>
                      <md-input v-model="item.usertell"></md-input>
                    </md-field>
                    <div class="text-center mt-3">
                      <md-button class="md-brown md-round mr-2" @click="update">수정</md-button>
                      <md-button class="md-brown md-round" @click="deleteMem">탈퇴</md-button>
                    </div>
                  </div>
                </div>
              </template>
            </tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Tabs } from '@/components';
import { mapGetters } from 'vuex';
import http from '@/util/http-common';
import InterestSection from '@/components/interestSection.vue';

export default {
  components: {
    Tabs,
    InterestSection,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      item: [],
      interestItem: [],
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/note.jpg'),
    },
    img: {
      type: String,
      default: require('@/assets/img/faces/christian.jpg'),
    },
  },
  computed: {
    ...mapGetters(['getProfileInterest']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  watch: {
    getProfileInterest: function() {
      this.interestItem = this.getProfileInterest;
    },
  },
  async created() {
    console.log('profile: ' + this.$store.state.loginedId);

    await http
      .get(`/member/profile/${this.$store.state.loginedId}`, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        },
      })
      .then(({ data }) => {
        this.item = data;
      })
      .catch(() => {
        alert('member : 에러');
        sessionStorage.removeItem('jwt-auth-token');
        this.$store.state.loginedId = null;
        this.$router.push('login');
      });

    await http
      .get(`/interest/${this.$store.state.loginedId}`, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        },
      })
      .then(({ data }) => {
        this.$store.dispatch('addProfileInterest', data);
        console.log(data);
      })
      .catch(() => {
        alert('interest에러');
      });
  },
  methods: {
    update() {
      console.log(this.item.username);
      console.log('pwd : ' + this.item.userpwd);
      console.log('thispwd : ' + this.password);
      if (!this.password) {
        alert('비밀번호를 입력해주세요');
      } else if (this.password != this.item.userpwd) {
        alert('비밀번호가 틀렸습니다.');
      } else if (!this.newpassword) {
        alert('새 비밀번호를 입력해주세요.');
      } else {
        http
          .post(
            `/member/profile`,
            {
              userid: this.item.userid,
              username: this.item.username,
              userpwd: this.newpassword,
              usertell: this.item.usertell,
              joindate: this.item.joindate,
              isAdmin: this.item.isAdmin,
            },
            {
              headers: {
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
              },
            }
          )
          .then((data) => {
            alert('수정 완료');
          })
          .catch(() => {
            alert('회원 수정 에러');
          });
      }
    },
    deleteMem() {
      if (!this.password) {
        alert('비밀번호를 입력해주세요');
      } else if (this.password != this.item.userpwd) {
        alert('비밀번호가 틀렸습니다.');
      } else {
        if (confirm('탈퇴하시겠습니까?')) {
          http
            .delete(`member/delete/${this.item.userid}`, {
              headers: {
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
              },
            })
            .then(() => {
              alert('회원탈퇴 성공');
              this.$router.push('/');
              this.$store.state.loginedId = null;
              sessionStorage.removeItem('jwt-auth-token');
            })
            .catch(() => {
              alert('회원탈퇴 에러');
            });
        }
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*='tab-pane-'] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }

  .readonlyItem {
    margin: 5px 0px 0px 10px;
  }
}
</style>
