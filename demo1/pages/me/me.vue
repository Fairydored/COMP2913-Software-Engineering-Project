<template>
  <view class="me-page">
    <view class="avatar-section">
      <image class="avatar" src="/static/uploads/微信图片_20240324161506.jpg"></image>
      <view class="user-info">
        <text class="user-name">用户名</text>
        <text class="user-email">xxx@xx.com</text>
      </view>
    </view>

     <!-- 统计数据 -->
        <view class="stats-section">
          <view class="stat">
            <text class="stat-desc">总运动时长</text>
            <text class="stat-number">xx 分钟</text>
          </view>
          <view class="divider"></view>
          <view class="stat">
            <text class="stat-desc">已坚持运动</text>
            <text class="stat-number">xx 天</text>
          </view>
          <view class="divider"></view>
          <view class="stat">
            <text class="stat-desc">总跑步时间</text>
            <text class="stat-number">xx 分钟</text>
          </view>
        </view>

    <!-- 会员充值部分 -->
    <view class="vip-section">
		<text class="vip-info">成为VIP会员，解锁更多功能！</text>
		<button class="vip-button" @click="rechargeVip">加入会员</button>
    </view>
	<!-- VIP 介绍弹窗 -->
	<view class="vip-modal" v-if="showModal" @click="closeModal">
		<view class="vip-modal-content">
			<!-- 月度会员 -->
			<view class="vip-tier">
				<text class="vip-title">月度会员</text><br/>
				<text class="vip-price">￥10</text><br/>
				<text class="vip-benefit">个性化的训练计划</text><br/>
				<text class="vip-benefit">更多统计数据与分析</text><br/>
				<button class="purchase-button" @click="purchase('monthly')">购买</button>
			</view>
		
			<view class="divider-horizontal"></view>
			
			<!-- 年度会员 -->
			<view class="vip-tier">
				<text class="vip-title">年费会员</text><br/>
				<text class="vip-price">￥99</text><br/>
				<text class="vip-benefit">一次付费，全年无忧</text><br/>
				<text class="vip-benefit">享受所有月度会员权益</text>
				<button class="purchase-button" @click="purchase('yearly')">购买</button>
			</view>
			
	         <view class="divider-horizontal"></view>
			<!-- 永久会员 -->
	        <view class="vip-tier">
	          <text class="vip-title">永久会员</text><br/>
	          <text class="vip-price">￥200</text><br/>
	          <text class="vip-benefit">一次付费，终身享受</text><br/>
	          <text class="vip-benefit">专享永久会员标识</text><br/>
	          <button class="purchase-button" @click="purchase('permanent')">购买</button>
	        </view>
	  </view>
	</view>
  </view>
  
</template>


<script>
export default {
	data() {
	    return {
	      showModal: false, // 控制弹窗是否显示
	    }
	  },
  methods: {
	rechargeVip() {
	      // 打开弹窗
		  console.log('rechargeVip method called'); 
	      this.showModal = true;
	    },
	closeModal() {
	      // 关闭弹窗
	      this.showModal = false;
	    }
	}
};
</script>

<style>
.me-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 50px 0;
  background-color: #f5f5f5;
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
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.stat {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px 20px;
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

/* 会员部分 */
.vip-section {
  display: flex;
  justify-content: space-between;
  margin-top: 30px;
  padding: 20px;
  width: 80%;
  height:20vh;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  background-image: url('/static/image/ad.webp');
  background-size: cover;
}

.vip-info {
  font-size: 16px;
  color: #333;
}

.vip-button {
  width: 50%;
  padding: 10px;
  font-size: 16px;
  color: #fff; 
  background-color: rgba(0, 0, 0, 0.6); 
  text-shadow: 0 1px 3px rgba(0, 0, 0, 0.3); 
  border: none;
  border-radius: 25px;
  margin-top: 5px;
  height:5vh;
  line-height:20px;
}

.vip-info {
  font-size: 16px;
  color: #fff; 
  text-shadow: 0 1px 3px rgba(0, 0, 0, 0.3); 
}

/* 弹窗背景 */
.vip-modal {
  display: flex; 
  position: fixed; 
  left: 0;
  top: 0;
  width: 100%;
  height: 60%;
  margin-top: 20vh;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1000;
}

/* 弹窗内容 */
.vip-modal-content {
  background-color: #fff;
  margin: 10% auto; 
  padding: 20px;
  border: 1px solid #ddd;
  width: 80%;
  display: flex; 
  flex-direction: column;
}

.vip-tier {
  display: flex;
  justify-content: space-between;
  padding: 15px 0;
}

.vip-title {
  font-size: 20px;
  font-weight: bold;
  color: #333;
  width: 30%;
}

.vip-details {
  font-size: 16px;
  color: #666;
  width: 70%;
}

.vip-price {
  font-size: 18px;
  font-weight: bold;
  color: #e67e22;
  padding:10px;
}

.vip-benefit {
  font-size: 14px;
}

.divider-horizontal {
  height: 1px;
  background-color: #ddd;
  width: 100%; 
}

.vip-tier:last-child .divider-horizontal {
  display: none;
}

.purchase-button {
  padding: 2px 10px;
  font-size: 15px;
  color: #fff;
  background-color: #1abc9c; /* 你可以选择一个适合你应用主题的颜色 */
  border: none;
  border-radius: 4px;
  cursor: pointer;
  height:10vh;
  margin-left: 5px;
}

.purchase-button:hover {
  background-color: #16a085; /* 鼠标悬停时的颜色变化 */
}
</style>