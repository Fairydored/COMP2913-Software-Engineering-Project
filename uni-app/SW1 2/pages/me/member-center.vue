<template>
	<view class="u-page">
		<view :class="['member-card', membershipBackground]">
			<u-row>
				<!-- User Avatar -->
				<u-col :span="4" class="avatar-container">
					<u-avatar :src="avatarUrl" size="70" class="member-avatar" />
				</u-col>

				<!-- Membership Info -->
				<u-col :span="8" class="member-info-container">
					<text class="membership-status">{{ membershipStatus }}</text>
					<text class="expiry-date">{{ expirationText }}</text>
				</u-col>
			</u-row>

			<!-- Progress Container -->
			<view class="progress-container">
				<u-line-progress :percentage="normalizedGrowthValue" :showText="false"></u-line-progress>
				<text class="points-info">{{ pointsInfo }}</text>
			</view>
		</view>

		<!-- VIP 特权展示部分 -->
		<view class="vip-privileges-section">
			<text class="section-title">VIP Privileges</text>
			<u-grid :border="true" col="3">
				<u-grid-item v-for="(listItem,listIndex) in list" :key="listIndex">
					<u-icon :customStyle="{paddingTop:20+'rpx'}" :name="listItem.name" :size="22"></u-icon>
					<text class="grid-text">{{listItem.title}}</text>
				</u-grid-item>
			</u-grid>
		</view>

		<!-- 会员展示部分 -->
		<!-- VIP Membership Section -->
		<view class="vip-show-section">
			<text class="section-title">Current Membership</text>
			<u-row>
				<u-col :span="1.5"></u-col>
				<u-col :span="9">
					<view class="membership-section " v-if="membershipLevel === 'level1'">
						<text class="membership-title">MileHunter VIP</text>
						<view class="subscription-options">
							<u-button size="small" @click="cancelMembership" type="warn">cancel</u-button>
							<u-button size="small" @click="renewMembership" type="primary">renew</u-button>
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
					<view class="membership-section" v-if="membershipLevel === 'level2'">
						<text class="membership-title">MileHunter Pro</text>
						<view class="subscription-options">
							<u-button size="small" @click="cancelMembership" type="warn">cancel</u-button>
							<u-button size="small" @click="renewMembership" type="primary">renew</u-button>
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
				membershipLevel: '',
				membershipDays: 10,
				growthValue: 1000,
				expirationDate: '',
				effectiveDate: '',
				isAutoRenewalCancelled: false,
				percentage: 0,
				list: [{
						name: 'edit-pen',
						title: 'Edit Username'
					},
					{
						name: 'calendar',
						title: 'Customized Plan'
					},
					{
						name: 'server-man',
						title: 'Customer Service'
					},
					{
						name: 'skip-forward-right',
						title: 'Growth Acceleration'
					},
					{
						name: 'file-text',
						title: 'Body Data Analysis'
					},
					{
						name: 'list-dot',
						title: 'Exclusive Features'
					},
				],
			};
		},
		mounted() {
			this.getUserData();
		},
		computed: {
			normalizedGrowthValue() {
				const today = new Date();
				const effective = new Date(this.effectiveDate);
				const daysRemaining = Math.ceil((today - effective) / (1000 * 60 * 60 * 24));
				// 获取当前成长值所在的范围，并计算进度条百分比
				if (this.growthValue < 1000) {
					return this.percentage = (this.growthValue / 1000) * 100;;
				} else if (this.growthValue < 5000) {
					return ((this.growthValue - 1000) / 4000) * 100;
				} else if (this.growthValue < 10000) {
					return ((this.growthValue - 5000) / 5000) * 100;
				} else {
					return 100; // 超过10000，进度条满值
				}
			},
			membershipBackground() {
				if (this.growthValue < 1000) {
					return 'blue-background';
				} else if (this.growthValue < 5000) {
					return 'gold-background';
				} else if (this.growthValue < 10000) {
					return 'platinum-background';
				} else {
					return 'diamond-background';
				}
			},
			membershipStatus() {
				// Logic to determine the membership status
				if (this.growthValue < 1000) {
					return 'Public Member';
				} else if (this.growthValue < 5000) {
					return 'Gold Member';
				} else if (this.growthValue < 10000) {
					return 'Platinum Member';
				} else {
					return 'Diamond Member';
				}
			},
			expirationText() {
				const today = new Date();
				const expiration = new Date(this.expirationDate);
				if (this.isAutoRenewalCancelled) {
					if (today > expiration) {
						return 'Expired';
					} else {
						const daysRemaining = Math.ceil((expiration - today) / (1000 * 60 * 60 * 24));
						return `Expiring date: ${expiration.getFullYear()}.${expiration.getMonth() + 1}.${expiration.getDate()} (expires in ${daysRemaining} days)`;
					}
				} else {
					return 'Auto-renewal'; // 当自动续费未取消时显示
				}
			},
			pointsInfo() {
				// Logic to determine the points and membership days info
				const today = new Date();
				const effective = new Date(this.effectiveDate);
				const daysRemaining = Math.ceil((today - effective) / (1000 * 60 * 60 * 24));
				this.growthValue = daysRemaining * 20;
				return `${this.growthValue} | Total membership days: ${daysRemaining} day(s)`;
			},
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
			renewMembership() {
				// 跳转到订阅页面
				uni.navigateTo({
					url: '/pages/me/subscribe' // 确保这是正确的路径
				});
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
					if (res.data) {
						var returnData = JSON.stringify(res.data);
						this.userData = JSON.parse(returnData)
						console.log('会员购买成功');
						console.log(res.data)
					} else {
						console.log('会员购买失败');
						// console.log(res.data)
					}
				} catch (error) {
					// 处理发送失败的逻辑
					console.error('路由发送失败', error);
				}
			},
			async cancelMembership() {
				const userDataStr = uni.getStorageSync('userData');
				if (!userDataStr) {
					console.error('No user data available in storage.');
					uni.showToast({
						title: '无用户数据',
						icon: 'none'
					});
					return;
				}
				try {
					let userData = JSON.parse(userDataStr);
					// 确保 userData.data 是数组
					if (!userData.data || !Array.isArray(userData.data)) {
						userData.data = [];
					}

					const data = {
						userId: this.userId
					};
					const res = await uni.request({
						url: 'http://waa.cool:3009/membership/cancel',
						method: 'POST',
						data
					});

					if (res.data && typeof res.data.cancelled !== 'undefined') {
						// 正确地更新 userData.data[0]
						userData.data[0] = res.data; // 假设有数据返回并且要这样存储
						this.userData = userData; // 更新组件状态
						console.log('会员续费取消成功', this.userData);
						this.isAutoRenewalCancelled = userData.data[0].cancelled;
						uni.showToast({
							title: 'Cancel Successfully',
							icon: 'success'
						});
					} else {
						console.log('会员续费取消失败', res.data);
						uni.showToast({
							title: 'Cancel Failed',
							icon: 'error'
						});
					}
				} catch (error) {
					console.error('请求发送失败', error);
					uni.showToast({
						title: 'Request Failed',
						icon: 'none'
					});
				}
			},

		},
	}
