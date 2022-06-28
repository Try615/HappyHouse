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
                  <h2 v-if="this.$route.query.type == 'notice'">공지사항 수정</h2>
                  <h2 v-else>QnA 수정</h2>
                </div>
                <div>
                  <!-- <div class="form-group">
                    <label for="authorId">작성자</label>
                    <input
                      type="text"
                      class="form-control"
                      id="authorId"
                      ref="authorId"
                      placeholder="작성자를 입력하세요"
                      v-model="authorId"
                    />
                  </div> -->
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
                    <md-button class="md-brown mr-2" @click="checkHandler">수정</md-button>
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
      else this.$route.query.type == 'notice' ? this.updateNotice() : this.updateQnA();
    },
    updateNotice() {
      http
        .put(
          `/notice/${this.no}`,
          {
            no: this.no,
            authorId: this.authorId,
            title: this.title,
            content: this.content,
          },
          {
            headers: {
              'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
            },
          }
        )
        .then(({ data }) => {
          let msg = '수정 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '수정이 완료되었습니다.';
          }
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert('수정 처리시 에러가 발생했습니다.');
        });
    },
    updateQnA() {
      http
        .put(
          `/qna/${this.no}`,
          {
            no: this.no,
            authorId: this.authorId,
            title: this.title,
            content: this.content,
          },
          {
            headers: {
              'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
            },
          }
        )
        .then(({ data }) => {
          let msg = '수정 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '수정이 완료되었습니다.';
          }
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert('수정 처리시 에러가 발생했습니다.');
        });
    },
    moveList() {
      this.$route.query.type == 'notice' ? this.$router.push('/notice') : this.$router.push('/qna');
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
    if (this.$route.query.type == 'notice') {
      http
        .get(`/notice/${this.$route.query.no}`, {
          headers: {
            'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          },
        })
        .then(({ data }) => {
          this.no = data.no;
          this.date = data.date;
          this.authorId = data.authorId;
          this.title = data.title;
          this.content = data.content;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    } else {
      http
        .get(`/qna/${this.$route.query.no}`, {
          headers: {
            'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          },
        })
        .then(({ data }) => {
          this.no = data.no;
          this.date = data.date;
          this.authorId = data.authorId;
          this.title = data.title;
          this.content = data.content;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    }
  },
};
</script>
