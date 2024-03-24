<template>
	<view class="content">
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
		<view class="icon-container">
			<navigator url="/pages/login/login?type=email" open-type="navigate">
			      <image src="/static/image/email.png" class="login-icon" @click="loginWithEmail"></image>
			</navigator>
			
			<navigator url="/pages/login/login?type=phone" open-type="navigate">
			    <image src="/static/image/phone.png" class="login-icon" @click="loginWithPhone"></image>
			</navigator>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: ''
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>	
	uni-page-body{
		height: 95vh;
	}
	body {
		margin: 0;
		padding: 0;
		/* 背景gif图 */
		background-image: url('/static/image/微信图片_20240324161506.jpg');
		background-size: cover;
	    background-attachment: fixed;
	    background-position: center;
		background-color: #ffffff; /*默认背景 */
	}	
	
	.content {
	  position: absolute; 
	  top: 65%; 
	  left: 50%; 
	  min-width: 50vh; 
		min-height: 40vh; 
	  transform: translate(-50%, -50%); 
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
	
	.icon-container {
	    display: flex;
	    justify-content: center; 
	    align-items: center; 
		margin-top: 99%;
	  }
	  
	
	  .login-icon{
	    margin: 0 30px; 
	    width: 45px; 
	    height: 45px; 
	    cursor: pointer; 
	  }
</style>
