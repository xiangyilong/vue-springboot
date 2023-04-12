<template>
  <div>
    <i class="el-icon-circle-plus-outline" @click="dialogFormVisible = true"></i>
    <el-dialog
      title="新增/修改资源表单"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form ref="form" :model="resource_form" label-width="80px">
        <el-form-item label="设备编号" :label-width="formLabelWidth" prop="id">
          <el-input v-model="resource_form.id" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="设备状态" :label-width="formLabelWidth" prop="state">
          <el-input v-model="resource_form.state" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="设备结束时间" :label-width="formLabelWidth" prop="end_time">
            <el-date-picker type="date" placeholder="结束时间" v-model="resource_form.end_time" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="内存大小" :label-width="formLabelWidth" prop="cpu">
          <el-select v-model="resource_form.cpu" placeholder="请选择">
            <el-option
              v-for="item in cpu_options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="显存大小" :label-width="formLabelWidth" prop="gpu">
          <el-select v-model="resource_form.gpu" placeholder="请选择">
            <el-option
              v-for="item in gpu_options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'resourceForm',
  props: ['deviceId'],
  data () {
    return {
      dialogFormVisible: false,
      resource_form: {
        id: '',
        state: '',
        end_time: '',
        cpu: '',
        gpu: ''
      },
      cpu_options: [{
        value: '8',
        label: '8'
      }, {
        value: '16',
        label: '16'
      }, {
        value: '32',
        label: '32'
      }],
      gpu_options: [{
        value: '4',
        label: '4'
      }, {
        value: '6',
        label: '6'
      }, {
        value: '8',
        label: '8'
      }],
      formLabelWidth: '120px'
    }
  },
  methods: {
    clear () {
      this.resource_form = {
        id: '',
        state: '',
        end_time: '',
        cpu: '',
        gpu: ''
      }
    },
    onSubmit () {
      this.$axios.post('/AddOrUpdateResource', {
        id: this.resource_form.id,
        end_time: this.resource_form.end_time,
        state: this.resource_form.state,
        cpu: this.resource_form.cpu,
        gpu: this.resource_form.gpu
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$emit('onSubmit')
          this.dialogFormVisible = false
        }
      })
    }
  }
}
</script>

<style scoped>
.el-icon-circle-plus-outline {
  margin: 50px 0 0 20px;
  font-size: 100px;
  float: left;
  cursor: pointer;
}
</style>
