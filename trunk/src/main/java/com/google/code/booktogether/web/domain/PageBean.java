package com.google.code.booktogether.web.domain;

public class PageBean extends BaseObject{

	/**
	 * 페이징 기법을 위한 클래스.
	 */
	private static final long serialVersionUID = 1L;
	private int numlist;   //리스트 뿌릴때 번호출력
	private int pageNo=1;	//선택된 페이지
	private int startRow;	//시작 페이지
	private int endRow;		//마지막 페이지
	private int dbcount;	//데이터베이스에 있는 모든 회원수
	private int pagesize=3;	//보여줄 페이지 갯수
	private int pagecount;
	private int limit=3;	//보여질 페이지 숫자 갯수
	private int startPage=1;
	private int endPage;
	private boolean prepage; //이전 페이지 유무확인
	private boolean nextpage;  //다음 페이지 유무확인

	private String word="";	//검색단어
	private String how_search="";	//검색방법

	public void setPageNo(int pageNo){
		this.pageNo=pageNo;
	}

	public void setLimit(int limit){
		this.limit=limit;
	}

	public void setDbcount(int dbcount){
		this.dbcount=dbcount;
	}
	public void setPagesize(int pagesize){
		this.pagesize=pagesize;
	}
	public int getPageNo(){
		return pageNo;
	}
	public int getLimit(){
		return limit;
	}
	public int getStartRow(){
		startRow=(pageNo-1)*pagesize+1;
		return startRow;
	}
	public int getEndRow(){
		endRow=startRow+pagesize-1;
		if(endRow>dbcount){
			endRow=dbcount;
		}
		return endRow;
	}
	public int getDbcount(){
		return dbcount;
	}
	public int getPagesize(){
		return pagesize;
	}
	public int getPagecount(){
		if(dbcount%pagesize == 0 )
			pagecount=dbcount/pagesize;
		else
			pagecount=dbcount/pagesize+1;
		return pagecount;
	}

	public int getStartPage(){
		startPage=pageNo-((pageNo-1)%limit);
		return startPage; 
	}
	public int getEndPage(){
		getPagecount();
		endPage=startPage+limit-1;
		if(endPage>pagecount){
			endPage=pagecount;
		}
		if(endPage==0){
			endPage=1;
		}
		return endPage; 
	}

	public boolean isNextpage() {
		if(getStartPage()+getLimit()-1<getPagecount()){
			nextpage=true;
		}else nextpage=false;
		return nextpage;
	}

	public boolean isPrepage() {
		if(getStartPage()-getLimit()>0){
			prepage=true;
		}else prepage=false;
		return prepage;
	}

	public int getNumlist() {
		numlist=dbcount-((getPageNo()-1)*getPagesize());
		return numlist;
	}

	public void setNumlist(int numlist) {
		this.numlist = numlist;
	}

	public String getHow_search() {
		return how_search;
	}

	public void setHow_search(String how_search) {
		this.how_search = how_search;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
