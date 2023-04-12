<template>
  <show-record :records="records" @onSubmit="loadRecords" :ad="$store.state.user.username === 'admin'"></show-record>
</template>

<script>
import store from '@/store'
import ShowRecord from '@/components/common/ShowRecords'
export default {
  name: 'requestRecord',
  components: {ShowRecord},
  data () {
    return {
      records: []
    }
  },
  mounted: function () {
    this.loadRecords()
  },
  methods: {
    loadRecords () {
      this.$axios.get('/getAllRecord?username=' + store.state.user.username, {
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.records = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
