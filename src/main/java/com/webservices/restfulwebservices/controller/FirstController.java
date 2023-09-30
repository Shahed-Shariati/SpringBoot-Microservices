package com.webservices.restfulwebservices.controller;

import com.webservices.restfulwebservices.model.HelloBean;
import com.webservices.restfulwebservices.producer.MyTextProducer;
import jakarta.servlet.http.HttpServletResponse;
import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.audio.Sample;
import nl.captcha.servlet.CaptchaServletUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

@RestController
public class FirstController {


     private MessageSource messageSource;

    public FirstController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello")
    public String helloWord(){
        return "Hello Word!!!!!!!!";
    }

    @GetMapping(path = "/hello-bean")
    public HelloBean helloWordBean(){
        return  new HelloBean("Hello Word!!!!!!!!!!!!");
    }

    // path parameters
    // users/{id}/todos/{id} => /users/1/todos/101
    @GetMapping(path = "/hello-bean/path-variable/{name}")
    public HelloBean helloWordPathVariable(@PathVariable String name){
        return  new HelloBean(String.format("Hello , %s",name));
    }

    @GetMapping(path = "/hello-Inter")
    public String helloWordInter(){
        Locale locale = LocaleContextHolder.getLocale();
       return messageSource.getMessage("good.morning.message",null,"Default",locale);

    }

    @GetMapping(path = "/captcha")
    public String captchaGenerate(){
        Captcha captcha = new Captcha.Builder(200, 50)
            .addText()
            .addBorder()
            .addNoise()
            .build();
        return captcha.getAnswer();
    }
    @GetMapping(path = "/captcha-audio")
    public String captchaAudioGenerate(HttpServletResponse response){
        AudioCaptcha ac = new AudioCaptcha.Builder()
           .addAnswer(new MyTextProducer())

           .build(); // Required


        writeAudio(response,ac.getChallenge());
        return ac.getAnswer();
    }

    private  void writeAudio(HttpServletResponse response, Sample sample) {
        response.setHeader("Cache-Control", "private,no-cache,no-store");
        response.setContentType("audio/x-wav");

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            AudioSystem.write(sample.getAudioInputStream(), AudioFileFormat.Type.WAVE, baos);
            response.setContentLength(baos.size());
            OutputStream os = response.getOutputStream();
            os.write(baos.toByteArray());
            os.flush();
            os.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }


}
