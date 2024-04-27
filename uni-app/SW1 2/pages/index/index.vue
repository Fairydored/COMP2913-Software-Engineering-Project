<template>
	<view class="u-page">
		<!-- 消息提示 -->
		<u-modal :show="infoShow" :title="infoTitle" :content='infoContent' confirmText="log in now" cancelText="Cancel"
			:showCancelButton="true" @cancel="() => infoShow = false" @confirm="ToMePage"></u-modal>
		<!-- 顶部搜索栏 -->
		<u-row style="margin-top: 50px" justify="space-between" gutter="10">
			<u-col span="10" style="margin-left: 5px">
				<u-search placeholder="Search..." v-model="keyword" :showAction="false"></u-search>
			</u-col>
			<u-col span="2" justify="center">
				<u-image v-if="userData && userData.length > 0" :src="userData[0].avatarUrl" shape="circle" width="35" height="35" @click="ToMePage()"></u-image>
				<!-- <u--image :src="userData[0].avatarUrl" shape="circle" width="35" height="35" @click="ToMePage()"></u--image> -->
			</u-col>
		</u-row>
		<view style="margin-left: 8px; margin-top: 20px;">
			<div v-if="userData && userData.length > 0">
				<div style="font-size: 18px;">Welcome, {{ userData[0].username }}</div>
			</div>
		</view>

		<view style="margin-left: 8px; margin-top: 10px;">
			<view style="color: #918F95;">{{formattedDate}}</view>
		</view>

		<view id="hello_card">
			<u-row justify="space-between" gutter="5">
				<u-col span="3" offset="1">
					<u-row style="margin-top: 10px ">
						<u--text text="MOVE" size="20" :bold="true"></u--text>
					</u-row>
					<u-row style="margin-bottom: 10px ">
						24/300CAL
					</u-row>
					<u-row>
						<u--text text="Steps" size="20" :bold="true"></u--text>
					</u-row>
					<u-row style="margin-bottom: 10px ">
						<u-count-to :start-val="30" :end-val="500" :duration="2000" :useEasing="false"></u-count-to>
					</u-row>
					<u-row>
						<u--text text="Distance" size="20" :bold="true"></u--text>
					</u-row>
					<u-row style="margin-bottom: 10px ">
						0.69km
					</u-row>
				</u-col>
				<u-col span="7" justify="center">
					<l-circularProgress :isBgShow="true" :lineWidth="30" :boxWidth="200" :boxHeight="200"
						progressColor="rgb(66, 83, 216)" fontColor="#5677fc" gradualColor="rgb(213, 51, 186))"
						:percent="80"></l-circularProgress>
				</u-col>
			</u-row>
		</view>

		<!-- 功能选项 -->
		<view style="margin-top: 20px;margin-left: 8px;">
			<u-scroll-list indicatorColor="#fff0f0" indicatorActiveColor="#641BCD">
				<view class="scroll-list" style="flex-direction: row;">
					<view class="scroll-list__goods-item" v-for="(item, index) in list" :key="index"
						:class="[(index === 9) && 'scroll-list__goods-item--no-margin-right']">

						<image class="scroll-list__goods-item__image" :src="item.thumb"></image>
						<text class="scroll-list__goods-item__text">{{ item.name }}</text>
					</view>
					<view class="scroll-list__show-more">
						<text class="scroll-list__show-more__text">M O R E</text>
						<u-icon name="arrow-leftward" color="white" size="12"></u-icon>
					</view>
				</view>
			</u-scroll-list>
		</view>
		<u-divider text=""></u-divider>
		<view class="card-container" style="margin-top: 10px;margin-left: 8px;margin-right: 8px;">
			<u-row style="margin-bottom: 20px;">
				<u-col span="12" class="activity">
					<u-row>
						<u-col span="8">
							<view>
								<h3 style="margin-top: 10px; text-align: center;"> Good job!</h3>
								<p style="margin-top: 10px; text-align: center;"> You have finished your plan!</p>
							</view>
						</u-col>
						<u-col span="3" style="margin-top: 15px;">
							<u-icon name="checkmark-circle" color="#C4BFC6" size="90"></u-icon>
						</u-col>
					</u-row>
				</u-col>
			</u-row>
			<h4 style="margin-bottom: 10px; margin-left: 10px;"> Your plan: </h4>
			<u-row style="margin-bottom: 20px;" justify="space-between" gutter="5">
				<u-col span="4">
					<view class="activity1">
						<h5 style="margin-top: 10px; margin-left: 10px; color: aliceblue;"> Consumption：</h5>
						<u--image style="margin-top: 10px; margin-left: 35px; " src="../../static/consumption.png"
							shape="circle" width="40" height="40"></u--image>
						<p style="margin-top: 10px; text-align: center; color: aliceblue; ">2000 Cal</p>
					</view>
				</u-col>
				<u-col span="4">
					<view class="activity1">
						<h5 style="margin-top: 10px; margin-left: 10px; color: aliceblue;"> Difficulty: </h5>
						<u--image style="margin-top: 12px; margin-left: 35px; " src="../../static/difficulty.png"
							 width="40" height="40"></u--image>
						<p style="margin-top: 10px; text-align: center; color: aliceblue; ">Easy</p>

					</view>
				</u-col>
				<u-col span="4">
					<view class="activity1">
						<h5 style="margin-top: 10px; margin-left: 10px; color: aliceblue;"> Time: </h5>
						<u--image style="margin-top: 10px; margin-left: 35px; " src="../../static/time.png"
							shape="circle" width="43" height="43"></u--image>
						<p style="margin-top: 10px; text-align: center; color: aliceblue; ">1 h 30 min</p>

					</view>
				</u-col>
			</u-row>
			<u-row style="margin-bottom: 70px;">
				<u-col span="12">
					<view class="activity">

					</view>
				</u-col>
			</u-row>
		</view>
		<!-- 底部导航栏 -->
		<u-tabbar :value="pageIndex" :fixed="true" :placeholder="false" :safeAreaInsetBottom="false"
			activeColor="#4253D8">
			<u-tabbar-item text="Home" @click="Nav2Pages"></u-tabbar-item>
			<u-tabbar-item text="Exercise" @click="Nav2Pages"></u-tabbar-item>
			<u-button text="Fun!" shape="circle" color="linear-gradient(to right, rgb(66, 83, 216), rgb(213, 51, 186))"
				style="margin-top: 5px"></u-button>
			<u-tabbar-item text="Friends" @click="Nav2Pages"></u-tabbar-item>
			<u-tabbar-item text="Me" @click="Nav2Pages"></u-tabbar-item>
		</u-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				infoShow: false,
				infoTitle: "",
				infoContent: "You does not Log in now!",
				keyword: "",
				formattedDate: "",
				userData: [],
				pageIndex: 0,
				list3: [
					'https://cdn.tatlerasia.com/tatlerasia/i/2022/01/27152850-gettyimages-1284730860_cover_1500x1000.jpg',
					'https://cdn.tatlerasia.com/tatlerasia/i/2022/01/27152850-gettyimages-942145636_cover_1500x1000.jpg',
					'https://cdn.tatlerasia.com/tatlerasia/i/2022/01/27161317-gettyimages-1212328081_cover_1500x1000.jpg',
				],
				list: [{
					name: 'Fun run',
					thumb: '../../static/run1.png'
				}, {
					name: 'Co-run',
					thumb: '../../static/run2.png'
				}, {
					name: 'Positioning',
					thumb: '../../static/run3.png'
				}, {
					name: 'Slow run',
					thumb: '../../static/run4.png'
				}, {
					name: 'Pacing',
					thumb: '../../static/run5.png'
				}]
			};
		},
		mounted() {
			this.updateDate();
			setInterval(this.updateDate, 1000 * 60);
			this.getUserData();
		},
		watch: {
			progress() {
				this.progressStyle()
			}
		},
		methods: {
			async getUserData() {
				// uni.removeStorageSync('userData');
				const userDataStr = uni.getStorageSync('userData')
				if (!userDataStr) {
					this.infoShow = true; // 如果不存在历史用户数据，显示信息
					return;
				}
				// 如果存在历史用户数据，更新用户数据
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
					// 处理路由发送成功的逻辑
					if (res.data.length > 0) {
						var returnData = JSON.stringify(res.data);
						this.userData = JSON.parse(returnData);
						console.log(this.userData);
					}
				} catch (error) {
					// 处理登录失败的逻辑
					console.error('路由发送失败', error);
				}

			},
			updateDate() {
				const today = new Date();
				const months = [
					"JAN", "FEB", "MAR", "APR", "MAY", "JUN",
					"JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
				];
				const days = [
					"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY",
					"THURSDAY", "FRIDAY", "SATURDAY"
				];
				this.formattedDate = `${days[today.getDay()]}, ${months[today.getMonth()]} ${today.getDate()}`;
			},
			progressStyle() {
				// 小于0则退出
				if (this.progress < 0) {
					this.progress = 0;
					return;
				}

				//   小于100
				if (this.progress < 100) {
					this.progressBgStyle = `#397AFC 0%, #397AFC ${this.progress}%, #fff ${this.progress}%, #fff 100%`
				} else {
					// 等于或者大于100
					this.progressBgStyle = `#397AFC 0%, #397AFC 100%`
				}
			},
			ToMePage() {
				var url = '../login/login';
				const userDataStr = uni.getStorageSync('userData');
				if (userDataStr) {
					const userData = JSON.parse(userDataStr);
					const now = new Date().getTime();
					const storedTime = userData.timestamp;
					const SEVEN_DAYS_IN_MS = 7 * 24 * 60 * 60 * 1000; // 7天的毫秒数
					if (now - storedTime <= SEVEN_DAYS_IN_MS) {
						url = '../me/me';
					} else {
						// 过期删除
						uni.removeStorageSync('userData');
					}
				}
				uni.navigateTo({
					url: url,
				});
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

<style lang="scss">
	.u-page {
		font-family: "April", sans-serif;
	}

	#hello_card {
		background-color: #F7F7F7;
		margin: 10px 10px 10px 10px;
		border-radius: 10px;
	}

	.activity {
		border-radius: 20px;
		background-color: #EDDEF1;
		height: 120px;
	}

	.activity1 {
		border-radius: 20px;
		background-color: #946FB1;
		height: 120px;
	}

	.scroll-list {
		@include flex(column);

		&__goods-item {
			margin-right: 20px;

			&__image {
				width: 60px;
				height: 60px;
				border-radius: 50%;
			}

			&__text {
				color: black;
				text-align: center;
				font-size: 12px;
				margin-top: 5px;
				margin-left: 5px;
			}
		}

		&__show-more {
			background-color: #641BCD;
			border-radius: 3px;
			padding: 3px 6px;
			@include flex(column);
			align-items: center;

			&__text {
				font-size: 12px;
				width: 12px;
				color: white;
				line-height: 16px;
			}
		}
	}
</style>