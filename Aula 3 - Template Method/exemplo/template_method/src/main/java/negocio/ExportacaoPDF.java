package negocio;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

public class ExportacaoPDF extends Exportacao {

    @Override
    protected String transformacao() {
        try (PdfWriter writer = new PdfWriter(
                "exportado." + this.getClass().getSimpleName().replace("Exportacao", "").toLowerCase());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf)) {
            for (Pessoa pessoa : vetPessoa) {
                Paragraph paragraph = new Paragraph(pessoa.getId()+":"+pessoa.getNome())
                    .setTextAlignment(TextAlignment.LEFT);
            document.add(paragraph);                
            }
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    protected void gravacao(String resultado) {
    }


}
