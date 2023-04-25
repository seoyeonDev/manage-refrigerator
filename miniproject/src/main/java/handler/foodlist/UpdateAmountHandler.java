package handler.foodlist;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodmanage.FoodManageService;
import foodmanage.FoodManageVo;
import handler.Handler;

public class UpdateAmountHandler implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int num = Integer.parseInt( request.getParameter("num"));
		FoodManageService service = new FoodManageService();
		service.deleteFood(num);
		
		return "/foodlist/mylist.do?id=test";
		}
	

}