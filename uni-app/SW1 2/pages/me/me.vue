<template>
	<view class="u-page">
		<view class="me-page">
			<u-row>
				<u-col :span="11"></u-col>
				<u-col :span="1">
					<u-icon name="setting-fill" color="#595959" size="25"></u-icon>
				</u-col>
			</u-row>
			<view class="avatar-section">
				<u-avatar size="100" :src="avatarUrl" />
				<view class="user-info">
					<view class="user-name-with-badge">
						<text class="user-name">{{ username }}</text>
						<view class="membership-badge" v-if="isMember">
							<!-- 根据会员等级显示不同的图标和文字 -->
							<svg v-if="membershipLevel === 'level1'" t="1713026811950" class="membership-icon"
								viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3883"
								width="200" height="200">
								<path
									d="M60.235294 361.411765l744.809412 425.622588A30.117647 30.117647 0 0 1 790.136471 843.294118H173.658353a60.235294 60.235294 0 0 1-59.783529-52.766118L60.235294 361.411765z"
									fill="#F5C164" p-id="3884"></path>
								<path
									d="M963.764706 361.411765l-53.63953 429.116235A60.235294 60.235294 0 0 1 850.341647 843.294118H233.893647a30.117647 30.117647 0 0 1-14.968471-56.259765L963.764706 361.411765z"
									fill="#F5C164" p-id="3885"></path>
								<path
									d="M512 240.941176l331.053176 509.289412A60.235294 60.235294 0 0 1 792.545882 843.294118H231.454118a60.235294 60.235294 0 0 1-50.507294-93.06353L512 240.941176z"
									fill="#FFE09E" p-id="3886"></path>
								<path
									d="M512 240.941176l331.053176 509.289412A60.235294 60.235294 0 0 1 792.545882 843.294118H512V240.941176z"
									fill="#F9CF7E" p-id="3887"></path>
								<path
									d="M512 210.823529m-60.235294 0a60.235294 60.235294 0 1 0 120.470588 0 60.235294 60.235294 0 1 0-120.470588 0Z"
									fill="#FFE19F" p-id="3888"></path>
								<path
									d="M963.764706 331.294118m-60.235294 0a60.235294 60.235294 0 1 0 120.470588 0 60.235294 60.235294 0 1 0-120.470588 0Z"
									fill="#FFE19F" p-id="3889"></path>
								<path
									d="M60.235294 331.294118m-60.235294 0a60.235294 60.235294 0 1 0 120.470588 0 60.235294 60.235294 0 1 0-120.470588 0Z"
									fill="#FFE19F" p-id="3890"></path>
							</svg>
							<svg v-else-if="membershipLevel === 'level2'" t="1713026836288" class="membership-icon"
								viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4051"
								width="200" height="200">
								<path
									d="M663.864 147.333H362.136c-34.421 0-67.432 13.998-91.773 38.916l-207.86 212.78c-19.338 19.798-19.338 51.893 0 71.691l404.102 413.673c25.623 26.229 67.165 26.229 92.788 0L963.495 470.72c19.338-19.798 19.338-51.893 0-71.691l-207.862-212.78c-24.337-24.918-57.348-38.916-91.769-38.916z"
									fill="#FFA820" p-id="4052"></path>
								<path
									d="M62.504 399.028c-19.338 19.798-19.338 51.893 0 71.691l8.667 8.873c2.085-38.628 9.355-75.876 21.127-111.063l-29.794 30.499zM963.496 399.028L861.313 294.427c34.885 61.489 54.811 132.578 54.811 208.323 0 5.627-0.113 11.228-0.33 16.802l47.704-48.833c19.337-19.798 19.337-51.893-0.002-71.691z"
									fill="#FEAC33" p-id="4053"></path>
								<path
									d="M160.006 299.218l-67.708 69.311c-11.772 35.187-19.041 72.435-21.127 111.063l41.377 42.357a368.9 368.9 0 0 1-2.075-39.073c0-66.966 18.046-129.715 49.533-183.658zM861.313 294.427L755.635 186.248c-24.339-24.917-57.35-38.916-91.771-38.916h-45.019C749 203.074 840.189 332.323 840.189 482.875c0 51.345-10.611 100.209-29.752 144.528l105.356-107.851c0.218-5.574 0.33-11.175 0.33-16.802 0-75.746-19.925-146.835-54.81-208.323zM427.792 844.659l38.814 39.733c25.623 26.229 67.165 26.229 92.788 0l68.278-69.895c-46.362 21.333-97.961 33.236-152.341 33.236a368.23 368.23 0 0 1-47.539-3.074z"
									fill="#FEB133" p-id="4054"></path>
								<path
									d="M618.845 147.333H362.136c-34.421 0-67.432 13.998-91.773 38.916l-110.358 112.97c-31.487 53.943-49.533 116.692-49.533 183.657 0 13.2 0.708 26.235 2.075 39.073l64.293 65.816c-16.982-38.122-26.436-80.336-26.436-124.763 0-169.51 137.415-306.925 306.925-306.925s306.925 137.415 306.925 306.925S626.839 769.926 457.33 769.926c-46.985 0-91.495-10.573-131.306-29.445L427.792 844.66a368.23 368.23 0 0 0 47.539 3.074c54.38 0 105.979-11.903 152.341-33.236l182.765-187.094c19.14-44.319 29.752-93.184 29.752-144.528 0-150.553-91.189-279.802-221.344-335.543z"
									fill="#FEB633" p-id="4055"></path>
								<path
									d="M764.254 463.001c0-169.51-137.415-306.925-306.925-306.925S150.405 293.491 150.405 463.001c0 44.427 9.453 86.642 26.436 124.763L326.024 740.48c39.81 18.872 84.321 29.445 131.306 29.445 169.509 0.001 306.924-137.414 306.924-306.924z m-573.917-19.875c0-137.514 111.477-248.991 248.991-248.991S688.32 305.612 688.32 443.126 576.842 692.118 439.328 692.118 190.337 580.641 190.337 443.126z"
									fill="#FFBC34" p-id="4056"></path>
								<path
									d="M688.32 443.126c0-137.514-111.477-248.991-248.991-248.991S190.337 305.612 190.337 443.126s111.477 248.991 248.991 248.991S688.32 580.641 688.32 443.126zM421.327 614.31c-105.518 0-191.058-85.54-191.058-191.058s85.54-191.058 191.058-191.058 191.058 85.54 191.058 191.058S526.846 614.31 421.327 614.31z"
									fill="#FFC134" p-id="4057"></path>
								<path
									d="M421.327 232.194c-105.518 0-191.058 85.54-191.058 191.058s85.54 191.058 191.058 191.058 191.058-85.54 191.058-191.058-85.539-191.058-191.058-191.058z m-18.001 304.308c-73.523 0-133.125-59.602-133.125-133.125s59.602-133.125 133.125-133.125 133.125 59.602 133.125 133.125-59.602 133.125-133.125 133.125z"
									fill="#FFC634" p-id="4058"></path>
								<path
									d="M403.326 403.378m-133.125 0a133.125 133.125 0 1 0 266.25 0 133.125 133.125 0 1 0-266.25 0Z"
									fill="#FFCB34" p-id="4059"></path>
								<path
									d="M663.864 165.333c14.702 0 29.048 2.922 42.639 8.686 13.62 5.775 25.818 14.122 36.256 24.808L950.62 411.606c12.532 12.83 12.532 33.706 0.001 46.535L546.518 871.814c-8.977 9.189-20.881 14.25-33.518 14.25-12.638 0-24.542-5.061-33.518-14.25L75.38 458.141c-12.532-12.83-12.532-33.706-0.001-46.535l207.86-212.78c10.439-10.686 22.637-19.033 36.257-24.808 13.591-5.763 27.937-8.686 42.639-8.686h301.729m0-17.999H362.136c-34.421 0-67.432 13.998-91.772 38.915l-207.86 212.78c-19.338 19.798-19.338 51.893 0 71.691l404.102 413.673c12.811 13.115 29.603 19.672 46.394 19.672s33.583-6.557 46.394-19.672l404.102-413.673c19.338-19.798 19.338-51.893 0-71.691l-207.862-212.78c-24.338-24.917-57.349-38.915-91.77-38.915z"
									fill="#FFA820" p-id="4060"></path>
								<path
									d="M585.506 299.37H440.494c-16.543 0-32.407 6.686-44.106 18.584L296.49 419.583c-9.294 9.454-9.294 24.783 0 34.237l194.213 197.576a31.154 31.154 0 0 0 44.593 0L729.509 453.82c9.294-9.454 9.294-24.783 0-34.237l-99.896-101.629c-11.698-11.898-27.564-18.584-44.107-18.584z"
									fill="#FFE3B4" p-id="4061"></path>
								<path
									d="M222.012 346.805a17.94 17.94 0 0 1-12.677-5.222c-7.057-7.001-7.102-18.398-0.101-25.456l87.419-88.112c7.002-7.057 18.398-7.102 25.456-0.1 7.057 7.001 7.102 18.398 0.101 25.456l-87.419 88.112a17.945 17.945 0 0 1-12.779 5.322zM172.371 396.84a17.94 17.94 0 0 1-12.677-5.222c-7.058-7.001-7.103-18.398-0.101-25.456l7.428-7.487c7.002-7.058 18.399-7.103 25.456-0.101 7.058 7.001 7.103 18.398 0.101 25.456l-7.428 7.487a17.946 17.946 0 0 1-12.779 5.323z"
									fill="#FFFFFF" p-id="4062"></path>
							</svg>
							<text class="membership-level">{{ membershipLevel }}</text>
						</view>
					</view>
					<text class="user-email">{{ email }}</text>
				</view>
			</view>
			<!-- Statistics -->
			<view class="stats-section">
				<view class="stat">
					<text class="stat-desc">Total Exercise Time</text>
					<text class="stat-number">{{ totalExerciseMinutes }} Minutes</text>
				</view>
				<view class="divider"></view>
				<view class="stat">
					<text class="stat-desc">Exercising Days</text>
					<text class="stat-number">{{ exerciseDays }} Days</text>
				</view>
				<view class="divider"></view>
				<view class="stat">
					<text class="stat-desc">Total Running Time</text>
					<text class="stat-number">{{ totalRunningMinutes }} Minutes</text>
				</view>
			</view>

			<u-cell-group>
				<u-cell :title="memberTitle" :value="memberValue" :label="memberLabel" icon="calendar" :isLink="true"
					:url="isMember ? '/pages/me/member-center' : '/pages/me/subscribe'" :border="false">
				</u-cell>
				<u-cell title="My Orders" icon="rmb-circle" :isLink="true" :border="false"></u-cell>
				<u-cell title="My Activities" icon="bookmark" :isLink="true" :border="false"></u-cell>
				<u-cell title="Edit Information" icon="edit-pen" :isLink="true" :border="false"></u-cell>
				<u-cell title="Help Center" icon="kefu-ermai" :isLink="true" :border="false"></u-cell>
			</u-cell-group>

			<!-- Bottom Navigation Bar -->
			<u-tabbar :value="pageIndex" @change="change1" :fixed="true" :placeholder="false"
				:safeAreaInsetBottom="false" activeColor="#4253D8">
				<u-tabbar-item text="Home" @click="Nav2Pages"></u-tabbar-item>
				<u-tabbar-item text="Exercise" @click="Nav2Pages"></u-tabbar-item>
				<u-button text="Fun !" shape="circle"
					color="linear-gradient(to right, rgb(66, 83, 216), rgb(213, 51, 186))"
					style="margin-top: 5px"></u-button>
				<u-tabbar-item text="Friends" @click="Nav2Pages"></u-tabbar-item>
				<u-tabbar-item text="Me" @click="Nav2Pages"></u-tabbar-item>
			</u-tabbar>

		</view>
	</view>
