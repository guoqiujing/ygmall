var app = new Vue({
	el: '#app',
	data: {
		object: {

		},
		message: 'Hello Vue!',
		hottype: [{
				typename: '颜料',
				lowertypename1: '水粉颜料',
				lowertypename2: '油画颜料',
				lowertypename3: '丙烯颜料',
				href1: '1',
				href2: '#',
				href3: '#',
				href4: '#'
			},
			{
				typename: '笔',
				lowertypename1: '炭笔',
				lowertypename2: '铅笔',
				lowertypename3: '钢笔',
				href1: '#',
				href2: '2',
				href3: '#',
				href4: '#'
			},
			{
				typename: '纸类',
				lowertypename1: '素描纸',
				lowertypename2: '水彩纸',
				lowertypename3: '速写纸',
				href1: '#',
				href2: '#',
				href3: '#',
				href4: '#'
			},
			{
				typename: '乐器',
				lowertypename1: '长笛',
				lowertypename2: '手风琴',
				lowertypename3: '吉他',
				href1: '#',
				href2: '#',
				href3: '#',
				href4: '#'
			},
			{
				typename: '舞蹈鞋',
				lowertypename1: '芭蕾舞鞋',
				lowertypename2: '拉丁舞鞋',
				lowertypename3: '儿童舞鞋',
				href1: '#',
				href2: '#',
				href3: '#',
				href4: '#'
			}
		],
		token: "abc"
	},
	methods: {

		change: function(event) {
			var b = [{
				typename: '颜料2',
				lowertypename1: '水粉颜料2',
				lowertypename2: '油画颜料2',
				lowertypename3: '丙烯颜料2',
				href1: '1',
				href2: '#',
				href3: '#',
				href4: '#'
			}];
			//			this.hottype = this.hottype.concat(b) 
//			this.hottype = b
//			alert('Hello' + this.hottype.length)
			//			if(event) {
			//				alert(event.target.tagName)
			//			}
		}
	}
})