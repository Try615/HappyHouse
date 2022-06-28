<template>
  <div class="wrapper">
    <div id="javascriptComponents">
      <div class="md-layout mt-5">
        <div class="md-layout-item md-size-66 mx-auto md-small-size-100">
          <h3 style="color: #613203; font-weight: bold">편의시설</h3>
          <div class="border facility">
            <div style="display: flex;" class="mb-5 mt-4">
              <pie-chart-cctv style="width: 300px; height: 300px;" class="mr-4"></pie-chart-cctv>
              <pie-chart-eschool style="width: 300px; height: 300px;"></pie-chart-eschool><br/>
            </div>
            <div style="display: flex;">
              <pie-chart-bike style="width: 300px; height: 300px;" class="mr-4"></pie-chart-bike>
              <pie-chart-park style="width: 300px; height: 300px;"></pie-chart-park>
            </div>
            <!-- <pie-chart style="width: 300px; height: 300px;"></pie-chart> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '../util/http-common';
import PieChartCctv from './PieChartCctv.vue';
import PieChartEschool from './PieChartEschool.vue';
import PieChartBike from './PieChartBike.vue';
import PieChartPark from './PieChartPark.vue';

export default {
  name: 'VueChartJS',
  components: {
    PieChartCctv,
    PieChartEschool,
    PieChartBike,
    PieChartPark
  },
  data() {
    return {
      //cctvList: [],
    };
  },
  created() {
    http
      .get(`facility/cctv`)
      .then((data) => {
        //console.log("facility : ");
        //console.log(data);
        this.$store.state.cctvList = data.data;
      })
      .catch((data) => {
        alert('facility error');
      });

    http
      .get(`facility/eschool`)
      .then((data) => {
        //console.log("facility eschool : ");
        //console.log(data);
        this.$store.state.eschoolList = data.data;
      })
      .catch((data) => {
        alert(data);
      });

    http
    .get(`facility/bike`)
    .then((data) => {
      //console.log("facility bike : ");
      //console.log(data);
      this.$store.state.bikeList = data.data;
    })
    .catch((data) => {
      alert(data);
    });

    http
    .get(`facility/park`)
    .then((data) => {
      console.log("facility park : ");
      console.log(data);
      this.$store.state.parkList = data.data;
    })
    .catch((data) => {
      alert(data);
    });
  },
};
</script>

<style>
.facility {
  width: 100%;
  height: 700px;
  margin-bottom: 100px;
  margin-top: 30px;
  
  padding-left: 50px;
}
</style>
