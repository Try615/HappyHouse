<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main member-raised">
      <div class="section section-navbars">
        <div class="container">
          <qn-a-section></qn-a-section>
        </div>
        <navigation></navigation>
      </div>
      <br><br>
    </div>
  </div>
</template>

<script>
import QnASection from '../components/QnASection.vue';
export default {
  components: {
    QnASection
  },
  bodyClass: 'profile-page',
  data() {
    return {
      
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
};
</script>