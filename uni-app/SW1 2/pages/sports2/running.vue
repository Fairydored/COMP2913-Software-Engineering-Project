<template>
	<view v-if="countdown > 0" class="black-screen">
		<view class="countdown">{{ countdown }}</view>
	</view>
	<view v-else>
		<view class="u-page">
			<u-modal :show="confirmUpload" title="Confirm uploading path" content="Are you sure to finish running?" showCancelButton="true" confirmText="Finish running" cancelText="Cancel" @confirm="confirm()"></u-modal>
			<iframe id='map' ref="webview" src="hybrid/html/running.html"></iframe>
			<view class="information" :style="{ height: dynamicHeight }" @touchstart="startDrag" @touchmove="onDrag"
				@touchend="stopDrag" @mousedown="startDrag" @mousemove="onDrag" @mouseup="stopDrag" ref="information">
				<view class="button-item" :style="{width:'100vw', backgroundColor: 'rgb(70,64,76)'}">
					<u-icon name='minus' size=100px></u-icon>
				</view>
				<view class="info-item" v-for="(item, index) in infoItems" :key="index"
					:style="{ width: infoItemWidth ,backgroundColor: item.bgColor}" @click="handleClick(index)">
					<view class="label">{{ item.label }}</view>
					<view class="value">{{ item.value }}</view>
				</view>
			</view>
		</view>
	</view>
</template>


