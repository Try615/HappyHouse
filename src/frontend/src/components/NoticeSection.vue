<template>
  <div class="wrapper">
    <div id="javascriptComponents">
      <div class="title">
        <h2>공지사항</h2>
      </div>
      <div class="md-layout">
        <div class="md-layout-item md-size-66 mx-auto md-small-size-100">
          <div>
            <div v-if="items.length">
              <table class="table table-bordered table-condensed">
                <colgroup>
                  <col :style="{ width: '5%' }" />
                  <col :style="{ width: '50%' }" />
                  <col :style="{ width: '10%' }" />
                  <col :style="{ width: '25%' }" />
                </colgroup>
                <tr class="text-center">
                  <th>번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>날짜</th>
                </tr>
                <list-row
                  v-for="(item, index) in items"
                  :key="`${index}_items`"
                  :no="item.no"
                  :title="item.title"
                  :authorId="item.authorId"
                  :date="item.date"
                  type="notice"
                />
              </table>
            </div>
            <div v-else>글이 없습니다.</div>
            <div v-if="this.$store.state.loginedId == 'admin'" class="text-right">
              <md-button class="md-brown" @click="registerNotice">등록</md-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common';
import ListRow from './Row.vue';

const storage = window.sessionStorage;

export default {
  name: 'list',
  components: {
    ListRow,
  },
  data: function() {
    return {
      items: [],
    };
  },
  created() {
    http
      .get('/notice/list', {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        },
      })
      .then((data) => {
        console.log(data);
        if (data.status == 200) {
          this.items = data.data;
        } else if (data.status == 204) {
          alert("공지사항이 존재하지 않습니다.");
        }
      })
      .catch((data) => {
        alert(data);
        alert('notice : 에러가 발생했습니다.');
      });
  },
  methods: {
    registerNotice() {
      this.$router.push('/registerArticle?type=공지사항');
    },
  },
};
</script>
