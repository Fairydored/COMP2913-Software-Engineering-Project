<template>
	<view class="u-page">
		<view class="info">
			<u-row class="blank"></u-row>
			<u-row>
				<u-col :span="1"></u-col>
				<u-col :span="2">
					<u-avatar :src="avatarUrl" size="35" />
				</u-col>
				<u-col :span="6">
					<text class="user-name">Hello, {{ username }}</text>
				</u-col>
				<u-col :span="2"></u-col>
			</u-row>
			<u-row>
				<u-col :span="1"></u-col>
				<u-col :span="2"></u-col>
				<u-col :span="6">
					<text class="recommendation">Here are the recommended subscription plans for you:</text>
				</u-col>
				<u-col :span="3"></u-col>
			</u-row>
		</view>

		<!-- 会员购买部分 -->
		<!-- VIP Membership Section -->
		<view> <u-row>
				<u-col :span="1.5"></u-col>
				<u-col :span="9">
					<view class="membership-section">
						<text class="membership-title">MileHunter VIP</text>
						<view class="subscription-options">
							<u-button size="small" @click="submitPayment('level1', 'monthly')"
								type="primary">￥9.99/m</u-button>
							<u-button size="small" @click="submitPayment('level1', 'yearly')"
								type="primary">￥99.99/y</u-button>
						</view>
						<view class="divider"></view>
						<view class="features">
							<view class="feature">
								<u-row>
									<u-col :span="1.5"><u-icon name="checkbox-mark" size="24" color="#4CAF50" /></u-col>
									<u-col :span="0.5"></u-col>
									<u-col :span="10"><text class="feature-title">Exclusive Access to All Tracks</text>
										<text class="feature-description">Unlimited access to all content and tracks
											available.</text>
									</u-col>
								</u-row>

							</view>
							<view class="feature">
								<u-row>
									<u-col :span="1.5"><u-icon name="checkbox-mark" size="24" color="#4CAF50" /></u-col>
									<u-col :span="0.5"></u-col>
									<u-col :span="10"><text class="feature-title">Monthly Progress Reports</text>
										<text class="feature-description">Receive detailed reports on your training
											progress.</text></u-col>
								</u-row>
							</view>
						</view>
					</view>
				</u-col>
				<u-col :span="1.5"></u-col>
			</u-row>
			<!-- MileHunter Pro Section -->
			<u-row>
				<u-col :span="1.5"></u-col>
				<u-col :span="9">
					<view class="membership-section">
						<text class="membership-title">MileHunter Pro</text>
						<view class="subscription-options">
							<u-button size="small" type="primary"
								@click="submitPayment('level2', 'monthly')">￥19.99/m</u-button>
							<u-button size="small" type="primary"
								@click="submitPayment('level2', 'yearly')">￥199.99/y</u-button>
						</view>
						<view class="divider"></view>
						<view class="features">
							<view class="feature">
								<u-row>
									<u-col :span="1.5"><u-icon name="checkbox-mark" size="24" color="#4CAF50" /></u-col>
									<u-col :span="0.5"></u-col>
									<u-col :span="10"><text class="feature-title">Personalized Training Plans</text>
										<text class="feature-description">Tailored plans that adapt to your fitness
											level and goals.</text></u-col>
								</u-row>

							</view>
							<view class="feature">
								<u-row>
									<u-col :span="1.5"><u-icon name="checkbox-mark" size="24" color="#4CAF50" /></u-col>
									<u-col :span="0.5"></u-col>
									<u-col :span="10"><text class="feature-title">Priority Support</text>
										<text class="feature-description">Get your queries resolved with our priority
											customer support.</text></u-col>
								</u-row>
							</view>
						</view>
					</view>
				</u-col>
				<u-col :span="1.5"></u-col>
			</u-row>
		</view>

		<u-row class="blank"></u-row>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				username: 'xxx',
				email: '',
				userId: '',
				avatarUrl: '../../static/default-avatar.png',
			}
		},
		mounted() {
			this.getUserData();
		},
		methods: {
			async getUserData() {
				const userDataStr = uni.getStorageSync('userData');
				if (!userDataStr) {
					this.infoShow = true; // 显示没有用户数据的信息
					return;
				}
				try {
					const userData = JSON.parse(userDataStr);
					const data = {
						username: userData.data[0].username,
						password: userData.data[0].password
					};

					const res = await uni.request({
						url: 'http://waa.cool:3009/login',
						method: 'POST',
						data
					});

					if (res.data && res.data.length > 0) {
						console.log(res.data[0]);
						// 使用返回的第一个用户信息对象更新 userData
						this.userData = res.data[0]; // 假设我们只关心数组的第一个元素
						// 更新组件内部状态
						this.username = this.userData.username;
						this.email = this.userData.email;
						this.userId = this.userData.id;
						this.membershipLevel = this.userData.membershipLevel;
						this.expirationDate = this.userData.expirationDate;
						this.effectiveDate = this.userData.effectiveDate;
						this.avatarUrl = this.userData.avatarUrl || this.avatarUrl;
						this.isAutoRenewalCancelled = this.userData.cancelled;
						this.isMember = ['level1', 'level2'].includes(this.userData.membershipLevel);
					}
				} catch (error) {
					console.error('Error logging in:', error);
					this.infoShow = true; // 可能需要显示错误信息
				}
			},
			async submitPayment(membershipLevel, paymentPlan) {
				const userDataStr = uni.getStorageSync('userData')
				const prices = {
					'level1': {
						'monthly': 9.99,
						'yearly': 99.99
					},
					'level2': {
						'monthly': 19.99,
						'yearly': 199.99
					}
				};
				const data = {
					userId: this.userId,
					membershipLevel: membershipLevel,
					paymentPlan: paymentPlan,
					amount: prices[membershipLevel][paymentPlan]
				};

				try {
					const userData = JSON.parse(userDataStr);
					console.log(data);
					const res = await uni.request({
						url: 'http://waa.cool:3009/membership/purchase',
						method: 'POST',
						data
					});
					if (res.data) { // 假设成功响应包含一个success属性
						this.userData = res.data; // 更新userData，这里假设res.data是完整的用户数据
						uni.showToast({
							title: 'Purchase Successfully',
							icon: 'success',
							duration: 2000
						});
						console.log('会员购买成功', res.data);
						setTimeout(() => {
							uni.navigateTo({
								url: '/pages/me/member-center'
							});
						}, 3000);
					} else {
						uni.showToast({
							title: 'Purchase Failed',
							icon: 'none'
						});
						console.log('会员购买失败', res.data);
					}
				} catch (error) {
					uni.showToast({
						title: 'Request Failed',
						icon: 'none'
					});
					console.error('路由发送失败', error);
				}
			}
		},

	}
