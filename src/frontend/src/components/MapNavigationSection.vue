<template>
  <div class="wrapper upperMap">
    <div id="menu">
      <div class="md-layout">
        <div class="md-layout-item md-size-100 md-small-size-100">
          <md-toolbar class="md-brown">
            <div class="md-toolbar-row">
              <div class="md-toolbar-section-start">
                <md-list style="width: 1500px;">
                  <!-- 시 도 구 -->
                  <div class="ml-3 mb-2" style="">
                    <form class="form-inline" style="padding-top:6px">
                      <div class="form-group mr-1">
                        <select class="form-control" id="sido" v-model="sido" ref="sido">
                          <option disabled value="0">도/광역시</option>
                          <option v-for="item in sidos" :key="item.sidoCode" :value="item">{{
                            item.sidoName
                          }}</option>
                        </select>
                      </div>
                      <div class="form-group mr-1">
                        <select
                          class="form-control"
                          name="gugun"
                          id="gugun"
                          v-model="gugun"
                          ref="gugun"
                        >
                          <option disabled value="all">시/구/군</option>
                          <option v-for="item in guguns" :key="item.gugunCode" :value="item">{{
                            item.gugunName
                          }}</option>
                        </select>
                      </div>
                      <div class="form-group mr-1">
                        <select
                          class="form-control"
                          name="dong"
                          id="dong"
                          v-model="dong"
                          ref="dong"
                        >
                          <option disabled value="all">동</option>
                          <option v-for="item in dongs" :key="item.code" :value="item">{{
                            item.dong
                          }}</option>
                        </select>
                      </div>
                    </form>
                  </div>
                  <md-button class="md-brown md-sm" @click="searchApart">
                    <b-spinner v-if="searching" label="Spinning"></b-spinner>
                    <b-icon v-if="!searching" icon="search" aria-hidden="true">검색</b-icon>
                  </md-button>

                  <li class="md-list-item">
                    <a
                      href="javascript:void(0)"
                      class="md-list-item-router md-list-item-container md-button-clean dropdown"
                    >
                      <div class="md-list-item-content">
                        <drop-down direction="down">
                          <md-button
                            slot="title"
                            class="md-button md-button-link md-white md-simple"
                            data-toggle="dropdown"
                          >
                            <p>옵션</p>
                          </md-button>
                          <ul class="dropdown-menu" :class="{ 'dropdown-menu-right': responsive }">
                            <li>
                              <b-button @click="showSC" class="dropdown-item">선별 진료소</b-button>
                            </li>
                            <li>
                              <b-button @click="showHospital" class="dropdown-item"
                                >안심 병원</b-button
                              >
                            </li>
                            <li class="dropdown-divider"></li>
                            <li>
                              <b-button @click="showTraffic" class="dropdown-item"
                                >도로 교통량</b-button
                              >
                            </li>
                          </ul>
                        </drop-down>
                      </div>
                    </a>
                  </li>

                  <li class="md-list-item">
                    <a
                      href="javascript:void(0)"
                      class="md-list-item-router md-list-item-container md-button-clean dropdown"
                    >
                      <div class="md-list-item-content">
                        <drop-down direction="down">
                          <md-button
                            slot="title"
                            class="md-button md-button-link md-white md-simple"
                            data-toggle="dropdown"
                          >
                            <p>관심지역</p>
                          </md-button>
                          <ul class="dropdown-menu" :class="{ 'dropdown-menu-right': responsive }">
                            <li
                              v-for="(item, index) in interestItem"
                              :key="index"
                              class="dropdown-item"
                              @click="clickInterest(index)"
                            >
                              {{ item.sido }} {{ item.gugun }} {{ item.dong }}
                            </li>
                          </ul>
                        </drop-down>
                      </div>
                    </a>
                  </li>
                </md-list>
              </div>
              <div class="md-toolbar-section-end">
                <md-button class="md-just-icon md-simple md-white md-toolbar-toggle">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </md-button>

                <div class="md-collapse"></div>
              </div>
            </div>
          </md-toolbar>
        </div>
      </div>
    </div>
    <!-- end menu -->
  </div>
</template>

