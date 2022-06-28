<template>
  <div class="wrapper">
    <div id="javascriptComponents">
      <div class="md-layout">
        <div class="md-layout-item md-size-66 mx-auto md-small-size-100 mt-5">
          <h3 style="color: #613203; font-weight: bold">조회수 Top3 지역</h3>
          
          <md-card>
            <carousel
              :per-page="1"
              loop
              :speed="700"
              autoplay
              :autoplay-timeout="5000"
              :mouse-drag="false"
              navigationEnabled
              navigationNextLabel="<i class='material-icons'>keyboard_arrow_right</i>"
              navigationPrevLabel="<i class='material-icons'>keyboard_arrow_left</i>"
            >
              <slide>
                <div class="carousel-caption">
                  <h4>
                    <md-icon>location_on</md-icon>
                    {{ rankitem[0] }}
                  </h4>
                </div>
                <img :src="require('@/assets/img/dong/' + rankitem[0] + '.jpg')" alt="carousel1" />
              </slide>
              <slide>
                <div class="carousel-caption">
                  <h4>
                    <md-icon>location_on</md-icon>
                    {{ rankitem[1] }}
                  </h4>
                </div>
                <img :src="require('@/assets/img/dong/' + rankitem[1] + '.jpg')" alt="carousel2" />
              </slide>
              <slide>
                <div class="carousel-caption">
                  <h4>
                    <md-icon>location_on</md-icon>
                    {{ rankitem[2] }}
                  </h4>
                </div>
                <img :src="require('@/assets/img/dong/' + rankitem[2] + '.jpg')" alt="carousel3" />
              </slide>
            </carousel>
          </md-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '../util/http-common';

export default {
  data() {
    return {
      rankitem: [],
    };
  },
  created() {
    http
      .get(`/apart/dongrank`)
      .then((data) => {
        this.rankitem = data.data;
      })
      .catch((data) => {
        console.log('index error : ' + data);
      });
  },
};
</script>
