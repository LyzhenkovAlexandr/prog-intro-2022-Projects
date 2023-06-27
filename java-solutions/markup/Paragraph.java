package markup;
import java.util.List;
public class Paragraph implements Mark {
    private final List<Mark> array;
    public Paragraph(List<Mark> array) {
        this.array = array;
    }
    public void toMarkdown(StringBuilder s) {
        for (Mark el : array) {
            el.toMarkdown(s);
        }
    }
    public void toTex(StringBuilder s) {
        for (Mark el : array) {
            el.toTex(s);
        }
    }
}