<script>
	export default {
		name: "map-view",
		data() {
			return {
				confirmUpload:false,
				countdown: 3,
				isDragging: false,
				isPause: false,
				isPhone: false,
				isExtend: false,
				isButton: false,
				startY: 0,
				path: [],
				speed: 0.00,
				dynamicHeight: '17vh',
				infoItemWidth: '33%',
				infoItems: [{
						label: 'Distance',
						value: '0.00',
						bgColor: 'transparent'
					},
					{
						label: 'Time',
						value: '00:00:00',
						bgColor: 'transparent'
					},
					{
						label: 'Speed',
						value: "0'00\"",
						bgColor: 'transparent'
					}
				]
			};
		},
		mounted() {
			const countdownInterval = setInterval(() => {
				if (this.countdown > 0) {
					this.countdown--;
				} else {
					// this.startTiming();
				}
			}, 1000);
			// 接受iframe中的信息
			// 格式为 [Object] {"speed":0,"time":14,"path":[[-122.084,37.421998],[-122.084,37.421998],[-122.084,37.421998]...}
			window.receiveRenderData = this.receiveRenderData
		},
		methods: {
			handleClick(index) {
				if (index === this.infoItems.length - 2 && !this.isPause) {
					this.pause();
				}
				else if (index === this.infoItems.length - 1 && !this.isPause) {
					// This is the last item, handle the click event
					// this.uploadPath();
					this.confirmUpload = true;
				}
				else if (index === this.infoItems.length - 1 && this.isPause) {
					// This is the last item, handle the click event
					console.log('Last item clicked');
					this.pause();
				}
			},
			confirm(){
				this.uploadPath();
				uni.navigateTo({
					url:'/pages/sports2/sports2'
				})
			},
			receiveRenderData(e) {
				//接收的值
				// console.log(e, '-father-event');
				this.infoItems[0].value = (e.distance / 1000).toFixed(2);
				this.infoItems[1].value = this.formatTime(e.time);
				//计算配速
				// if (e.speed === 0) {
				// 	this.infoItems[2].value = "0'00\"";
				// } else {
				// 	this.speed = e.speed;
				// 	const paceSeconds = (60 /1000) * e.speed;
				// 	const paceMinutes = Math.floor(paceSeconds / 60);
				// 	const paceSecondsRemainder = Math.round(paceSeconds % 60);
				// 	this.infoItems[2].value = `${paceMinutes}'${this.padZero(paceSecondsRemainder)}''`;
				// }
				this.infoItems[2].value = e.speed.toFixed(2);
			},
			formatTime(seconds) {
				const hours = Math.floor(seconds / 3600);
				const minutes = Math.floor((seconds % 3600) / 60);
				const remainingSeconds = seconds % 60;
				return `${this.padZero(hours)}:${this.padZero(minutes)}:${this.padZero(remainingSeconds)}`;
			},
			padZero(value) {
				return value < 10 ? `0${value}` : value;
			},
			async uploadPath() {
				// 获取本地存储用户信息
				const userDataStr = uni.getStorageSync('userData')
				if (!userDataStr) {
					this.infoShow = true; // 如果不存在历史用户数据，显示信息
					return;
				}
				const userData = JSON.parse(userDataStr);
				// 包装路径信息
				const routeData = {
					"type": "Feature",
					"geometry": {
						"type": "LineString",
						"coordinates": this.path
					},
				};
				// 路径信息的形式和文档中有差别
				const routeDataStr = JSON.stringify(routeData);
				// 生成路径id
				const timestamp = Date.now(); // 获取当前时间戳，单位为毫秒
				const pathName = `${timestamp}_${userData.data[0].id}`;

				// 准备上传数据
				const data = {
					"userId": userData.data[0].id,
					"routeName": pathName,
					"routeData": routeDataStr,
					"averagePace": this.speed,
					"activityType": "慢跑"
				};
				console.log(data);
				try {
					const res = await uni.request({
						url: 'http://waa.cool:3009/gps/upload',
						method: 'POST',
						data
					});
					// 处理路由发送成功的逻辑
					if (res.data === 'submission success') {
						uni.$u.toast('submission success!');
					} else {
						uni.$u.toast('submission failed.');
					}
				} catch (error) {
					// 处理登录失败的逻辑
					console.error('路由发送失败', error);
				}
			},
			pause() {
				if (this.isPause == false) {
					this.infoItems = [{
							label: 'Distance',
							value: '0.00'
						},
						{
							label: 'Time',
							value: '00:00'
						},
						{
							label: 'Speed',
							value: "0'00\""
						},
						{
							label: 'Consumption',
							value: "0"
						},
						{
							label: '',
							value: ">",
							button: true,
							bgColor: 'green'
						},
					];
					this.isPause = true;
				} else {
					this.infoItems = [{
							label: 'Distance',
							value: '0.00'
						},
						{
							label: 'Time',
							value: '00:00'
						},
						{
							label: 'Speed',
							value: "0'00\""
						},
						{
							label: 'Heart rate',
							value: "0 ❤"
						},
						{
							label: '',
							value: "||",
							button: true
						},
						{
							label: '',
							value: "✓",
							button: true,
							bgColor: 'blue'
						},
					];
					this.isPause = false;
				}

			},
			startDrag(event) {
				this.isDragging = true;
				// const subNVue = uni.getSubNVueById('popup');
				let targetElement = this.$refs.information;
				// 区分触摸事件和鼠标事件
				if (event.type.startsWith('touch')) {
					targetElement.addEventListener('touchmove', this.onDrag);
					targetElement.addEventListener('touchend', this.stopDrag);
					this.isPhone = true;
				} else {
					document.addEventListener('mousemove', this.onDrag);
					document.addEventListener('mouseup', this.stopDrag);
					this.isPhone = false;
				}
				this.startY = event.type.startsWith('touch') ? event.touches[0].clientY : event.clientY;
			},
			onDrag(event) {
				if (!this.isDragging) return;
				const clientY = event.type.startsWith('touch') ? event.touches[0].clientY : event.clientY;


				// 根据鼠标指针的位置设置盒子的高度
				if (Math.abs(this.startY - clientY) > 50) {
					this.isButton = true;
					if (this.isExtend == false) {

						this.dynamicHeight = '90vh';
						this.infoItemWidth = '50%';
						if (this.isPause == false) {
							this.infoItems = [{
									label: 'Dsitance',
									value: '0.00'
								},
								{
									label: 'Time',
									value: '00:00'
								},
								{
									label: 'Speed',
									value: "0'00\""
								},
								{
									label: 'Heart rate',
									value: "0 ❤"
								},
								{
									label: '',
									value: "||",
									button: true
								}, {
									label: '',
									value: "✓",
									button: true,
									bgColor: 'blue'
								}
							]
						} else {
							this.infoItems = [{
									label: 'Distance',
									value: '0.00'
								},
								{
									label: 'Time',
									value: '00:00'
								},
								{
									label: 'Speed',
									value: "0'00\""
								},
								{
									label: 'Consumption',
									value: "0"
								},
								{
									label: '',
									value: ">",
									button: true,
									bgColor: 'green'
								}
							]
						}
					} else {
						this.isButton = false;
						this.dynamicHeight = '17vh';
						this.infoItemWidth = '33%';
						this.infoItems = [{
								label: 'Distance',
								value: '0.00'
							},
							{
								label: 'Time',
								value: '00:00'
							},
							{
								label: 'Speed',
								value: "0'00\""
							}
						]
					}


				}
			},
			stopDrag() {
				if (this.isExtend == false) {
					this.isExtend = true;
				} else {
					this.isExtend = false;
				}
				this.isDragging = false;
				let targetElement = this.$refs.information;
				if (this.isPhone == true) {
					targetElement.removeEventListener('touchmove', this.onDrag);
					targetElement.removeEventListener('touchend', this.stopDrag);
				} else {
					document.removeEventListener('mousemove', this.onDrag);
					document.removeEventListener('mouseup', this.stopDrag);
				}

			}
		}
	}
