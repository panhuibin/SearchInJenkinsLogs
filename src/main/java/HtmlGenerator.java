import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Teo on 10/11/2016.
 */
public class HtmlGenerator {
    private File file;
    private boolean append;
    private String content = "";

    public HtmlGenerator(File file, boolean append) throws IOException {
        this.file = file;
        this.append = append;
    }

    public HtmlGenerator(File file) throws IOException {
        this(file, false);
    }

    public HtmlGenerator startTable() {
        content = content.concat("<table>\n");
        return this;
    }

    public HtmlGenerator endTable() {
        content = content.concat("</table>\n");
        return this;
    }

    public HtmlGenerator startRow() {
        content = content.concat("<tr>\n");
        return this;
    }

    public HtmlGenerator endRow() {
        content = content.concat("</tr>\n");
        return this;
    }

    public HtmlGenerator startColumn() {
        content = content.concat("<td>\n");
        return this;
    }

    public HtmlGenerator endColumn() {
        content = content.concat("</td>\n");
        return this;
    }

    public HtmlGenerator addParagraph(String text) {
        content = content.concat("<p>").concat(text).concat("</p>\n");
        return this;
    }

    public HtmlGenerator addLink(String name, String link) {
        content = content.concat("<a href=\"").concat(link).concat("\">").concat(name).concat("</a>\n");
        return this;
    }

    public void saveHtmlFile() throws IOException {
        FileUtils.write(file, content, append);
    }
}
