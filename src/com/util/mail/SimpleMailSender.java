package com.util.mail;   
  
import java.io.UnsupportedEncodingException;
import java.util.Date;    
import java.util.Properties;   

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;    
import javax.mail.BodyPart;    
import javax.mail.Message;    
import javax.mail.MessagingException;    
import javax.mail.Multipart;    
import javax.mail.Session;    
import javax.mail.Transport;    
import javax.mail.internet.InternetAddress;    
import javax.mail.internet.MimeBodyPart;    
import javax.mail.internet.MimeMessage;    
import javax.mail.internet.MimeMultipart;    
import javax.mail.internet.MimeUtility;
  
/**   
* 简单邮件（不带附件的邮件）发送器   
http://www.bt285.cn BT下载
*/    
public class SimpleMailSender  {    
/**   
  * 以文本格式发送邮件   
  * @param mailInfo 待发送的邮件的信息   
  */    
    public boolean sendTextMail(MailSenderInfo mailInfo) {    
      // 判断是否需要身份认证    
      MyAuthenticator authenticator = null;    
      Properties pro = mailInfo.getProperties();   
      if (mailInfo.isValidate()) {    
      // 如果需要身份认证，则创建一个密码验证器    
        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
      }   
      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
      try {    
      // 根据session创建一个邮件消息    
      Message mailMessage = new MimeMessage(sendMailSession);    
      
      if(mailInfo.getNickname()!=null ||"".equals(mailInfo.getNickname())){
	    	//设置自定义发件人昵称  
	          String nick="";  
	          try {  
	              nick=javax.mail.internet.MimeUtility.encodeText(mailInfo.getNickname());  
	              mailMessage.setFrom(new InternetAddress(mailInfo.getFromAddress(),nick)); 
	              System.out.println("自定义昵称为："+nick);
	          } catch (UnsupportedEncodingException e) {  
	              e.printStackTrace(); 
	              System.out.println("自定义昵称异常");
	          }         
      }else{
    	// 创建邮件发送者地址    
          Address from = new InternetAddress(mailInfo.getFromAddress());    
          // 设置邮件消息的发送者    
          mailMessage.setFrom(from);  
      }
      // 创建邮件的接收者地址，并设置到邮件消息中    
      if(mailInfo.getToAddress()!=null&&!"".equals(mailInfo.getToAddress())){
      InternetAddress[] to = new InternetAddress().parse(getMailList(mailInfo.getToAddress()));    
      mailMessage.setRecipients(Message.RecipientType.TO,to);   
      }  
      //设置邮件的抄送者，并设置到邮件的消息中
   	  if (mailInfo.getCopytos() != null&&!"".equals(mailInfo.getToAddress())) {
   		   InternetAddress[] cps = new InternetAddress().parse(getMailList(mailInfo.getCopytos()));    
   	      mailMessage.setRecipients(Message.RecipientType.CC,cps);   
   	   }
   	  //密送
   	 if (mailInfo.getMs() != null&&!"".equals(mailInfo.getMs())) {
		      InternetAddress[] ms = new InternetAddress().parse(getMailList(mailInfo.getMs()));    
	   	      mailMessage.setRecipients(Message.RecipientType.BCC,ms);   
	  }
      // 设置邮件消息的主题    
      mailMessage.setSubject(mailInfo.getSubject());    
      // 设置邮件消息发送的时间    
      mailMessage.setSentDate(new Date());   
   /*   Multipart mainPart = new MimeMultipart();
      //设置附件
      try {
		addTach(mailInfo.getAttachFileNames(), mainPart);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		System.out.println("添加附件失败");
		e.printStackTrace();
	}*/
      // 设置邮件消息的主要内容    
      String mailContent = mailInfo.getContent();    
      mailMessage.setText(mailContent);    
      // 发送邮件    
      Transport.send(mailMessage);   
      return true;    
      } catch (MessagingException ex) {    
          ex.printStackTrace();    
      }    
      return false;    
    }    
       
