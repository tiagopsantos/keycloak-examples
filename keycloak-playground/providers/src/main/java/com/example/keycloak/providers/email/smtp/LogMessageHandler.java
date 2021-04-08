package com.example.keycloak.providers.email.smtp;

import com.google.common.io.CharStreams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.RejectException;

@RequiredArgsConstructor
@Slf4j
class LogMessageHandler implements MessageHandler {

  private final MessageContext ctx;


  @Override
  public void from(String from) throws RejectException {
    log.info("FROM: {}", from);
  }

  @Override
  public void recipient(String recipient) throws RejectException {
    log.info("RECIPIENT: {}", recipient);
  }

  @Override
  public void data(InputStream data) throws IOException {
    try {
      String lineSeparator = System.lineSeparator();
      String separator = "= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =";
      String message = convertStreamToString(data);
//      MimeMessage mimeMessage = getMimeMessage(data);
      MimeMessage mimeMessage = getMimeMessage(new ByteArrayInputStream(message.getBytes()));

      Function<Stream<Address>, String> toStringAddressStream = stream ->
          stream.map(Address::toString).collect(Collectors.joining());

      log.info("MAIL DATA{}{}{}{}{}{}",
          lineSeparator,
          separator, lineSeparator,
          message, lineSeparator,
          separator);
      log.info("mimeMessage :: subject:{} | from:{} | to:{}",
          mimeMessage.getSubject(),
          toStringAddressStream.apply(Arrays.stream(mimeMessage.getFrom())),
          toStringAddressStream.apply(Arrays.stream(mimeMessage.getRecipients(RecipientType.TO)))
      );
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      throw new IOException(new IllegalArgumentException(e));
    }
  }

  @Override
  public void done() {
    log.info("Finished");
  }

  private String convertStreamToString(InputStream is) throws IOException {
    try (Reader reader = new InputStreamReader(is)) {
      return CharStreams.toString(reader);
    }
  }

  private MimeMessage getMimeMessage(InputStream is) throws MessagingException {
    Session s = Session.getInstance(new Properties());
    return new MimeMessage(s, is);
  }

}
