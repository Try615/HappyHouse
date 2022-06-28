<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1 class="title">OndolHouse</h1>
              <h3 class="title">The Warmest Apartment</h3>
            </div>
          </div>
        </div>
      </div>
    </parallax>
    
    <div class="main main-raised">
      <div class="section section-navbars">
        <div class="container">
          <recommend-apart></recommend-apart>
          <hr class="solid">
          <facility></facility>
        </div>
        <navigation></navigation>
      </div>
    </div>
  </div>
</template>

<script>
import RecommendApart from "../components/RecommendApart";
import Facility from "../components/Facility";

export default {
  components: {
    RecommendApart,
    Facility
  },
  name: "index",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/wood2.jpg")
    },
  },
  data() {
    return {
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`
      };
    },
  },
};
</script>
<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}

hr.solid {
  border-top: 3px solid #bbb;
}
</style>
