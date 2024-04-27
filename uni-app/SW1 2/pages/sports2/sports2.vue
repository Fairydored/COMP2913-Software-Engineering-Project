<template>
	<view class="u-page">
		<u-navbar leftText="返回" title="Exercise" :safeAreaInsetTop="false" :autoBack="true">
			<view class="u-nav-slot" slot="left">
				<u-icon name="arrow-left" size="19"></u-icon>
			</view>
		</u-navbar>
		<web-view ref="webview" src="../../hybrid/html/current_location.html"></web-view>
		<!-- sports nav -->
		<u-sticky bgColor="#fff" style="margin-top: 500px;">
			<u-tabs :list="sports_list" lineColor="#641BCD"></u-tabs>
		</u-sticky>
		<!-- test -->
		<u-grid :border="true" :customStyle="{marginTop:20+'px'}">
			<u-grid-item @click="showPath()">
				<view class="count-down__grid-item">
					<u--image :showLoading="true" src="../../static/path.png" width="20px" height="20px"></u--image>
					<text class="count-down__grid-item__grid-text">Route</text>
				</view>
			</u-grid-item>
			<u-grid-item @click="toRunPage()">
				<view class="count-down__grid-item">
					<view class="count-down__grid-item__circle">
						<u-icon color="#fff" name="play-right-fill" :size="22"></u-icon>
					</view>
					<text class="count-down__grid-item__grid-text">GO!</text>
				</view>
			</u-grid-item>
			<u-grid-item @click="startAnimation()">
				<view class="count-down__grid-item">
					<u-icon name="pause-circle" :size="22"></u-icon>
					<text class="count-down__grid-item__grid-text">Show</text>
				</view>
			</u-grid-item>
		</u-grid>

		<!-- 底部导航栏 -->
		<u-tabbar :value="pageIndex" @change="change1" :fixed="true" :placeholder="false" :safeAreaInsetBottom="false"
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
	var wv; //计划创建的webview
	export default {
		onReady() {
			// #ifdef APP-PLUS
			var currentWebview = this.$scope
				.$getAppWebview() //此对象相当于html5plus里的plus.webview.currentWebview()。在uni-app里vue页面直接使用plus.webview.currentWebview()无效
			setTimeout(function() {
				wv = currentWebview.children()[0]
				wv.setStyle({
					top: 45,
					height: 520
				})
				wv.setStyle({
					scalable: true
				})
			}, 1000); //如果是页面初始化调用时，需要延时一下
			// #endif
		},
		data() {
			return {
				pageIndex: 1,
				sports_list: [{
					name: 'Running',
				}, {
					name: 'Jogging',
				}, {
					name: 'Walking'
				}]
			};
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
			toRunPage() {
				uni.navigateTo({
					url: 'running'
				});
			},
			showPath() {
				setTimeout(function() {
					wv.evalJS("showPath()");
				}, 1000);
			}
		}
	};
</script>

<style lang="scss">
	.u-page {
		font-family: "April", sans-serif;
	}
</style>