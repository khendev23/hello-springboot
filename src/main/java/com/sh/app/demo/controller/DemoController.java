package com.sh.app.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.app.demo.dto.DevCreateDto;
import com.sh.app.demo.dto.DevUpdateDto;
import com.sh.app.demo.entity.Dev;
import com.sh.app.demo.entity.Gender;
import com.sh.app.demo.service.DemoService;

/**
 * @Controller
 * - @Component 상속
 * - mvc의 controller클래스 등록
 *
 * 
 * 
 * @Controller 클래스의 handler메소드가 가질수 있는 매개변수 타입
 * 
 * HttpServletRequest
 * HttpServletResponse
 * HttpSession
 * 
 * java.util.Locale : 요청에 대한 Locale
 * InputStream/Reader : 요청에 대한 입력스트림
 * OutputStream/Writer : 응답에 대한 출력스트림. ServletOutputStream, PrintWriter
 * 
 * 사용자입력값처리
 * Command객체 : http요청 파라미터를 커맨드객체에 저장한 VO객체
 * CommandMap :  HandlerMethodArgumentResolver에 의해 처리된 사용자입력값을 가진 Map객체
 * @Valid : 커맨드객체 유효성 검사객체
 * Error, BindingResult : Command객체에 저장결과(Command객체 바로 다음위치시킬것.)
 * @PathVariable : 요청url중 일부를 매개변수로 취할 수 있다.
 * @RequestParam : 사용자입력값을 자바변수에 대입처리(필수여부 설정)
 * @RequestHeader : 헤더값
 * @CookieValue : 쿠키값
 * @RequestBody : http message body에 작성된 json을 vo객체로 변환처리
 * 
 * 뷰에 전달할 모델 데이터 설정
 * ModelAndView
 * ModelMap 
 * Model
 
 * @ModelAttribute : model속성에 대한 getter
 * @SessionAttribute : session속성에 대한 getter(required여부 선택가능)
 * @SessionAttributes : session에서 관리될 속성명을 class-level에 작성
 * SessionStatus: @SessionAttributes로 등록된 속성에 대하여 사용완료(complete)처리. 세션을 폐기하지 않고 재사용한다.
 * 
 * 기타
 * MultipartFile : 업로드파일 처리 인터페이스. CommonsMultipartFile
 * RedirectAttributes : DML처리후 요청주소 변경을 위한 redirect시 속성처리 지원
 *
 */
@Controller
@Validated
@RequestMapping("/demo")
public class DemoController {

	// slf4j(simple logging facade for java) 관련 코드 - slf4j(스프링 추상화레이어)를 통해 log4j(구현체)를 제어 (PSA)
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private DemoService demoService;
	
	/**
	 * handler 
	 * - 사용자요청을 처리하는 @Controller 메소드
	 * - @RequestMapping(value, method)
	 * - 모든 annotation은 value속성 하나만 작성시, (value =) 생략가능
	 * - method속성은 미작성시 모든 전송방식 허용.
	 * @return
	 */
	@RequestMapping(value = "/devForm.do", method = RequestMethod.GET)
	public String devForm() {
		log.info("/demo/devForm.do 요청");
		return "demo/devForm"; // viewName지정 /WEB-INF/views/ 접두사, .jsp 접미사를 통해 path 완성
	}
	
	@RequestMapping(value = "/dev1.do", method = RequestMethod.POST)
	public String dev1(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		int career = Integer.parseInt(request.getParameter("career"));
		String email = request.getParameter("email");
		String _gender = request.getParameter("gender");
		String[] _langs = request.getParameterValues("lang");
		
		log.debug("name = {}", name);
		log.debug("career = {}", career);
		log.debug("email = {}", email);
		log.debug("gender = {}", _gender);
		log.debug("langs = {}", _langs != null ? Arrays.toString(_langs) : _langs);
		
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		List<String> langs = _langs != null ? Arrays.asList(_langs) : null; 
		
		DevCreateDto devDto = DevCreateDto.builder()
				.name(name)
				.career(career)
				.email(email)
				.gender(gender)
				.lang(langs)
				.build();
		request.setAttribute("dev", devDto);
		
		return "demo/devResult";
	}
	
