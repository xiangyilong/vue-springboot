<template>
  <el-menu
    class="categories"
    default-active="this.$router.path"
    @select="handleSelect"
    active-text-color="red"
    router
  >
    <div v-for="item in sideMenuList" :key="item.index">
    <el-menu-item :index="item.pagepath">
      <i class="el-icon-menu"></i>
      <span slot="title">{{item.name}}</span>
    </el-menu-item>
<!--      <button @click="$emit('indexSelect')">{{item.name}}</button>-->
    <!--      <el-menu-item index="1">-->
    <!--        <i class="el-icon-menu"></i>-->
    <!--        <span slot="title">文学</span>-->
    <!--      </el-menu-item>-->
    <!--      <el-menu-item index="2">-->
    <!--        <i class="el-icon-menu"></i>-->
    <!--        <span slot="title">流行</span>-->
    <!--      </el-menu-item>-->
    <!--      <el-menu-item index="3">-->
    <!--        <i class="el-icon-menu"></i>-->
    <!--        <span slot="title">文化</span>-->
    <!--      </el-menu-item>-->
    <!--      <el-menu-item index="4">-->
    <!--        <i class="el-icon-menu"></i>-->
    <!--        <span slot="title">生活</span>-->
    <!--      </el-menu-item>-->
    <!--      <el-menu-item index="5">-->
    <!--        <i class="el-icon-menu"></i>-->
    <!--        <span slot="title">经管</span>-->
    <!--      </el-menu-item>-->
    <!--      <el-menu-item index="6">-->
    <!--        <i class="el-icon-menu"></i>-->
    <!--        <span slot="title">科技</span>-->
    <!--      </el-menu-item>-->
    </div>
  </el-menu>
</template>

<script>
export default {
  name: 'SideMenu',
  props: {
    sideMenuList: []
  },
  emits: ['indexselect'],
  data () {
    return {
      name: ''
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      this.name = this.sideMenuList[key].name
      // alert(this.name)
      this.$emit('indexSelect')
    }
  }
}
</script>

<style scoped>
.categories{
  position: fixed;
  margin-left: 50%;
  left: -810px;
  top: 100px;
  width: 150px;
}
</style>