</template>



<script>
	export default {
		data() {
			return {
				pageIndex: 3,
				showModal: false,
				username: 'Username', // 在用户数据加载后更新
				email: 'xxx@xx.com',
				userId: '',
				totalExerciseMinutes: '0',
				exerciseDays: '0',
				totalRunningMinutes: '0',
				membershipLevel: '',
				isMember: false,
				avatarUrl: '../../static/default-avatar.png', // 默认头像URL占位符
				isCancelled: false,
			}
		},
		mounted() {
			this.getUserData();
		},
		computed: {
			memberTitle() {
				return this.isMember ? `Honored ${this.membershipLevel} Member` : 'VIP Subscription';
			},
			memberValue() {
				return this.isMember ? 'Member Center' : 'Click to Subscribe';
			},
			memberLabel() {
				if (!this.isMember) {
					return 'Subscribe now for exclusive benefits!';
				}
				if (!this.expirationDate) {
					return 'Loading membership details...';
				}
				const today = new Date();
				const expiration = new Date(this.expirationDate);
				if (today > expiration) {
					return 'Membership expired. Click to renew.';
				}
				const timeDiff = expiration - today;
				const daysRemaining = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
				return this.isCancelled ?
					`Your membership expires in ${daysRemaining} day(s).` : // 如果会员被取消，显示剩余天数
					` Automatic renewal upon expiration`; // 如果没有取消，显示自动续订信息
			}
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
						this.isCancelled = this.userData.cancelled;
						this.isMember = ['level1', 'level2'].includes(this.userData.membershipLevel);
					}
				} catch (error) {
					console.error('Error logging in:', error);
					this.infoShow = true; // 可能需要显示错误信息
				}
			},
			Nav2Pages(e) {
				if (e === 3) {
					uni.navigateTo({
						url: '../me/me'
					});
				}
				if (e === 2) {
					uni.navigateTo({
						url: '../friends/friends'
					});
				}
				if (e === 1) {
					uni.navigateTo({
						url: '../sports2/sports2'
					});
				}
				if (e === 0) {
					uni.navigateTo({
						url: '../index/index'
					});
				}
			},

		}
	};
