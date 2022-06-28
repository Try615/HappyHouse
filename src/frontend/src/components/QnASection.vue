<template>
  <div class="wrapper">
    <div id="javascriptComponents">
      <div class="title">
        <h2>QnA</h2>
      </div>
      <div class="md-layout">
        <div class="md-layout-item md-size-66 mx-auto md-small-size-100">
          <div>
            <div v-if="items.length">
              <table class="table table-bordered table-condensed">
                <colgroup>
                  <col :style="{ width: '8%' }" />
                  <col :style="{ width: '50%' }" />
                  <col :style="{ width: '12%' }" />
                  <col :style="{ width: '15%' }" />
                  <col :style="{ width: '15%' }" />
                </colgroup>
                <tr class="text-center">
                  <th>번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>날짜</th>
                  <th>답변여부</th>
                </tr>
                <list-row
                  v-for="(item, index) in items"
                  :key="`${index}_items`"
                  :no="item.no"
                  :title="item.title"
                  :authorId="item.authorId"
                  :date="item.date"
                  :isAnswered="item.isAnswered"
                  :answer="item.answer"
                  type="qna"
                />
              </table>
            </div>
            <div v-else>글이 없습니다.</div>
            <div v-if="this.$store.state.loginedId" class="text-right">
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
export default {
  name: 'qnalist',
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
      .get('/qna/list', {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
        },
      })
      .then((data) => {
        console.log(data);
        if (data.status == 200) {
          this.items = data.data;
        } else if (data.status == 204) {
          alert("QnA가 존재하지 않습니다.");
        }
      })
      .catch((data) => {
        alert(data);
        alert('qna : 에러가 발생했습니다.');
      });
  },
  methods: {
    registerNotice() {
      this.$router.push('/registerArticle?type=qna');
    },
  },
};
</script>
