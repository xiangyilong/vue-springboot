<template>
<show-record :records="records" :ad="$store.state.user.username === 'admin'" @onSubmit="loadRecords"></show-record>
</template>

<script>
import ShowRecord from '@/components/common/ShowRecords'
export default {
  name: 'approving',
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
      this.$axios.get('/getRecordsApproving').then(resp => {
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
