package actions;





import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import dao.GrilleDao;

import entities.Grille;
import entities.MotGrille;

public class TelechargerGrille {

	private int idGrille;
	private InputStream inputStream;
	
	
	
	public String execute() throws Exception
	{
		
		
		 Grille g = new Grille();
		 GrilleDao gdao = new GrilleDao();
		 
		 g = gdao.findById(getIdGrille());


		Document document = new Document();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, buffer);
		document.open();
		for (MotGrille mg:g.getMotsGrille())
		{
			document.add(new Paragraph(mg.getMot() + "   " + mg.getDefinition()));
		}
		

		document.close();
		setInputStream(new ByteArrayInputStream(buffer.toByteArray()));
		return "success";
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public void setIdGrille(int idGrille) {
		this.idGrille = idGrille;
	}
	
	public int getIdGrille() {
		return idGrille;
	}
}