    /**   
      * 以HTML格式发送邮件   
      * @param mailInfo 待发送的邮件信息   
      */    
    public  boolean sendHtmlMail(MailSenderInfo mailInfo){    
      // 判断是否需要身份认证    
      MyAuthenticator authenticator = null;   
      Properties pro = mailInfo.getProperties();   
      //如果需要身份认证，则创建一个密码验证器     
      if (mailInfo.isValidate()) {    
        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());   
      }    
      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
      try {    
      // 根据session创建一个邮件消息    
      Message mailMessage = new MimeMessage(sendMailSession);    
      // 创建邮件发送者地址    
      if(mailInfo.getNickname()!=null &&!"".equals(mailInfo.getNickname())){
	    	//设置自定义发件人昵称  
	          String nick="";  
	          try {  
	              nick=javax.mail.internet.MimeUtility.encodeText(mailInfo.getNickname());  
	              mailMessage.setFrom(new InternetAddress(mailInfo.getFromAddress(),nick)); 
	              System.out.println("自定义昵称为："+nick);
	          } catch (UnsupportedEncodingException e) {  
	              e.printStackTrace(); 
	              System.out.println("自定义昵称异常");
	          }         
    }else{
  	// 创建邮件发送者地址    
        Address from = new InternetAddress(mailInfo.getFromAddress());    
        // 设置邮件消息的发送者    
        mailMessage.setFrom(from);  
    }  
      // 创建邮件的接收者地址，并设置到邮件消息中    
      if(mailInfo.getToAddress()!=null&&!"".equals(mailInfo.getToAddress())){
	      InternetAddress[] to = new InternetAddress().parse(getMailList(mailInfo.getToAddress()));    
	      mailMessage.setRecipients(Message.RecipientType.TO,to);  
	      System.out.println("添加接收人成功");
      }  
      //设置邮件的抄送者，并设置到邮件的消息中
   	  if (mailInfo.getCopytos() != null&&!"".equals(mailInfo.getToAddress())) {
   		   InternetAddress[] cps = new InternetAddress().parse(getMailList(mailInfo.getCopytos()));    
   		   mailMessage.setRecipients(Message.RecipientType.CC,cps); 
   		   System.out.println("添加抄送人成功");
   	   }
   	  //密送
   	 if (mailInfo.getMs() != null&&!"".equals(mailInfo.getMs())) {
		      InternetAddress[] ms = new InternetAddress().parse(getMailList(mailInfo.getMs()));    
	   	      mailMessage.setRecipients(Message.RecipientType.BCC,ms);  
	   	      System.out.println("添加密送人成功");
	  }
      // 设置邮件消息的主题    
      mailMessage.setSubject(mailInfo.getSubject());    
      // 设置邮件消息发送的时间    
      mailMessage.setSentDate(new Date());    
      // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
      MimeMultipart mainPart = new MimeMultipart();    
      // 创建一个包含HTML内容的MimeBodyPart    
      BodyPart html = new MimeBodyPart();    
      // 设置HTML内容    
      String text=mailInfo.getContent();
      html.setContent(text, "text/html; charset=utf-8");   
      //添加图片
      if(mailInfo.getImgs()!=null&&!"".equals(mailInfo.getImgs())){
    	  MimeMultipart mm = new MimeMultipart();
    	  try {
    		StringBuffer texts=new StringBuffer(text); 
			addImg(mailInfo.getImgs(),texts, mm);//创建图片用于在邮件中显示
			System.out.println(texts.toString());
			html.setContent(texts.toString(), "text/html; charset=utf-8");
			mm.addBodyPart(html);
			mm.setSubType("related");// 设置正文与图片之间的关系
			// 图班与正文的 body 
			MimeBodyPart all = new MimeBodyPart();
			all.setContent(mm);
			mainPart.addBodyPart(all);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
      }
		    //设置附件
			if (mailInfo.getAttachFileNames() != null
					&& !"".equals(mailInfo.getAttachFileNames())) {
				try {
					addTach(mailInfo.getAttachFileNames(), mainPart);
					System.out.println("添加附件成功");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					System.out.println("添加附件失败");
					e.printStackTrace();
				}
			}
		if(mailInfo.getImgs()==null||"".equals(mailInfo.getImgs())){	
			mainPart.addBodyPart(html); 
			System.out.println("添加正文内容");
		}
		mainPart.setSubType("mixed");
      // 将MiniMultipart对象设置为邮件内容    
      mailMessage.setContent(mainPart);
      mailMessage.saveChanges();
      // 发送邮件    
      Transport.send(mailMessage);   
      System.out.println("发送成功");
      return true;    
      } catch (MessagingException ex) {    
          ex.printStackTrace();  
          System.out.println("发送失败！");
      }    
      return false;    
    }    
    /** 多个接收人   */
    private static String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
					System.out.println(mailArray[i]);
				}

			}
		}
		return toList.toString();

	}
    /* 添加多个附件*/
	public static void addTach(String fileList[], Multipart multipart)
			throws MessagingException, UnsupportedEncodingException {
		for (int index = 0; index < fileList.length; index++) {
			MimeBodyPart mailArchieve = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(fileList[index]);
			mailArchieve.setDataHandler(new DataHandler(fds));
			//mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(),"GBK", "B"));
			mailArchieve.setFileName(MimeUtility.encodeWord(fds.getName()));
			System.out.println(MimeUtility.encodeWord(fds.getName()));
			multipart.addBodyPart(mailArchieve);
		}
	}
	 /* 添加多个图片*/
		public static void addImg(String fileList[],StringBuffer text, Multipart multipart)
				throws MessagingException, UnsupportedEncodingException {
			for (int i = 0; i < fileList.length; i++) {
				MimeBodyPart img = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(fileList[i]);
				img.setDataHandler(new DataHandler(fds));
				//mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(),"GBK", "B"));
			//	img.setFileName(MimeUtility.encodeWord(fds.getName()));
				String cid=""+i;
				img.setContentID(cid);
				System.out.println(cid);
				System.out.println(MimeUtility.encodeWord(fds.getName()));
				multipart.addBodyPart(img);
				text=text.append("<a href='http://www.baidu.com'><img src='cid:").append(cid).append("/></a>");
				System.out.println(text.toString());
			}
		}
    
}   