<template>
  <div id="map" ref="parentSidebar">
    <md-button
      v-if="isApartList"
      style="z-index:99; position: relative; top: -30vh; right: -42vh"
      class="md-just-icon md-round"
      color-button="success"
      @click="addInterest"
      ><md-icon>favorite</md-icon></md-button
    >
    <notifications group="notifyApp" position="bottom center" />
    <div ref="parentSidebar" id="parentx">
      <!-- <vs-button @click="active=!active" color="primary" type="filled" style="z-index:999">Open Sidebar</vs-button> -->
      <vs-sidebar
        id="apartlist"
        v-model="isApartList"
        :parent="$refs.parentSidebar"
        default-index="1"
        class="sidebarx"
        position-right
        hidden-background="false"
      >
        <div v-if="activeMode == 0">
          <br />
          <div class="mt-3 ml-3 mr-3 border-top border-bottom">
            <a class="apartname" style="font-weight:bold; font-size:25px; color:#8d6e63">
              검색되는 정보가 존재하지 않습니다.
            </a>
          </div>
        </div>
        <div v-if="activeMode == 1">
          <div class="ml-3 mr-3" style="font-weight:bold; font-size:20px; text-align: center">
            {{ getAddress }} 지역 아파트
          </div>
          <div v-for="(item, idx) in getApartSetInfo" :key="idx" @click="apartDetail(item)">
            <div class="mt-3 ml-3 mr-3 border-top border-bottom">
              <a class="apartname" style="font-weight:bold; font-size:25px; color:#8d6e63">{{
                item.info[0].aptName
              }}</a>
              <ul style="list-style: none; padding: 0">
                <br />
                <li>
                  <label>지번 주소&nbsp;:&nbsp;&nbsp;</label>
                  <label id="apartarea">&nbsp;&nbsp;{{ item.addr }}</label>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div v-if="activeMode == 2">
          <div class="ml-3 mr-3" style="font-weight:bold; font-size:20px; text-align: center">
            아파트 정보
          </div>
          <div class="mt-3 ml-3 mr-3 border-top border-bottom">
            <a
              class="apartname"
              style="font-weight:bold; font-size:25px; color:#8d6e63"
              v-bind="selectedItem"
              >{{ selectedItem.info[0].aptName }}</a
            ><br />
            <md-button @click="apartDetail(item)" class="md-brown md-sm" style="font-size:16px"
              ><b-icon-backspace>&nbsp;&nbsp;</b-icon-backspace>이전</md-button
            >
            <ul style="list-style: none; padding: 0; font-weight:bold">
              <br />
              <li>
                <label>지번 주소&nbsp;:&nbsp;&nbsp;</label>
                <label id="apartarea">&nbsp;&nbsp;{{ selectedItem.addr }}</label>
              </li>
            </ul>
            <br />
            <table class="table table-striped">
              <tr>
                <td>면적</td>
                <td>층</td>
                <td>건축년도</td>
                <td>거래 일자</td>
                <td>거래 가격</td>
              </tr>
              <tr v-for="(item, idx) in selectedItem.info" :key="idx">
                <td>{{ item.area }} ㎡</td>
                <td>{{ item.floor }} 층</td>
                <td>{{ item.buildYear }} 년도</td>
                <td>{{ item.dealYear }} - {{ item.dealMonth }} - {{ item.dealDay }}</td>
                <td>{{ item.dealAmount }} 만원</td>
              </tr>
            </table>

            <div></div>
          </div>
        </div>
        <div v-if="activeMode == 3">
          <div class="ml-3 mr-3" style="font-weight:bold; font-size:20px; text-align: center">
            선별진료소 정보
          </div>
          <div class="mt-3 ml-3 mr-3 border-top border-bottom">
            <a
              class="apartname"
              style="font-weight:bold; font-size:25px; color:#8d6e63"
              v-bind="selectedItem"
              >{{ selectedItem.officename }}</a
            ><br />
            <md-button @click="apartDetail(item)" class="md-brown md-sm" style="font-size:16px"
              ><b-icon-backspace>&nbsp;&nbsp;</b-icon-backspace>이전</md-button
            >
            <ul style="list-style: none; padding: 0; font-weight:bold">
              <br />
              <li>
                <label>지번 주소&nbsp;:&nbsp;&nbsp;</label>
                <label id="apartarea">&nbsp;&nbsp;{{ selectedItem.address }}</label>
              </li>
            </ul>
            <br />
            <table class="table table-striped">
              <tr>
                <td>연락처</td>
                <td>{{ selectedItem.tel }}</td>
              </tr>
              <tr>
                <td>평일 운영 시간</td>
                <td>{{ selectedItem.daytime }}</td>
              </tr>
              <tr>
                <td>토요일 운영 시간</td>
                <td>{{ selectedItem.sattime }}</td>
              </tr>
              <tr>
                <td>일요일 운영 시간</td>
                <td>{{ selectedItem.suntime }}</td>
              </tr>
            </table>

            <div></div>
          </div>
        </div>
        <div v-if="activeMode == 4">
          <div class="ml-3 mr-3" style="font-weight:bold; font-size:20px; text-align: center">
            안심 병원 정보
          </div>
          <div class="mt-3 ml-3 mr-3 border-top border-bottom">
            <a
              class="apartname"
              style="font-weight:bold; font-size:25px; color:#8d6e63"
              v-bind="selectedItem"
              >{{ selectedItem.hospitalname }}</a
            ><br />
            <md-button @click="apartDetail(item)" class="md-brown md-sm" style="font-size:16px"
              ><b-icon-backspace>&nbsp;&nbsp;</b-icon-backspace>이전</md-button
            >
            <ul style="list-style: none; padding: 0; font-weight:bold">
              <br />
              <li>
                <label>지번 주소&nbsp;:&nbsp;&nbsp;</label>
                <label id="apartarea">&nbsp;&nbsp;{{ selectedItem.address }}</label>
              </li>
            </ul>
            <br />
            <table class="table table-striped">
              <tr>
                <td>연락처</td>
                <td>{{ selectedItem.tell }}</td>
              </tr>
              <tr>
                <td>안심 병원 유형</td>
                <td>{{ selectedItem.type }}</td>
              </tr>
              <tr>
                <td v-if="selectedItem.type === 'A'" colspan="2">
                  코로나 유증상 또는 의심환자와 일반 호흡기 환자 진료 분리.
                </td>
                <td v-if="selectedItem.type === 'B'" colspan="2">
                  코로나 유증상 또는 의심환자와 일반 호흡기 환자 진료 분리.<br />
                  선별진료소. 호흡기 질환자 입원 가능.
                </td>
              </tr>
            </table>

            <div></div>
          </div>
        </div>
        <div v-if="activeMode == 5">
          <div class="ml-3 mr-3" style="font-weight:bold; font-size:20px; text-align: center">
            안심 병원
          </div>
          <div v-for="(item, idx) in getHospitalInfo" :key="idx" @click="apartDetail(item)">
            <div class="mt-3 ml-3 mr-3 border-top border-bottom">
              <a class="apartname" style="font-weight:bold; font-size:25px; color:#8d6e63">{{
                item.hospitalname
              }}</a>
              <ul style="list-style: none; padding: 0">
                <br />
                <li>
                  <label>주소&nbsp;:&nbsp;&nbsp;</label>
                  <label id="apartarea">&nbsp;&nbsp;{{ item.address }}</label>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div v-if="activeMode == 6">
          <div class="ml-3 mr-3" style="font-weight:bold; font-size:20px; text-align: center">
            선별진료소
          </div>
          <div v-for="(item, idx) in getOfficeInfo" :key="idx" @click="apartDetail(item)">
            <div class="mt-3 ml-3 mr-3 border-top border-bottom">
              <a class="apartname" style="font-weight:bold; font-size:25px; color:#8d6e63">{{
                item.officename
              }}</a>
              <ul style="list-style: none; padding: 0">
                <br />
                <li>
                  <label>주소&nbsp;:&nbsp;&nbsp;</label>
                  <label id="apartarea">&nbsp;&nbsp;{{ item.address }}</label>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </vs-sidebar>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import axios from '@/util/http-common';