</script>

<style>
	.u-page {
		font-family: "April", sans-serif;
	}

	.me-page {
		display: flex;
		flex-direction: column;
		/*  align-items: center; */
		min-height: 100vh;
		padding: 20px 0;
		background-color: #f5f5f5;
		/*  background: linear-gradient(to right, rgb(196, 54, 189), rgb(74, 81, 212)); */
	}

	/* 头像 */
	.avatar-section {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-bottom: 30px;
	}

	.avatar {
		width: 120px;
		height: 120px;
		border-radius: 60px;
		margin-bottom: 10px;
	}

	/* 用户信息 */
	.user-info {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.user-name {
		font-size: 18px;
		font-weight: bold;
		color: #333;
	}

	.user-email {
		font-size: 14px;
		color: #666;
	}

	/* 用户状态 */
	.stats-section {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		background-color: #fff;
		padding: 10px;
		margin-top: 20px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	.stat {
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 8px 8px;
		min-width: 25vw;
	}

	.stat-desc {
		font-size: 14px;
		color: #666;
	}



	.stat-number {
		font-size: 16px;
		font-weight: bold;
		color: #333;
	}

	.divider {
		height: 40px;
		width: 1px;
		background-color: #ddd;
	}

	/*ucell风格 */
	.u-cell-group {
		margin-top: 20px;
		background-color: #fff;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	.u-cell title {
		font-size: 16px;
		font-weight: bold;
		color: #333;
	}

	.u-cell label {
		font-size: 14px;
		color: #666;
	}

	/* 链接和图标的样式 */
	.u-cell icon {
		color: #595959;
	}

	.u-cell value {
		color: #4253D8;
		font-weight: bold;
	}

	.user-info {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 100%;
	}

	.user-name-with-badge {
		display: flex;
		justify-content: center;
		position: relative;
		width: 100%;
	}

	.user-name {
		font-size: 18px;
		font-weight: bold;
		color: #333;
		display: block;
		/* 使名称成为块级元素，以便自己独立居中 */
		z-index: 1;
	}

	.membership-badge {
		position: absolute;
		left: 50%;
		transform: translateX(calc(100% + 1px));
		display: flex;
		align-items: center;
		background-image: linear-gradient(to bottom, #FFFF99, #FFD700);
		border-radius: 10px;
		padding: 2px 4px;
		z-index: 0;
		/* 确保徽章在名称文本之下 */
	}

	.membership-icon {
		width: 16px;
		height: 16px;
	}

	.membership-level {
		margin-left: 4px;
		font-size: 12px;
		font-weight: bold;
		/* 字体加粗 */
		color: #FF8C00;
	}
</style>