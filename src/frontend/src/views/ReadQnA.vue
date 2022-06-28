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
                  <h2>QnA 상세보기</h2>
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

                <answer-section
                  v-show="item.isAnswered == 't'"
                  :answer="item.answer"
                ></answer-section>

                <div v-if="item.isAnswered == 'f' && this.$store.state.loginedId == 'admin'">
                  <md-field>
                    <label>답변 입력</label>
                    <md-textarea v-model="answerInput"></md-textarea>
                  </md-field>
                  <md-button class="md-brown mr-2" @click="registerAnswer">등록</md-button>
                </div>
                <br /><br />

                <div class="text-center">
                  <router-link to="/qna" style="float: right">
                    <md-button class="md-brown mr-2">목록</md-button>
                  </router-link>
                  <div v-if="this.$store.state.loginedId === item.authorId" style="float: right">
                    <router-link :to="`/updateArticle?no=${item.no}&type=qna`">
                      <md-button class="md-brown mr-2">수정</md-button>
                    </router-link>
                    <md-button class="md-brown mr-2" @click="deleteQnA(item.no)">
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
import AnswerSection from '../components/AnswerSection.vue';

export default {
  name: 'readNotice',
  components: {
    AnswerSection,
  },
  bodyClass: 'profile-page',
  data: function() {
    return {
      item: {},
      answerInput: '',
    };
  },
  created() {
    console.log(this.$store.state.loginedId);
    http
      .get(`/qna/${this.$route.query.no}`, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        },
      })
      .then(({ data }) => {
        this.item = data;
      })
      .catch(() => {
        alert('로그인해주세요.');
          sessionStorage.removeItem("jwt-auth-token");
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
    deleteQnA(no) {
      if (confirm('삭제하시겠습니까?')) {
        http
          .delete(`/qna/${no}`, {
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
            this.$router.push('/qna');
          })
          .catch(() => {
            alert('삭제 실패');
          });
      }
    },
    registerAnswer() {
      console.log(this.answerInput);
      http
        .post(
          '/qna/answer',
          {
            //title: this.title,
            //authorId: this.authorId,
            content: this.answerInput,
            no: this.item.no,
            //isAnswered: 'f',
          },
          {
            headers: {
              'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
            },
          }
        )
        .then(({ data }) => {
          let msg = '등록 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '등록이 완료되었습니다.';
          }
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert('등록 처리시 에러가 발생했습니다.');
        });
    },
    moveList(){
      console.log("ssss");
      this.$router.push("qna");
    }
  },
};
</script>
