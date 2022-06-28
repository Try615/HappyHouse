<template>
  <tr class="text-center">
    <td>{{ no }}</td>
    <td>
      <router-link v-if="this.type==='notice'" :to="`/readNotice?no=${no}`">{{ title }}</router-link>
      <router-link v-else :to="`/readQnA?no=${no}`">{{ title }}</router-link>
    </td>
    <td>{{ authorId }}</td>
    <td>{{ getFormatDate(date) }}</td>
    <td v-if="this.type === 'qna'">{{tmpAnswer}}</td>
  </tr>
</template>

<script>
import moment from 'moment';
export default {
  name: 'row',
  props: {
    no: { type: Number },
    authorId: { type: String },
    title: { type: String },
    date: { type: String },
    type: { type: String },
    isAnswered: { type: String },
    answer: { type: String },
  },
  computed:{
    tmpAnswer: function(){
      return this.isAnswered === "t" ? "완료" : "미답변";
    }
  },
  methods: {
    getFormatDate(date) {
      return moment(new Date(date)).format('YYYY.MM.DD');
    },
  },
};
</script>
