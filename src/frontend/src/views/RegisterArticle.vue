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
                  <h2>{{ type }} 등록</h2>
                </div>
                <div>
                  <div class="form-group">
                    <label for="title">제목</label>
                    <input
                      type="text"
                      class="form-control"
                      id="title"
                      ref="title"
                      placeholder="제목을 입력하세요"
                      v-model="title"
                    />
                  </div>
                  <div class="form-group">
                    <label for="content">내용</label>
                    <textarea
                      type="text"
                      class="form-control"
                      id="contnet"
                      ref="content"
                      placeholder="내용을 입력하세요"
                      v-model="content"
                    ></textarea>
                  </div>
                  <div class="text-right">
                    <md-button class="md-brown mr-2" @click="checkHandler">등록</md-button>
                    <md-button class="md-brown mr-2" @click="moveList">목록</md-button>
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

export default {
  name: 'updateNotice',
  components: {},
  bodyClass: 'profile-page',
  data: function() {
    return {
      no: '',
      title: '',
      authorId: '',
      content: '',
      date: '',
      type: '',
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = '';
      err && !this.title && ((msg = '제목 입력해주세요'), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = '내용 입력해주세요'), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type == 'qna' ? this.createQnAHandler() : this.createNoticeHandler();
    },
    createNoticeHandler() {
      http
        .post(
          '/notice',
          {
            title: this.title,
            authorId: 'admin',
            content: this.content,
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
    createQnAHandler() {
      http
        .post(
          '/qna',
          {
            title: this.title,
            authorId: this.$store.state.loginedId,
            content: this.content,
            isAnswered: 'f',
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
    moveList() {
      this.type == 'qna' ? this.$router.push('/qna') : this.$router.push('/notice');
    },
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
  created() {
    console.log(this.$route.query.type);
    this.type = this.$route.query.type;
  },
};
</script>
