<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main member-raised">
      <div class="section section-navbars">
        <div class="container">
          <div class="wrapper">
            <div class="md-layout">
              <div class="md-layout-item md-size-66 mx-auto md-small-size-100">
                <div class="title">
                  <h2>공지사항 상세보기</h2>
                </div>
                <table class="table table-bordered0">
                  <tr>
                    <th>번호</th>
                    <td>{{ item.no }}</td>
                  </tr>
                  <tr>
                    <th>글쓴이</th>
                    <td>{{ item.authorId }}</td>
                  </tr>
                  <tr>
                    <th>제목</th>
                    <td>{{ item.title }}</td>
                  </tr>
                  <tr>
                    <th>날짜</th>
                    <td>{{ getFormatDate(item.date) }}</td>
                  </tr>
                  <tr>
                    <td colspan="2">
                      {{ item.content }}
                    </td>
                  </tr>
                </table>

                <br />
                <div class="text-center">
                  <router-link to="/notice" style="float: right">
                    <md-button class="md-brown">목록</md-button>
                  </router-link>
                  <div v-if="this.$store.state.loginedId === item.authorId" style="float: right">
                    <router-link :to="`/updateArticle?no=${item.no}&type=notice`">
                      <md-button class="md-brown mr-2">수정</md-button>
                    </router-link>
                    <md-button class="md-brown mr-2" @click="deleteNotice(item.no)">
                      삭제
                    </md-button>
                  </div>
                </div>
                <br /><br />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common';
import moment from 'moment';

export default {
  name: 'readNotice',
  components: {},
  bodyClass: 'profile-page',
  data: function() {
    return {
      item: {},
    };
  },
  created() {
    http
      .get(`/notice/${this.$route.query.no}`, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        },
      })
      .then(({ data }) => {
        this.item = data;
      })
      .catch(() => {
        alert('로그인해주세요.');
        sessionStorage.removeItem('jwt-auth-token');
        this.$store.state.loginedId = null;
        this.$router.push('login');
      });
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
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD HH:mm:ss');
    },
    deleteNotice(no) {
      if (confirm('삭제하시겠습니까?')) {
        http
          .delete(`/notice/${no}`, {
            headers: {
              'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
            },
          })
          .then(({ data }) => {
            let msg = '삭제 실패';
            if (data === 'success') {
              msg = '삭제가 완료되었습니다.';
            }
            alert(msg);
            this.$router.push('/notice');
          })
          .catch(() => {
            alert('삭제 실패');
          });
      }
    },
  },
};
</script>
