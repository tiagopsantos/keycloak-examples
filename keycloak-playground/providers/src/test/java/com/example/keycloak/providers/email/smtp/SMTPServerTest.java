package com.example.keycloak.providers.email.smtp;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.server.SMTPServer;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
class SMTPServerTest {

  @RequiredArgsConstructor
  static class TestMessageHandlerFactory implements MessageHandlerFactory {
    private final Map<String, String> contextData;

    public MessageHandler create(MessageContext ctx) {
      return new TestMessageHandler();
    }

    class TestMessageHandler implements MessageHandler {

      @Override
      public void from(String from) throws RejectException {
        // not required
      }

      @Override
      public void recipient(String recipient) throws RejectException {
        // not required
      }

      @Override
      public void data(InputStream data) throws RejectException, TooMuchDataException, IOException {
        try {
          Session session = Session.getInstance(new Properties());
          MimeMessage mimeMessage = new MimeMessage(session, data);
          contextData.put(mimeMessage.getSubject(), mimeMessage.getMessageID());
        } catch (MessagingException e) {
          throw new IOException(e);
        }
      }

      @Override
      public void done() {
        // not required
      }
    }
  }

  @Test
  void test() throws MessagingException {
    Map<String, String> mailsContextData = Maps.newConcurrentMap();
    TestMessageHandlerFactory messageHandlerFactory = new TestMessageHandlerFactory(mailsContextData);
    SMTPServer smtpServer = new SMTPServer(messageHandlerFactory);
    smtpServer.setPort(2500);
    smtpServer.start();

    Properties mailProperties = new Properties();
    mailProperties.put("mail.smtp.host", "localhost");
    mailProperties.put("mail.smtp.port", "2500");

    int messageCount = 1000;
    Flux.range(0, messageCount)
        .publishOn(Schedulers.boundedElastic())
        .log()
        .flatMap(this::processValue)
        .publishOn(Schedulers.boundedElastic())
        .log()
        .doOnNext(id -> {
              try { sendMail(id, mailProperties); }
              catch (Exception e) { Exceptions.propagate(e); }
        })
        .collectList()
        .block();

    smtpServer.stop();

    assertThat(mailsContextData)
        .hasSize(messageCount);
    log.debug("data: {}", mailsContextData);
  }

  private Mono<Integer> processValue(Integer value) {
    return Mono.just(value)
        .delayElement(Duration.ofMillis(RandomUtils.nextInt(0, 50)));
  }

  private void sendMail(Integer id, Properties mailProperties) throws MessagingException {
    Session session = Session.getInstance(mailProperties);

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress("from@gmail.com"));
    message.setRecipients(
        Message.RecipientType.TO, InternetAddress.parse("to@gmail.com"));
    message.setSubject(id.toString());

    String msg = "This is my first email using JavaMailer";

    MimeBodyPart mimeBodyPart = new MimeBodyPart();
    mimeBodyPart.setContent(msg, "text/html");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(mimeBodyPart);

    message.setContent(multipart);

    Transport.send(message);
  }

}