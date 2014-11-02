package com.util.mail;   
  
import java.lang.reflect.Array;

import javax.mail.*;   
     
public class MyAuthenticator extends Authenticator{   
    String userName=null;   
    String password=null;   
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
 



public static void main(String[] args){   
         //这个类主要是设置邮件   
      MailSenderInfo mailInfo = new MailSenderInfo();    
      mailInfo.setMailServerHost("smtp.qq.com");    
      mailInfo.setMailServerPort("25");    
      mailInfo.setValidate(true);    
      mailInfo.setUserName("610907778@qq.com");    
      mailInfo.setPassword("l,s199101033113");//您的邮箱密码    
      mailInfo.setFromAddress("610907778@qq.com");  
      mailInfo.setNickname("我是冰清雪酷");
/*      mailInfo.setUserName(args[0]);    
      mailInfo.setPassword(args[1]);//您的邮箱密码    
      mailInfo.setFromAddress(args[2]);*/ 
      System.out.println(args[0]);
   //   String[] to={"luosai19910103@163.com","850503695@qq.com","1044479650@qq.com"};
     String[] to=args[0].split(",");
      mailInfo.setToAddress(to); 
      String[] cp=args[1].split(",");
/*      mailInfo.setCopytos(cp);
      String[] ms={"1103469539@qq.com","1810179509@qq.com"};
      mailInfo.setMs(ms);*/
      String subject=args[2];
      mailInfo.setSubject(subject);    
      mailInfo.setContent("系统自动发送，请勿回复");  
/*      String[] imgs={"E:\\图片\\动漫.jpg","E:\\图片\\故宫_颐和园\\DSCN0095.JPG"};// windows 
      String[] files={"E:\\电子书\\励志书\\21岁当总裁.txt"};
      String[] imgs={"/eshopapp/share/img/product/mid/2014/04/09745317-b01f-4a6a-8c05-820399bc719e.jpg","/eshopapp/share/img/product/mid/2014/04/6a634c74-67a3-4f65-a2b4-42ded25b3b63.jpg"};
      String[] files= {"/eshopapp/me.txt","/eshopapp/share/img/product/mid/2014/04/09745317-b01f-4a6a-8c05-820399bc719e.jpg"};//linux
*/     
      /*将文件以正文的形式发送*/
     // String[] fileTocontent=args[3].split(","); 
     // mailInfo.setImgs(fileTocontent);
      String[] files=null;
      if(args[3].indexOf(",")==-1){
    	  files=new String[1];
    	  files[0]=args[3];
      }else{
    	  files=args[3].split(",");
      }
       
      /* 将文件以附件的形式发送*/
      mailInfo.setAttachFileNames(files);
         //这个类主要来发送邮件   
      SimpleMailSender sms = new SimpleMailSender();   
         // sms.sendTextMail(mailInfo);//发送文体格式     
          sms.sendHtmlMail(mailInfo);//发送html格式   
    }  
}