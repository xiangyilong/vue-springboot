<template>
  <div>
    <el-row v-for="item in records" :key="item.id" :gutter="20">
      <el-col :span="22">
        <el-steps :active="step" simple>
          <el-step title="提交中" icon="el-icon-edit"></el-step>
          <el-step title="审核中" icon="el-icon-upload"></el-step>
          <el-step title="审核结束" icon="el-icon-picture"></el-step>
        </el-steps>
      </el-col>
      <el-col :span="2">
        <el-tag  @click="detail(item.state)">详情</el-tag>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import store from '@/store'

export default {
  name: 'requestProgress',
  data () {
    return {
      records: [],
      step: 1
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
    },
    detail (state) {
      if (state === '提交中') {
        this.$message('申请提交中')
      } else if (state === '审核中') {
        this.$message('申请正在审核')
      } else if (state === '通过') {
        this.$message({
          message: '申请通过',
          type: 'success'
        })
      } else if (state === '未通过') {
        this.$message({
          message: '申请未通过',
          type: 'warning'
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
