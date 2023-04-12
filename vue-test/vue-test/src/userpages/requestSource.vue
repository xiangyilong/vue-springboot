<template>
  <div class="block">
    <show-resource :ad="$store.state.user.username === 'admin'"></show-resource>
  </div>
</template>

<script>
import SearchBar from '@/components/library/SearchBar'
import store from '@/store'
import ShowResource from '@/components/common/showResource'
export default {
  name: 'requestSource',
  components: {ShowResource, SearchBar},
  data () {
    return {
      form: {
        username: '',
        id: '',
        begin_time: '',
        end_time: '',
        purpose: ''
      },
      resources: [],
      currentPage: 1,
      pagesize: 12,
      pagesizes: [3, 6, 9, 12],
      showform: false
    }
  },
  mounted: function () {
    this.loadResource()
  },
  methods: {
    loadResource () {
      var _this = this
      this.$axios.get('/loadallresource').then(resp => {
        if (resp && resp.status === 200) {
          _this.resources = resp.data
        }
      })
    },
    // handleSizeChange (val) {
    //   console.log(`每页 ${val} 条`);
    // },
    // handleCurrentChange (val) {
    //   console.log(`当前页: ${val}`);
    // },
    handleRequest () {
      this.showform = true
    },
    onSubmit () {
      this.form.username = store.state.user.username
      this.showform = false
      this.$axios.post('/requesthandle', {
        id: '',
        username: this.form.username,
        deviceId: this.form.id,
        request_time: Date.now(),
        begin_time: this.form.begin_time,
        end_time: this.form.end_time,
        state: '提交中',
        purpose: this.form.purpose
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.showform = false
          this.$router.push('/requestProgress')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
