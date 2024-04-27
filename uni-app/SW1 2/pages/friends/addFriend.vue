<template>
	<view class="u-page">
		<u-row customStyle="margin-top: 10px" justify="center">
			<u-col span="11">
				<u-search id="top_search_bar" placeholder="Search username" :showAction="true" actionText="Search"
					v-model="keyword" @search="getSearchResult()"></u-search>
			</u-col>
		</u-row>
		<u-divider text="分割线" :dot="true"></u-divider>
		<u-row customStyle="margin-top: 10px" justify="center">
			<u-col span="12">
				<u-list @scrolltolower="scrolltolower">
					<u-list-item v-for="(item, index) in indexList" :key="index">
						<u-cell :title="item.username" isLink rightIcon="man-add-fill" value="Add" @click="addFriend(item.id)">
							<u-avatar :src="item.avatarUrl" shape="square"></u-avatar>
							<u-avatar slot="icon" shape="square" size="35" :src="item.avatarUrl"
								customStyle="margin: -3px 5px -3px 0"></u-avatar>
						</u-cell>
					</u-list-item>
				</u-list>
			</u-col>
		</u-row>
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
	export default {
		data() {
			return {
				keyword: "",
				indexList: [],
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
			async getSearchResult() {
				const data = {
					"username": this.keyword
				}

				try {
					const res = await uni.request({
						url: 'http://waa.cool:3009/users/search',
						method: 'POST',
						data
					});
					if (res.data.length > 0) {
						console.log(res.data);
						this.indexList = res.data;

					} else {
						uni.$u.toast('User does not exist!');
					}
				} catch (error) {
					// 处理登录失败的逻辑
					console.error('路由发送失败', error);
				}
			},
			async addFriend(friendId) {
				const userDataStr = uni.getStorageSync('userData')
				const userData = JSON.parse(userDataStr);
				const data = {
					"userId": userData.data[0].id,
					"friendId": friendId,
				}
				console.log(data);
				try {
					const res = await uni.request({
						url: 'http://waa.cool:3009/friends/add',
						method: 'POST',
						data
					});
					console.log(res);
					if (res.data === "add friend success") {
						uni.$u.toast('Add friend successfully');
					} else {
						uni.$u.toast('Add friend failed');
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