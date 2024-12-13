package com.kh.hyper.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.hyper.member.model.service.MemberService;
import com.kh.hyper.member.model.vo.Member;

// MVC같은경우는 애노테이션 작성 시 자기 역할에 맞는걸 기재해줄것
@Controller
//@RequestMapping(value="member")
public class MemberController {
	
	/*
	@Autowired // 필드주입
	// 장점 : 쓸 게 줄어듬
	// 단점 : 순환의존성이 줄어듬, 여러곳에서 서비스를 사용할 때 문제가 생길 수 있음
	// 		문제가 생겼을때 확인이 어려움
	 */
	private MemberService memberService;
	
	@Autowired // 생성자 주입
	public MemberController(MemberService memberService) {
		// MemberService를 Bean으로 올려두어야함. <--- 중요 MemberService 클래스에 @Service
		// 주입해주는 내용응 Bean에서 찾기 때문.
		this.memberService = memberService;
		// 매개변수와 일치하는 타입의 Bean객체를 검색해서 인자값으로 주입을 해줌
	}
	
	/*
	 * public void login(String userId, String userPwd){
	 * 
	 * }
	 * 
	 * public void save(String uesrId, ...,){
	 * 	new MemberView().success
	 * }
	 */
	/* login.me
	 * insert.me
	 * update.me
	 */
	
	/*
	@RequestMapping(value="login.me") // RequestMapping타입의 에노테이션을 등록함으로서 HandlerMapping을 등록
	public void login() {
		System.out.println("로그인 요청을 보냈습니까?");
	}
	
	@RequestMapping(value="insert")
	public void insert() {
		System.out.println("추가 요청을 보냈습니까?");
	}
	
	@RequestMapping(value="delete")
	public void update() {
		System.out.println("삭제 요청을 보냈습니까?");
	}
	*/
	
	/*
	 * Spring에서 요청 시 전달 값(Parameter)를 받아서 사용하는 방법
	 * 
	 * 1. HttpServletRequest를 이용해서 전달받기 (기존의 JSP / Servlet 방식)
	 * 
	 * 핸들러의 매개변수로 HttpServletRequest타입을 작성해두면
	 * DispatcherServlet이 해당 메서드를 호출할 때 request객체를 전달해서 매개변수로 주입해줌
	 */
	
	/*
	@RequestMapping(value="login.me")
	public String login(HttpServletRequest request) {
		// 매개변수로 넣어두기만 해도 Spring이 알아서 처리해준다.
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		System.out.println(id);
		System.out.println(pwd);
		
		return "main";
	}
	*/
	
	/*
	 * 2. @RequestParam 애노테이션을 이용하는 방법
	 * 
	 * request.getParameter("키")로 밸류를 뽑아오는 역할을 대신하는 애노테이션
	 * value속성의 값으로 jsp에 작성한 키 값을 적으면 알아서 해당 매개변수에 주입을 해준다.
	 * 
	 * @RequestParam(value="id") value 속성값 뒤에 defaultValue를 넣어 기본값을 지정할 수 있다.
	 * 비어있는 값이 넘어온 상태라면 defaultValue값이 넘어옴
	 * 많이 쓸듯
	 */
	/*
	@RequestMapping(value="login.me")
	public String login(@RequestParam(value="id") String id, 
						@RequestParam(value="pwd") String pwd) {
		System.out.println(id);
		System.out.println(pwd);
			
		return "main";
	}
	*/
	
	/*
	 * 3. RequestParam 애노테이션을 생략하는 방법
	 * 
	 * 단, 매개변수 명의 jsp에서 전달한 key값과 동일하게 세팅해둬야 자동으로 값이 주입됨
	 * 
	 * 단점은 키값의 의미가 명확하지 않을 수 있고, defaultValue속성을 사용할 수 없음
	 * 잘 안쓸듯
	 */
	/*
	@RequestMapping("login.me")
	public String login(String id, String pwd) {
		
		System.out.println(id);
		System.out.println(pwd);
		
		Member member = new Member();
		member.setUserId(id);
		member.setUserPwd(pwd);
				
		return "main";
	}
	*/
	/*
	 * 4. 커맨드 객체 방식
	 * 
	 * 1. 전달되는 키값과 객체의 필드명이 동일해야함
	 * 2. 매개변수의 기본생성자가 반드시 존재해야함
	 * 3. 매개변수의 클래스에 setter메소드가 반드시 존재해야함
	 *
	 * 스프링에서 해당 객체를 기본생성자를 통해서 생성한 후
	 * 내부적으로 setter메소드를 찾아서 요청 시 전달값을 해당 필드에 담아준다.
	 */
	