<script>
import axios from '../util/http-common';
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      searching: false,
      responsive: false,
      sidos: [],
      sido: null,
      guguns: [],
      gugun: null,
      dongs: [],
      dong: null,
      apartlist: [],
      interestItem: [],
    };
  },
  methods: {
    showTraffic() {
      this.$store.dispatch('setTrafficVisible');
    },
    showHospital() {
      if (this.sido == null || this.gugun == null) {
        alert('지역을 선택해주세요. (시도, 구군)');
        return;
      }

      axios
        .get('/corona/getHospital/' + this.sido.sidoName + '&' + this.gugun.gugunName)
        .then((response) => {
          this.$store.dispatch('addHospitalInfo', response.data);
        })
        .catch();
    },
    showSC() {
      if (this.sido == null || this.gugun == null) {
        alert('지역을 선택해주세요. (시도, 구군)');
        return;
      }

      axios
        .get('/corona/getOffice/' + this.sido.sidoName + '&' + this.gugun.gugunName)
        .then((response) => {
          this.$store.dispatch('addOfficeInfo', response.data);
        })
        .catch();
    },
    searchApart() {
      if (this.sido == null || this.gugun == null || this.dong == null) {
        alert('검색 지역을 선택해주세요.');
        return;
      }

      this.searching = true;
      setTimeout(() => {
        const list = this.apartlist;
        // this.apartlist = [];
        this.$store.dispatch('addApartList', list);
        this.searching = false;
      }, 1000);
    },
    onResponsiveInverted() {
      if (window.innerWidth < 500) {
        this.responsive = true;
      } else {
        this.responsive = false;
      }
    },
    async getApart(address, data) {
      const addr = address + ' ' + data.jibun;

      const result = await axios
        .get('/apart/deal/' + this.dong.dong + '&' + data.aptName)
        .then((response) => {
          // console.log(addr, response.data);
          this.apartlist.push({
            addr: addr,
            info: response.data,
          });
        })
        .catch(console.log('getApart catch'));
      // console.log("MapNev getApart : ", this.apartlist);
    },
    async clickInterest(idx) {
      var arr =
        this.interestItem[idx].sido +
        ' ' +
        this.interestItem[idx].gugun +
        ' ' +
        this.interestItem[idx].dong;
      console.log(arr);
      this.$store.state.selectedInterest = arr;
      this.$store.dispatch('addAddress', arr);

      this.apartlist = [];
      const response = await axios.get('/apart/apt/' + this.interestItem[idx].dong);
      this.$store.dispatch('addHouseInfo', response.data);
      this.$store.state.selectedArea = arr;

      response.data.forEach(async (element) => {
        const addr = arr + ' ' + element.jibun;
        const result = await axios
          .get('/apart/deal/' + this.interestItem[idx].dong + '&' + element.aptName)
          .then((response) => {
            // console.log(addr, response.data);
            this.apartlist.push({
              addr: addr,
              info: response.data,
            });
          })
          .catch(console.log('getApart catch'));
        setTimeout(() => {}, 1000);
      });

      this.searching = true;
      setTimeout(() => {
        const list = this.apartlist;
        // this.apartlist = [];
        this.$store.dispatch('addApartList', list);
        this.searching = false;
      }, 1500);
    },
  },
  mounted() {
    this.onResponsiveInverted();
    window.addEventListener('resize', this.onResponsiveInverted);

    axios
      .get('/apart/sido')
      .then((response) => {
        this.sidos = response.data;
      })
      .catch();
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.onResponsiveInverted);
  },
  computed: {
    ...mapGetters(['getProfileInterest']),
  },
  created() {
    this.interestItem = this.$store.state.profileInterest;
  },
  watch: {
    sido: function(newSido) {
      axios
        .get('/apart/gugun/' + newSido.sidoCode)
        .then((response) => {
          this.guguns = response.data;
        })
        .catch();
    },
    gugun: function(newGugun) {
      axios
        .get('/apart/dong/' + newGugun.gugunCode)
        .then((response) => {
          this.dongs = response.data;
        })
        .catch();
    },
    dong: async function(newDong) {
      console.log('newDong : ', newDong);
      this.apartlist = [];
      const address = this.sido.sidoName + ' ' + this.gugun.gugunName + ' ' + this.dong.dong;
      const response = await axios.get('/apart/apt/' + newDong.dong);
      this.$store.dispatch('addAddress', address);
      this.$store.dispatch('addHouseInfo', response.data);
      this.$store.state.selectedArea = address;

      response.data.forEach((element) => {
        this.getApart(address, element);
        setTimeout(() => {}, 1000);
      });
    },
    getProfileInterest: function() {
      this.interestItem = this.getProfileInterest;
    },
  },
  updated() {
    console.log('updated');
  },
};
</script>

<style lang="css"></style>
