package com.bookshop01.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

import net.sf.json.JSONObject;

@Controller("goodsController")
@RequestMapping(value="/goods")
public class GoodsControllerImpl extends BaseController   implements GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value="/goodsDetail.do" ,method = RequestMethod.GET)
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id,//조회할 상품 번호 전달받기
			                       HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		HttpSession session=request.getSession();
		Map goodsMap=goodsService.goodsDetail(goods_id);//상품 번호를 조회한 후 Map으로 반환
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsMap", goodsMap);
		GoodsVO goodsVO=(GoodsVO)goodsMap.get("goodsVO");
		addGoodsInQuick(goods_id,goodsVO,session);//조회한 상품 정보를 빠른 메뉴에 표시하기 위해 전달
		return mav;
	}
	
	@RequestMapping(value="/keywordSearch.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
	public @ResponseBody String  keywordSearch(@RequestParam("keyword") String keyword,//검색할 키워드 가져옴
			                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");//한글 인코딩으로 지정
		response.setCharacterEncoding("utf-8");
		//System.out.println(keyword);
		if(keyword == null || keyword.equals(""))
		   return null ;
	
		keyword = keyword.toUpperCase();
	    List<String> keywordList =goodsService.keywordSearch(keyword);//가져온 키워드가 포함된 상품 제목 조회
	    
	 // JSONObject 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);//조회한 데이터를 JSON에 저장
		 		
	    String jsonInfo = jsonObject.toString();//JSON을 문자열로 변환 후 브라우저로 출력
	   //System.out.println(jsonInfo);
	    return jsonInfo ;
	}
	
	@RequestMapping(value="/searchGoods.do" ,method = RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName=(String)request.getAttribute("viewName");
		List<GoodsVO> goodsList=goodsService.searchGoods(searchWord);//검색창에서 가져온 단어가 포함된 상품 제목을 조회
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsList", goodsList);
		return mav;
		
	}
	
	private void addGoodsInQuick(String goods_id,GoodsVO goodsVO,HttpSession session){
		boolean already_existed=false;
		List<GoodsVO> quickGoodsList; 
		quickGoodsList=(ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");//세션에 저장된 최근 본 상품 목록을 가져오기
		
		if(quickGoodsList!=null){//최근 본 상품이 있는경우
			if(quickGoodsList.size() < 4){ //상품 목록이 네개 이하인 경우
				for(int i=0; i<quickGoodsList.size();i++){
					GoodsVO _goodsBean=(GoodsVO)quickGoodsList.get(i);
					if(goods_id.equals(_goodsBean.getGoods_id())){
						already_existed=true;
						break;
					}
				}
				if(already_existed==false){//상품 목록을 가져와 이미 존재하는 상품인지 비교후 아니면(false), 상품 정보를 목록에 저장
					quickGoodsList.add(goodsVO);
				}
			}
			
		}else{
			quickGoodsList =new ArrayList<GoodsVO>();//최근 본 상품 목록이 없으면 생성하여 상품정보 저장
			quickGoodsList.add(goodsVO);
			
		}
		session.setAttribute("quickGoodsList",quickGoodsList);//최근 본 상품 목록을 세션에 저장
		session.setAttribute("quickGoodsListNum", quickGoodsList.size());//최근 본 상품 목록에 저장된 상품 개수를 세션에 저장
	}
}
