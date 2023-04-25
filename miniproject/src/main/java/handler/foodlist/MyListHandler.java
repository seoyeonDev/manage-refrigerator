package handler.foodlist;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import foodlist.FoodListService;
import foodmanage.FoodManageVo;
import handler.Handler;

public class MyListHandler implements Handler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		System.out.println(id);
		FoodListService service = new FoodListService();
		
//		service.checkDate(id);
		System.out.println("asddf");
		ArrayList<FoodManageVo> list = service.getbyId(id);
		request.setAttribute("list", list);
		System.out.println(list);
		return "/foodlist/mylist.jsp";
	}

}
