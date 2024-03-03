<template>
	<div>
		<h1>这是一个主页</h1>
		<h2>测试结果：{{res}}</h2>
	</div>
</template>

<script>

export default {
	name: 'App',
	data() {
        return {
			res:''
            
        }
    },
	components: {

	},
    methods: {
		
		test(){
// url: 'http://43.143.129.235:3009/number',
			this.$axios({
				method: "post",
				
				url: 'http://127.0.0.1:3009/number',

				headers: { 
					// "Content-Type":"multipart/form-data"
					"Content-Type":"application/json"
				},
				data:{
					
					a: 3,
					b: 2
				}
			})
			.then((res) => {
				console.log(res.data);
				this.res = res.data
			})
		},
		
		
		
        
    },
	created() {  // 生命周期created函数
		this.test()
    },
}
</script>

<style>

</style>
