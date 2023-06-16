package com.fpoly.ShopBanGiay.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.ShopBanGiay.dao.NguoiDungDAO;
import com.fpoly.ShopBanGiay.model.NguoiDung;
import com.fpoly.ShopBanGiay.model.VerificationCode;
import com.fpoly.ShopBanGiay.service.MailerService;
import com.fpoly.ShopBanGiay.service.SessionService;

import jakarta.validation.Valid;

@Controller
public class DoiMatKhauController {
	
	@Autowired
	MailerService mail;
	
	@Autowired
	NguoiDungDAO nguoiDungDAO;
	
	NguoiDung user = new NguoiDung();
//	private String code;
	VerificationCode vc ;
	private String pass = "";
	
	@Autowired
	SessionService sessionService;
	
	String  messageCheckInputData;
	
	NguoiDung userSession =  new NguoiDung();

	@GetMapping("/doimatkhau")
	public String doimatkhau(Model model) {
		userSession = new NguoiDung(sessionService.getSessionAttribute("user"));
		user.setEmail(userSession.getEmail());
		model.addAttribute("nguoidung", user);
		return "/nguoidung/doimatkhau";
	}
	
	@PostMapping("/doimatkhau/nguoidung")
	public String changePass(@Valid @ModelAttribute("nguoidung") NguoiDung nguoidung,BindingResult result, Model model) {System.out.println("Thông tin đổi mật khẩu: "+nguoidung);
		if(nguoidung != null) {
			System.out.println("u: "+nguoidung);
			System.out.println("Đang xác thực pass...");
			user = new NguoiDung(nguoiDungDAO.findByEmail(nguoidung.getEmail()));
			this.pass = nguoidung.getDiachi();
			System.out.println("Mật khẩu cũ: "+user.getMatkhau());
			if(!checkInput(nguoidung)) {
				model.addAttribute("messageConfirmPassWrong", this.messageCheckInputData);
				model.addAttribute("nguoidung", this.user);
				return "/nguoidung/doimatkhau";
			}
			if(!nguoidung.getMatkhau().equalsIgnoreCase(user.getMatkhau())) {
				System.out.println("chưa khớp");
				model.addAttribute("messageConfirmPassWrong", "Lỗi: Mật khẩu cũ không chính xác");
				nguoidung.setMatkhau("");
				model.addAttribute("nguoidung", nguoidung);
				return "/nguoidung/doimatkhau";
			}
			if(!nguoidung.getDiachi().equalsIgnoreCase(nguoidung.getHinh())) {
				System.out.println("chưa khớp");
				model.addAttribute("messageConfirmPassWrong", "Lỗi: Mật khẩu mới và xác nhận mật khẩu chưa khớp!");
				nguoidung.setMatkhau(user.getMatkhau());
				System.out.println(user.getMatkhau());
				nguoidung.setHinh("");
				nguoidung.setDiachi("");
				model.addAttribute("nguoidung", nguoidung);
				return "/nguoidung/doimatkhau";
			}
			return "redirect:/doimatkhau/xacnhan";
		}
		
		return "/nguoidung/doimatkhau";
	}
	
	@RequestMapping("/doimatkhau/xacnhanS2")
	public String xacnhanB2(Model model, @RequestParam("code") String codeFormUser) {
		System.out.println("Mã code user nhập: "+codeFormUser);
		System.out.println("Mã code: "+this.vc.getCode());
		if(!this.vc.getCode().equals(codeFormUser)) {
			model.addAttribute("messageConfirmPassWrong", "Mã xác thực chưa đúng!");
			return "/nguoidung/confirmCodeChangePass";
		}
		if(!countTimeOfCode(this.vc)) {
			model.addAttribute("messageConfirmPassWrong", "Mã xác thực đã hết hiệu lực");
			return "/nguoidung/confirmCodeChangePass";
		}
		System.out.println("------mkm----- "+this.pass);
		userSession.setMatkhau(this.pass);
		nguoiDungDAO.save(userSession);
		return "redirect:/logout";
	}
	
	@RequestMapping("/doimatkhau/xacnhan")
	public String xacnhandoimatkhau(Model model) {
		System.out.println("Thông tin đk: "+user);
		this.vc  = new VerificationCode(generateCode(6));
		System.out.println("Mã code: "+this.vc.getCode());
		System.out.println("Thời gian tạo: "+this.vc.getCreatedTime());
		mail.queue(user.getEmail(), "ĐỔI MẬT KHẨU CONVERSE", sendHTML(this.vc.getCode(), this.user.getHoten()));
		return "/nguoidung/confirmCodeChangePass";
	}
	
	
	
