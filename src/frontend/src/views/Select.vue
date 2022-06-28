<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main member-raised">
      <div class="section section-navbars">
        <map-navigation-section :interestItem="interestItem"></map-navigation-section>
      </div>
      <div class="mapdiv">
        <kakao-map />
      </div>
      <navigation></navigation>
    </div>
  </div>
</template>

<script>
import KakaoMap from '../components/KakaoMap.vue';
import MapNavigationSection from '../components/MapNavigationSection.vue';
import http from '../util/http-common';
// import NoticeSection from '../components/NoticeSection.vue';
export default {
  components: {
    KakaoMap,
    MapNavigationSection,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      interestItem: [],
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
  async created() {
    console.log(this.$store.state.loginedId);
    if (
      sessionStorage.getItem('jwt-auth-token') == null ||
      sessionStorage.getItem('jwt-auth-token') == ''
    ) {
      console.log('로그인아이디 없음');
    } else {
      await http
        .get(`/interest/${this.$store.state.loginedId}`)
        .then(({ data }) => {
          this.$store.dispatch('addProfileInterest', data);
        })
        .catch(() => {
          alert('interest에러');
        });
      setTimeout(() => {}, 1000);
    }
  },
};
</script>

<style scoped>
/* .mapdiv {
  width: inherit;
  height: inherit;
} */
</style>
