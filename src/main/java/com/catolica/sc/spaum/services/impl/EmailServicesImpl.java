package com.catolica.sc.spaum.services.impl;

import com.catolica.sc.spaum.model.Analysis;
import com.catolica.sc.spaum.model.TokenAnalysisAccess;
import com.catolica.sc.spaum.repositories.TokenAnalysisAccessRepository;
import com.catolica.sc.spaum.services.EmailServices;
import com.catolica.sc.spaum.utils.CollectionsUtils;
import com.catolica.sc.spaum.utils.CryptographyUtils;
import com.catolica.sc.spaum.vo.EmailDataVo;
import com.catolica.sc.spaum.vo.StudentVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServicesImpl implements EmailServices {

    @Autowired
    TokenAnalysisAccessRepository tokenAnalysisAccessRepository;

    private final JavaMailSender javaMailSender;


    @Override
    public String sendEmail(EmailDataVo emailDataVo, String origin) throws Exception {
        String token = createToken(emailDataVo);
        TokenAnalysisAccess tokenAnalysisAccess = convertEmailDataVoToTokenAnalysisAccess(emailDataVo, token);
        tokenAnalysisAccessRepository.save(tokenAnalysisAccess);
        String title = "Análise dos dados do Curso de " +  CryptographyUtils.decryptAES(emailDataVo.getCourse());
        String body = title;
        body += "\nNome do arquivo analisado: " + CryptographyUtils.decryptAES(emailDataVo.getPath());
        body += "\nTipo da análise: " + emailDataVo.getTypeAnalyses();
        body += "\nLink para acessar a análise:\n " + origin + "/token?token=" + token;
        send(CryptographyUtils.decryptAES(emailDataVo.getForEmail()), title, body);
        return "";
    }

    private TokenAnalysisAccess convertEmailDataVoToTokenAnalysisAccess(EmailDataVo emailDataVo, String token){
        var tokenAnalysisAccess = new TokenAnalysisAccess();
        tokenAnalysisAccess.setToken(token);
        tokenAnalysisAccess.setPath(emailDataVo.getPath());
        tokenAnalysisAccess.setCourse(emailDataVo.getCourse());
        tokenAnalysisAccess.setTypeAnalyses(emailDataVo.getTypeAnalyses());
        for (StudentVo studentVo: emailDataVo.getDataProcessing()) {
            if(CollectionsUtils.isNotEmpty(studentVo.getValue())){
                studentVo.getValue().forEach((studentSubjectVo -> {
                    var analysis = new Analysis();
                    analysis.setSubject(studentSubjectVo.getSubject());
                    analysis.setName(studentSubjectVo.getName());
                    analysis.setRegistration(studentSubjectVo.getRegistration());
                    analysis.setNoteOne(studentSubjectVo.getNoteOne());
                    analysis.setNoteTwo(studentSubjectVo.getNoteTwo());
                    analysis.setNoteThree(studentSubjectVo.getNoteThree());
                    analysis.setNoteSubs(studentSubjectVo.getNoteSubs());
                    analysis.setStatusFouls(studentSubjectVo.getStatusFouls());
                    analysis.setStatusNotes(studentSubjectVo.getStatusNotes());
                    analysis.setTotalFouls(studentSubjectVo.getTotalFouls());
                    tokenAnalysisAccess.getAnalyses().add(analysis);
                }));
            }
        }
        for (Analysis analysis: tokenAnalysisAccess.getAnalyses()) {
            analysis.setTokenAnalysisAccess(tokenAnalysisAccess);
        }
        return tokenAnalysisAccess;
    }

    private String createToken(EmailDataVo emailDataVo){
        String token1 = CryptographyUtils.encodeWithPBKDF2(emailDataVo.getForEmail());
        String token2 = CryptographyUtils.encodeWithPBKDF2(emailDataVo.getPath());
        String token3 = CryptographyUtils.encodeWithPBKDF2(new Timestamp(System.currentTimeMillis()).toString());
        String token = token1.replace("{pbkdf2}", "")
                + token2.replace("{pbkdf2}", "")
                + token3.replace("{pbkdf2}", "");
        if(token.length() > 150){
            token = token.substring(0, 150);
        }
        return token;
    }

    public void send(String forEmail, String title, String body) {
        log.info("Enviando email...");

        var mensagem = new SimpleMailMessage();
        mensagem.setTo(forEmail);

        mensagem.setSubject(title);
        mensagem.setText(body);
        javaMailSender.send(mensagem);
        log.info("Email enviado com sucesso!");
    }
}