</script>

<style>
	.u-page {
		font-family: "April", sans-serif;
	}
	.member-card {
		background-color: #DAA520;
		border-radius: 15px;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
		padding: 20px;
		overflow: hidden;
	}

	.avatar-container {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.member-info-container {
		display: flex;
		flex-direction: column;
		justify-content: center;
	}

	.membership-status {
		font-size: 20px;
		color: white;
		font-weight: bold;
	}

	.expiry-date {
		font-size: 12px;
		color: white;
		margin-top: 5px;
	}

	.progress-container {
		margin-top: 10px;
	}

	.points-info {
		font-size: 14px;
		color: white;
		margin-top: 10px;
	}

	/* Different background colors for membership levels */
	.blue-background {
		background-color: #3498db;
	}

	.gold-background {
		background-color: #DAA520;
	}

	.platinum-background {
		background-color: #3d3d3c;
	}

	.diamond-background {
		background-color: #0d0d0d;
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
		color: #FFF;
		font-weight: bold;

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

	/* Primary按钮专用颜色 */
	.u-button--primary {
		background: linear-gradient(to right, rgb(196, 54, 189), rgb(74, 81, 212));
	}

	/* Warn按钮专用颜色 */
	.u-button--warn {
		background: linear-gradient(to right, rgb(255, 69, 0), rgb(255, 140, 0));
		/* 橙红色到橙色的渐变 */
	}

	.section-title {
		font-size: 18px;
		font-weight: bold;
		color: #333;
		margin-bottom: 10px;
		text-align: center;
	}

	.vip-privileges-section {
		background-color: #fff;
		padding: 20px;
		margin-top: 20px;
		border-radius: 10px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	.vip-show-section {
		background-color: #fff;
		padding: 20px;
		margin-top: 20px;
		border-radius: 10px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	.grid-text {
		text-align: center;
		display: block;
		margin-top: 5px;
	}

	.u-grid-item {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
</style>