	/*
	@RequestMapping("login.me")
	public String login(Member member) {
		// 스프링에서 해당 객체를 기본생성자로 생성한 후에 필드를 확인한다.
		// 필드값하고, 앞단에서 넘어온 키값이 동일하다면
		// 같은 이름을 가진 setter를 찾고, 존재한다면 키값을 대입함
		
		//System.out.println(member);
		
		// new MemberServiceImpl().login(member);
		// 위와 같이 진행한다면, MemberServiceImpl 클래스에 너무 의존하게됨
		// Service클래스의 수정이 일어날 경우 Service클래스를 의존하고 있던 Controller가 영향을받음
		Member loginMember = memberService.login(member);
		
		if(loginMember != null) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
		
		return "main";
		
		
	}
	*/
	
	/*
	 * Spring에서는 Requst를 잘 사용하지않고 특별한방법 사용
	 * Model and View로 돌아가야하기 때문
	 * 
	 * Client의 요청 처리 후 응답데이터를 담아서 응답페이지로 포워딩 또는 URL재요청 하는 방법
	 * RequestMapping을 메소드에 쓸 땐 더 구체적인 방법을 사용
	 * 
	 * 1. 스프링에서 제공하는 Model객체를 사용하는 방법 MAV의 M
	 * 포워딩할 응답 뷰로 전달하고자 하는 데이터를 맵형식(key-value)으로 담을 수 있는 영역
	 * Model 객체는 requestScope
	 * 
	 * 단, setAttribute()가 아닌 addAttribute()를 호출해야 함
	 * 
	 * 
	 */
	
	/*
	@PostMapping(value="login.me")
	public String login(Member member, Model model, HttpSession session) {
		
		Member loginMember = memberService.login(member);
		
		if(loginMember != null) {// 로그인 성공 (정보가 있다)=> loginMember를 sessionScope에 담기
			session.setAttribute("loginUser", loginMember);
			
			return "redirect:/";
			
		} else {// 로그인 실패 (정보가 없다) => 에러문구를 requestScope에 담아서 에러페이지로 포워딩
			
			model.addAttribute("errorMsg", "로그인 실패");
			/*
			 *\/WEB-INF/views/common/error_page/jsp
			 *
			 * String Type return -> viewName에 대입
			 * -> DispatcherServlet으로 이동 -> ViewResolver에게 전달
			 * 
			 * - prefix 내용 앞에 붙임 : /WEB-INF/views/
			 * 
			 * - 중간: return viewName;
			 * 
			 * -suffix 내용 뒤에 붙임 : .jsp
			 
			return "comon/error_page";
		}
	}
	*/
	
	/*2. ModelAndView타입을 사용하는 방법
	 * 
	 * Model은 데이터를 key-value세트로 담을 수 있는 객체
	 * View는 응답 뷰에 대한 정보를 담을 수 있음
	 * 
	 * Model객체와 View가 결합된 형태의 객체
	 */
	
	@PostMapping("login.me")
	public ModelAndView login(Member member,
							  HttpSession session,
							  ModelAndView mv) {
	
		Member loginMember = memberService.login(member);
	
		if(loginMember != null) {
			session.setAttribute("loginUser", loginMember);
			mv.setViewName("redirect:/");
		} else {
			// model.addAttribute
			mv.addObject("errorMsg", "로그인 실패");
			mv.setViewName("common/error_page");
		}
		return mv;
	}
	
	@GetMapping("logout.me")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	/* MAV로 하는 방법 (model에 담을게 있었다면 이 방법이 더 좋았을듯?)
	@GetMapping("logout.me")
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		session.removeAttribute("loginUser");
		mv.setViewName("redirect:/");
		return mv;
	}
	*/
}
