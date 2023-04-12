<template>
  <el-form :model="regesiterForm" status-icon :rules="rules" ref="regesiterForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="regesiterForm.username"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="regesiterForm.password" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkpassword">
      <el-input type="password" v-model="regesiterForm.checkpassword" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('regesiterForm')">提交</el-button>
      <el-button @click="resetForm('regesiterForm')">重置</el-button>
      <el-button @click="gotoLogin">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: 'register',
  data () {
    let checkUsername = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'))
      }
      callback()
      // setTimeout(() => {
      //   if (!Number.isInteger(value)) {
      //     callback(new Error('请输入数字值'))
      //   } else {
      //     if (value < 18) {
      //       callback(new Error('必须年满18岁'))
      //     } else {
      //       callback()
      //     }
      //   }
      // }, 1000)
    }
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('密码不能为空'))
      } else {
        if (this.regesiterForm.checkpassword !== '') {
          this.$refs.regesiterForm.validateField('checkpassword')
        }
        callback()
      }
    }
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.regesiterForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      regesiterForm: {
        username: '',
        password: '',
        checkpassword: ''
      },
      rules: {
        username: [
          { validator: checkUsername, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkpassword: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      // alert(this.regesiterForm.username + this.regesiterForm.password + this.regesiterForm.checkpassword + formName)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post('/register', {
              username: this.regesiterForm.username,
              password: this.regesiterForm.password
            })
            .then(successResponse => {
              if (successResponse.data.code === 200) {
                this.$message({
                  message: '注册成功',
                  type: 'success'
                })
              } else if (successResponse.data.code === 500) {
                this.$message('用户已存在')
              } else if (successResponse.data.code === 400) {
                this.$message.error('注册失败')
              }
            })
            .catch(failResponse => {
            })
        } else {
          // console.log('error submit!!')
          alert('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    gotoLogin () {
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>

</style>
