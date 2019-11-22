package com.doosan.web.pxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component("pager")
@Lazy
@Data		//속성값을 가지니까 롬복처리! (prev,next를 넘길 예정이기 때문에)
public class PageProxy extends Proxy{
	@Autowired CrawlingProxy crawler;
	private int rowCount, startRow, endRow,
					  pageCount, pageSize, startPage, endPage, currPage,
					  blockCount, blockSize, prevBlock, nextBlock, currBlock
		;
	private boolean existPrev, existNext;
	private String search;
	
	public void paging() {
		// rowCount, pageSize, blockSize, currPage; = 외부주입값
		print("크롤링 사이즈 : "+rowCount);
		pageCount = (rowCount%pageSize != 0) ? rowCount/pageSize+1 : rowCount/pageSize;
		blockCount = (pageCount%blockSize != 0)? pageCount/blockSize+1 : pageCount/blockSize;
		startRow = currPage * pageSize;
		endRow = (currPage != (pageCount-1))? startRow + (pageSize -1) : rowCount -1;
		currBlock = currPage / blockSize;
		startPage = currBlock * blockSize;
		endPage = (currBlock != (blockCount-1)) ? startPage + (blockSize -1) : pageCount -1;
		nextBlock = startPage + blockSize;
		prevBlock = startPage - blockSize;
		existPrev = currBlock != 0 ;
		existNext = (currBlock+1) != blockCount;
	}
}
