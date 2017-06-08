package info.idgst.digest;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import info.idgst.config.IdgstConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Freemarker implementation of the {@link DigestTemplateProcessor}.
 *
 * @author Aliaksei Bahdanau
 */
@Service
public class FreemarkerDigestTemplatelProcessor implements DigestTemplateProcessor {

    private final IdgstConfigReader idgstConfigReader;
    private final Configuration cfg;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FreemarkerDigestTemplatelProcessor(IdgstConfigReader idgstConfigReader, Configuration cfg) {
        this.idgstConfigReader = idgstConfigReader;
        this.cfg = cfg;
    }

    @Override
    public String generateDigest(Map<String, Object> model) {
        Writer out = new StringWriter();
        try {
            /* Get the template (uses cache internally) */
            Template template = cfg.getTemplate(idgstConfigReader.getTemplateName(), "UTF-8");

            /* Merge data-model with template */
            template.process(model, out);
            logger.debug(out.toString());
        } catch (TemplateException | IOException e) {
            String msg = "Error while processing template";
            logger.error(msg, e);
            throw new RuntimeException(msg);
        }
        return out.toString();
    }
}
