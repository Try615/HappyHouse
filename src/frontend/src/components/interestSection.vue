<template>
  <div class="md-layout">
    <div class="md-layout-item md-size-50 m-auto">
      <md-toolbar class="md-brown">
        <div class="md-toolbar-row">
          <div class="md-toolbar-section-start">
            <h3 class="md-title">{{ sido }} {{ gugun }} {{ dong }}</h3>
          </div>
          <div class="md-toolbar-section-end">
            <div class="md-collapse">
              <md-list>
                <md-list-item @click="deleteArea">
                  <i class="material-icons">delete</i>
                </md-list-item>
              </md-list>
            </div>
          </div>
        </div>
      </md-toolbar>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common';
export default {
  data() {
    return {};
  },
  props: {
    dong: { type: String },
    gugun: { type: String },
    sido: { type: String },
  },
  methods: {
    deleteArea() {
      http
        .delete(
          `/interest/${this.sido}/${this.gugun}/${this.dong}/${this.$store.state.loginedId}`,
          {
            headers: {
              'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
            },
          }
        )
        .then(({ data }) => {
          this.$store.dispatch('addProfileInterest', data);
        })
        .catch(() => {
          alert('관심지역 삭제 오류');
        });
    },
  },
};
</script>