</script>

<style>
	.u-page {
		font-family: "April", sans-serif;
	}
	.blank {
		margin: 3vh;
	}

	.info {
		margin-top: 3vh;
	}

	.membership-section {
		margin-top: 20px;
		padding: 20px;
		background-color: #fff;
		border-radius: 10px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		flex-direction: column;
	}

	.membership-title {
		font-size: 18px;
		font-weight: bold;
		color: #333;
		margin-bottom: 15px;
	}

	.vip-options {
		display: flex;
		flex-direction: column;
	}

	.vip-option {
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 10px 0;
		border-bottom: 1px solid #eee;
	}

	.vip-level {
		font-size: 16px;
		font-weight: bold;
		color: #333;
	}

	.vip-title {
		text-align: center;
		font-size: 20px;
		font-weight: bold;
		color: #333;
		margin-bottom: 20px;
	}

	.vip-section {
		margin-top: 20px;
		padding: 20px;
		background-color: #fff;
		border-radius: 10px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	.membership-section {
		background-color: #FFF;
		border-radius: 10px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		padding: 20px;
		margin-top: 20px;
		flex-direction: column;
	}

	.membership-title {
		font-size: 18px;
		font-weight: bold;
		color: #333;
		margin-bottom: 15px;
		text-align: center;
		display: block;
	}

	.subscription-option {
		display: flex;
		align-items: center;
		flex-direction: column;
		justify-content: space-between;
		margin-bottom: 10px;
	}

	.subscription-detail {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
	}

	.subscription-type {
		font-size: 16px;
		font-weight: bold;
		color: #333;
	}

	.subscription-price {
		font-size: 14px;
		color: #555;
	}

	.u-button {
		width: 90px;
		padding: 5px 10px;
		font-size: 14px;
		text-align: center;
		border-radius: 5px;
		margin-right: 5%;
		background: linear-gradient(to right, rgb(196, 54, 189), rgb(74, 81, 212));
		border: none;

	}

	.u-button--primary {
		min-width: 90px;
		padding: 5px 10px;
	}

	/* 调整头像和用户名区域的样式 */
	.user-avatar {
		border-radius: 50%;
		margin-right: 10px;
	}

	.recommendation {
		font-size: 14px;
		color: #666;
		padding: 10px 0;
		border-top: 1px solid #eee;
		width: 100%;
		box-sizing: border-box;
	}

	.user-name {
		font-size: 18px;
		font-weight: 500;
		color: #333;
		letter-spacing: 0.5px;
		margin-top: 0;
		margin-bottom: 5px;
	}


	.main-title {
		font-size: 24px;
		font-weight: bold;
		color: #333;
	}

	.feature-title {
		font-size: 16px;
		font-weight: bold;
		color: #333;
		margin-right: 5px;
	}

	.feature-description {
		font-size: 14px;
		color: #888;
		flex: 1;
		margin-top: 5px;
	}

	.subscription-options {
		display: flex;
		justify-content: space-around;
		margin-bottom: 15px;
	}

	.divider {
		height: 1px;
		background-color: #EEE;
		margin: 20px 0;
	}

	.features {
		display: flex;
		flex-direction: column;
	}

	.feature {
		display: flex;
		flex-direction: column;
		margin-bottom: 10px;
	}

	.u-button--primary {
		background: linear-gradient(to right, rgb(196, 54, 189), rgb(74, 81, 212));
		border: none;
		border-radius: 5px;
		padding: 8px 8px;
		color: #FFF;
		font-size: 12px;
		font-weight: bold;
	}
</style>