<template>
	<view class="u-page">
		<view class="signin-content">
			<u--form labelPosition="left" :model="model1" :rules="rules" ref="uForm">
				<u-form-item label="Register role" prop="userInfo.role" borderBottom
					@click="showRole = true; hideKeyboard()" ref="item1" labelWidth="30%">
					<u--input v-model="model1.userInfo.role" disabled disabledColor="#ffffff"
						placeholder="Please select role" border="none"></u--input>
					<u-icon slot="right" name="arrow-right"></u-icon>
				</u-form-item>
				<u-form-item label="Username" prop="userInfo.name" borderBottom ref="item2" labelWidth="30%">
					<u--input v-model="model1.userInfo.name" border="none"></u--input>
				</u-form-item>
				<u-form-item label="Email" prop="userInfo.email" borderBottom ref="item3" labelWidth="30%">
					<u--input v-model="model1.userInfo.email" border="none"></u--input>
				</u-form-item>
				<u-form-item label="Password" prop="userInfo.password" borderBottom ref="item4" labelWidth="30%">
					<u--input v-model="model1.userInfo.password" border="none" :password="true"></u--input>
				</u-form-item>
				<u-form-item label="Confirm password" prop="userInfo.validatePassword" borderBottom ref="item5"
					labelWidth="30%">
					<u--input v-model="model1.userInfo.validatePassword" border="none" :password="true"></u--input>
				</u-form-item>
			</u--form>
			<u-action-sheet :show="showRole" :actions="actions" title="Please select role" @close="showRole = false"
				@select="RoleSelect">
			</u-action-sheet>
		</view>
		<view id="button">
			<u-row justify="center" customStyle="margin-top: 10px">
				<u-col span="6">
					<u-button customStyle="color:white;" text="注册" size="normal" color="rgb(255, 227, 242, 0.5)"
						shape="circle" @click="signin()"></u-button>
				</u-col>
			</u-row>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				showRole: false,
				model1: {
					userInfo: {
						role: '',
						username: '',
						email: '',
						password: '',
						validatePassword: ''
					},
				},
				actions: [{
						name: 'admin',
					},
					{
						name: 'user',
					},
				],
				rules: {
					'userInfo.name': {
						type: 'string',
						required: true,
						message: 'Please input username',
						trigger: ['blur', 'change']
					},
					'userInfo.role': {
						type: 'string',
						required: true,
						message: 'Please select role',
						trigger: ['blur', 'change']
					},
					'userInfo.email': {
						type: 'email',
						required: true,
						message: 'Please input correct Email',
						trigger: ['blur', 'change']
					},
					'userInfo.password': {
						required: true,
						message: 'Please input password',
						trigger: ['blur', 'change']
					},
					'userInfo.validatePassword': [{
							required: true,
							message: 'Please confirm password',
							trigger: ['blur', 'change']
						},
						{
							validator: (rule, value, callback) => {
								if (value !== this.model1.userInfo.password) {
									return false
								} else {
									return true
								}
							},
							message: 'Passeord is not the same!',
							// 触发器可以同时用blur和change
							trigger: ['change', 'blur'],
						}
					],
				}
			}
		},
		methods: {
			RoleSelect(e) {
				this.model1.userInfo.role = e.name
				this.$refs.uForm.validateField('userInfo.role')
			},
			signin() {
				this.$refs.uForm.validate().then(res => {
					uni.request({
						url: 'http://waa.cool:3009/register',
						method: 'POST',
						data: {
							role: "普通用户",
							username: this.model1.userInfo.name,
							email: this.model1.userInfo.email,
							password: this.model1.userInfo.password,
							confirmPassword: this.model1.userInfo.confirmPassword,
						}
					});
					console.log(res);
					if (res) {
						uni.$u.toast('Register successfully');
						uni.navigateTo({
							url: '../index/index'
						});
					}
				}).catch(errors => {
					uni.$u.toast('Please input right information!')
				})
			}
		}
	}
</script>

<style>
	.u-page {
		height: 100vh;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		background-image: url('../../static/login_bg.jpg');
		background-size: cover;
	}

	.signin-content {
		width: 85%;
		background-color: rgba(255, 255, 255, 0.5);
		border-radius: 10px;
		padding: 0.5em;
	}

	#button {
		width: 90%;
	}
</style>