package model2;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MyAuth extends Authenticator{	
		private String fromEmail;
		private String password;
		
		public MyAuth(String fromEmail, String password) {
			
			this.fromEmail = fromEmail;
			this.password = password;
		}
		
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			// TODO Auto-generated method stub
			return new PasswordAuthentication(fromEmail, password);
					
		}
		
	}


