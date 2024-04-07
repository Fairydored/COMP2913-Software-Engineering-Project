<template>
	<view v-if="countdown > 0" class="black-screen">
	    <view class="countdown">{{ countdown }}</view>
	</view>
	<view v-else>
		<view class="u-page">
			<view id="map"></view>
			<view class="information" :style="{ height: dynamicHeight }" @touchstart="startDrag" @touchmove="onDrag" @touchend="stopDrag" @mousedown="startDrag" 
    @mousemove="onDrag" 
    @mouseup="stopDrag"
	 ref ="information">
				<view class="button-item" :style="{width:'100vw', backgroundColor: 'rgb(70,64,76)'}">
					<u-icon name='minus' size=100px></u-icon>
				    </view>	
				<view class="info-item" v-for="(item, index) in infoItems" :key="index" :style="{ width: infoItemWidth ,backgroundColor: item.bgColor}"
				@click="item.button ? pause() : null">
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
				countdown: 3,
				isDragging: false,
				isPause : false,
				isPhone :false,
				isExtend: false,
				startY: 0,
				dynamicHeight: '17vh',  
				infoItemWidth: '33%',
				  
				infoItems: [
				        { label: '距离', value: '0.00' , bgColor: 'transparent'},
				        { label: '时间', value: '00:00' , bgColor: 'transparent'},
				        { label: '实时配速', value: "0'00\"" , bgColor: 'transparent'}
				      ]
			};
		},
		mounted() {
			const countdownInterval = setInterval(() => {
			      if (this.countdown > 0) {
			        this.countdown--;
			      } else {
					this.initAMap();
			      }
			    }, 1000);
			
		},
		methods: {
			initAMap() {
				
			},
			pause() {
				if (this.isPause==false){
					this.infoItems = [
					{ label: '距离', value: '0.00' },
					{ label: '时间', value: '00:00' },
					{ label: '实时配速', value: "0'00\"" },
					{ label: '总消耗', value: "0"},
					{ label: '', value: ">", button: true ,bgColor: 'green'},
					];
					this.isPause = true;
				} else {
					this.infoItems = [
					{ label: '距离', value: '0.00' },
					{ label: '时间', value: '00:00' },
					{ label: '实时配速', value: "0'00\"" },
					{ label: '实时心率', value: "0 ❤"},
					{ label: '', value: "||", button: true },
					];
					this.isPause = false;
				}
			      
			},
			startDrag(event) {
			      this.isDragging = true;
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
							if (this.isExtend == false){
								
								this.dynamicHeight = '90vh';
								  this.infoItemWidth = '50%';
								  if (this.isPause==false){this.infoItems = [
								{ label: '距离', value: '0.00' },
								{ label: '时间', value: '00:00' },
								{ label: '实时配速', value: "0'00\"" },
								{ label: '实时心率', value: "0 ❤"},
								{ label: '', value: "||", button: true }]
							}else{
							this.infoItems = [
							{ label: '距离', value: '0.00' },
							{ label: '时间', value: '00:00' },
							{ label: '实时配速', value: "0'00\"" },
							{ label: '总消耗', value: "0"},
							{ label: '', value: ">", button: true ,bgColor: 'green'}]
						}
						  }else {
							  
						  this.dynamicHeight = '17vh';
						  this.infoItemWidth = '33%';
						  this.infoItems = [
						          { label: '距离', value: '0.00' },
						          { label: '时间', value: '00:00' },
						          { label: '实时配速', value: "0'00\"" }
						        ]
						}
						
						  
						} 
				},
			    stopDrag() {
					if (this.isExtend == false){
						this.isExtend = true;
					}else{
						this.isExtend = false;
					}
			      this.isDragging = false;
				  let targetElement = this.$refs.information;
				  if (this.isPhone == true){
					  targetElement.removeEventListener('touchmove', this.onDrag);
					  targetElement.removeEventListener('touchend', this.stopDrag);
				  }
else{
	document.removeEventListener('mousemove', this.onDrag);
	document.removeEventListener('mouseup', this.stopDrag);
}

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
		height: 100vh;
		background-color: white;
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
	    color: pink;
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
	  background-color:rgb(70,64,76);
	  z-index: 5;
	  display: flex;
	  flex-wrap: wrap;
	  row-gap: 10px;
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
		    .label {
		      font-size: 20px; 
		    }
		
		    .value {
		      font-size: 25px; 
		      font-weight: bold;
		    }
		  }
		  .button-item{
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