<template>
  <el-container>
    <el-aside v-if="privilege">
      <side-menu :side-menu-list="adSideMenu" @indexSelect="changeView"></side-menu>
    </el-aside>
    <el-aside v-else>
      <side-menu :side-menu-list="userSideMenu" @indexSelect="changeView"></side-menu>
    </el-aside>
    <el-container>
      <el-header>
        <my-header username="this.username"></my-header>
<!--        <nav-menu></nav-menu>-->
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import NavMenu from '@/components/common/NavMenu'
import SideMenu from '@/components/library/SideMenu'
import MyHeader from '@/components/common/myHeader'
import store from '@/store'
export default {
  name: 'Home',
  components: {MyHeader, NavMenu, SideMenu},
  data () {
    return {
      privilege: false,
      username: '',
      userSideMenu: [
        {index: '1', pagepath: '/requestSource', name: '申请资源'},
        {index: '2', pagepath: '/requestProgress', name: '申请进度'},
        {index: '3', pagepath: '/requestRecord', name: '申请记录'}
      ],
      adSideMenu: [
        {index: '1', pagepath: '/approving', name: '待审批'},
        {index: '2', pagepath: '/approved', name: '已审批'},
        {index: '3', pagepath: '/sourceManagement', name: '计算机资源管理'}
      ]
    }
  },
  mounted () {
    this.username = store.state.user.username
    if (this.username === 'admin') {
      this.privilege = true
    }
  },
  methods: {
    changeView () {
      var name = this.$refs.sideMenu.name
      alert(this.$refs.sideMenu.name)
      if (name === '申请资源') {
        this.$router.push('/requestSource')
      } else if (name === '申请进度') {
        this.$router.push('/requestProgress')
      } else if (name === '申请记录') {
        this.$router.push('/requestRecord')
      } else if (name === '待审批') {
        this.$router.push('/approving')
      } else if (name === '已审批') {
        this.$router.push('/approved')
      } else if (name === '计算机资源管理') {
        this.$router.push('/sourceManagement')
      }
    }
  }
}
</script>

<style scoped>

</style>
