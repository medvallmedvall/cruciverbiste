package actions;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.URL;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import dao.GrilleDao;

import entities.Grille;
import entities.MotGrille;

public class TelechargerGrille {
	private int idGrille;
	private InputStream inputStream;
	private final float CELLSIZE = 50f;


	/**
	 * T�l�charger une grille au format PDF
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {


		Grille g = new Grille();
		GrilleDao gdao = new GrilleDao();
		g = gdao.findById(getIdGrille());
		
		Document document = new Document();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, buffer);
		
		
		

		if(g.getIdTypeGrille() == 2) {
			char [][] grille = new char[g.getHauteur()][g.getLargeur()];
			

			for (int i = 0; i < grille.length; i++) {
				for (int j = 0; j < grille[i].length; j++) {
					grille[i][j] = '0';
				}
			}

			
			PdfPTable tableGrille = new PdfPTable(g.getHauteur() + 1);
			
			float[] cols = new float[g.getLargeur() + 1];
			for (int i = 0; i < cols.length; i++) {
				if (i==0) {
					cols[i]=13f;
				}else{
					cols[i] = CELLSIZE;	
				}
					
			}
			tableGrille.setWidths(cols);
			for (MotGrille mg : g.getMotsGrille()) {
				String mot = mg.getMot();
				if(mg.getOrientation() == 5) {
					//horizontal
					for (int i = 0; i < mot.length(); i++) {
						int x = mg.getCoordX() + i;
						int y = mg.getCoordY();
						grille[x][y] = mot.toCharArray()[i];
					}
				}
				else if (mg.getOrientation() == 6) {
					//vertical
					for (int i = 0; i < mot.length(); i++) {
						int x = mg.getCoordX();
						int y = mg.getCoordY() + i;
						grille[x][y] = mot.toCharArray()[i];
					}
				}	 
			}
			for (int i = 0; i < grille.length + 1; i++) {
				if (i == 0) {
					PdfPCell hCell = new PdfPCell();
					tableGrille.addCell(hCell);
				}
				else  {
					int codeASCII = 64+i;
					char lettre;
					lettre=(char)codeASCII;
					PdfPCell hCell = new PdfPCell(new Paragraph("" + lettre));
					tableGrille.addCell(hCell);
				}
			}
			for (int i=0; i<grille.length; i++) {
				for (int j=0; j < grille[i].length; j++) {
					PdfPCell cell = new PdfPCell();
					cell.setFixedHeight(CELLSIZE);
					if ((j == 0)) {
						PdfPCell hCell = new PdfPCell(new Paragraph("" + (i+1)));
						tableGrille.addCell(hCell);
					}
					cell.setBackgroundColor(BaseColor.BLACK);
					if(grille[j][i] != '0') {
						cell.setBackgroundColor(BaseColor.WHITE);
					}
					tableGrille.addCell(cell);
				}		
			}
			
			PdfPTable tableDefinitions = new PdfPTable(4);
			
			PdfPCell numCell = new PdfPCell(new Paragraph());
			tableDefinitions.addCell(numCell);
			PdfPCell hCell = new PdfPCell(new Paragraph("Horizontalement"));
			tableDefinitions.addCell(hCell);
			PdfPCell alpCell = new PdfPCell(new Paragraph());
			tableDefinitions.addCell(alpCell);
			PdfPCell vCell = new PdfPCell(new Paragraph("Verticalement"));
			tableDefinitions.addCell(vCell);
			ArrayList<String> ver = new ArrayList<String>();
			ArrayList<Integer> ordVer = new ArrayList<Integer>();
			ArrayList<String> hor = new ArrayList<String>();
			ArrayList<Integer> ordHor = new ArrayList<Integer>();
			
		for(MotGrille mg : g.getMotsGrille()) {
			if(mg.getOrientation() == 5){
				hor.add(mg.getDefinition());
				ordHor.add(mg.getCoordY());
			}
			else{//if()
				ver.add(mg.getDefinition());
				ordVer.add(mg.getCoordX());
			}
		}
		
		if(hor.size() == ver.size()){
			for(int i = 0; i < ver.size(); i++){
				PdfPCell cell = new PdfPCell(new Paragraph("" + (ordHor.get(i) + 1 )));
				tableDefinitions.addCell(cell);
				cell = new PdfPCell(new Paragraph(hor.get(i)));
				tableDefinitions.addCell(cell); 
				int codeASCII = 64 + ordVer.get(i) + 1;
				char lettre;
				lettre=(char)codeASCII;
				cell = new PdfPCell(new Paragraph("" + lettre));
				tableDefinitions.addCell(cell);
				cell = new PdfPCell(new Paragraph(ver.get(i)));
				tableDefinitions.addCell(cell); 	
			}
			
		}else{
			if(hor.size() < ver.size()){
				for(int i = 0; i < hor.size(); i++){
					PdfPCell cell = new PdfPCell(new Paragraph("" + (ordHor.get(i) + 1)));
					tableDefinitions.addCell(cell);
					cell = new PdfPCell(new Paragraph(hor.get(i)));
					tableDefinitions.addCell(cell); 
					int codeASCII = 64 + ordVer.get(i) + 1;
					char lettre;
					lettre=(char)codeASCII;
					cell = new PdfPCell(new Paragraph("" + lettre));
					tableDefinitions.addCell(cell);
					cell = new PdfPCell(new Paragraph(ver.get(i)));
					tableDefinitions.addCell(cell); 	
				}
				
				for(int i = hor.size(); i < ver.size(); i++){
					PdfPCell cell = new PdfPCell(new Paragraph(""));
					tableDefinitions.addCell(cell);
					cell = new PdfPCell(new Paragraph(""));
					tableDefinitions.addCell(cell); 
					int codeASCII = 64 + ordVer.get(i) + 1;
					char lettre;
					lettre=(char)codeASCII;
					cell = new PdfPCell(new Paragraph("" + lettre));
					tableDefinitions.addCell(cell);
					cell = new PdfPCell(new Paragraph(ver.get(i)));
					tableDefinitions.addCell(cell); 	
				}
			}else{
				for(int i = 0; i < ver.size(); i++){
					PdfPCell cell = new PdfPCell(new Paragraph("" + (ordHor.get(i) + 1)));
					tableDefinitions.addCell(cell);
					cell = new PdfPCell(new Paragraph(hor.get(i)));
					tableDefinitions.addCell(cell); 
					int codeASCII = 64 + ordVer.get(i) + 1;
					char lettre;
					lettre=(char)codeASCII;
					cell = new PdfPCell(new Paragraph("" + lettre));
					tableDefinitions.addCell(cell);
					cell = new PdfPCell(new Paragraph(ver.get(i)));
					tableDefinitions.addCell(cell); 	
				}
				
				for(int i = ver.size(); i < hor.size(); i++){
					PdfPCell cell = new PdfPCell(new Paragraph("" + (ordHor.get(i) + 1)));
					tableDefinitions.addCell(cell);
					cell = new PdfPCell(new Paragraph(hor.get(i)));
					tableDefinitions.addCell(cell); 
					
					cell = new PdfPCell(new Paragraph(""));
					tableDefinitions.addCell(cell);
					cell = new PdfPCell(new Paragraph(""));
					tableDefinitions.addCell(cell); 	
				}
			}
		}
		document.open();
		document.add(new Paragraph("Grille " + g.getNomGrille()));
		document.add(Chunk.NEWLINE); 
		document.add(Chunk.NEWLINE); 
		document.add(tableGrille);
		System.out.println();
		document.add(Chunk.NEWLINE); 
		document.add(Chunk.NEWLINE); 
		document.add(tableDefinitions);
		document.close();

		setInputStream(new ByteArrayInputStream(buffer.toByteArray()));
		return "success";
		}
		else{
			
			ServletContext servletcontext = ServletActionContext.getServletContext();
			String path = "workspace" + servletcontext.getContextPath() + "/WebContent/images/grilles/";
			System.out.println(path);
			
			System.out.println("grille mot fleche");
			System.out.println("nb mot " + g.getMotsGrille().size());
			System.out.println("Hauteur " + g.getHauteur() + "Largeur " + g.getLargeur());
			System.out.println("Mot :  " + g.getMotsGrille().get(2).getSynonyme());
			System.out.println("X :  " + g.getMotsGrille().get(2).getCoordX() + "Y :  " + g.getMotsGrille().get(2).getCoordY());
			
			
			String [][] grille = new String[g.getHauteur()][g.getLargeur()];
			for(int i=0; i< g.getHauteur(); i++){
				for (int j =0; j<g.getLargeur(); j++){
					grille[i][j] = "";
				}
			}

			
			//Element[][] e = new Element[g.getHauteur()][g.getLargeur()];
			PdfPTable tableGrille = new PdfPTable(g.getLargeur());
			float[] cols = new float[g.getLargeur() + 1];
			for (int i = 0; i < cols.length; i++) {
				if (i==0) {
					cols[i]=13f;
				}else{
					cols[i] = CELLSIZE;	
				}
					
			}
			
			for (MotGrille mg : g.getMotsGrille()) {
				
				int x = mg.getCoordY();
				int y = mg.getCoordX();
				
				if(mg.getOrientation() == 1)
				{
					grille[x][y] = grille[x][y] + "a";
					//grille[x + 1][y] = grille[x + 1][y] + "1";
					
					
				}else{
					if(mg.getOrientation() == 2){
						grille[x][y] = grille[x][y] + "b";
						//grille[x][y + 1] = grille[x][y + 1] + "2";
					}else{
						if(mg.getOrientation() == 3)
						{
							grille[x][y] = grille[x][y] + "c";
							//grille[x + 1][y] = grille[x + 1][y] + "3";
						}
						else{
							grille[x][y] = grille[x][y] + "d";
							//grille[x][y + 1] = grille[x][y + 1] + "4";
						}
					}
				}
				
			}
			

			for (int i=0; i<grille.length; i++) {
				for (int j=0; j < grille[i].length; j++) {
					PdfPCell cell = new PdfPCell(new Phrase(grille[i][j]));
					cell.setFixedHeight(CELLSIZE);
				
					
					if(grille[i][j].length() >1) {
						PdfPTable t = new PdfPTable(1);
						t.setWidthPercentage(100);
						for(MotGrille mg : g.getMotsGrille()){
							if(mg.getCoordX() == j && mg.getCoordY() == i){
								Paragraph p = new Paragraph(new Chunk(mg.getDefinition(), FontFactory.getFont(FontFactory.TIMES, 8f, Font.BOLD, BaseColor.BLACK)));
								t.addCell(p);
							}
						}
						
						cell.addElement(t);
						tableGrille.addCell(cell);

					} else if (grille[i][j].length() == 1){
						if(grille[i][j].equals("1")){
							
						}else if(grille[i][j].equals("2")){
							
							
						}else if(grille[i][j].equals("3")){
							
							
						}else if(grille[i][j].equals("4")){
							
							
						}else{
							for(MotGrille mg : g.getMotsGrille()){
								if(mg.getCoordX() == j && mg.getCoordY() == i){
									Paragraph p = new Paragraph(new Chunk(mg.getDefinition(), FontFactory.getFont(FontFactory.TIMES, 8f, Font.BOLD, BaseColor.BLACK)));
									tableGrille.addCell(p);
								}
							}
						}
					
						
					} else{
						tableGrille.addCell(new Phrase(""));
					}
					
						
						//
					
				}		
			}
			document.open();
			document.add(new Paragraph("Grille " + g.getNomGrille()));
			document.add(Chunk.NEWLINE); 
			document.add(tableGrille);
			
			//Image image = Image.getInstance(path + "bas.png"); 
			
			//document.add(image);
			document.close();
			setInputStream(new ByteArrayInputStream(buffer.toByteArray()));
			return "success";
		}
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
