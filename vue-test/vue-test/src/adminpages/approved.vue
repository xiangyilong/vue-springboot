<template>
<show-record :ad="$store.state.user.username === 'admin'" :records="records"></show-record>
</template>

<script>
import ShowRecord from '@/components/common/ShowRecords'
export default {
  name: 'approved',
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
      this.$axios.get('/getRecordsApproved').then(resp => {
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