</script>
<script module="renderModal" lang="renderjs">
	export default {
		data() {
			return {
				dom: '',
			}
		},
		mounted() {
			this.dom = document.getElementById('map')
			// 接收iframe传过来的值
			window.addEventListener('message', (e) => {
				this.emitData(e.data)
			});
		},
		methods: {
			emitData(e) {
				// 将值传到当前页面
				this.$ownerInstance.callMethod('receiveRenderData', e)
			},
			// // data的值发生改变时会触发dataChange并且将值传到iframe页面中
			// messageChange(e) {
			// 	const param = {
			// 		data: e
			// 	}
			// 	this.dom.contentWindow.postMessage(param, '*')
			// }
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
		height: 100vh;
		background-color: white;
		box-shadow: none;
	}

	.black-screen {
		position: fixed;
		top: 0;
		left: 0;
		width: 100vw;
		height: 100vh;
		background-color: black;
		display: flex;
		justify-content: center;
		align-items: center;
		z-index: 9999; // 确保黑屏覆盖其他内容
	}

	.countdown {
		color: white;
		font-size: 8em; // 根据需要调整字体大小
	}

	.information {
		position: fixed;
		width: 100vw;
		left: 0;
		right: 0;
		bottom: 0;
		border-top-left-radius: 2vh; // 根据需要调整圆角大小
		border-top-right-radius: 2vh; // 根据需要调整圆角大小
		background-color: rgb(70, 64, 76);
		z-index: 5;
		display: flex;
		flex-wrap: wrap;
		// row-gap: 10px;
		justify-content: flex-start;
		transition: 0.5s;

		.info-item {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			color: #fff;
			height: 10vh;
			margin: 1vh 0;
			margin-bottom: 10px;

			&:nth-child(6) {
				background-color: red;
				border-radius: 50%;
				align-self: center;
				margin: auto;
				width: 70px !important;
				height: 70px;
			}

			&:nth-child(7) {
				background-color: red;
				border-radius: 50%;
				align-self: center;
				margin: auto;
				width: 70px !important;
				height: 70px;
			}

			.label {
				font-size: 20px;
			}

			.value {
				font-size: 25px;
				font-weight: bold;
			}
		}

		.button-item {
			border-top-left-radius: 2vh; // 根据需要调整圆角大小
			border-top-right-radius: 2vh; // 根据需要调整圆角大小
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			color: #fff;
			height: 2vh;
		}
	}
</style>