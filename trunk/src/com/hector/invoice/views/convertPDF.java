/**
 * 
 */
package com.hector.invoice.views;

/**
 * @author Duchha
 *
 */

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.hector.invoice.common.InvoiceInfo;
import com.hector.invoice.dto.CompanyDTO;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.InvoiceOrderNumberInfoView;
import com.hector.invoice.lib.ExternalStorage;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class convertPDF {

	// private static String FILE = Environment.getExternalStorageDirectory()
	// + File.separator + "firstPdf.pdf";
	File fi;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	private static convertPDF instance = null;
	private static Context context = null;
	InvoiceOrderNumberInfoView invoiceInfo = new InvoiceOrderNumberInfoView();
	CompanyDTO companyInfo = new CompanyDTO();

	public convertPDF(Context c, InvoiceOrderNumberInfoView invoiceInfo,
			CompanyDTO myCompany) {
		context = c;
		// fi = new File(ExternalStorage.getFileDBPath(
		// InvoiceInfo.getInstance().getAppContext()).getAbsolutePath(),
		// "MyPDFFILE.pdf");
		this.invoiceInfo = invoiceInfo;
		this.companyInfo = myCompany;
	}

	public void createFilePDF_R(String fileName) {
		fi = new File(ExternalStorage.getFileDBPath(
				InvoiceInfo.getInstance().getAppContext()).getAbsolutePath(),
				fileName);
		createPDF_R();
	}

	public void createFilePDF_L(String fileName) {
		fi = new File(ExternalStorage.getFileDBPath(
				InvoiceInfo.getInstance().getAppContext()).getAbsolutePath(),
				fileName);
		createPDF_L();
	}

	public void createFilePDF_A(String fileName) {
		fi = new File(ExternalStorage.getFileDBPath(
				InvoiceInfo.getInstance().getAppContext()).getAbsolutePath(),
				fileName);
		createPDF_A();
	}

	// public static convertPDF getinstance(Context context,
	// InvoiceOrderNumberInfoView invoiceInfo) {
	// if (instance == null) {
	// instance = new convertPDF(context, invoiceInfo);
	// }
	// return instance;
	// }

	void createPDF_R() {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(fi));
			document.open();
			addMetaData(document);
			createImage(document);

			// addTitlePage(document);
			addContent_R(document);

			document.close();
		} catch (Exception e) {
		}

	}

	void createPDF_L() {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(fi));
			document.open();
			addMetaData(document);

			createImage(document);

