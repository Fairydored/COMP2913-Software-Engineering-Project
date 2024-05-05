<template>
  <view class="container">
    <u-navbar title="Create Group" :safeAreaInsetTop="false"></u-navbar>
    
    <!-- 组名 -->
    <view class="input-item">
      <text class="input-title">Group Name</text>
      <u-input v-model="groupName" placeholder="Enter group name" :border="false" :round="true" />
    </view>

    <!-- 头像 -->
    <view class="input-item">
      <text class="input-title">Group Avatar</text>
      <u-upload :fileList="groupAvatar" @after-read="afterRead" @delete="deletePic"
		name="upload"
		multiple
		:maxCount="1">
        <!-- 预览头像 -->
        <view slot="preview" v-for="(file, index) in groupAvatar" :key="index">
          <image class="preview-image" :src="file.url" mode="aspectFill" />
        </view>
      </u-upload>
    </view>
    
    <!-- 组描述 -->
    <view class="input-item">
      <text class="input-title">Group Description</text>
      <u-textarea v-model="groupDesc" placeholder="Group description..." :border="false" :round="true" />
    </view>
    
    <u-button type="primary" shape="round" @click="createGroup">Create Now</u-button>
  </view>
</template>

<!-- The script section remains unchanged -->

<style scoped>
  /* The style section remains unchanged */
</style>

<script>
export default {
  data() {
    return {
      groupName: '',
      groupAvatar: [],
      groupDesc: ''
    };
  },
  methods:{
  			// 删除图片
  			deletePic(event) {
  				this[`fileList${event.name}`].splice(event.index, 1)
  			},
  			// 新增图片
  			async afterRead(event) {
  				// 当设置 multiple 为 true 时, file 为数组格式，否则为对象格式
  				let lists = [].concat(event.file)
  				let fileListLen = this[`fileList${event.name}`].length
  				lists.map((item) => {
  					this[`fileList${event.name}`].push({
  						...item,
  						status: 'uploading',
  						message: 'uploading'
  					})
  				})
  				for (let i = 0; i < lists.length; i++) {
  					const result = await this.uploadFilePromise(lists[i].url)
  					let item = this[`fileList${event.name}`][fileListLen]
  					this[`fileList${event.name}`].splice(fileListLen, 1, Object.assign(item, {
  						status: 'success',
  						message: '',
  						url: result
  					}))
  					fileListLen++
  				}
  			},
  			uploadFilePromise(url) {
  				return new Promise((resolve, reject) => {
  					let a = uni.uploadFile({
  						url: 'http://waa.cool:3009/upload',
  						filePath: url,
  						name: 'file',
  						formData: {
  							user: 'test'
  						},
  						success: (res) => {
  							setTimeout(() => {
  								resolve(res.data.data)
  							}, 1000)
  						}
  					});
  				})
  			},
    createGroup() {
      // 这个方法应该要调用创建群组的接口，可以等我回来再写
	  // 或者参考member-center的cancelMembership（）怎么请求的
      console.log('创建群组', this.groupName, this.groupAvatar, this.groupDesc);

    }
  }
}
</script>

<style scoped>
.container {
  background-color: #f5f5f5;
  padding: 16px;
  height: 100vh;
}

.input-item {
  margin-bottom: 24px; 
}

.input-title {
  color: #666666; 
  margin-bottom: 12px; 
  font-size: 16px;
}


.u-input, .u-textarea {
  background-color: #fff;
  border-radius: 10px; 
}

.preview-image {
  width: 80px;
  height: 80px;
  border-radius: 10px;
  margin-right: 10px;
}

u-button {
  margin-top: 24px;
}
</style>
