package com.util.mail;   
/**   
* 发送邮件需要使用的基本信息 
*author by wangfun
http://www.5a520.cn 小说520  
*/    
import java.util.Properties;    
public class MailSenderInfo {    
    // 发送邮件的服务器的IP和端口    
    private String mailServerHost;    
    private String mailServerPort = "25";    
    // 邮件发送者的地址    
    private String fromAddress;    
    // 邮件接收者的地址    
    private String[] toAddress;    
    // 登陆邮件发送服务器的用户名和密码    
    private String userName;    
    private String password;    
    // 是否需要身份验证    
    private boolean validate = false;    
    // 邮件主题    
    private String subject;    
    // 邮件的文本内容    
    private String content;    
    // 邮件附件的文件名    
    private String[] attachFileNames;   
    //抄送人
    private String[] copytos;
    //密送人
    private String[] ms;
    //昵称
    private String nickname;
    // 图片
    private String[] imgs;
    
    
	/**   
      * 获得邮件会话属性   
      */    
    public Properties getProperties(){    
      Properties p = new Properties();    
      p.put("mail.smtp.host", this.mailServerHost);    
      p.put("mail.smtp.port", this.mailServerPort);    
      p.put("mail.smtp.auth", validate ? "true" : "false");    
      return p;    
    }    
    public String getMailServerHost() {    
      return mailServerHost;    
    }    
    public void setMailServerHost(String mailServerHost) {    
      this.mailServerHost = mailServerHost;    
    }   
    public String getMailServerPort() {    
      return mailServerPort;    
    }   
    public void setMailServerPort(String mailServerPort) {    
      this.mailServerPort = mailServerPort;    
    }   
    public boolean isValidate() {    
      return validate;    
    }   
    public void setValidate(boolean validate) {    
      this.validate = validate;    
    }   
    public String[] getAttachFileNames() {    
      return attachFileNames;    
    }   
    public void setAttachFileNames(String[] fileNames) {    
      this.attachFileNames = fileNames;    
    }   
    public String getFromAddress() {    
      return fromAddress;    
    }    
    public void setFromAddress(String fromAddress) {    
      this.fromAddress = fromAddress;    
    }   
    public String getPassword() {    
      return password;    
    }   
    public void setPassword(String password) {    
      this.password = password;    
    }   
    public String[] getToAddress() {    
      return toAddress;    
    }    
    public void setToAddress(String[] toAddress) {    
      this.toAddress = toAddress;    
    }    
    public String getUserName() {    
      return userName;    
    }   
    public void setUserName(String userName) {    
      this.userName = userName;    
    }   
    public String getSubject() {    
      return subject;    
    }   
    public void setSubject(String subject) {    
      this.subject = subject;    
    }   
    public String getContent() {    
      return content;    
    }   
    public void setContent(String textContent) {    
      this.content = textContent;    
    }
	public String[] getCopytos() {
		return copytos;
	}
	public void setCopytos(String[] copytos) {
		this.copytos = copytos;
	}
	public String[] getMs() {
		return ms;
	}
	public void setMs(String[] ms) {
		this.ms = ms;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String[] getImgs() {
		return imgs;
	}
	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}    
    
    
}   
