<template>
  <div class="block">
    <el-row style="height: 840px;">
<!--      <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>-->
      <el-tooltip effect="dark" placement="right"
                  v-for="item in resources.slice((currentPage - 1) * pagesize, currentPage*pagesize)"
                  :key="item.id">
        <el-descriptions>
          <el-descriptions-item label="编号">{{item.id}}</el-descriptions-item>
          <el-descriptions-item label="状态">
            <el-tag v-if="item.state === '空闲'" type="success">
              {{item.state}}
            </el-tag>
            <el-tag v-else-if="item.state === '使用中'" type="danger">
              {{ item.state }}
            </el-tag>
            <el-tag v-else type="info">
              {{ item.state }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="结束时间">{{ item.end_time }}</el-descriptions-item>
          <el-descriptions-item label="内存大小">{{ item.cpu }}G</el-descriptions-item>
          <el-descriptions-item label="显存大小">{{ item.gpu }}G</el-descriptions-item>
          <el-descriptions-item>
            <el-button v-if="item.state === '空闲' && !ad" @click="addRequest(item.id)">申请</el-button>
            <el-button v-if="item.state === '空闲' && ad" @click="deleteResource(item.id)">删除</el-button>
            <el-button v-if="ad" @click="updateResource(item)">修改</el-button>
          </el-descriptions-item>
        </el-descriptions>
      </el-tooltip>
    </el-row>
    <el-row>
      <el-button v-if="ad" @click="updateResource">添加</el-button>
    </el-row>
    <el-row>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="pagesizes"
        :page-size="pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="resources.length">
      </el-pagination>
    </el-row>
    <record-form @onSubmit="loadResource" ref="editRecord"></record-form>
    <resource-form @onSubmit="loadResource" ref="editResource"></resource-form>
  </div>
</template>

<script>
import SearchBar from '@/components/library/SearchBar'
import store from '@/store'
import RecordForm from '@/components/common/recordForm'
import ResourceForm from '@/components/common/resourceForm'

export default {
  name: 'showResource',
  components: {ResourceForm, RecordForm, SearchBar},
  props: {
    ad: Boolean
  },
  data () {
    return {
      resources: [],
      currentPage: 1,
      pagesize: 12,
      pagesizes: [3, 6, 9, 12],
      username: store.state.user.username
    }
  },
  mounted: function () {
    this.loadResource()
  },
  methods: {
    loadResource () {
      var _this = this
      this.$axios.get('/LoadAllResources').then(resp => {
        if (resp && resp.status === 200) {
          _this.resources = resp.data
        }
      })
    },
    addRequest (deviceId) {
      this.$refs.editRecord.dialogFormVisible = true
      this.$refs.editRecord.record_form = {
        id: '',
        username: this.username,
        device_id: deviceId,
        request_time: '',
        begin_time: '',
        end_time: '',
        state: '',
        purpose: ''
      }
      // alert(deviceId)
    },
    updateResource (item) {
      this.$refs.editResource.dialogFormVisible = true
      this.$refs.editResource.resource_form = {
        id: item.id,
        state: item.state,
        end_time: item.end_time,
        cpu: item.cpu,
        gpu: item.gpu
      }
    },
    deleteResource (deviceId) {
      this.$axios.post('/deleteResource', {id: deviceId}).then(resp => {
        if (resp && resp.status === 200) {
          this.loadResource()
        }
      })
    },
    searchResult () {

    }
  }
}
</script>

<style scoped>

</style>
