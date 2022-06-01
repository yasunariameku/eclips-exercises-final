package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.Form;
import com.example.controller.form.InsertForm;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.service.ProductService;
import com.example.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	HttpSession session; 
	
	List<Product> list = null;
	
	//ログインページに遷移
	@RequestMapping("/index")
	public String indexPage(@ModelAttribute("login") Form form, Model model) {
		return "index";
	}
	
	@RequestMapping("/insert")
	public String insertPage(@ModelAttribute("insert") InsertForm form, Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("name",user.getName());
		return "insert";
	}
	
	//新規登録に遷移
//	@RequestMapping("/insert")
//	public String insert(@ModelAttribute("insert") InsertForm form, Model model) {
//		System.out.println("aaaa");
//		return "insert";
//	}
	
	//ログイン機能
	@RequestMapping(value = "/login", method = RequestMethod.POST )
	public String login(@Validated @ModelAttribute("login") Form form, @ModelAttribute("search") Form form2, BindingResult bindingResult, Model model) {
		//System.out.println(bindingResult.getFieldValue("login_id"));
		//System.out.println(bindingResult.getFieldError("password"));
		if (bindingResult.hasErrors()) {
			//System.out.println("aaaa");
            return "index";
        }
		
		String user_id = form.getLogin_id();
		String password = form.getPassword();
		
		User user = userService.find(user_id, password);
		
		if (!(user == null) ) {
			session.setAttribute("user", user);
			model.addAttribute("name",user.getName());
			model.addAttribute("msg","ログインに成功しました");
			
			list = productService.findAll();
			
			if (list.size() == 0) {
				model.addAttribute("msg","検索結果は0件です。");
				return "menu";
			}
			
			session.setAttribute("result", list);
			model.addAttribute("result", list);
			
			return "menu";
		}else {
			model.addAttribute("msg","IDかPASSWORDが間違っています");
			return "index";
		}
		
	}
	
	//検索
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, Model model) {
				
		
		String searchWord = search;
		
		if (("").equals(searchWord)) {
			
			list = productService.findAll();
			
			if (list.size() == 0) {
				model.addAttribute("msg","検索結果は0件です。");
				return "menu";
			}
			
			session.setAttribute("result", list);
			model.addAttribute("result", list);
					
			return "menu";
			
		}else if (!("").equals(searchWord)) {
			
			list = productService.find(searchWord);
			
			if(list.size() == 0) {
				model.addAttribute("msg","検索結果は0件です。");
				return "menu";
			}
			
			session.setAttribute("result", list);
			model.addAttribute("result", list);
			
			return "menu";
		}
		
		return null;
		
	}	
	
	//並び替え
	@RequestMapping(value = "/sort", method = RequestMethod.GET)
	public String sort(@RequestParam("sort") String sort, Model model) {
		
		System.out.println(sort);
		
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) session.getAttribute("result");
		
		
		if ("id".equals(sort)) {
			list.sort((p1, p2) -> p1.getId() >= p2.getId() ? 1 : -1);
		}else if ("category".equals(sort)) {
			list.sort((p1, p2) -> p1.getCategory().compareTo(p2.getCategory()));
		}else if ("price_asc".equals(sort)) {
			list.sort((p1, p2) -> p1.getPrice() >= p2.getPrice() ? -1 : 1);
		}else if ("price_desc".equals(sort)) {
			list.sort((p1, p2) -> p1.getPrice() >= p2.getPrice() ? 1 : -1);
		}

		session.setAttribute("result", list);
		
		return "menu";
	}
//	
	//新規登録
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute("insert") InsertForm Insert, Model model) {
		
		Integer product_id = Insert.getProsuct_id(); //null値はバリデーションにて
		String name = Insert.getName();  //null値はバリデーションにて
		Integer price = Insert.getPrice();  //null値はバリデーションにて
		Integer category_id = Insert.getCategory_id();
		String description = Insert.getDescription();
		
		Product p = new Product(null, product_id, category_id, name,price, description, null);
		
		String result = productService.register(p);
		
		if (!("").equals(result)) {
			
			list = productService.findAll();
			session.setAttribute("result", list);
			model.addAttribute("result", list);
			
			return "menu";
		}else {
			
			model.addAttribute("msg","登録に失敗しました。");
			return "insert";
		}

		
	}
		
}
