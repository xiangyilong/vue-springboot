<template>
<div>
  <show-resource :ad="this.username === 'admin'"></show-resource>
  <button>{{this.username}}</button>
</div>
</template>

<script>
import ShowResource from '@/components/common/showResource'
export default {
  name: 'sourceManagement',
  components: {ShowResource},
  data () {
    return {
      resource: [],
      username: ''
    }
  },
  mounted: function () {
    this.loadResource()
  },
  methods: {
    loadResource () {
      this.username = this.$store.state.user.username
      this.$axios.get('/loadallresource').then(resp => {
        if (resp && resp.status === 200) {
          this.resource = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
