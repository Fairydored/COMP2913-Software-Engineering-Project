<template>
	<view class="u-page">
		<view id="u-login-block">
			<u-row style="margin-bottom: 10px;">
				<u-col span="8" offset="2">
					<u-tabs :list="modes" :scrollable="false" @click="click" lineColor="#DA7598"
						activeStyle="color:white;"></u-tabs>
				</u-col>
			</u-row>

			<view class="login-content" v-show="curIndex === 0">
				<u-row justify="space-between" gutter="10" customStyle="margin-bottom: 10px">
					<u-col span="3">
						<text class="u-demo-block__title">手机号</text>
					</u-col>
					<u-col span="9">
						<u-input placeholder="请输入手机号" placeholderStyle="color: #686868;" border="bottom"
							@change="change">
							<template slot="suffix">
								<u-code ref="uCode" @change="codeChange" seconds="60" changeText="X秒重新获取"></u-code>
								<u-button class="custom-button" @tap="getCode" :text="tips" type="success"
									size="small"></u-button>
							</template>
						</u-input>
					</u-col>
				</u-row>
				<u-row justify="space-between" gutter="5">
					<u-col span="3">
						<text class="u-demo-block__title">验证码</text>
					</u-col>
					<u-col span="9">
						<u-input placeholder="请输入验证码" placeholderStyle="color: #686868;" border="bottom"
							v-model="verifiedCode" @change="change"></u-input>
					</u-col>
				</u-row>
			</view>
			<view class="login-content" v-show="curIndex === 1">
				<u-row justify="space-between" gutter="10" customStyle="margin-bottom: 10px">
					<u-col span="3">
						<text class="u-demo-block__title">账号</text>
					</u-col>
					<u-col span="9">
						<u-input placeholder="请输入账号" placeholderStyle="color: #686868;" border="bottom"
							v-model="username">
						</u-input>
					</u-col>
				</u-row>
				<u-row justify="space-between" gutter="5">
					<u-col span="3">
						<text class="u-demo-block__title">密码</text>
					</u-col>
					<u-col span="9">
						<u-input placeholder="请输入密码" placeholderStyle="color: #686868;" border="bottom" :password="true"
							v-model="password"></u-input>
					</u-col>
				</u-row>
				<u-row justify="end" customStyle="margin-top: 10px">
					<u-col span="3">
						<u-link href="pages/forget_password/forget_password" text="忘记密码？" color="#580029"
							line-color="#19be6b"></u-link>
					</u-col>
				</u-row>
			</view>
		</view>
		<view id="buttons">
			<u-row justify="space-between" gutter="10" customStyle="margin-top: 10px">
				<u-col span="6">
					<u-button customStyle="color:black;" text="注册" size="normal" color="rgb(255, 227, 242, 0.5)"
						shape="circle" @click="ToSignInPage()"></u-button>
				</u-col>
				<u-col span="6">
					<u-button customStyle="color:black;" text="登录" size="normal" color="rgb(255, 227, 242, 0.5)"
						shape="circle" @click="login()"></u-button>
				</u-col>
			</u-row>
		</view>


	</view>
</template>

<script>
	export default {
		data() {
			return {
				modes: [{
					name: '验证码登录',
				}, {
					name: '密码登录',
				}],
				curIndex: 0,
				username: '',
				password: '',
				verifiedCode: '',
				tips: '',
				value: ''
			}
		},
		watch: {
			value(newValue, oldValue) {
				// console.log('v-model', newValue);
			}
		},
		methods: {
			codeChange(text) {
				this.tips = text;
			},
			ToSignInPage() {
				uni.navigateTo({
					url: '../login/signin',
				});
			},
			getCode() {
				if (this.$refs.uCode.canGetCode) {
					// 模拟向后端请求验证码
					uni.showLoading({
						title: '正在获取验证码'
					})
					setTimeout(() => {
						uni.hideLoading();
						// 这里此提示会被this.start()方法中的提示覆盖
						uni.$u.toast('验证码已发送');
						// 通知验证码组件内部开始倒计时
						this.$refs.uCode.start();
					}, 2000);
				} else {
					uni.$u.toast('倒计时结束后再发送');
				}
			},
			click(item) {
				this.curIndex = item.index;
			},
			// 登录逻辑
			async login() {
				let data;
				if (this.curIndex === 0) { // 验证码登录
					data = {
						username: this.username,
						code: this.verifiedCode
					};
				} else { // 密码登录
					data = {
						username: this.username,
						password: this.password
					};
				}
				try {
					const res = await uni.request({
						url: 'http://waa.cool:3009/login',
						method: 'POST',
						data
					});
					// 处理路由发送成功的逻辑
					if (res.data.length > 0) {
						uni.setStorageSync('userData', JSON.stringify({
							data: res.data,
							timestamp: new Date().getTime()
						}));
						uni.$u.toast('Login successfully！');
						uni.navigateTo({
							url: '../index/index',
						});
					} else {
						uni.$u.toast('登录失败！');
					}
					// console.log('登录成功', res.data);
				} catch (error) {
					// 处理登录失败的逻辑
					console.error('路由发送失败', error);
				}
			}
		}
	}
</script>

<style lang="scss">
	.u-page {
		height: 100vh;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		background-image: url('../../static/login_bg.jpg');
		background-size: cover;
	}

	#u-login-block {
		width: 85%;
	}

	#buttons {
		width: 85%;
	}

	.login-content {
		background-color: rgba(255, 255, 255, 0.5);
		border-radius: 10px;
		padding: 0.5em;
	}

	.custom-button {
		background-color: #DA7598;
		border: #DA7598;
	}
</style>