	public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        
        return sb.toString();
    }
	
	public boolean checkInput(NguoiDung u) {	
		// Pass check
		if(u.getMatkhau().equals("")) {
			this.messageCheckInputData = "Lỗi: Mật khẩu phải từ 9-50 ký tự. Có it nhất 1 số , 1 chữ cái viết hoa, 1 ký tự đặc biệt!";
			this.user = new NguoiDung(1);
			this.user.setHoten(u.getHoten());
			this.user.setEmail(u.getEmail());
			return false;
		}
		String regexPass = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[a-zA-Z]).{9,50}$";
		boolean isValidPass = Pattern.matches(regexPass, u.getMatkhau());
		boolean isValidPass2 = Pattern.matches(regexPass, u.getHinh());
		boolean isValidPass3 = Pattern.matches(regexPass, u.getDiachi());
		if(!isValidPass ||!isValidPass2 || !isValidPass3) {
			this.messageCheckInputData = "Lỗi: Mật khẩu phải từ 9-50 ký tự. Có it nhất 1 số , 1 chữ cái viết hoa, 1 ký tự đặc biệt!";
			this.user = new NguoiDung(1);
			this.user.setEmail(u.getEmail());
			return false;
		}	
		
		
		return true;
	}
	
	public boolean countTimeOfCode(VerificationCode vc) {
		LocalDateTime currentTime = LocalDateTime.now();
		Duration duration = Duration.between(vc.getCreatedTime(), currentTime);
		if (duration.toSeconds() <= 30) {
		    // Mã xác nhận còn hiệu lực
			return true;
		} 
		return false;
	}
	
	public String sendHTML(String code, String name) {
		String x = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
				+ "<html xmlns:v=\"urn:schemas-microsoft-com:vml\">\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0;\" />\r\n"
				+ "    <!--[if !mso]--><!-- -->\r\n"
				+ "    <link href='https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,600,700' rel=\"stylesheet\">\r\n"
				+ "    <link href='https://fonts.googleapis.com/css?family=Quicksand:300,400,700' rel=\"stylesheet\">\r\n"
				+ "    <!-- <![endif]-->\r\n"
				+ "\r\n"
				+ "    <title>Material Design for Bootstrap</title>\r\n"
				+ "\r\n"
				+ "    <style type=\"text/css\">\r\n"
				+ "        body {\r\n"
				+ "            width: 100%;\r\n"
				+ "            background-color: #ffffff;\r\n"
				+ "            margin: 0;\r\n"
				+ "            padding: 0;\r\n"
				+ "            -webkit-font-smoothing: antialiased;\r\n"
				+ "            mso-margin-top-alt: 0px;\r\n"
				+ "            mso-margin-bottom-alt: 0px;\r\n"
				+ "            mso-padding-alt: 0px 0px 0px 0px;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        p,\r\n"
				+ "        h1,\r\n"
				+ "        h2,\r\n"
				+ "        h3,\r\n"
				+ "        h4 {\r\n"
				+ "            margin-top: 0;\r\n"
				+ "            margin-bottom: 0;\r\n"
				+ "            padding-top: 0;\r\n"
				+ "            padding-bottom: 0;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        span.preheader {\r\n"
				+ "            display: none;\r\n"
				+ "            font-size: 1px;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        html {\r\n"
				+ "            width: 100%;\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        table {\r\n"
				+ "            font-size: 14px;\r\n"
				+ "            border: 0;\r\n"
				+ "        }\r\n"
				+ "        /* ----------- responsivity ----------- */\r\n"
				+ "        \r\n"
				+ "        @media only screen and (max-width: 640px) {\r\n"
				+ "            /*------ top header ------ */\r\n"
				+ "            .main-header {\r\n"
				+ "                font-size: 20px !important;\r\n"
				+ "            }\r\n"
				+ "            .main-section-header {\r\n"
				+ "                font-size: 28px !important;\r\n"
				+ "            }\r\n"
				+ "            .show {\r\n"
				+ "                display: block !important;\r\n"
				+ "            }\r\n"
				+ "            .hide {\r\n"
				+ "                display: none !important;\r\n"
				+ "            }\r\n"
				+ "            .align-center {\r\n"
				+ "                text-align: center !important;\r\n"
				+ "            }\r\n"
				+ "            .no-bg {\r\n"
				+ "                background: none !important;\r\n"
				+ "            }\r\n"
				+ "            /*----- main image -------*/\r\n"
				+ "            .main-image img {\r\n"
				+ "                width: 440px !important;\r\n"
				+ "                height: auto !important;\r\n"
				+ "            }\r\n"
				+ "            /* ====== divider ====== */\r\n"
				+ "            .divider img {\r\n"
				+ "                width: 440px !important;\r\n"
				+ "            }\r\n"
				+ "            /*-------- container --------*/\r\n"
				+ "            .container590 {\r\n"
				+ "                width: 440px !important;\r\n"
				+ "            }\r\n"
				+ "            .container580 {\r\n"
				+ "                width: 400px !important;\r\n"
				+ "            }\r\n"
				+ "            .main-button {\r\n"
				+ "                width: 220px !important;\r\n"
				+ "            }\r\n"
				+ "            /*-------- secions ----------*/\r\n"
				+ "            .section-img img {\r\n"
				+ "                width: 320px !important;\r\n"
				+ "                height: auto !important;\r\n"
				+ "            }\r\n"
				+ "            .team-img img {\r\n"
				+ "                width: 100% !important;\r\n"
				+ "                height: auto !important;\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "        \r\n"
				+ "        @media only screen and (max-width: 479px) {\r\n"
				+ "            /*------ top header ------ */\r\n"
				+ "            .main-header {\r\n"
				+ "                font-size: 18px !important;\r\n"
				+ "            }\r\n"
				+ "            .main-section-header {\r\n"
				+ "                font-size: 26px !important;\r\n"
				+ "            }\r\n"
				+ "            /* ====== divider ====== */\r\n"
				+ "            .divider img {\r\n"
				+ "                width: 280px !important;\r\n"
				+ "            }\r\n"
				+ "            /*-------- container --------*/\r\n"
				+ "            .container590 {\r\n"
				+ "                width: 280px !important;\r\n"
				+ "            }\r\n"
				+ "            .container590 {\r\n"
				+ "                width: 280px !important;\r\n"
				+ "            }\r\n"
				+ "            .container580 {\r\n"
				+ "                width: 260px !important;\r\n"
				+ "            }\r\n"
				+ "            /*-------- secions ----------*/\r\n"
				+ "            .section-img img {\r\n"
				+ "                width: 280px !important;\r\n"
				+ "                height: auto !important;\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "    <!-- [if gte mso 9]><style type=”text/css”>\r\n"
				+ "        body {\r\n"
				+ "        font-family: arial, sans-serif!important;\r\n"
				+ "        }\r\n"
				+ "        </style>\r\n"
				+ "    <![endif]-->\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "<body class=\"respond\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">\r\n"
				+ "    <!-- pre-header -->\r\n"
				+ "    <table style=\"display:none!important;\">\r\n"
				+ "        <tr>\r\n"
				+ "            <td>\r\n"
				+ "                <div style=\"overflow:hidden;display:none;font-size:1px;color:#ffffff;line-height:1px;font-family:Arial;maxheight:0px;max-width:0px;opacity:0;\">\r\n"
				+ "                    Pre-header for the newsletter template\r\n"
				+ "                </div>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "    </table>\r\n"
				+ "    <!-- pre-header end -->\r\n"
				+ "    <!-- header -->\r\n"
				+ "    <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\">\r\n"
				+ "\r\n"
				+ "        <tr>\r\n"
				+ "            <td align=\"center\">\r\n"
				+ "                <table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\">\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"center\">\r\n"
				+ "\r\n"
				+ "                            <table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\">\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\" height=\"70\" style=\"height:70px;\">\r\n"
				+ "                                        <a href=\"\" style=\"display: block; border-style: none !important; border: 0 !important;\"><img width=\"100\" border=\"0\" style=\"display: block; width: 100px;\" src=\"https://firebasestorage.googleapis.com/v0/b/java-a0974.appspot.com/o/userImages%2FConverse_logo.png?alt=media&token=f305dedd-f395-4218-8d61-3bb0719031f7\" alt=\"\" /></a>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                </table>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "    </table>\r\n"
				+ "    <!-- end header -->\r\n"
				+ "\r\n"
				+ "    <!-- big image section -->\r\n"
				+ "    <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\" class=\"bg_color\">\r\n"
				+ "\r\n"
				+ "        <tr>\r\n"
				+ "            <td align=\"center\">\r\n"
				+ "                <table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\">\r\n"
				+ "                    <tr>\r\n"
				+ "\r\n"
				+ "                        <td align=\"center\" class=\"section-img\">\r\n"
				+ "                            <a href=\"\" style=\" border-style: none !important; display: block; border: 0 !important;\"><img src=\"https://images.hdqwalls.com/download/gta-vice-city-skyline-palm-trees-if-1024x768.jpg\" style=\"display: block; width: 590px;\" width=\"590\" border=\"0\" alt=\"\" /></a>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td height=\"20\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"center\" style=\"color: #343434; font-size: 24px; font-family: Quicksand, Calibri, sans-serif; font-weight:700;letter-spacing: 3px; line-height: 35px;\" class=\"main-header\">\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "                            <div style=\"line-height: 35px\">\r\n"
				+ "\r\n"
				+ "                                Xin chào <span style=\"color: #5caad2;\">"+name+"</span>\r\n"
				+ "\r\n"
				+ "                            </div>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td height=\"10\" style=\"font-size: 10px; line-height: 10px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"center\">\r\n"
				+ "                            <table border=\"0\" width=\"40\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"eeeeee\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td height=\"2\" style=\"font-size: 2px; line-height: 2px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td height=\"20\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"center\">\r\n"
				+ "                            <table border=\"0\" width=\"400\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\" style=\"color: #888888; font-size: 16px; font-family: 'Work Sans', Calibri, sans-serif; line-height: 24px;\">\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "                                        <div style=\"line-height: 24px\">\r\n"
				+ "\r\n"
				+ "                                            Chào mừng bạn đã đến với CONVERSE. Dưới đây sẽ là mã xác thực của bạn\r\n"
				+ "                                        </div>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td align=\"center\">\r\n"
				+ "                            <table border=\"0\" align=\"center\" width=\"160\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"5caad2\" style=\"\">\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td height=\"10\" style=\"font-size: 10px; line-height: 10px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\" style=\"color: #ffffff; font-size: 14px; font-family: 'Work Sans', Calibri, sans-serif; line-height: 26px;\">\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "                                        <div style=\"line-height: 26px;\">\r\n"
				+ "                                            <b style=\"color: #ffffff; text-decoration: none;\">"+code+"</b>\r\n"
				+ "                                        </div>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td height=\"10\" style=\"font-size: 10px; line-height: 10px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "                </table>\r\n"
				+ "\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "\r\n"
				+ "    </table>\r\n"
				+ "    <!-- end section -->\r\n"
				+ "\r\n"
				+ "    <!-- contact section -->\r\n"
				+ "    <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"ffffff\" class=\"bg_color\">\r\n"
				+ "\r\n"
				+ "        <tr class=\"hide\">\r\n"
				+ "            <td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "            <td height=\"40\" style=\"font-size: 40px; line-height: 40px;\">&nbsp;</td>\r\n"
				+ "        </tr>\r\n"
				+ "\r\n"
				+ "        <tr>\r\n"
				+ "            <td height=\"60\" style=\"border-top: 1px solid #e0e0e0;font-size: 60px; line-height: 60px;\">&nbsp;</td>\r\n"
				+ "        </tr>\r\n"
				+ "\r\n"
				+ "        <tr>\r\n"
				+ "            <td align=\"center\">\r\n"
				+ "                <table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590 bg_color\">\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td>\r\n"
				+ "                            <table border=\"0\" width=\"300\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\">\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <!-- logo -->\r\n"
				+ "                                    <td align=\"left\">\r\n"
				+ "                                        <a href=\"\" style=\"display: block; border-style: none !important; border: 0 !important;\"><img width=\"80\" border=\"0\" style=\"display: block; width: 80px;\" src=\"https://firebasestorage.googleapis.com/v0/b/java-a0974.appspot.com/o/userImages%2FConverse_logo.png?alt=media&token=f305dedd-f395-4218-8d61-3bb0719031f7\" alt=\"\" /></a>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"left\" style=\"color: #888888; font-size: 14px; font-family: 'Work Sans', Calibri, sans-serif; line-height: 23px;\" class=\"text_color\">\r\n"
				+ "                                        <div style=\"color: #333333; font-size: 14px; font-family: 'Work Sans', Calibri, sans-serif; font-weight: 600; mso-line-height-rule: exactly; line-height: 23px;\">\r\n"
				+ "\r\n"
				+ "                                            Email us: <br/> <a href=\"mailto:\" style=\"color: #888888; font-size: 14px; font-family: 'Hind Siliguri', Calibri, Sans-serif; font-weight: 400;\">nihvpc04493@fpt.edu.vn</a>\r\n"
				+ "\r\n"
				+ "                                        </div>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                            </table>\r\n"
				+ "\r\n"
				+ "                            <table border=\"0\" width=\"2\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td width=\"2\" height=\"10\" style=\"font-size: 10px; line-height: 10px;\"></td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "\r\n"
				+ "                            <table border=\"0\" width=\"200\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\">\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td class=\"hide\" height=\"45\" style=\"font-size: 45px; line-height: 45px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td height=\"15\" style=\"font-size: 15px; line-height: 15px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td>\r\n"
				+ "                                        <table border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "                                            <tr>\r\n"
				+ "                                                <td>\r\n"
				+ "                                                    <a href=\"#\" style=\"display: block; border-style: none !important; border: 0 !important;\"><img width=\"24\" border=\"0\" style=\"display: block;\" src=\"http://i.imgur.com/Qc3zTxn.png\" alt=\"\"></a>\r\n"
				+ "                                                </td>\r\n"
				+ "                                                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n"
				+ "                                                <td>\r\n"
				+ "                                                    <a href=\"#\" style=\"display: block; border-style: none !important; border: 0 !important;\"><img width=\"24\" border=\"0\" style=\"display: block;\" src=\"http://i.imgur.com/RBRORq1.png\" alt=\"\"></a>\r\n"
				+ "                                                </td>\r\n"
				+ "                                                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n"
				+ "                                                <td>\r\n"
				+ "                                                    <a href=\"#\" style=\"display: block; border-style: none !important; border: 0 !important;\"><img width=\"24\" border=\"0\" style=\"display: block;\" src=\"http://i.imgur.com/Wji3af6.png\" alt=\"\"></a>\r\n"
				+ "                                                </td>\r\n"
				+ "                                            </tr>\r\n"
				+ "                                        </table>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "                </table>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "\r\n"
				+ "        <tr>\r\n"
				+ "            <td height=\"60\" style=\"font-size: 60px; line-height: 60px;\">&nbsp;</td>\r\n"
				+ "        </tr>\r\n"
				+ "\r\n"
				+ "    </table>\r\n"
				+ "    <!-- end section -->\r\n"
				+ "\r\n"
				+ "    <!-- footer ====== -->\r\n"
				+ "    <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"f4f4f4\">\r\n"
				+ "\r\n"
				+ "        <tr>\r\n"
				+ "            <td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td>\r\n"
				+ "        </tr>\r\n"
				+ "\r\n"
				+ "        <tr>\r\n"
				+ "            <td align=\"center\">\r\n"
				+ "\r\n"
				+ "                <table border=\"0\" align=\"center\" width=\"590\" cellpadding=\"0\" cellspacing=\"0\" class=\"container590\">\r\n"
				+ "\r\n"
				+ "                    <tr>\r\n"
				+ "                        <td>\r\n"
				+ "                            <table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"left\" style=\"color: #aaaaaa; font-size: 14px; font-family: 'Work Sans', Calibri, sans-serif; line-height: 24px;\">\r\n"
				+ "                                        <div style=\"line-height: 24px;\">\r\n"
				+ "\r\n"
				+ "                                            <span style=\"color: #333333;\">© 2023 Copyright: Converse.Store</span>\r\n"
				+ "\r\n"
				+ "                                        </div>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "\r\n"
				+ "                            <table border=\"0\" align=\"left\" width=\"5\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\">\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td height=\"20\" width=\"5\" style=\"font-size: 20px; line-height: 20px;\">&nbsp;</td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </table>\r\n"
				+ "\r\n"
				+ "                            <table border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"container590\">\r\n"
				+ "\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td align=\"center\">\r\n"
				+ "                                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
				+ "                                            <tr>\r\n"
				+ "                                                <td align=\"center\">\r\n"
				+ "                                                    <a style=\"font-size: 14px; font-family: 'Work Sans', Calibri, sans-serif; line-height: 24px;color: #5caad2; text-decoration: none;font-weight:bold;\"  >THANKS YOU</a>\r\n"
				+ "                                                </td>\r\n"
				+ "                                            </tr>\r\n"
				+ "                                        </table>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "\r\n"
				+ "                            </table>\r\n"
				+ "                        </td>\r\n"
				+ "                    </tr>\r\n"
				+ "\r\n"
				+ "                </table>\r\n"
				+ "            </td>\r\n"
				+ "        </tr>\r\n"
				+ "\r\n"
				+ "        <tr>\r\n"
				+ "            <td height=\"25\" style=\"font-size: 25px; line-height: 25px;\">&nbsp;</td>\r\n"
				+ "        </tr>\r\n"
				+ "\r\n"
				+ "    </table>\r\n"
				+ "    <!-- end footer ====== -->\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
		return x;
	}
}
