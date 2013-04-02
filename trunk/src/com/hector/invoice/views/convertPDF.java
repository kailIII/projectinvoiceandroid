/**
 * 
 */
package com.hector.invoice.views;

/**
 * @author Hai
 *
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.graphics.Bitmap;

import com.hector.invoice.common.InvoiceInfo;
import com.hector.invoice.common.StringUtil;
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
	String fileName_R = "";
	String fileName_L = "";
	String fileName_A = "";

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
		fi = new File(ExternalStorage.getFilePDFPath(
				InvoiceInfo.getInstance().getAppContext()).getAbsolutePath(),
				fileName);
		fileName_R = fileName;
		createPDF_R();
	}

	public void createFilePDF_L(String fileName) {
		fi = new File(ExternalStorage.getFilePDFPath(
				InvoiceInfo.getInstance().getAppContext()).getAbsolutePath(),
				fileName);
		fileName_L = fileName;
		createPDF_L();
	}

	public void createFilePDF_A(String fileName) {
		fi = new File(ExternalStorage.getFilePDFPath(
				InvoiceInfo.getInstance().getAppContext()).getAbsolutePath(),
				fileName);
		fileName_A = fileName;
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
			Chapter catPart = new Chapter(1);
			createImage(catPart);

			// addTitlePage(document);
			addContent_R(catPart);
			document.add(catPart);
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
			Chapter catPart = new Chapter(1);
			createImage(catPart);
			// addTitlePage(document);
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
			Chapter catPart = new Chapter(1);
			createImage(catPart);

			// addTitlePage(document);
			addContent_A(document);
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
	private void createImage(Chapter document) {
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

	private void addContent_R(Chapter catPart) throws DocumentException {

		// Anchor anchor = new Anchor("ESTIMATING APP", catFont);
		// anchor.setName("ESTIMATING APP");

		// chuong 1
//		Chapter catPart = new Chapter(1);
		// Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		// Paragraph subPara = new Paragraph("", subFont);
		// Paragraph subPara = new Paragraph("text", subFont);
		Section subCatPart = catPart.addSection("");

		// add content table 1
		createContentR_table1(subCatPart);

		// add content table 2
		createContentR_table2(subCatPart);

		// add content table 3
		double total = createContentR_tableValue(subCatPart);

		// add content 3
		createContentR_table3(subCatPart, total);

		// add content 4
		createContentR_table4(subCatPart);

//		document.add(catPart);
//		document.newPage();

	}

	public void createContentR_table1(Section catpart) {
		PdfPTable table1 = new PdfPTable(2);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);

		c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		StringBuffer strContent1 = new StringBuffer();
		strContent1.append("Firma \n ");
		if (!StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.firstName)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.firstName
							+ "\n");
		} else {
			strContent1.append("" + "\n");
		}

		if (invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			if (!StringUtil
					.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.firstName)) {
				strContent1.append("Herr "
						+ invoiceInfo.invoiceOrder.contactInvoice.firstName
						+ "\n");

			} else {
				strContent1.append("Herr " + " " + "\n");
			}
		} else {
			if (!StringUtil
					.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.firstName)) {
				strContent1.append("Frau "
						+ invoiceInfo.invoiceOrder.contactInvoice.firstName
						+ "\n");
			} else {
				strContent1.append("Frau " + " " + "\n");
			}
		}
		if (!StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.contactAddress)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.contactAddress
							+ "\n");
		} else {
			strContent1.append(" " + "\n");
		}
		if (!StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.contactPLZ)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.contactPLZ);
		} else {
			strContent1.append(" ");
		}
		if (StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.contactStadt)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.contactStadt);
		} else {
			strContent1.append(" ");
		}

		c1 = new PdfPCell(new Phrase(strContent1.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		// content 2
		StringBuffer strContent2 = new StringBuffer();
		if (!StringUtil.isNullOrEmpty(companyInfo.companyName)) {
			strContent2.append(companyInfo.companyName + "\n");
		} else {
			strContent2.append(" " + "\n");
		}
		strContent2
				.append((companyInfo.companyAddress != null ? companyInfo.companyAddress
						: " ")
						+ "\n");
		strContent2
				.append(companyInfo.companyPLZ != null ? companyInfo.companyPLZ
						: " "
								+ " "
								+ (companyInfo.companyCity != null ? companyInfo.companyCity
										: " ") + "\n \n ");
		strContent2.append("lhre Ansprechpartner/in \n");
		if (companyInfo.sex == ContactDTO.SEX_MALE) {
			strContent2
					.append("Herr "
							+ (companyInfo.certificateOfOrigin != null ? companyInfo.certificateOfOrigin
									: " ") + "\n");
		} else {
			strContent2
					.append("Faur"
							+ (companyInfo.certificateOfOrigin != null ? companyInfo.certificateOfOrigin
									: " ") + "\n");
		}
		strContent2
				.append("Tel: "
						+ (this.companyInfo.telephone != null ? this.companyInfo.telephone
								: " ") + "\n");
		strContent2.append("Fax: "
				+ (this.companyInfo.fax != null ? this.companyInfo.fax : " ")
				+ "\n");
		strContent2.append("Email: "
				+ (this.companyInfo.email != null ? this.companyInfo.email
						: " ") + "\n");
		strContent2
				.append((this.companyInfo.unitedStatesT != null ? this.companyInfo.unitedStatesT
						: " ")
						+ "\n");

		c1 = new PdfPCell(new Phrase(strContent2.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public void createContentR_table2(Section catpart) {
		PdfPTable table1 = new PdfPTable(2);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);

		c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		StringBuffer strContent1 = new StringBuffer();
		strContent1.append("Rechnungsnr");

		c1 = new PdfPCell(new Phrase(strContent1.toString(), subFont));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		// content 2
		StringBuffer strContent2 = new StringBuffer();
		Date currentDateTime = new Date();
		SimpleDateFormat format = null;
		format = new SimpleDateFormat("dd.MM.yyyy");
		String line = "Datum: " + format.format(currentDateTime);
		strContent2.append(line + "\n");
		strContent2.append("Rechnungsnr: "
				+ fileName_R.substring(0, fileName_R.length() - 4) + "\n");
		c1 = new PdfPCell(new Phrase(strContent2.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public void createContentR_table3(Section catpart, double total) {
		PdfPTable table1 = new PdfPTable(1);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table1.addCell(c1);

		// content 4
		StringBuffer strContent4 = new StringBuffer();
		strContent4.append("Zwischensumme          " + String.valueOf(total)
				+ " €" + "\n");
		float vatValue = 0;
		if (!StringUtil.isNullOrEmpty(companyInfo.vatValue)) {
			vatValue = Float.valueOf(companyInfo.vatValue);
		}
		double newTotal = (vatValue * total) / 100;
		strContent4.append((companyInfo.vatText != null ? companyInfo.vatText
				: " ") + "          " + String.valueOf(newTotal) + " €" + "\n");

		c1 = new PdfPCell(new Phrase(strContent4.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table1.addCell(c1);

		StringBuffer dataNew = new StringBuffer();
		dataNew.append("Gesamtsumme:          "
				+ String.valueOf(total + newTotal) + " €");
		c1 = new PdfPCell(new Phrase(dataNew.toString(), smallBold));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public void createContentR_table4(Section catpart) {
		PdfPTable table1 = new PdfPTable(1);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table1.addCell(c1);

		// content 4
		StringBuffer strContent5 = new StringBuffer();
		strContent5.append((companyInfo.bankName != null ? companyInfo.bankName
				: " "));
		c1 = new PdfPCell(new Phrase(strContent5.toString(), smallBold));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		StringBuffer strContent6 = new StringBuffer();
		strContent6.append("BLZ: "
				+ (companyInfo.bankBLZ != null ? companyInfo.bankBLZ : " ")
				+ "\n");
		strContent6.append("Konto-Nr: "
				+ (companyInfo.bankAcctnum != null ? companyInfo.bankAcctnum
						: " ") + "\n");
		strContent6
				.append("Bank: "
						+ (companyInfo.bankCompanyName != null ? companyInfo.bankCompanyName
								: " ") + "\n \n");
		strContent6.append("Wir danken fur den Auftrag. " + "\n \n ");
		strContent6.append("Mit freundlichen Grussen " + "\n \n");
		strContent6
				.append((companyInfo.staffSale != null ? companyInfo.staffSale
						: " ") + "\n");

		c1 = new PdfPCell(new Phrase(strContent6.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public double createContentR_tableValue(Section catpart) {
		PdfPTable table1 = new PdfPTable(5);

		PdfPCell c1 = new PdfPCell(new Phrase("Pos", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase("Bezeichnung", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase("Menge", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase("Einzelpreis", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase("Gesamt", smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		double total = 0;
		for (int i = 0; i < this.invoiceInfo.listOrderDetail.size(); i++) {

			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).pos);
			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).designation);
			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).quantity);
			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).single_price
					+ " €");
			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).total + " €");
			total += Double
					.parseDouble(this.invoiceInfo.listOrderDetail.get(i).total);
		}

		catpart.add(table1);
		return total;
	}

	private void addContent_L(Document document) throws DocumentException {

		Anchor anchor = new Anchor("ESTIMATING APP", catFont);
		anchor.setName("ESTIMATING APP");

		// chuong 1
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("", subFont);
		Section subCatPart = catPart.addSection(subPara);

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

		line = "							" + "Bestellt am: "
				+ this.invoiceInfo.invoiceOrder.invoiceOrderInfo.orderedOn;
		subCatPart.add(new Paragraph(line));

		line = "							" + "lieferdatum: "
				+ this.invoiceInfo.invoiceOrder.invoiceOrderInfo.delivery;
		subCatPart.add(new Paragraph(line));

		subCatPart.add(new Paragraph("Lieferschein", catFont));

		line = "							" + "Kunden-Nr: "
				+ this.invoiceInfo.invoiceOrder.invoiceOrderInfo.customerNumber;
		subCatPart.add(new Paragraph(line));

		line = "							" + "Leiferschein-Nr: " + "file name L";
		subCatPart.add(new Paragraph(line));

		line = "Kontrollieren Sie die Liefernung auf Vollstandigkeit und Beschadigungen .";
		subCatPart.add(new Paragraph(line));
		line = "Sollten Sie Grund zur Beanstandung haben, setzen Sie sich bitte umgehend mit uns in Verbindung.";
		subCatPart.add(new Paragraph(line));
		line = "Reklamationen konnen nur am Tag der Lieferung angenommen werden.";
		subCatPart.add(new Paragraph(line));

		// Date currentDateTime = new Date();
		// SimpleDateFormat format = null;
		// format = new SimpleDateFormat("dd.MM.yyyy");
		// line = "							" + "Datum: " + format.format(currentDateTime);
		// subCatPart.add(new Paragraph(line));
		//
		// line = "							" + "Rechnungsnr: " + "file name";
		// subCatPart.add(new Paragraph(line));

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

		// subCatPart.add(new Paragraph("Import thÆ° viá»‡n"));
		// subCatPart.add(new Paragraph("Run program"));
		//
		// // Add a list
		// createList(subCatPart);
		//
		// Paragraph paragraph = new Paragraph();
		// addEmptyLine(paragraph, 5);
		// subCatPart.add(paragraph);
		//
		// // Add a table
		// createTable(subCatPart);

		// Now add all this to the document
		document.add(catPart);
		document.newPage();

	}

	private void addContent_A(Document document) throws DocumentException {

		Anchor anchor = new Anchor("ESTIMATING APP", catFont);
		anchor.setName("ESTIMATING APP");

		// chuong 1
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("text", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Wel come to HaiTC"));

		// subPara = new Paragraph("Báº¯t Ä‘áº§u", subFont);
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
		double total = 0;
		for (int i = 0; i < this.invoiceInfo.listOrderDetail.size(); i++) {

			table.addCell(this.invoiceInfo.listOrderDetail.get(i).pos);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).designation);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).quantity);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).single_price);
			table.addCell(this.invoiceInfo.listOrderDetail.get(i).total);
			total += Double
					.parseDouble(this.invoiceInfo.listOrderDetail.get(i).total);
		}
		subCatPart.add(table);

		line = "							" + "Zwischensumme: " + "	" + String.valueOf(total);
		subCatPart.add(new Paragraph(line));

		double vat = total * Float.valueOf(this.companyInfo.vatValue);
		line = "							" + this.companyInfo.vatText + "	" + String.valueOf(vat);
		subCatPart.add(new Paragraph(line));

		line = "							" + "Gesamtsumme: " + "	" + String.valueOf(total + vat);
		subCatPart.add(new Paragraph(line));

		// subCatPart.add(new Paragraph("Import thÆ° viá»‡n"));
		// subCatPart.add(new Paragraph("Run program"));
		//
		// // Add a list
		// createList(subCatPart);
		//
		// Paragraph paragraph = new Paragraph();
		// addEmptyLine(paragraph, 5);
		// subCatPart.add(paragraph);
		//
		// // Add a table
		// createTable(subCatPart);

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
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("test"));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("asdasd"));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Email"));
		c1.setBorder(0);
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
	// c1 = new PdfPCell(new Phrase("TÃªn NgÆ°á»�i DÃ¹ng"));
	// c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	// table.addCell(c1);
	//
	// c1 = new PdfPCell(new Phrase("Ä�iá»‡n Thoáº¡i"));
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