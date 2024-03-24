<template>
<view class="login-page">
	<view class="login-content">
		<!-- 登录方式切换区域 -->
		<view class="login-switch">
			<view :class="{'switch-item': true, 'active': loginType === 'phone'}" @click="setLoginType('phone')">验证码登录</view>
			<view class="switch-divider"></view>
			<view :class="{'switch-item': true, 'active': loginType === 'email'}" @click="setLoginType('email')">密码登录</view>
		</view>
		<!-- 登录表单区域 -->
		<view class="login-form">
			<!-- 手机号输入 -->
			 <view class="input-group combined-input" v-if="loginType === 'phone'">
				<view class="input-container">
					<label class="input-label">手机</label>
					<input type="number" placeholder="请输入手机号" v-model="phone"/>
					<!-- 验证码发送按钮 -->
					<view :class="{'verification-code-send': true, 'code-active': isPhoneValid}" @click="isPhoneValid && sendVerificationCode">获取验证码</view>
					<view class="input-divider"></view>
				</view>
				<!-- 验证码输入 -->
				<view class="input-group">
					<view class="input-container">
						<label class="input-label">验证码</label>
						<input type="text" placeholder="请输入验证码" v-model="verificationCode"/>
					</view>
				</view>
			</view>
      
			<!-- 邮箱输入 -->
			<view class="input-group combined-input" v-else>
				<view class="input-container">
				<label class="input-label">账号</label>
				<input type="text" placeholder="请输入账号或邮箱" v-model="email"/>
				</view>
				<!-- 密码输入 -->
				<view class="input-group">
					<view class="input-container">
					<label class="input-label">密码</label>
					<input :type="showPassword ? 'text' : 'password'" placeholder="密码" v-model="password"/>
					<image class="toggle-password" @click="toggleShowPassword" :src="showPassword ? '/static/image/eye-open.png' : '/static/image/eye-closed.png'"></image> 
					<navigator url="/pages/forgotPassword/forgotPassword" class="forgot-password-link">忘记密码?</navigator>
				  </view>
				</view>
			</view>
	
			<!-- 按钮区域 -->
			<view class="button-area">
				<!-- 注册按钮 -->
				<button class="auth-button register-button" @click="goToRegister">注册</button>
				<!-- 登录按钮 -->
				<button class="auth-button login-button" @click="login" :disabled="isLoginDisabled">登录</button>
			</view>
		</view>
	</view>	
	<!-- 用户协议 -->
	<view class="agree-section">
		<input type="checkbox" id="agree" :checked="agreed" @click="toggleAgreement" />
		<label for="agree">我已阅读并同意
			<navigator url="/pages/userAgreement/userAgreement" >《用户协议》</navigator>
		</label>		
	</view>
</view>
</template>

<script>
export default {
  data() {
    return {
      loginType: 'phone', // 默认为手机号登录
      phone: '',
      email: '',
      password: '',
      showPassword: false,
	  agreed: false,
    };
  },
  computed: {
	isLoginDisabled() {
	    if (this.loginType === 'email') {
	    // 邮箱登录：需要邮箱和密码输入，以及用户协议被勾选
	        return !this.agreed || !this.email || !this.password;
	    } else {
	        // 手机号登录：需要11位手机号和验证码输入，以及用户协议被勾选
	        return !this.agreed || !this.isPhoneValid || !this.verificationCode;
	    }
	},
	isPhoneValid() {
	    return this.phone.length === 11;
	},
  },
  methods: {
    setLoginType(type) {
      this.loginType = type;
    },
	goToRegister(){
		uni.navigateTo({
			url:'/pages/register/register'
		})
	},
    login() {
		console.log('尝试登录');
		uni.switchTab({
			url: '/pages/home/home'
		});
    },
    toggleShowPassword() {
          this.showPassword = !this.showPassword;
    },
	toggleAgreement() {
	    this.agreed = !this.agreed; // 切换勾选状态
	},
  }
};
</script>

<style>
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh; 
  margin: 0; 
  background-image: url('/static/image/微信图片_20240324161506.jpg');
  background-size: cover;
  background-attachment: fixed;
  background-position: center;
  background-color: #ffffff; /*默认背景 */
}

.login-page {
	display: flex;
	flex-direction: column;
	align-items: center;
	box-sizing: border-box;
}

.login-content{
	margin-top: 24vh;
}

.login-switch {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin-bottom: 20px;
}

.switch-divider {
  width: 1px;
  height: 20px;
  background-color: #ccc;
}

.switch-item {
  padding: 10px;
  color: #505050;
}

.switch-item.active {
  color: #f4f4f4;
}
.input-group {
  width: 100%;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #00aeec;
  color: white;
  border: none;
}

.register-link {
  margin-top: 20px;
}

.button-area {
	margin-top: 1.5vh;
	display: flex;
	justify-content: space-between;
	width: 100%;
}

.auth-button {
  height: 40px;
  line-height:20px;
  border: none;
  border-radius: 15px;
  margin-right: 5px;
  flex: 1;
}

.register-button {
  background-color: #fff;
  color: #000;
  border: 1px solid #ccc;
}

.login-button {
  background-color: #FFB6C1;
  color: white;
}

.auth-button:last-child {
  margin-right: 0;
}

.input-container {
  position: relative;
  display: flex;
  align-items: center;
}

.toggle-password {
  position: absolute;
  right: 85px; 
  cursor: pointer;
  width: 25px;
  height: 25px;
}

.forgot-password-link {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%); 
  color: #00a1d6;
  font-size: 14px;
}

.input-label {
  position: absolute;
  left: 10px;
  color: #000000;
  pointer-events: none;
}

.input-group input {
  width: 100%;
  padding: 10px;
  padding-left: 65px; 
  padding-right: 100px; 
  border: 1px solid #ccc;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.75);
}

.input-group input:focus {
  background-color: rgba(255, 255, 255, 0.9);
}
.verification-code-send {
    position: absolute;
    right: 15px;
    top: 50%;
    transform: translateY(-50%);
    color: #ccc;
	cursor: not-allowed;
  }

.code-active {
	color: #007AFF; 
    cursor: pointer; 
}

.input-divider {
    position: absolute;
    right: 110px;
    height: 24px;
    width: 1px;
    background-color: #ccc;
}

.agree-section {
	display: flex;
	align-items: center;
	margin-left: 5%;
/* 	padding:10px; */
	margin-top: 30vh;
}

.agree-section label {
  display: inline-block; 
  color: #ffffff;
}

.agree-section navigator{
	display: inline-block;
	color: #0aa4d7;
}

/* 大圆角矩形框 */
.combined-input {
  border: 1px solid #ccc;
  border-radius: 8px;
  overflow: hidden;
  padding: 0;
}

.input-container {
  margin: 0; 
}

.input-container:first-child {
  border-bottom: 1px solid #ccc; 
}

/* 移除输入框的边框和圆角 */
.input-container input {
  border: none; 
  border-radius: 0; 
}

</style>