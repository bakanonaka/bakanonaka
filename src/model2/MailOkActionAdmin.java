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

public class MailOkActionAdmin implements BasicAction{
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
			
			System.out.println("innerContent" + innerContent);
			System.out.println("toEmail" + toEmail);
			
			String fromName = "RedLipstick 회원님들";
			String subject = "RedLipstick 단체메일입니다.";			
			String content = "<html><head></head><body><font color='blue'>안녕하세요, RedLipstick입니다.<br> "
					+ innerContent
					+ "<br> 감사합니다.<br>***여러분의 취향을 존중하는 RedLipstick***</font></body></html>";
			
			for (int i=0 ; i < email.length ; i++) {
				
				if(email.length > 2 || !email.equals("")) {
//					MailOkActionAdmin mail = new MailOkActionAdmin();
//					mail.sendMail(email[i], fromName, subject, content);
					
					sendMail(email[i], fromName, subject, content);
					
				} else {
					System.out.println("이메일주소 리스트에 공백이 있음");
				}
			}
							

			int flag = 0;
		
			request.setAttribute("flag", flag);
			
		}
		
		public void sendMail(String toEmail, String fromName, String subject, String content) {
			// SMPT 서버에 대한 접속관계 설정
			
			try {
				
			Properties props = new Properties();
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
				
			//System.out.println("전송이 완료되었습니다");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}


		
	}


