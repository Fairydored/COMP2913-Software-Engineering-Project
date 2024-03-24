<template>
<view class="user-agreement-container">
    <view class="user-agreement-title">用户协议</view>
    <scroll-view class="user-agreement-content">
      <view>
        欢迎使用我们的软件！<br/>本软件旨在帮助用户记录和分析他们的跑步活动。
        在开始使用本软件之前，请仔细阅读以下用户协议：<br/><br/>

        1. 使用条款<br/>
           您在使用本软件时，需要遵守所有适用的法律法规。<br/><br/>
        2. 隐私政策<br/>
           我们重视您的隐私。您的跑步记录和个人信息将被妥善保护。<br/><br/>
        3. 免责声明<br/>
           本软件仅供健身记录之用，不应作为任何医疗或健康建议。<br/><br/>
        4. 版权信息<br/>
           本软件的所有内容、图标和界面设计均受版权保护。<br/><br/>
        5. 最终解释权<br/>
           本协议的最终解释权归软件开发者所有。<br/><br/>

        继续使用本软件即表示您同意上述条款。<br/>
        感谢您选择我们的软件！
      </view>
    </scroll-view>
  </view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>
.user-agreement-container {
  padding: 20px;
}

.user-agreement-title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.user-agreement-content {
  font-size: 16px;
  line-height: 1.5;
  text-align: left;
}
</style>