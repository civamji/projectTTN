package security.oauth.events;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    @Autowired
    private JavaMailSender javaMailSender;

   /* @Autowired
    public EmailNotificationService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }*/

    @Async
    public void sendNotification(String subject,String text,String sendTo){
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(sendTo);
        mail.setFrom("sharmashivam637@gmail.com");
        mail.setSubject(subject);
        mail.setText(text);
        javaMailSender.send(mail);
    }


}