export default {
  name: 'kakaomap',
  components: {},
  data() {
    return {
      map: null,
      markers: [],
      markerPositions: [],
      markerList: [],
      activeMode: 1,
      selectedItem: null,
      mapBounds: null,
      officeMarkerPositions: [],
      officeMarkers: [],
      hospitalMarkerPositions: [],
      hospitalMarkers: [],
      selectedArea: '',
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a7d7a5595cb01f70e17e37ee84b8f40f&libraries=services';
      document.head.appendChild(script);
    }
  },
  created() {
    this.selectedArea = this.getProfileInterest;
  },
  methods: {
    initMap() {
      const container = document.getElementById('map');
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById('map');
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      console.log('markerpos :', markerPositions);
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      console.log(markerPositions.length);
      var cur = this;
      if (markerPositions.length > 0) {
        this.markers = markerPositions.map((position) => {
          var imageSrc = require('@/assets/img/markers/apart.png'), // 마커이미지의 주소입니다
            imageSize = new kakao.maps.Size(20, 40), // 마커이미지의 크기입니다
            imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

          // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

          var marker = new kakao.maps.Marker({
            map: cur.map,
            position: position.pos,
            image: markerImage,
          });
          kakao.maps.event.addListener(marker, 'click', function() {
            cur.selectedItem = position.item;
            cur.activeMode = 2;

            cur.map.setCenter(position.pos);
            cur.map.setLevel(1);
          });
          return marker;
        });

        const bounds = markerPositions.reduce(
          (bounds, latlng) => bounds.extend(latlng.pos),
          new kakao.maps.LatLngBounds()
        );

        this.mapBounds = bounds;
        this.map.setBounds(bounds);
      }
      // console.log("displayMarker markers : ", this.markers);
    },
    displayOfficeMarker(officeMarkerPositions) {
      console.log('markerpos :', officeMarkerPositions);
      if (this.officeMarkers.length > 0) {
        this.officeMarkers.forEach((marker) => marker.setMap(null));
      }

      console.log(officeMarkerPositions.length);
      var cur = this;
      if (officeMarkerPositions.length > 0) {
        this.officeMarkers = officeMarkerPositions.map((position) => {
          var imageSrc = require('@/assets/img/markers/screeningClinic2.png'), // 마커이미지의 주소입니다
            imageSize = new kakao.maps.Size(60, 60), // 마커이미지의 크기입니다
            imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

          // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

          var marker = new kakao.maps.Marker({
            map: cur.map,
            position: position.pos,
            image: markerImage,
          });
          kakao.maps.event.addListener(marker, 'click', function() {
            cur.selectedItem = position.item;
            cur.activeMode = 3;

            cur.map.setCenter(position.pos);
            cur.map.setLevel(1);
          });
          return marker;
        });

        const bounds = officeMarkerPositions.reduce(
          (bounds, latlng) => bounds.extend(latlng.pos),
          new kakao.maps.LatLngBounds()
        );

        this.mapBounds = bounds;
        this.map.setBounds(bounds);
      }
      // console.log("displayMarker markers : ", this.markers);
    },
    displayHospitalMarker(hospitalMarkerPositions) {
      console.log('markerpos :', hospitalMarkerPositions);
      if (this.hospitalMarkers.length > 0) {
        this.hospitalMarkers.forEach((marker) => marker.setMap(null));
      }

      console.log(hospitalMarkerPositions.length);
      var cur = this;
      if (hospitalMarkerPositions.length > 0) {
        this.hospitalMarkers = hospitalMarkerPositions.map((position) => {
          var imageSrc = require('@/assets/img/markers/hospital2.png'), // 마커이미지의 주소입니다
            imageSize = new kakao.maps.Size(60, 60), // 마커이미지의 크기입니다
            imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

          // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

          var marker = new kakao.maps.Marker({
            map: cur.map,
            position: position.pos,
            image: markerImage,
          });
          kakao.maps.event.addListener(marker, 'click', function() {
            cur.selectedItem = position.item;
            cur.activeMode = 4;

            cur.map.setCenter(position.pos);
            cur.map.setLevel(1);
          });
          return marker;
        });

        const bounds = hospitalMarkerPositions.reduce(
          (bounds, latlng) => bounds.extend(latlng.pos),
          new kakao.maps.LatLngBounds()
        );

        this.mapBounds = bounds;
        this.map.setBounds(bounds);
      }
      // console.log("displayMarker markers : ", this.markers);
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
    apartDetail(item) {
      if (this.activeMode == 1) {
        this.selectedItem = item;
        console.log('selected item : ', this.selectedItem);
        this.activeMode = 2;
        axios
          .get(
            'https://dapi.kakao.com/v2/local/search/address.json?query=' + this.selectedItem.addr,
            {
              headers: { Authorization: 'KakaoAK 1021f683bc23a0ea815a29f3ebec8eaf' },
            }
          )
          .then((response) => {
            var pos = new kakao.maps.LatLng(
              response.data.documents[0].y,
              response.data.documents[0].x
            );

            this.map.setCenter(pos);
            this.map.setLevel(1);
          });
      } else if (this.activeMode == 2) {
        this.map.setBounds(this.mapBounds);
        this.activeMode = 1;
      } else if (this.activeMode == 3) {
        this.map.setBounds(this.mapBounds);
        this.activeMode = 6;
      } else if (this.activeMode == 4) {
        this.map.setBounds(this.mapBounds);
        this.activeMode = 5;
      } else if (this.activeMode == 5) {
        this.selectedItem = item;
        console.log('selected item : ', this.selectedItem);
        this.activeMode = 4;
        axios
          .get(
            'https://dapi.kakao.com/v2/local/search/address.json?query=' +
              this.selectedItem.address,
            {
              headers: { Authorization: 'KakaoAK 1021f683bc23a0ea815a29f3ebec8eaf' },
            }
          )
          .then((response) => {
            var pos = new kakao.maps.LatLng(
              response.data.documents[0].y,
              response.data.documents[0].x
            );

            this.map.setCenter(pos);
            this.map.setLevel(1);
          });
      } else if (this.activeMode == 6) {
        this.selectedItem = item;
        console.log('selected item : ', this.selectedItem);
        this.activeMode = 3;
        axios
          .get(
            'https://dapi.kakao.com/v2/local/search/address.json?query=' +
              this.selectedItem.address,
            {
              headers: { Authorization: 'KakaoAK 1021f683bc23a0ea815a29f3ebec8eaf' },
            }
          )
          .then((response) => {
            var pos = new kakao.maps.LatLng(
              response.data.documents[0].y,
              response.data.documents[0].x
            );

            this.map.setCenter(pos);
            this.map.setLevel(1);
          });
      }
    },
    addInterest() {
      if (
        sessionStorage.getItem('jwt-auth-token') == null ||
        sessionStorage.getItem('jwt-auth-token') == ''
      ) {
        this.$notify({
          group: 'notifyApp',
          //type: 'default',
          duration: 3000,
          title: '관심지역 등록 실패!',
          text: '로그인해주세요.',
          width: 500,
          position: 'bottom left',
        });
      } else {
        var arr = this.selectedArea.split(' ');

        axios
          .post(
            `/interest/add`,
            {
              userid: this.$store.state.loginedId,
              sido: arr[0],
              gugun: arr[1],
              dong: arr[2],
            },
            {
              headers: {
                'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
              },
            }
          )
          .then(({ data }) => {
            this.$notify({
              group: 'notifyApp',
              //type: 'default',
              duration: 3000,
              title: '관심지역 등록 완료!',
              text: '',
              width: 500,
              position: 'bottom left',
            });
            this.$store.dispatch('addProfileInterest', data);
          })
          .catch((data) => {
            //에러코드 500이라면 중복일수 있음
            this.$notify({
              group: 'notifyApp',
              //type: 'default',
              duration: 3000,
              title: '관심지역 등록 실패!',
              text: data,
              width: 500,
              position: 'bottom left',
            });
          });
      }
    },
  },
  computed: {
    ...mapGetters([
      'getApartSetInfo',
      'isApartList',
      'getApartList',
      'getApartInfo',
      'getSelectedInterest',
      'getSelectedArea',
      'getOfficeInfo',
      'getHospitalInfo',
      'getInterestItem',
      'getAddress',
      'getTrafficVisible',
    ]),
  },
  beforeCreate() {
    this.$store.state.apartListActive = false;
  },
  watch: {
    getHospitalInfo: function(newVal) {
      this.hospitalMarkerPositions = [];
      const list = this.getHospitalInfo;

      if (list === 'undefined' || list.length == 0) {
        this.activeMode = 0;
        return;
      }

      console.log('kakaomap updated hospital : ', list);
      list.forEach(async (item) => {
        await axios
          .get('https://dapi.kakao.com/v2/local/search/address.json?query=' + item.address, {
            headers: { Authorization: 'KakaoAK 1021f683bc23a0ea815a29f3ebec8eaf' },
          })
          .then((response) => {
            var pos = new kakao.maps.LatLng(
              response.data.documents[0].y,
              response.data.documents[0].x
            );

            this.hospitalMarkerPositions.push({ pos, item });
          });
      });
      setTimeout(() => {
        // console.log("검증 : ", this.markerPositions);
        console.log('method before item : ', this.hospitalMarkerPositions);
        this.displayHospitalMarker(this.hospitalMarkerPositions);
        this.activeMode = 5;
      }, 1000);
      console.log('after watch : ', this.hospitalMarkerPositions);
    },
    getApartList: function(newVal) {
      this.activeTrafficMode = false;
      this.markerPositions = [];
      const list = this.getApartList;
      console.log('kakaomap updated list : ', list);

      if (list === 'undefined' || list.length == 0) {
        this.activeMode = 0;
        return;
      }

      list.forEach(async (item) => {
        await axios
          .get('https://dapi.kakao.com/v2/local/search/address.json?query=' + item.addr, {
            headers: { Authorization: 'KakaoAK 1021f683bc23a0ea815a29f3ebec8eaf' },
          })
          .then((response) => {
            var pos = new kakao.maps.LatLng(
              response.data.documents[0].y,
              response.data.documents[0].x
            );

            this.markerPositions.push({ pos, item });
          });
      });
      setTimeout(() => {
        // console.log("검증 : ", this.markerPositions);
        console.log('method before item : ', this.markerPositions);
        this.displayMarker(this.markerPositions);
        this.activeMode = 1;
      }, 1000);
      console.log('after watch : ', this.markerPositions);
    },
    getTrafficVisible: function(newVal) {
      if (!newVal) {
        // 아래 코드는 위에서 추가한 교통정보 지도타입을 제거합니다
        this.map.removeOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
      } else {
        // 지도에 교통정보를 표시하도록 지도타입을 추가합니다
        this.map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
      }
    },
    getSelectedInterest: function(arr) {
      var container = this.map;
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(arr, function(result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          container.setCenter(coords);
        } else {
          console.log(status);
        }
      });
    },
    getSelectedArea: function(arr) {
      this.selectedArea = arr;
    },
    getOfficeInfo: function(newVal) {
      this.officeMarkerPositions = [];
      const list = this.getOfficeInfo;
      if (list === 'undefined' || list.length == 0) {
        this.activeMode = 0;
        return;
      }

      console.log('kakaomap updated office : ', list);
      list.forEach(async (item) => {
        await axios
          .get('https://dapi.kakao.com/v2/local/search/address.json?query=' + item.address, {
            headers: { Authorization: 'KakaoAK 1021f683bc23a0ea815a29f3ebec8eaf' },
          })
          .then((response) => {
            var pos = new kakao.maps.LatLng(
              response.data.documents[0].y,
              response.data.documents[0].x
            );

            this.officeMarkerPositions.push({ pos, item });
          });
      });
      setTimeout(() => {
        // console.log("검증 : ", this.markerPositions);
        console.log('method before item : ', this.officeMarkerPositions);
        this.displayOfficeMarker(this.officeMarkerPositions);
        this.activeMode = 6;
      }, 1000);
      console.log('after watch : ', this.officeMarkerPositions);
    },
  },
};
</script>

<style scoped>
#map {
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 70vh;
  border-radius: 10px;
  z-index: 999;
}
.vs-sidebar--background {
  display: none;
}
</style>
