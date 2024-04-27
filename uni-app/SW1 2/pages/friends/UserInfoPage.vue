<template>
	<view class="u-page">
		<u-cell :title="userInfo.name" size="large" :label="userInfo.email">
			<u-avatar :src="userInfo.url" shape="square"></u-avatar>
			<u-avatar slot="icon" shape="square" size="35" :src="userInfo.url"
				customStyle="margin: -3px 5px -3px 0"></u-avatar>
		</u-cell>
		<u-cell title="Delete this friend" @click="deleteFriend(userInfo.id)">
			<u-icon slot="icon" size="25" name="close-circle-fill" color="red"></u-icon>
		</u-cell>
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
	export default {
		data() {
			return {
				userInfo: {}
			}
		},
		onLoad(options) {
			if (options.userInfo) {
				this.userInfo = JSON.parse(options.userInfo);
			}
		},
		methods: {
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
			async deleteFriend(friendId) {
				const userDataStr = uni.getStorageSync('userData')
				const userData = JSON.parse(userDataStr);
				const data = {
					"userId": userData.data[0].id,
					"friendId": this.userInfo.id,
				}
				console.log(data);
				try {
					const res = await uni.request({
						url: 'http://waa.cool:3009/friends/delete',
						method: 'POST',
						data
					});
					console.log(res);
					if (res.data === "delete friend success") {
						uni.$u.toast('Delete friend successfully');
					} else {
						uni.$u.toast('Delete friend failed');
					}
				} catch (error) {
					// 处理登录失败的逻辑
					console.error('路由发送失败', error);
				}
			},
		}
	}
</script>

<style>
	.u-page {
		font-family: "April", sans-serif;
	}
</style>