package pe.edu.upeu.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import pe.edu.upeu.model.Encomienda;

import java.io.FileOutputStream;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfUtil {

    public static void exportar(
            List<Encomienda> lista) {

        try {

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(
                            "reporte_encomiendas.pdf"
                    )
            );

            document.open();

            document.add(
                    new Paragraph(
                            "REPORTE DE ENCOMIENDAS"
                    )
            );
            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy HH:mm:ss"
                    );

            document.add(
                    new Paragraph(
                            "Fecha de generación: "
                                    + LocalDateTime.now()
                                    .format(formato)
                    )
            );
            document.add(
                    new Paragraph(
                            "Total de encomiendas: "
                                    + lista.size()
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(" ")
            );

            PdfPTable tabla =
                    new PdfPTable(8);

            tabla.addCell("Código");
            tabla.addCell("Remitente");
            tabla.addCell("Destinatario");
            tabla.addCell("Origen");
            tabla.addCell("Destino");
            tabla.addCell("Estado");
            tabla.addCell("Peso");
            tabla.addCell("Precio");

            for (Encomienda e : lista) {

                tabla.addCell(
                        e.getCodigo()
                );

                tabla.addCell(
                        e.getRemitente()
                );

                tabla.addCell(
                        e.getDestinatario()
                );

                tabla.addCell(
                        e.getOrigen()
                );

                tabla.addCell(
                        e.getDestino()
                );

                tabla.addCell(
                        e.getEstado().name()
                );
                tabla.addCell(
                        String.valueOf(
                                e.getPeso()
                        )
                );

                tabla.addCell(
                        String.valueOf(
                                e.getPrecio()
                        )
                );
            }

            document.add(tabla);

            document.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}