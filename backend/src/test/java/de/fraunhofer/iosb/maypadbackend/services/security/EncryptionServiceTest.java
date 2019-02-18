package de.fraunhofer.iosb.maypadbackend.services.security;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptionServiceTest {

    @Value("${MAYPAD_HOME:/usr/share/maypad/}")
    private String maypadHomePath;

    @Autowired
    EncryptionService encryptionService;

    @Rule
    public ExpectedException expectedException;

    @ClassRule
    public static final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Test
    public void testEncryption() {
        final String password = "12345password54321";
        EncryptedText encryptedText = encryptionService.encrypt(password);
        assertThat(encryptedText).isNotNull();
        assertThat(encryptedText.getSalt()).isNotEqualTo("");
        assertThat(encryptedText.getText()).isNotEqualTo("");

        String decryptedText = encryptionService.decrypt(encryptedText.getText(), encryptedText.getSalt());

        assertThat(decryptedText).isEqualTo(password);

    }

    @Test
    public void testSingleton() {
        final String password = "12345password54321";
        EncryptedText encryptedText = EncryptionService.encryptText(password);
        assertThat(encryptedText).isNotNull();
        assertThat(encryptedText.getSalt()).isNotEqualTo("");
        assertThat(encryptedText.getText()).isNotEqualTo("");

        String decryptedText = EncryptionService.decryptText(encryptedText.getText(), encryptedText.getSalt());

        assertThat(decryptedText).isEqualTo(password);
    }

    @Test
    public void testInit() throws Exception {
        FileUtils.deleteDirectory(new File(maypadHomePath.concat("/security/")));
        final String password = "12345password54321";
        EncryptedText encryptedText = EncryptionService.encryptText(password);
        String decrypted = EncryptionService.decryptText(encryptedText.getText(), encryptedText.getSalt());
        assertThat(decrypted).isEqualTo(password);

    }
}
