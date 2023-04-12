<template>
  <div>
    <i class="el-icon-circle-plus-outline" @click="dialogFormVisible = true"></i>
    <el-dialog
      title="申请资源表单"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form ref="form" :model="record_form" label-width="80px">
        <el-form-item label="使用时间">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="开始时间" v-model="record_form.begin_time" style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-date-picker type="date" placeholder="结束时间" v-model="record_form.end_time" style="width: 100%;"></el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="record_form.purpose"
            clearable>
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import store from '@/store'

export default {
  name: 'recordForm',
  props: ['deviceId'],
  data () {
    return {
      dialogFormVisible: false,
      username: store.state.user.username,
      record_form: {
        id: '',
        username: '',
        device_id: '',
        request_time: '',
        begin_time: '',
        end_time: '',
        state: '',
        purpose: ''
      }
    }
  },
  methods: {
    clear () {
      this.record_form = {
        id: '',
        username: '',
        device_id: '',
        request_time: '',
        begin_time: '',
        end_time: '',
        state: '',
        purpose: ''
      }
    },
    onSubmit () {
      // alert(this.record_form.device_id)
      this.$axios.post('/addRequest', {
        id: this.record_form.id,
        username: this.record_form.username,
        device_id: this.record_form.device_id,
        request_time: this.record_form.request_time,
        begin_time: this.record_form.begin_time,
        end_time: this.record_form.end_time,
        state: '提交中',
        purpose: this.record_form.purpose
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.dialogFormVisible = false
          this.$emit('onSubmit')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
