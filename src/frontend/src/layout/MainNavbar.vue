<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <router-link to="/"><h3 class="md-title">OndolHouse</h3></router-link>
      </div>
      <div class="md-toolbar-section-end">
        <md-button
          class="md-just-icon md-simple md-toolbar-toggle"
          :class="{ toggled: toggledClass }"
          @click="toggleNavbarMobile()"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <div class="md-collapse-wrapper">
            <md-list>
              <div v-if="this.$store.state.loginedId" class="md-list-item" style="display: flex;">
                <li>
                  <a
                    href="javascript:void(0)"
                    class="md-list-item-router md-list-item-container md-button-clean dropdown"
                  >
                    <div class="md-list-item-content" style="margin-right:20px">
                      <router-link to="select">
                        <i class="material-icons">layers</i>
                        <p>검색</p>
                      </router-link>
                    </div>
                  </a>              
                </li>
                <li>
                  <a
                    href="javascript:void(0)"
                    class="md-list-item-router md-list-item-container md-button-clean dropdown"
                  >
                    <div class="md-list-item-content">
                      <drop-down direction="down">
                        <md-button
                          slot="title"
                          class="md-button md-button-link md-white md-simple dropdown-toggle"
                          data-toggle="dropdown"
                        >
                          <i class="material-icons">apps</i>
                          <p>게시판</p>
                        </md-button>
                        <ul class="dropdown-menu dropdown-with-icons">
                          <li>
                            <router-link to="notice">
                              <i class="material-icons">layers</i>
                              <p>공지사항</p>
                            </router-link>
                          </li>
                          <li>
                            <router-link to="qna">
                              <i class="material-icons">content_paste</i>
                              <p>QnA</p>
                            </router-link>
                          </li>
                        </ul>
                      </drop-down>
                    </div>
                  </a>
                </li>
                <li>
                  <a
                    href="javascript:void(0)"
                    class="md-list-item-router md-list-item-container md-button-clean dropdown"
                  >
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        <i class="material-icons">view_carousel</i>
                        <p>마이페이지</p>
                      </md-button>
                      <ul class="dropdown-menu dropdown-with-icons">
                        <li @click="logout" class="logout-li">
                          <div class="text-center" style="display: flex;">
                            <i class="material-icons">fingerprint</i>
                            <p>로그아웃</p>
                          </div>
                        </li>
                        <li>
                          <router-link to="profile">
                            <i class="material-icons">account_circle</i>
                            <p>회원정보</p>
                          </router-link>
                        </li>
                      </ul>
                    </drop-down>
                  </a>
                </li>
              </div>
              <div v-else class="md-list-item" style="display: flex;">
                <li>
                  <a
                    href="javascript:void(0)"
                    class="md-list-item-router md-list-item-container md-button-clean dropdown"
                  >
                    <div class="md-list-item-content" style="margin-right:20px">
                      <router-link to="select">
                        <i class="material-icons">layers</i>
                        <p>검색</p>
                      </router-link>
                    </div>
                  </a>
                </li>
                <li>
                  <a
                    href="javascript:void(0)"
                    class="md-list-item-router md-list-item-container md-button-clean dropdown"
                  >
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        <i class="material-icons">apps</i>
                        <p>게시판</p>
                      </md-button>
                      <ul class="dropdown-menu dropdown-with-icons">
                        <li>
                          <router-link to="notice">
                            <i class="material-icons">layers</i>
                            <p>공지사항</p>
                          </router-link>
                        </li>
                        <li>
                          <router-link to="qna">
                            <i class="material-icons">content_paste</i>
                            <p>QnA</p>
                          </router-link>
                        </li>
                      </ul>
                    </drop-down>
                  </a>
                </li>
                <li class="text-center">
                  <a
                    href="javascript:void(0)"
                    class="md-list-item-router md-list-item-container md-button-clean dropdown"
                  >
                    <div class="md-list-item-content" style="margin-right:20px">
                      <router-link to="login">
                        <i class="material-icons">fingerprint</i>
                        <p>로그인</p>
                      </router-link>
                    </div>
                  </a>
                </li>
              </div>
            </md-list>
          </div>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();
    }, 66);
  }
}

export default {
  components: {

  },
  props: {
    type: {
      type: String,
      default: 'white',
      validator(value) {
        return ['white', 'default', 'primary', 'danger', 'success', 'warning', 'info'].includes(
          value
        );
      },
    },
    colorOnScroll: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      extraNavClasses: '',
      toggledClass: false,
    };
  },
  methods: {
    bodyClick() {
      let bodyClick = document.getElementById('bodyClick');

      if (bodyClick === null) {
        let body = document.querySelector('body');
        let elem = document.createElement('div');
        elem.setAttribute('id', 'bodyClick');
        body.appendChild(elem);

        let bodyClick = document.getElementById('bodyClick');
        bodyClick.addEventListener('click', this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue = document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById('toolbar');
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove('md-transparent');
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = '';
          navbarColor.classList.add('md-transparent');
        }
      }
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById('downloadSection');
      if (element_id) {
        element_id.scrollIntoView({ block: 'end', behavior: 'smooth' });
      }
    },
    logout() {
      sessionStorage.removeItem('jwt-auth-token');
      this.$store.state.loginedId = null;
      this.$router.push('/');
    },
  },
  mounted() {
    document.addEventListener('scroll', this.scrollListener);
    console.log(this.$store.state.loginedId);
  },
  beforeDestroy() {
    document.removeEventListener('scroll', this.scrollListener);
  },
};
</script>

<style>
.logout-li {
  padding: 12px 20px 12px 12px;
  margin: 0px 5px;
}
.logout-li:hover {
  background-color: #4caf50;
  color: white;
}
</style>
