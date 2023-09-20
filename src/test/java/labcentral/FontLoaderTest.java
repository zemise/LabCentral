package labcentral;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FontLoaderTest {
    @Autowired
    private FontLoader fontLoader;

    @Test
    void testFontLoader(){
        fontLoader.loadCustomFont();
    }
}
