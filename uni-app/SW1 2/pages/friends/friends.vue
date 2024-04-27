<template>
	<view class="u-page">
		<u-index-list :indexList="indexList">
			<view slot="header" class="list">
				<view class="list__item" @click="ToAddFriend()">
					<u-avatar shape="square" size="35" icon="man-add-fill" fontSize="26" bgColor="#B876DA"></u-avatar>
					<text class="list__item__user-name">New friends</text>
				</view>
				<u-line></u-line>
				<view class="list__item">
					<u-avatar shape="square" size="35" icon="tags-fill" fontSize="26" bgColor="#989FEC"></u-avatar>
					<text class="list__item__user-name">Groups</text>
				</view>
				<u-line></u-line>
				<view class="list__item">
					<u-avatar shape="square" size="35" icon="chrome-circle-fill" fontSize="26"
						bgColor="#BAA3C4"></u-avatar>
					<text class="list__item__user-name">Moments</text>
				</view>
				<u-line></u-line>
			</view>
			<template v-for="(item, index) in itemArr">
				<u-index-item :key="index">
					<u-index-anchor :text="indexList[index]" :id="index" :index="index"
						bgColor="#ECE2F0"></u-index-anchor>
					<view class="list" v-for="(item1, index1) in item" :key="index1">
						<view class="list__item" @click="showUserInfo" :data-info="item1">
							<image class="list__item__avatar" :src="item1.url"></image>
							<text class="list__item__user-name">{{item1.name}}</text>
						</view>
						<u-line></u-line>
					</view>
				</u-index-item>
			</template>
			<view slot="footer" class="u-safe-area-inset--bottom" style="margin-top: 10px; margin-left: 10px;">
				<text class="list__footer">Total {{friendList.length}} friends</text>
			</view>
		</u-index-list>
		<!-- 底部导航栏 -->
		<u-tabbar :value="pageIndex" @change="change1" :fixed="true" :placeholder="false" :safeAreaInsetBottom="false"
			activeColor="#4253D8">
			<u-tabbar-item text="Home" @click="Nav2Pages"></u-tabbar-item>
			<u-tabbar-item text="Exercise" @click="Nav2Pages"></u-tabbar-item>
			<u-button text="Fun!" shape="circle" color="linear-gradient(to right, rgb(66, 83, 216), rgb(213, 51, 186))"
				style="margin-top: 5px"></u-button>
			<u-tabbar-item text="Friends" @click="Nav2Pages"></u-tabbar-item>
			<u-tabbar-item text="me" @click="Nav2Pages"></u-tabbar-item>
		</u-tabbar>
	</view>
</template>

<script>
	import addFriendVue from './addFriend.vue';
	import UserInfoPage from './UserInfoPage.vue'

	export default {
		data() {
			return {
				pageIndex: 2,
				userData: {},
				indexList: [],
				friendList: [],
			}
		},
		computed: {
			itemArr() {
				return this.indexList.map(item => {
					const arr = [];
					for (let i = 0; i < this.friendList.length; i++) {
						if (this.friendList[i].username.charAt(0).toUpperCase() === item) {
							arr.push({
								id: this.friendList[i].id,
								name: this.friendList[i].username,
								url: this.friendList[i].avatarUrl,
								email: this.friendList[i].email,
							})
						}
					}
					return arr
				})
			}
		},
		mounted() {
			// this.getUserData();
			this.getFriends();
		},
		methods: {
			async getFriends() {
				const userDataStr = uni.getStorageSync('userData')
				const userData = JSON.parse(userDataStr);
				const data = {
					"userId": userData.data[0].id,
					// "userId": 1
				}

				try {
					const res = await uni.request({
						url: 'http://waa.cool:3009/friends/list',
						method: 'POST',
						data
					});
					if (res.data.length > 0) {
						this.friendList = res.data;
						console.log(this.friendList);
						this.generateIndexList();
					} else {
						uni.$u.toast('fail to get friends！');
					}
				} catch (error) {
					// 处理登录失败的逻辑
					console.error('路由发送失败', error);
				}
			},
			generateIndexList() {
				const indexSet = new Set();
				this.friendList.forEach(item => {
					const firstLetter = item.username.charAt(0).toUpperCase();
					indexSet.add(firstLetter);
				});

				this.indexList = Array.from(indexSet).sort();
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
			ToAddFriend() {
				uni.navigateTo({
					url: '/pages/friends/addFriend',
				});
			},
			showUserInfo(e) {
				// 在导航时传递用户信息给用户信息展示页面
				const userInfo = JSON.stringify(e.currentTarget.dataset.info);
				uni.navigateTo({
					url: '/pages/friends/UserInfoPage?userInfo=' + userInfo, // 用户信息展示页面的路径
				});
			},
		}
	}
</script>

<style lang="scss">
	.u-page {
		font-family: "April", sans-serif;
	}

	.list {

		&__item {
			@include flex;
			padding: 6px 12px;
			align-items: center;

			&__avatar {
				height: 35px;
				width: 35px;
				border-radius: 3px;
			}

			&__user-name {
				font-size: 16px;
				margin-left: 10px;
				color: $u-main-color;
			}
		}

		&__footer {
			color: $u-tips-color;
			font-size: 14px;
			text-align: center;
			margin: 15px 0;
		}
	}
</style>