//			addTitlePage(document);
			addContent_L(document);
			document.close();
		} catch (Exception e) {
		}

	}

	void createPDF_A() {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(fi));
			document.open();
			addMetaData(document);

			createImage(document);

			addTitlePage(document);
			addContent_R(document);
			document.close();
		} catch (Exception e) {
		}

	}

	/**
	 * Noi dung can mo ta
	 * 
	 * @author: DucHHA
	 * @return: void
	 * @throws:
	 */
	private void createImage(Document document) {
		try {
			if (this.companyInfo.logo != null
					&& this.companyInfo.logo.length > 0) {
				Image image = Image.getInstance(this.companyInfo.logo);
				image.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
				image.setBorder(Image.BOX);
				image.setBorderWidth(15);
				document.add(image);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void addMetaData(Document document) {
		document.addTitle("demo PDF");
		document.addSubject("Using lib iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("zinduchuynh");
		document.addCreator("zinduchuynh");
	}

	private static void addTitlePage(Document document)
			throws DocumentException {
		Paragraph preface = new Paragraph();

		// xuong 1 dong
		addEmptyLine(preface, 1);

		// Lets write a big header
		preface.add(new Paragraph("Tieu de cua tai lieu", catFont));

		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Duoc tao boi: "
				+ System.getProperty("user.name") + ", " + new Date(),
				smallBold));

		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Noi dung can xuat ra", smallBold));

		document.add(preface);

		// Start a new page
		document.newPage();
	}

	private void addContent_R(Document document) throws DocumentException {

		Anchor anchor = new Anchor("ESTIMATING APP", catFont);
		anchor.setName("ESTIMATING APP");

		// chuong 1
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("Giới thiệu", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Chào mừng các ban đến với zinduchuynh"));

		// subPara = new Paragraph("Bắt đầu", subFont);
		// subCatPart = catPart.addSection(subPara);
		String line = "Firma" + "				" + this.companyInfo.companyName;
		subCatPart.add(new Paragraph(line));
		line = this.invoiceInfo.invoiceOrder.contactInvoice.contactName
				+ "				" + this.companyInfo.companyAddress;
		subCatPart.add(new Paragraph(line));
		if (this.invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			line = "Herr "
					+ this.invoiceInfo.invoiceOrder.contactInvoice.firstName
					+ "				" + this.companyInfo.companyPLZ + " "
					+ this.companyInfo.companyCity;
		} else {
			line = "Frau "
					+ this.invoiceInfo.invoiceOrder.contactInvoice.firstName
					+ "				" + this.companyInfo.companyPLZ + " "
					+ this.companyInfo.companyCity;
		}
		subCatPart.add(new Paragraph(line));

		line = this.invoiceInfo.invoiceOrder.contactInvoice.contactAddress;
		subCatPart.add(new Paragraph(line));

		line = this.invoiceInfo.invoiceOrder.contactInvoice.contactPLZ + " "
				+ this.invoiceInfo.invoiceOrder.contactInvoice.contactStadt;
		subCatPart.add(new Paragraph(line));

		line = "							" + "lhre Ansprechpartner/in";
		subCatPart.add(new Paragraph(line));

		if (this.companyInfo.sex == ContactDTO.SEX_MALE) {
			line = "							" + "Herr " + this.companyInfo.certificateOfOrigin;
		} else {
			line = "							" + "Faur" + this.companyInfo.certificateOfOrigin;
		}
		subCatPart.add(new Paragraph(line));

		line = "							" + "Tel: " + this.companyInfo.telephone;
		subCatPart.add(new Paragraph(line));

		line = "							" + "Fax: " + this.companyInfo.fax;
		subCatPart.add(new Paragraph(line));

		line = "							" + "Email: " + this.companyInfo.email;
		subCatPart.add(new Paragraph(line));

		line = "							" + this.companyInfo.unitedStatesT;
		subCatPart.add(new Paragraph(line));

		subCatPart.add(new Paragraph("Rechnung", catFont));

		Date currentDateTime = new Date();
		SimpleDateFormat format = null;
		format = new SimpleDateFormat("dd.MM.yyyy");
		line = "							" + "Datum: " + format.format(currentDateTime);
		subCatPart.add(new Paragraph(line));

		line = "							" + "Rechnungsnr: " + "file name";
		subCatPart.add(new Paragraph(line));

		PdfPTable table = new PdfPTable(5);
		PdfPCell c1 = new PdfPCell(new Phrase("Pos"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Bezeichnung"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Menge"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Einzelpreis"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Gesamt"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		table.setHeaderRows(1);
		long total = 0;
		for (int i = 0; i < this.invoiceInfo.listOrderDetail.size(); i++) {

			table.addCell(this.invoiceInfo.listOrderDetail.get(i).pos);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).designation);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).quantity);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).single_price);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).total);
			total += Long
					.parseLong(this.invoiceInfo.listOrderDetail.get(i).total);
		}
		subCatPart.add(table);

		line = "							" + "Zwischensumme: " + "	" + String.valueOf(total);
		subCatPart.add(new Paragraph(line));

		float vat = total * Integer.valueOf(this.companyInfo.vatValue);
		line = "							" + this.companyInfo.vatText + "	" + String.valueOf(vat);
		subCatPart.add(new Paragraph(line));

		line = "							" + "Gesamtsumme: " + "	" + String.valueOf(total + vat);
		subCatPart.add(new Paragraph(line));

