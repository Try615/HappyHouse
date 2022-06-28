import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginedId: '',
    apartList: [],
    apartListActive: false,
    markerPos: [],
    houseInfo: [],
    officeInfo: [],
    hospitalInfo: [],
    interestItem: [], //디비에서 가져온 관심지역들
    selectedInterest: '', //지도에서 관심지역리스트에서 선택한 관심지역
    selectedArea: '', //지도에서 선택한 시군구
    cctvList: [],
    eschoolList: [],
    bikeList: [],
    parkList: [],
    address: '',
    trafficVisible: false,
    profileInterest: [],
  },
  getters: {
    getApartSetInfo(state) {
      // console.log("getters getApartSetInfo : ", state.apartList);
      if (state.apartList.length != 0) {
        var set = new Set();
        var list = [];
        for (var i = 0; i < state.apartList.length; i++) {
          if (set.has(state.apartList[i].info[0].aptName) != true) {
            list.push(state.apartList[i]);
            // console.log("state.apartList[i] : ", state.apartList[i]);
            set.add(state.apartList[i].info[0].aptName);
          }
        }
        // console.log("getApartSetInfo :", list);
        return list;
      }
    },
    getApartInfo(state) {
      return state.apartList.info;
    },
    getApartList(state) {
      return state.apartList;
    },
    isApartList(state) {
      return state.apartListActive;
    },
    getMarkerPos(state) {
      return state.markerPos;
    },
    getHouseInfo(state) {
      return state.houseInfo;
    },
    getOfficeInfo(state) {
      return state.officeInfo;
    },
    getHospitalInfo(state) {
      return state.hospitalInfo;
    },
    getSelectedInterest(state) {
      //console.log("store : " + state.selectedInterest);
      return state.selectedInterest;
    },
    getSelectedArea(state) {
      //console.log("store selectedArea: " + state.selectedArea);
      return state.selectedArea;
    },
    getInterestItem(state) {
      //console.log('store interestItem: ' + state.interestItem);
      return state.interestItem;
    },
    getCctvList(state) {
      //console.log('store cctvList : ' + state.cctvList);
      return state.cctvList;
    },
    getEschoolList(state) {
      //console.log('store eschoolList : ' + state.eschoolList);
      return state.eschoolList;
    },
    getBikeList(state) {
      //console.log('store bikeList : ' + state.bikeList);
      return state.bikeList;
    },
    getParkList(state) {
      console.log('store parkList : ' + state.parkList);
      return state.parkList;
    },
    getAddress(state) {
      return state.address;
    },
    getTrafficVisible(state) {
      return state.trafficVisible;
    },
    getProfileInterest(state) {
      return state.profileInterest;
    },
  },
  mutations: {
    ADD_APART_LIST(state, apartList) {
      state.apartList = apartList;
      this.state.apartListActive = true;
      // console.log("mutations ADD_APART_LIST : ", state.apartList);
    },
    ADD_HOUSE_INFO(state, houseInfo) {
      state.houseInfo = houseInfo;
      // console.log("mutations ADD_HOUSE_INFO : ", state.houseInfo);
    },
    ADD_OFFICE_INFO(state, officeInfo) {
      state.officeInfo = officeInfo;
      this.state.apartListActive = true;
    },
    ADD_HOSPITAL_INFO(state, hospitalInfo) {
      state.hospitalInfo = hospitalInfo;
      this.state.apartListActive = true;
    },
    ADD_ADDRESS(state, address) {
      state.address = address;
    },
    SET_TRAFFIC_VISIBLE(state) {
      state.trafficVisible = !state.trafficVisible;
      console.log('mutation trafficVisible : ', state.trafficVisible);
    },
    ADD_PROFILE_INTEREST(state, profileInterest) {
      state.profileInterest = profileInterest;
    },
  },
  actions: {
    addApartList({ commit }, apartList) {
      commit('ADD_APART_LIST', apartList);
    },
    addHouseInfo({ commit }, houseInfo) {
      commit('ADD_HOUSE_INFO', houseInfo);
    },
    addOfficeInfo({ commit }, officeInfo) {
      commit('ADD_OFFICE_INFO', officeInfo);
    },
    addHospitalInfo({ commit }, hospitalInfo) {
      commit('ADD_HOSPITAL_INFO', hospitalInfo);
    },
    addAddress({ commit }, address) {
      commit('ADD_ADDRESS', address);
    },
    setTrafficVisible({ commit }) {
      commit('SET_TRAFFIC_VISIBLE');
    },
    addProfileInterest({ commit }, profileInterest) {
      commit('ADD_PROFILE_INTEREST', profileInterest);
    },
  },
  modules: {},
});
