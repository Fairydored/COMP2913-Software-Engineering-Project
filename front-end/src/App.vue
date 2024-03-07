<template>

	<div>
		<h1>这是一个简单的登录页面</h1>
		<div>
			用户test
			<input type="text" v-model="username">
		</div>
		<div>
			密码test
			<input type="text" v-model="password">
		</div>
		<button @click="login()">登录</button>
		<div>{{res}}</div>
	</div>
</template>

<script>

export default {
	name: 'App',
	data() {
        return {
			res:'请您输入用户名和密码（用户名:tingwoshuo 密码:xiexieni)',
			username:'',
			password:'',
            
        }
    },
	components: {

	},
    methods: {
		login(){
			console.log('点击了登录按钮');

			this.$axios({
				method: "post",
				
				url: 'http://43.143.129.235:3009/login',

				headers: { 
					// "Content-Type":"multipart/form-data"
					"Content-Type":"application/json"
				},
				data:{
					
					username: this.username,
					password: this.password,
				}
			})
			.then((res) => {
				if (res.data == 0) {
					this.res = '登录失败'
				}else{
					this.res = '登录成功'
				}
			})

		},
		
		
		
        
    },
	created() { 
    },

}
</script>

<style>

</style>
