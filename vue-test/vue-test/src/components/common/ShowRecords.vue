<template>
  <el-table
    :data="records"
    style="width: 100%">
    <el-table-column
      prop="request_time"
      type="Date"
      label="申请日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="username"
      label="申请人"
      width="180">
    </el-table-column>
    <el-table-column
      prop="device_id"
      label="设备编号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="begin_time"
      label="开始时间"
      type="Date"
      width="180">
    </el-table-column>
    <el-table-column
      prop="end_time"
      label="结束时间"
      width="180">
    </el-table-column>
    <el-table-column
      prop="state"
      label="状态"
      width="180">
    </el-table-column>
    <el-table-column
      prop="purpose"
      label="目的"
      width="300">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button v-if="!ad" @click="requestDetail()" type="text" size="small">查看</el-button>
        <el-button v-if="!ad" @click="cancelRequest(scope.$index)" type="text" size="small">取消</el-button>
        <el-button v-if="ad" @click="passRequest(scope.$index)" type="text" size="small">通过</el-button>
        <el-button v-if="ad" @click="unpassRequest(scope.$index)" type="text" size="small">不通过</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import Qs from 'qs'
export default {
  name: 'ShowRecord',
  props: {
    records: [],
    ad: Boolean
  },
  data () {
    return {
    }
  },
  methods: {
    requestDetail () {
      this.$router.push('/requestProgress')
    },
    cancelRequest (row) {
      var id = this.records[row].id
      this.$confirm('此操作将取消申请, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/cancelRequest', Qs.stringify({id: id})).then(resp => {
          if (resp && resp.status === 200) {
            this.$emit('onSubmit')
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    passRequest (row) {
      var recordId = this.records[row].id
      var deviceId = this.records[row].device_id
      var endTime = this.records[row].end_time
      this.$confirm('此操作将通过申请, 是否继续', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/passRequest', Qs.stringify({recordId: recordId, deviceId: deviceId, end_time: endTime})).then(resp => {
          if (resp && resp.status === 200) {
            this.$emit('onSubmit')
          }
        })
      })
    },
    unpassRequest (row) {
      var recordId = this.records[row].id
      this.$confirm('此操作将不通过申请, 是否继续', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/unpassRequest', Qs.stringify({recordId: recordId})).then(resp => {
          if (resp && resp.status === 200) {
            this.$emit('onSubmit')
          }
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