	/**
	 * @RequestParam 
	 * - 사용자입력값을 매개변수 대입
	 * - input태그의 name값이 변수명과 연결
	 * 		- value속성값으로 이름이 다른경우 지정가능
	 * - required=true(기본값) 해당값이 주어지지 않으면 400오류
	 * 		- required=false 지정가능
	 * - defaultValue속성으로 기본값 지정가능 
	 * 
	 * Model - view단에 데이터를 전송하기 위한 객체
	 * - 기본적으로 request scope에 속성저장
	 * - 별도설정을 통해서 session scope에 저장도 가능(클래스레벨 @SessionAttributes)
	 * 
	 * @param name
	 * @param career
	 * @param gender
	 * @param email
	 * @param langs
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/dev2.do", method = RequestMethod.POST)
	@PostMapping("/dev2.do")
	public String dev2(
		@RequestParam String name, 
		@RequestParam int career, 
		@RequestParam(required = false, defaultValue = "M") Gender gender, 
		@RequestParam String email, 
		@RequestParam("lang") List<String> langs, 
		Model model
	) {
		DevCreateDto devDto = DevCreateDto.builder()
				.name(name)
				.career(career)
				.email(email)
				.gender(gender)
				.lang(langs)
				.build();
		log.debug("devDto = {}", devDto);
		// request.setAttribute 대신 view단에 데이터를 전달하는 경우
		model.addAttribute("dev", devDto);
		return "demo/devResult";
	}
	
	/**
	 * 커맨드객체 
	 * - input[name]속성과 일치하는 property를 찾아 연결.
	 * - 기본생성자 / setter 준비 필수
	 * - 자동으로 모델의 속성으로 등록. 속성명은 소문자로 시작하는 클래스명.
	 * - 명시적으로 속성명을 작성가능 @ModelAttribute(속성명)
	 * @param dev
	 * @return
	 */
	@PostMapping("/dev3.do")
	public String dev3(@ModelAttribute("dev") DevCreateDto dev) {
		// model.addAttribute("devDto", dev);
		
		return "demo/devResult";
	}
	
	@PostMapping("/dev4.do")
	public String dev4(
			@ModelAttribute("dev") @Valid DevCreateDto dev, 
			BindingResult bindingResult,
			RedirectAttributes redirectAttr) {
		// model.addAttribute("devDto", dev);
		
		if(bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			String message = null;
			for(ObjectError err : errors) {
				log.error("message = {} {}", err.getCodes()[1], err.getDefaultMessage());
				message = err.getDefaultMessage();
			}
			// RedirectAttributes 세션에 속성저장, 1회용
			redirectAttr.addFlashAttribute("msg", message);
			return "redirect:/demo/devForm.do";
		}
		
		return "demo/devResult";
	}
	
	@PostMapping("/createDev.do")
	public String createDev(@Valid DevCreateDto devDto, BindingResult bindingResult, RedirectAttributes redirectAttr) {
		if(bindingResult.hasErrors()) {
			redirectAttr.addFlashAttribute("msg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/demo/devForm.do";
		}
		
		int result = demoService.insertDev(devDto);
		redirectAttr.addFlashAttribute("msg", "성공적으로 Dev 등록했습니다.");
		return "redirect:/demo/devList.do";
	}
	
	@GetMapping("/devList.do")
	public void devList(Model model) {
		// 요청주소에서 viewName 유추함. 
		// /demo/devList.do -> demo/devList
		List<Dev> devs = demoService.findAllDev();
		log.debug("devs = {}", devs);
		model.addAttribute("devs", devs);
	}
	
	@GetMapping("/updateDev.do")
	public void updateDev(@RequestParam int id, Model model) {
		// /demo/updateDev.do -> demo/updateDev
		Dev dev = demoService.findDevById(id);
		log.debug("dev = {}", dev);
		model.addAttribute("dev", dev);
	}
	
	@PostMapping("/updateDev.do")
	public String updateDev(@Valid DevUpdateDto dev, BindingResult bindingResult, RedirectAttributes redirectAttr) {
		if(bindingResult.hasErrors()) {
			redirectAttr.addFlashAttribute("msg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "redirect:/demo/updateDev.do?id=" + dev.getId();
		}
		
		int result = demoService.updateDev(dev);
		redirectAttr.addFlashAttribute("msg", "Dev정보 수정 완료!");
		return "redirect:/demo/updateDev.do?id=" + dev.getId();
	}
	
	@PostMapping("/deleteDev.do")
	public String deleteDev(@RequestParam int id, RedirectAttributes redirectAttr) {
		int result = demoService.deleteDev(id);
		redirectAttr.addFlashAttribute("msg", "Dev정보 삭제 완료!");
		return "redirect:/demo/devList.do";
	}
	
	
	
}
