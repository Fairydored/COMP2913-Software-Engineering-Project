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
		<u-row>
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
									<text class="feature-description">Tailored plans that adapt to your fitness level
										and goals.</text></u-col>
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
		computed: {
			remainingMembershipDays() {
				if (!this.isMember || !this.expirationDate) {
					return 'Not a member';
				}
				const today = new Date();
				const expiration = new Date(this.expirationDate);
				const timeDiff = expiration - today;
				if (timeDiff < 0) {
					return 'Membership expired';
				}
				const daysRemaining = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
				return `${daysRemaining} days remaining`;
			}
		},
		methods: {
			getUserData() {
				const userDataStr = localStorage.getItem('userData');
				if (userDataStr) {
					const userData = JSON.parse(userDataStr);
					this.username = userData.data[0].username;
					this.email = userData.data[0].email;
					this.userId = userData.data[0].id;
					this.membershipLevel = userData.data[0].membershipLevel;
					this.expirationDate = userData.data[0].expirationDate;
					this.effectiveDate = userData.data[0].effectiveDate;
					this.avatarUrl = userData.data[0].avatarUrl || this.avatarUrl;
				} else {
					console.error('No user data available');
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

				try {
					const userData = JSON.parse(userDataStr);
					const data = {
						userId: this.userId,
						membershipLevel: membershipLevel,
						paymentPlan: paymentPlan,
						amount: prices[membershipLevel][paymentPlan]
					}
					const response = await uni.request({
						url: 'http://waa.cool:3009/membership/purchase',
						method: 'POST',
						data
					});

					const result = await response.json();
					if (res.data.length > 0) {
						var returnData = JSON.stringify(res.data);
						this.userData = JSON.parse(returnData)
						console.log('数据更新成功');
					}
				} catch (error) {
					// 处理登录失败的逻辑
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