//		subCatPart.add(new Paragraph("Import thư viện"));
//		subCatPart.add(new Paragraph("Run program"));
//
//		// Add a list
//		createList(subCatPart);
//
//		Paragraph paragraph = new Paragraph();
//		addEmptyLine(paragraph, 5);
//		subCatPart.add(paragraph);
//
//		// Add a table
//		createTable(subCatPart);

		// Now add all this to the document
		document.add(catPart);
		document.newPage();

	}
	private void addContent_L(Document document) throws DocumentException {
		
		Anchor anchor = new Anchor("ESTIMATING APP", catFont);
		anchor.setName("ESTIMATING APP");
		
		// chuong 1
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);
		
		Paragraph subPara = new Paragraph("introduce", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Welcome to pdf"));
		
		// subPara = new Paragraph("Bắt đầu", subFont);
		// subCatPart = catPart.addSection(subPara);
		String line = "Firma" + "				" + this.companyInfo.companyName;
		subCatPart.add(new Paragraph(line));
		line = this.invoiceInfo.invoiceOrder.contactInvoice.contactName
				+ "				" + this.companyInfo.companyAddress;
		subCatPart.add(new Paragraph(line));
		if (this.invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			line = "Herr "
					+ this.invoiceInfo.invoiceOrder.contactInvoice.firstName
					+ "				" + this.companyInfo.companyPLZ + " "
					+ this.companyInfo.companyCity;
		} else {
			line = "Frau "
					+ this.invoiceInfo.invoiceOrder.contactInvoice.firstName
					+ "				" + this.companyInfo.companyPLZ + " "
					+ this.companyInfo.companyCity;
		}
		subCatPart.add(new Paragraph(line));
		
		line = this.invoiceInfo.invoiceOrder.contactInvoice.contactAddress;
		subCatPart.add(new Paragraph(line));
		
		line = this.invoiceInfo.invoiceOrder.contactInvoice.contactPLZ + " "
				+ this.invoiceInfo.invoiceOrder.contactInvoice.contactStadt;
		subCatPart.add(new Paragraph(line));
		
		line = "							" + "lhre Ansprechpartner/in";
		subCatPart.add(new Paragraph(line));
		
		if (this.companyInfo.sex == ContactDTO.SEX_MALE) {
			line = "							" + "Herr " + this.companyInfo.certificateOfOrigin;
		} else {
			line = "							" + "Faur" + this.companyInfo.certificateOfOrigin;
		}
		subCatPart.add(new Paragraph(line));
		
		line = "							" + "Tel: " + this.companyInfo.telephone;
		subCatPart.add(new Paragraph(line));
		
		line = "							" + "Fax: " + this.companyInfo.fax;
		subCatPart.add(new Paragraph(line));
		
		line = "							" + "Email: " + this.companyInfo.email;
		subCatPart.add(new Paragraph(line));
		
		line = "							" + this.companyInfo.unitedStatesT;
		subCatPart.add(new Paragraph(line));
		
		
		
		line = "							" + "Bestellt am: " + this.invoiceInfo.invoiceOrder.invoiceOrderInfo.orderedOn;
		subCatPart.add(new Paragraph(line));
		
		line = "							" + "lieferdatum: " + this.invoiceInfo.invoiceOrder.invoiceOrderInfo.delivery;
		subCatPart.add(new Paragraph(line));
		
		subCatPart.add(new Paragraph("Lieferschein", catFont));
		
		line = "							" + "Kunden-Nr: " + this.invoiceInfo.invoiceOrder.invoiceOrderInfo.customerNumber;
		subCatPart.add(new Paragraph(line));
		
		line = "							" + "Leiferschein-Nr: " + "file name L";
		subCatPart.add(new Paragraph(line));
		
		line = "Kontrollieren Sie die Liefernung auf Vollstandigkeit und Beschadigungen .";
		subCatPart.add(new Paragraph(line));
		line = "Sollten Sie Grund zur Beanstandung haben, setzen Sie sich bitte umgehend mit uns in Verbindung.";
		subCatPart.add(new Paragraph(line));
		line = "Reklamationen konnen nur am Tag der Lieferung angenommen werden.";
		subCatPart.add(new Paragraph(line));
		
		
//		Date currentDateTime = new Date();
//		SimpleDateFormat format = null;
//		format = new SimpleDateFormat("dd.MM.yyyy");
//		line = "							" + "Datum: " + format.format(currentDateTime);
//		subCatPart.add(new Paragraph(line));
//		
//		line = "							" + "Rechnungsnr: " + "file name";
//		subCatPart.add(new Paragraph(line));
		
		PdfPTable table = new PdfPTable(5);
		PdfPCell c1 = new PdfPCell(new Phrase("Pos"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		
		c1 = new PdfPCell(new Phrase("Menge"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Art.-Nr."));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Bezeichnung"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		table.setHeaderRows(1);
		for (int i = 0; i < this.invoiceInfo.listOrderDetail.size(); i++) {
			
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).pos);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).quantity);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).art_nr);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).designation);
		}
		subCatPart.add(table);
		
		
		line = "Die Ware bleibt bis zur vollstandigen Bezahlung unser Eigentum.";
		subCatPart.add(new Paragraph(line));
		
		line = "Ich habe die Ware im ordnungsgemaBen Zustand erhalten.";
		subCatPart.add(new Paragraph(line));
		
		
