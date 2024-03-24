<template>
	<view class="register-container">
		<view class="combined-input-container">
			<!-- 用户名 -->
			<view class="input-container">
				<label class="input-label">用户名</label>
				<input type="text" placeholder="请输入用户名" v-model="username"/>
			</view>
	
			<!-- 邮箱 -->
			<view class="input-container">
				<label class="input-label">邮箱</label>
				<input type="email" placeholder="请输入邮箱" v-model="email"/>
			</view>

			<!-- 手机号 -->
			<view class="input-container">
				<label class="input-label">手机号</label>
				<input type="tel" placeholder="请输入手机号" v-model="phone"/>
			</view>

			<!-- 密码 -->
			<view class="input-container">
				<label class="input-label">密码</label>
				<input type="password" placeholder="请输入密码" v-model="password"/>
			</view>

			<!-- 确认密码 -->
			<view class="input-container">
				<label class="input-label">确认密码</label>
				<input type="password" placeholder="请确认密码" v-model="confirmPassword"/>
			</view>
		</view>
		<div class="button-container">
			<button @click="goBack" class="back-button">返回登录</button>
			<button @click="register" :class="isFormComplete ? 'register-button-active' : 'register-button'" :disabled="!isFormComplete">注册</button>
		</div>	
	</view>
</template>


<script>
export default {
	data() {
		return {
			username: '',
			email: '',
			phone: '',
			password: '',
			confirmPassword: ''
		};
	},
	computed: {
	    isFormComplete() {
	        return this.username && this.email && this.phone && this.password && this.confirmPassword;
	    }
	},
	methods: {
		register() {
		// 注册逻辑
		console.log("注册信息", this.username, this.email, this.phone, this.password, this.confirmPassword);
		},
		goBack(){
			uni.navigateTo({
				url:'/pages/login/login'
			})
		}
	}
}
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

.register-container {
	display: flex;
	margin-top: 5vh;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	width: 100%;
	margin-top:25vh;
}

.register-title {
	font-size: 24px;
	margin-bottom: 20px;
	color: #333;
}

.input-container {
	position: relative;
	display: flex;
	align-items: center;
	width: 100%;
	margin-bottom: 10px;
}

.input-label {
	position: absolute;
	left: 13px;
	color: #363636;
	pointer-events: none;
}

.input-container input {
	width: 100%;
	padding: 12px 15px 10px 10px; 
	padding-left: 85px;
	border: none;
	border-radius: 0;
	background-color: rgba(255, 255, 255, 0.5);
}

.input-group input:focus {
	background-color: rgba(255, 255, 255, 0.9);
}

.register-button {
	width: 48%;
	background-color: #b3d8f5;
	color: white;
	border: none;
	border-radius: 15px;
	margin-top: 3vh;
}

.register-button-active {
	width: 48%;
	background-color: #ffacc9;
	color: white;
	border: none;
	border-radius: 15px;
	margin-top: 3vh;
}

.button-container {
    display: flex;
    justify-content: space-between;
    width: 100%;
}

.back-button {
    width: 48%; 
    background-color:#FFB6C1; 
    color: white;
    border: none;
    border-radius: 15px;
	margin-top: 3vh;
}

.combined-input-container {
	border: 1px solid #ccc;
	border-radius: 10px;
	overflow: hidden;
	width: 100%;
}

.input-container {
	margin: 0;
	border-bottom: 1px solid #ccc; 
}

.input-container:last-child {
	border-bottom: none;
}

</style>

