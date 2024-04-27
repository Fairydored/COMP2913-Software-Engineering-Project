<template>
	<view>
		<view class="u-page">
			<!-- top navigator -->
			<u-navbar leftText="返回" title="开始运动" :safeAreaInsetTop="false" :autoBack="true">
				<view class="u-nav-slot" slot="left">
					<u-icon name="arrow-left" size="19"></u-icon>
				</view>
			</u-navbar>
			<!-- map -->
			<iframe src="../../hybrid/html/test.html"></iframe>
			<!-- <view id="map"></view> -->
			<!-- <web-view src="../../hybrid/html/test.html"></web-view> -->
			<!-- sports nav -->
			<u-sticky bgColor="#fff">
				<u-tabs :list="sports_list" lineColor="#641BCD"></u-tabs>
			</u-sticky>
			<!-- test -->
			<u-grid :border="true" :customStyle="{marginTop:30+'px'}">
				<u-grid-item @click="showPath()">
					<view class="count-down__grid-item">
						<u--image :showLoading="true" src="../../static/path.png" width="20px" height="20px"></u--image>
						<text class="count-down__grid-item__grid-text">路线</text>
					</view>
				</u-grid-item>
				<u-grid-item @click="start">
					<view class="count-down__grid-item">
						<view class="count-down__grid-item__circle">
							<u-icon color="#fff" name="play-right-fill" :size="22"></u-icon>
						</view>
						<text class="count-down__grid-item__grid-text">开始</text>
					</view>
				</u-grid-item>
				<u-grid-item @click="startAnimation()">
					<view class="count-down__grid-item">
						<u-icon name="pause-circle" :size="22"></u-icon>
						<text class="count-down__grid-item__grid-text">动画</text>
					</view>
				</u-grid-item>
			</u-grid>
			<!-- 底部导航栏 -->
			<u-tabbar :value="pageIndex" @change="change" :fixed="true" :placeholder="false"
				:safeAreaInsetBottom="false" activeColor="#4253D8">
				<u-tabbar-item text="首页" @click="Nav2Pages"></u-tabbar-item>
				<u-tabbar-item text="运动" @click="Nav2Pages"></u-tabbar-item>
				<u-button text="趣味健身" shape="circle"
					color="linear-gradient(to right, rgb(66, 83, 216), rgb(213, 51, 186))"
					style="margin-top: 5px"></u-button>
				<u-tabbar-item text="订阅" @click="Nav2Pages"></u-tabbar-item>
				<u-tabbar-item text="我的" @click="Nav2Pages"></u-tabbar-item>
			</u-tabbar>
		</view>

	</view>
</template>

<script>
	import AMapLoader from "@amap/amap-jsapi-loader";
	export default {
		name: "map-view",
		data() {
			return {
				pageIndex: 1,
				map: null,
				polyline: null,
				passedPolyline: null,
				marker: null,
				pathVisible: false,
				lineArr: [
					[104.06180355807209, 30.64236326473902],
					[104.10389541075799, 30.639489379227957],
					[104.0582860219486, 30.658582821336747],
					[104.10643719804897, 30.67475157089868],
					[103.94109344430673, 30.579172406289633],
					[103.98773185239645, 30.66070204196852],
					[103.9786574177346, 30.59590693461704],
					[104.10603081738167, 30.6637809317031],
					[104.02537614683423, 30.630417359291442],
					[104.0826365970918, 30.591505161703285]
				],
				sports_list: [{
					name: '目标跑',
				}, {
					name: '自由跑',
				}, {
					name: '轻松跑'
				}, {
					name: '燃脂'
				}]
			};
		},
		mounted() {
			this.initAMap();
		},
		unmounted() {
			this.map?.destroy();
		},
		methods: {
			click(item) {
				this.curIndex = item.index;
			},
			Nav2Pages(e) {
				if (e === 1) {
					uni.navigateTo({
						url: '../sports/sports'
					});
				}
				if (e === 0) {
					uni.navigateTo({
						url: '../index/index'
					});
				}
			},
			initAMap() {
				AMapLoader.load({
						key: "07a8cceed8195d4bdfa88bd551ea52b3", // 申请好的Web端开发者Key，首次调用 load 时必填
						version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
						plugins: ["AMap.Scale", "AMap.ToolBar", "AMap.MoveAnimation"], //需要使用的的插件列表
					})
					.then((AMap) => {
						this.map = new AMap.Map("map", {
							viewMode: "2D", // 是否为3D地图模式
							zoom: 11, // 初始化地图级别
							center: [104.06180355807209, 30.64236326473902], // 初始化地图中心点位置
						});
					})
					.catch((e) => {
						console.log(e);
					});
			},
			showPath() {
				const start_icon = new AMap.Icon({
					size: new AMap.Size(40, 40), //图标尺寸
					image: "../../static/start_point.png", //Icon 的图像
					imageSize: new AMap.Size(50, 50), //根据所设置的大小拉伸或压缩图片
				});
				const end_icon = new AMap.Icon({
					size: new AMap.Size(40, 40),
					image: "../../static/end_point.png",
					imageSize: new AMap.Size(50, 50),
				});

				var start_marker = new AMap.Marker({
					map: this.map,
					icon: start_icon,
					position: this.lineArr[0],
					offset: new AMap.Pixel(-23, -40)
				});

				var end_marker = new AMap.Marker({
					map: this.map,
					icon: end_icon,
					position: this.lineArr[this.lineArr.length - 1],
					offset: new AMap.Pixel(-26, -40)
				});

				this.polyline = new AMap.Polyline({
					map: this.map,
					path: this.lineArr,
					showDir: true,
					strokeColor: "#28F", //线颜色
					strokeWeight: 6, //线宽
				});
			},
			startAnimation() {
				this.map.setFitView();
				const people_icon = new AMap.Icon({
					size: new AMap.Size(40, 40),
					image: "../../static/runner.png",
					imageSize: new AMap.Size(50, 50),
				});
				this.marker = new AMap.Marker({
					map: this.map,
					icon: people_icon,
					position: this.lineArr[0],
					offset: new AMap.Pixel(-23, -40)
				});
				this.marker.moveAlong(this.lineArr, {
					// 每一段的时长
					duration: 500, //可根据实际采集时间间隔设置
					// JSAPI2.0 是否延道路自动设置角度在 moveAlong 里设置
					autoRotation: true,
				});
			}
		}
	}
</script>

<style lang="scss">
	::v-deep .amap-logo {
		display: none;
		opacity: 0 !important;
	}

	::v-deep .amap-copyright {
		opacity: 0;
	}

	.u-page {
		font-family: '黑体', sans-serif;
	}

	#map {
		width: 100%;
		height: 450px;
		// background-color: crimson;
	}

	.count-down {
		&__grid-item {
			width: 70px;
			height: 70px;
			@include flex;
			justify-content: center;
			align-items: center;

			&__circle {
				width: 32px;
				height: 32px;
				border-radius: 32px;
				background-color: $u-primary;
				/* #ifndef APP-NVUE */
				display: flex;
				/* #endif */
				justify-content: center;
				align-items: center;
				box-shadow: 1px 1px 4px rgba(155, 191, 255, .7);
			}

			&__grid-text {
				font-size: 14px;
				color: #909399;
				/* #ifndef APP-PLUS */
				box-sizing: border-box;
				/* #endif */
				margin-left: 6px;
			}
		}
	}
</style>