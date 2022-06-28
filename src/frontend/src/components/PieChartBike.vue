<script>
import { Pie } from 'vue-chartjs';
import { mapGetters } from 'vuex';

export default {
  extends: Pie,
  data: function() {
    return {
      options: {
        responsive: true,
        legend: {
          display: true,
          position: 'bottom',
        },
        title: {
          display: true,
          text: '공공자전거',
          fontSize: 30,
          // position: 'top',
          // align: 'end'
        },
      },
    };
  },
  computed: {
    ...mapGetters(['getBikeList']),
  },
  watch: {
    getBikeList: function(list) {
      this.renderChart(
        {
          labels: [list[0].gu, list[1].gu, list[2].gu, list[3].gu, list[4].gu],
          datasets: [
            {
              backgroundColor: ['#723b03', '#875829', '#9c764f', '#b19374', '#c7b19a'],
              data: [list[0].cnt, list[1].cnt, list[2].cnt, list[3].cnt, list[4].cnt],
            },
          ],
        },
        this.options
      );
    },
  },
};
</script>
