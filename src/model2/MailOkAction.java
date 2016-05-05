package model2;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberTO;
import model1.TempPasswordDAO;

public class MailOkAction implements BasicAction{
		//g메일 계정
		private String fromEmail = "bakanonaka@gmail.com";
		//g메일 암호
		private String password = "dpdlzhs123";
		String cPassword = "";
		
		public void execute(HttpServletRequest request, HttpServletResponse response)  {
			// TODO Auto-generated method stub
			cPassword = CreateTempPassword();			
			
			String toEmail = request.getParameter("email");
			String fromName = "보내는 이름";
			String subject = "제목";
			String innercontent = cPassword;
			String content = "<html><head></head><body><font color='blue'>임시비밀번호는 ["
					+ innercontent
					+ "] 입니다.</font></body></html>";
			
			MemberTO to = new MemberTO();
			
			to.setEmail(toEmail);
			to.setPassword(innercontent);
			
			
			MailOkAction mail = new MailOkAction();
			
			mail.sendMail(toEmail, fromName, subject, content);				

			TempPasswordDAO dao = new TempPasswordDAO();
			int flag = dao.setTempPassword(to);
			
			
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

		public String CreateTempPassword()
        {
            String tempPassword = "";
            String[] alpabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "z" };
            String[] number = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
            String[] special = { "!", "@", "#", "$", "%", "^", "&", "*" };

           Random nalpabet = new Random();
            Random nnumber = new Random();
            Random nspecial = new Random();

            tempPassword = alpabet[nalpabet.nextInt(24)] + alpabet[nalpabet.nextInt(24)] + alpabet[nalpabet.nextInt(24)]
                       + number[nnumber.nextInt(9)] + number[nnumber.nextInt(9)] + number[nnumber.nextInt(9)]
                       + special[nspecial.nextInt(7)] + special[nspecial.nextInt(7)];

          return tempPassword;
        }

		
	}


