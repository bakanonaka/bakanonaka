package model2;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberTO;
import model1.ModifyDAO;
import model1.TempPasswordDAO;

public class MailOkActionAdminKick implements BasicAction{
		//g메일 계정
		private String fromEmail = "bakanonaka@gmail.com";
		//g메일 암호
		private String password = "dpdlzhs123";

		public void execute(HttpServletRequest request, HttpServletResponse response)  {
			// TODO Auto-generated method stub
			String innerContent = request.getParameter("content");
			//String toEmail = request.getParameter("email");
			String toEmail = request.getParameter("email");
			String[] email = toEmail.split(",");
			
			//System.out.println(innerContent + toEmail);
			String fromName = "RedLipstick에서 강제탈퇴 되셨습니다.";
			String subject = "RedLipstick에서 강제탈퇴 되셨습니다.";			
			String content = "<html><head></head><body><font color='blue'>안녕하세요, RedLipstick입니다.<br> "					
					+ "<br> ---다음과 같은 이유로 탈퇴처리 되셨습니다.---<br>"
					+ innerContent
					+ "<br>감사합니다.</font></body></html>";
			
			for (int i=0 ; i < email.length ; i++) {
				MailOkActionAdminKick mail = new MailOkActionAdminKick();
				
				mail.sendMail(email[i], fromName, subject, content);	
			}
												

			MemberTO to = new MemberTO();
			
			to.setEmail(toEmail);			
			
			ModifyDAO dao = new ModifyDAO();
			int flag = dao.byebyeMembers(email);	 			
			
			request.setAttribute("flag", flag);
			
		}
		
		public void sendMail(String toEmail, String fromName, String subject, String content) {
			// SMPT 서버에 대한 접속관계 설정
			Properties props = new Properties();
			try {
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			
			MyAuth auth = new MyAuth(fromEmail, password);
			Session sess = Session.getDefaultInstance(props, auth);
			
			// 메시지 작성(객체임)
			MimeMessage msg = new MimeMessage(sess);
			
			msg.setHeader("content-type", "text/plain; charset=utf-8");
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, fromName, "utf-8"));
			msg.setSubject(subject);
			msg.setContent(content, "text/html; charset=utf-8");
			msg.setSentDate(new java.util.Date());
				
			Transport.send(msg);
				
			System.out.println("전송이 완료되었습니다");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}


		
	}