//		subCatPart.add(new Paragraph("Import thư viện"));
//		subCatPart.add(new Paragraph("Run program"));
//		
//		// Add a list
//		createList(subCatPart);
//		
//		Paragraph paragraph = new Paragraph();
//		addEmptyLine(paragraph, 5);
//		subCatPart.add(paragraph);
//		
//		// Add a table
//		createTable(subCatPart);
		
		// Now add all this to the document
		document.add(catPart);
		document.newPage();
		
	}

	// private static void createTable(Section subCatPart, ArrayList<Contact>
	// con)
	private static void createTable(Section subCatPart)
			throws BadElementException {
		PdfPTable table = new PdfPTable(4);

		PdfPCell c1 = new PdfPCell(new Phrase("ID"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Tên Người Dùng"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Điện Thoại"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Email"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		table.setHeaderRows(1);
		for (int i = 0; i < 3; i++) {

			table.addCell(i + " iD");
			table.addCell(i + " Ten");
			table.addCell(i + " phone");
			table.addCell(i + " Email");

		}

		subCatPart.add(table);
	}

	// private static void createTable(Section subCatPart)
	// throws BadElementException {
	// PdfPTable table = new PdfPTable(4);
	//
	// PdfPCell c1 = new PdfPCell(new Phrase("ID"));
	// c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	// table.addCell(c1);
	//
	// c1 = new PdfPCell(new Phrase("Tên Người Dùng"));
	// c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	// table.addCell(c1);
	//
	// c1 = new PdfPCell(new Phrase("Điện Thoại"));
	// c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	// table.addCell(c1);
	//
	// c1 = new PdfPCell(new Phrase("Email"));
	// c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	// table.addCell(c1);
	//
	// table.setHeaderRows(1);
	// // for (int i = 0; i < con.size(); i++) {
	// //
	// // table.addCell(String.valueOf(con.get(i).getID()));
	// // table.addCell(con.get(i).getTenND());
	// // table.addCell(con.get(i).getDienThoai());
	// // table.addCell(con.get(i).getEmail());
	// //
	// // }
	//
	// subCatPart.add(table);
	// }

	private static void createList(Section subCatPart) {
		List list = new List(true, false, 10);
		list.add(new ListItem("First point"));
		list.add(new ListItem("Second point"));
		list.add(new ListItem("Third point"));
		subCatPart.add(list);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(""));
		}
	}
}