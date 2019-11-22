"use strict"
var app = app || {}
app=(()=>{
	let _,js, img, pop_js
	let init=()=>{
	_= $.ctx()
	js = $.js()
	img = $.img()
	pop_js = js+'/pop.js'
	}
	let run =x=>{
		$.when(
			$.getScript(x+'/resources/js/router.js',()=>{$.extend(new Session(x))}),
			getScript(pop_js)
		)
		.done(()=>{
			onCreate()
		})
		.fail(()=>{alert('getScript 실패')})
	}
	let onCreate=()=>{
		alert('하이염 ㅎㅎ')
		init()
		$(pop.view())
		.appendTo('body')
		pop.open()
		setContentView()
	}
	let setContentView=()=>{
		$('<table id = "tab"><tr></tr></table>>')
		.css({
			width:'80%', height:'800px', border: '1px solid black', margin: '0 auto'})
		.appendTo('#wrapper')
		$('<td/>', {id : 'left'}).appendTo('tr')
		.css({
			width:'20%', height:'100%', border: '1px solid black', 'vertical-align':'top'
		})
		$('<td/>', {id : 'right'}).appendTo('tr')
		.css({
			width:'80%', height:'100%', border: '1px solid black'
		})
		.appendTo('tr')
		$.each(['naver', 'cgv', 'bugs'],(i,j)=>{
			$('<div/>').text(j).css({
			width:'100%',
			height:'50px',
			border: '1px solid black'
			})
			.appendTo('#left')
			.click(function(){
			$(this).css({'background=color' : 'yellow'})
			$(this).siblings().css({'background-color' : 'white'})
			switch($(this).text()) {
			case 'NAVER' :
				$.getJSON(_+'/crawl/naver',d=>{  //d가 리스트이니까 바로 d로 가능
					$('#right').empty()						//중복클릭했을 때 중복실행을 막는녀석
					$.each(d, (i,j)=>{							//d가 hashMap이면 d.list 등이 됨. 
						$('<div/>')									
						.html('<h1>'+j.origin+'</h1><h4>'+j.trans+'</h4>')
						.css({
							width:'40%',
							height:'40%',
							border:'3px solid red',
							float:'left'
					})
					.appendTo('#right')						//appendTo를 하지 않으면 보이지않음(GHOST)
					})
				})
				break;
			case 'CGV' :
				$.getJSON(_+'/crawl/cgv',d=>{  
					$('#right').empty()						
					$.each(d, (i,j)=>{						 
						$('<div><img style ="width : 200px;" src = "'+j.photo+'"/><br/>'+j.title+'<br/>'+j.precent+'<br/>'+'<br/>'+j.textinfo+'</div>')									
						.css({
							border:'3px solid red',
							float:'left'
					})
					.appendTo('#right')					
					})
				})
				break;
			case 'BUGS' :
				 	list(0)//dp
					break
			}
		})})
	}
	let list =x=>{ //DP작업
		$.getJSON(_+'/crawl/bugs/page/'+x,d=>{
			let pager = d.pager;
			let list = d.list;
			
			$('#right').emtpy()
			
			$('<table id = "content"><tr id = "head"></tr></table>') 
			.css({
				width:'99%',
				height : '80px',
				border:'1px solid red'})
			.appendTo('#right')
			$.each(['No.','제목','가수','앨범'],(i,j)=>{
				$('<th/>') // th : 스키마 영역
				.html('<b>'+j+'</b>')
				.css({
					width:'25%',
					height : '100%',
					border:'1px solid red',
					'vertical-align':'top'
				})
				.appendTo('#head')
			})
			$.each(list, (i,j)=>{
				$('<tr><td>'+j.seq+'</td><td><img src="'+j.thumnail+'"/></td><td>'+j.title+'</td><td>'+j.artist+'</td></tr>')
				.css({
					width : '25%',
					height : '100%',
					border : '1px solid black'
				})
				.appendTo('#content tbody')
			})
			$('#content tr td')
			.css({
				border : '1px solid black'
			})
			$('<div/>',{
				id : 'pagination'
			})
			.css({
				width : '50%',
				height : '50px',
				margin: '20px auto'
			})
			.appendTo('#right')
			
			if(pager.existPrev){
				$('<span/>')
				.css({
					width : '50px',
					height : '30px',
					display: 'inline-block',
					border : '1px solid black'
				})
				.text('Prev')
				.appendTo('#pagination')
				.click(()=>{
					app.list(pager.prevBlock)
				})
			}
			let i = pager.startPage
			for(; i<pager.endPage; i++){
				$('<span/>')
				.css({
					width : '30px',
					height : '30px',
					display: 'inline-block',
					border : '1px solid black'
				})
				.text(i+1)
				.appendTo('#pagination')
				.click(function(){					//each루프 돌릴 때는 $(this)가 세트!
					let page = parseInt($(this).text())
					app.list(page -1)
				})
			}
			if(pager.existNext){
				$('<span/>')
				.css({
					width : '50px',
					height : '30px',
					display: 'inline-block',
					border : '1px solid black'
				})
				.text('Next')
				.appendTo('#pagination')
				.click(()=>{
					app.list(pager.nextBlock)
				})
			}
				
		})
			

	}
	return {run, list}  //dp(list)를 하게되면 자기자신을 바로 호출 못하기 때문에 밖에서 다시 호출해줘야함.
})()