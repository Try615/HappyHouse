<template>
  <div class="wrapper">
    <div id="nav-tabs">
      <div class="md-layout">
        <div class="md-layout-item md-size-100 md-small-size-100">
          <nav-tabs-card no-label>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="left">
                <md-tab id="tab-pages" md-label="답변" md-icon="chat">
                  <p v-if="answer">
                    {{ answer }}
                  </p>
                  <textarea v-if="type">
                  </textarea>
                </md-tab>
              </md-tabs>
            </template>
          </nav-tabs-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { NavTabsCard } from '@/components';

export default {
  components: {
    NavTabsCard,
  },
  props: {
    answer: { type: String },
    type: { type: String },
  },
};
</script>

<style lang="css"></style>
