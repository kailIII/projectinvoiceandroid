/**
 * 
 */
package com.hector.invoice.views;

/**
 * @author Hai
 *
 */

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

import com.hector.invoice.R;
import com.hector.invoice.common.GlobalUtil;
import com.hector.invoice.common.InvoiceInfo;
import com.hector.invoice.common.StringUtil;
import com.hector.invoice.dto.CompanyDTO;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.InvoiceOrderNumberInfoView;
import com.hector.invoice.lib.ExternalStorage;
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
			addContent_L(catPart);

			document.add(catPart);
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
			addContent_A(catPart);

			document.add(catPart);
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

		// add content table 1
		createContentR_table1(catPart);

		// add content table 2
		createContentR_table2(catPart);

		// add content table 3
		double total = createContentR_tableValue(catPart);

		// add content 3
		createContentR_table3(catPart, total);

		// add content 4
		createContentR_table4(catPart);
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
		strContent1.append("\n\nFirma \n");
		if (!StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.contactName)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.contactName
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
		if (!StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.contactStadt)) {
			strContent1.append(" "
					+ invoiceInfo.invoiceOrder.contactInvoice.contactStadt);
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
			strContent2.append("\n\n" + companyInfo.companyName + "\n");
		} else {
			strContent2.append("\n\n\n");
		}
		strContent2
				.append((companyInfo.companyAddress != null ? companyInfo.companyAddress
						: " ")
						+ "\n");
		String tmp = companyInfo.companyPLZ != null ? companyInfo.companyPLZ
				: "";
		if (!StringUtil.isNullOrEmpty(companyInfo.companyCity)) {
			tmp += " " + companyInfo.companyCity != null ? companyInfo.companyCity
					: "";
		}
		tmp += "\n\n";
		strContent2.append(tmp);
		strContent2.append("lhre Ansprechpartner/in \n");
		if (companyInfo.sex == ContactDTO.SEX_MALE) {
			strContent2
					.append("Herr "
							+ (companyInfo.certificateOfOrigin != null ? companyInfo.certificateOfOrigin
									: " ") + "\n");
		} else {
			strContent2
					.append("Faur "
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
						+ "\n\n\n\n");

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
		strContent1.append("Rechnung");

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
		strContent2
				.append("Rechnungsnr: "
						+ fileName_R.substring(0, fileName_R.length() - 4)
						+ "\n\n\n\n");
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
		strContent4.append("\n\n\nZwischensumme          "
				+ GlobalUtil.getInstance().convertFormatNumberOrder(total)
				+ " " + StringUtil.getString(R.string.TEXT_USD_GERMAN) + " "
				+ "\n");
		float vatValue = 0;
		if (!StringUtil.isNullOrEmpty(companyInfo.vatValue)) {
			vatValue = Float.valueOf(companyInfo.vatValue);
		}
		double newTotal = (vatValue * total) / 100;
		strContent4.append((companyInfo.vatText != null ? companyInfo.vatText
				: " ")
				+ "          "
				+ GlobalUtil.getInstance().convertFormatNumberOrder(newTotal)
				+ " "
				+ StringUtil.getString(R.string.TEXT_USD_GERMAN)
				+ " "
				+ "\n");

		c1 = new PdfPCell(new Phrase(strContent4.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table1.addCell(c1);

		StringBuffer dataNew = new StringBuffer();
		dataNew.append("Gesamtsumme:          "
				+ GlobalUtil.getInstance().convertFormatNumberOrder(
						total + newTotal) + " "
				+ StringUtil.getString(R.string.TEXT_USD_GERMAN) + " ");
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
		strContent5
				.append((companyInfo.bankCompanyName != null ? companyInfo.bankCompanyName
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
		strContent6.append("Bank: "
				+ (companyInfo.bankName != null ? companyInfo.bankName : " ")
				+ "\n\n");
		strContent6.append(StringUtil.getString(R.string.TEXT5) + "\n\n");
		strContent6.append(StringUtil.getString(R.string.TEXT6) + "\n\n\n\n");
		strContent6
				.append((companyInfo.staffSale != null ? companyInfo.staffSale
						: " ") + "\n");

		c1 = new PdfPCell(new Phrase(strContent6.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public double createContentA_tableValue(Section catpart) {
		PdfPTable table1 = new PdfPTable(5);

		PdfPCell c1 = new PdfPCell(
				new Phrase(
						StringUtil.getString(R.string.text_lb_header_tb_Pos),
						smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase(
				StringUtil.getString(R.string.text_lb_header_tb_menge),
				smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase(
				StringUtil.getString(R.string.text_lb_header_tb_bezeichnung),
				smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase(
				StringUtil.getString(R.string.text_lb_header_tb_einze),
				smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase(
				StringUtil.getString(R.string.text_lb_header_tb_gesamt),
				smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		if (this.invoiceInfo.listOrderDetail.size() > 0) {
			table1.setHeaderRows(1);
		}

		double total = 0;
		for (int i = 0; i < this.invoiceInfo.listOrderDetail.size(); i++) {

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).pos));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).quantity));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).designation));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).single_price + " "
							+ StringUtil.getString(R.string.TEXT_USD_GERMAN)
							+ " "));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table1.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).total + " "
							+ StringUtil.getString(R.string.TEXT_USD_GERMAN)
							+ " "));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table1.addCell(c1);

			total += Double
					.parseDouble(this.invoiceInfo.listOrderDetail.get(i).total);
		}

		catpart.add(table1);
		return total;
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
		if (this.invoiceInfo.listOrderDetail.size() > 0) {
			table1.setHeaderRows(1);
		}

		double total = 0;
		for (int i = 0; i < this.invoiceInfo.listOrderDetail.size(); i++) {
			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).pos));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).designation));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).quantity));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).single_price + " "
							+ StringUtil.getString(R.string.TEXT_USD_GERMAN)
							+ " "));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table1.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					this.invoiceInfo.listOrderDetail.get(i).total + " "
							+ StringUtil.getString(R.string.TEXT_USD_GERMAN)
							+ " "));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table1.addCell(c1);

			total += Double
					.parseDouble(this.invoiceInfo.listOrderDetail.get(i).total);
		}

		catpart.add(table1);
		return total;
	}

	private void addContent_L(Chapter catPart) throws DocumentException {

		// add content table 1
		createContentL_Content1(catPart);

		// add content table 2
		createContentL_Content2(catPart);

		// add content 3
		createContentL_Content3(catPart);

		// add content 4
		createContentL_tableValue(catPart);

		// add content 5
		createContentL_Content5(catPart);

		// add content 6
		createContentL_Content6(catPart);
	}

	/**
	 * method description
	 * 
	 * @param @param subCatPart
	 * @return: void
	 * @author: HaiTC3
	 * @date: Apr 3, 2013
	 */
	private void createContentL_Content6(Section subCatPart) {
		// TODO Auto-generated method stub
		PdfPTable table1 = new PdfPTable(4);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);

		c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);

		c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);

		c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		// left
		c1 = new PdfPCell(new Phrase("Ort, Datum"));
		c1.setBorder(0);
		c1.setBorderWidthTop(1);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		c1 = new PdfPCell(new Phrase("Unterschrift"));
		c1.setBorder(0);
		c1.setBorderWidthTop(1);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		subCatPart.add(table1);
	}

	public void createContentL_Content1(Section catpart) {

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

		// content 1
		StringBuffer strContent1 = new StringBuffer();
		strContent1.append("\n\nFirma \n");
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.firstName != null ? invoiceInfo.invoiceOrder.contactInvoice.firstName
						: " ")
						+ "\n");
		if (invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			strContent1
					.append("Herr "
							+ (invoiceInfo.invoiceOrder.contactInvoice.firstName != null ? invoiceInfo.invoiceOrder.contactInvoice.firstName
									: " ") + "\n");
		} else {
			strContent1
					.append("Frau "
							+ (invoiceInfo.invoiceOrder.contactInvoice.firstName != null ? invoiceInfo.invoiceOrder.contactInvoice.firstName
									: " ") + "\n");
		}
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactAddress != null ? invoiceInfo.invoiceOrder.contactInvoice.contactAddress
						: " ")
						+ "\n");
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactPLZ != null ? (invoiceInfo.invoiceOrder.contactInvoice.contactPLZ + " ")
						: " "));
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactStadt != null ? invoiceInfo.invoiceOrder.contactInvoice.contactStadt
						: " "));
		c1 = new PdfPCell(new Phrase(strContent1.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		// content 2
		StringBuffer strContent2 = new StringBuffer();
		strContent2.append("\n\n");
		strContent2
				.append((companyInfo.companyName != null ? companyInfo.companyName
						: " ")
						+ "\n");
		strContent2
				.append((companyInfo.companyAddress != null ? companyInfo.companyAddress
						: " ")
						+ "\n");
		strContent2
				.append((companyInfo.companyPLZ != null ? companyInfo.companyPLZ
						: " ")
						+ " "
						+ (companyInfo.companyCity != null ? companyInfo.companyCity
								: " ") + "\n\n");
		strContent2.append("lhre Ansprechpartner/in \n");
		if (companyInfo.sex == ContactDTO.SEX_MALE) {
			strContent2
					.append("Herr "
							+ (companyInfo.certificateOfOrigin != null ? companyInfo.certificateOfOrigin
									: " ") + "\n");
		} else {
			strContent2
					.append("Faur "
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
						: " ") + "\n\n\n");
		c1 = new PdfPCell(new Phrase(strContent2.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public void createContentL_Content2(Section catpart) {

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

		// content 1
		StringBuffer strContent1 = new StringBuffer();
		strContent1.append("Lieferschein");
		c1 = new PdfPCell(new Phrase(strContent1.toString(), subFont));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		// content 2
		StringBuffer strContent2 = new StringBuffer();
		strContent2
				.append("Bestellt am: "
						+ (invoiceInfo.invoiceOrder.invoiceOrderInfo.orderedOn != null ? invoiceInfo.invoiceOrder.invoiceOrderInfo.orderedOn
								: " ") + "\n");
		strContent2
				.append("Lieferdatum: "
						+ (invoiceInfo.invoiceOrder.invoiceOrderInfo.delivery != null ? invoiceInfo.invoiceOrder.invoiceOrderInfo.delivery
								: " ") + "\n");
		strContent2
				.append("Kunden-Nr.: "
						+ (invoiceInfo.invoiceOrder.invoiceOrderInfo.customerNumber != null ? invoiceInfo.invoiceOrder.invoiceOrderInfo.customerNumber
								: " ") + "\n");
		strContent2.append("Lieferschein-Nr: "
				+ fileName_L.substring(0, fileName_L.length() - 4) + "\n\n\n");
		c1 = new PdfPCell(new Phrase(strContent2.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public void createContentL_Content5(Section catpart) {

		PdfPTable table1 = new PdfPTable(1);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		// content 1
		StringBuffer strContent1 = new StringBuffer();
		strContent1.append("\n\n" + StringUtil.getString(R.string.TEXT4)
				+ "\n\n\n\n\n");
		c1 = new PdfPCell(new Phrase(strContent1.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public void createContentL_Content3(Section catpart) {

		PdfPTable table1 = new PdfPTable(1);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		// content 1
		StringBuffer strContent1 = new StringBuffer();
		strContent1.append(StringUtil.getString(R.string.TEXT1) + "\n\n\n");
		c1 = new PdfPCell(new Phrase(strContent1.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		catpart.add(table1);
	}

	public void createContentL_tableValue(Section catpart) {
		PdfPTable table1 = new PdfPTable(4);

		PdfPCell c1 = new PdfPCell(
				new Phrase(
						StringUtil.getString(R.string.text_lb_header_tb_Pos),
						smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase(
				StringUtil.getString(R.string.text_lb_header_tb_menge),
				smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase(
				StringUtil.getString(R.string.text_lb_header_tb_art_nr),
				smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		c1 = new PdfPCell(new Phrase(
				StringUtil.getString(R.string.text_lb_header_tb_bezeichnung),
				smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		if (this.invoiceInfo.listOrderDetail.size() > 0) {
			table1.setHeaderRows(1);
		}

		for (int i = 0; i < this.invoiceInfo.listOrderDetail.size(); i++) {

			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).pos);
			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).quantity);
			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).art_nr);
			table1.addCell(this.invoiceInfo.listOrderDetail.get(i).designation);
		}

		catpart.add(table1);
	}

	private void addContent_A(Chapter catPart) throws DocumentException {

		// add content table 1
		createContentA_table1(catPart);

		// add content table 2
		createContentA_table2(catPart);

		// add content 3
		createContentA_table3(catPart);

		// add content table 3
		double total = createContentA_tableValue(catPart);

		// add content 4
		createContentA_table4(catPart, total);

		// add content 5
		createContentA_table5(catPart);

	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: HaiTC3
	 * @param subCatPart
	 * @return: void
	 * @throws:
	 * @since: Apr 3, 2013
	 */
	private void createContentA_table5(Section subCatPart) {
		// TODO Auto-generated method stub
		PdfPTable table1 = new PdfPTable(1);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		// content 1
		c1 = new PdfPCell(new Phrase(StringUtil.getString(R.string.TEXT3)));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		subCatPart.add(table1);
	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: HaiTC3
	 * @param subCatPart
	 * @return: void
	 * @throws:
	 * @since: Apr 3, 2013
	 */
	private void createContentA_table4(Section subCatPart, double total) {

		PdfPTable table1 = new PdfPTable(1);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		// content 1
		StringBuffer strContent1 = new StringBuffer();
		strContent1.append("\n\nZwischensumme          "
				+ GlobalUtil.getInstance().convertFormatNumberOrder(total)
				+ " " + StringUtil.getString(R.string.TEXT_USD_GERMAN) + " "
				+ "\n");
		float vatValue = 0;
		if (!StringUtil.isNullOrEmpty(companyInfo.vatValue)) {
			vatValue = Float.valueOf(companyInfo.vatValue);
		}

		double newTotal = (vatValue * total) / 100;
		strContent1.append("Mehrwertsteuer "
				+ (companyInfo.vatValue != null ? companyInfo.vatValue : "0 ")
				+ "%     von     "
				+ GlobalUtil.getInstance().convertFormatNumberOrder(total)
				+ " " + StringUtil.getString(R.string.TEXT_USD_GERMAN) + " "
				+ "     "
				+ GlobalUtil.getInstance().convertFormatNumberOrder(newTotal)
				+ " " + StringUtil.getString(R.string.TEXT_USD_GERMAN) + " ");

		c1 = new PdfPCell(new Phrase(strContent1.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table1.addCell(c1);

		strContent1 = new StringBuffer();
		strContent1.append("Gesamtsumme:          "
				+ GlobalUtil.getInstance().convertFormatNumberOrder(
						total + newTotal) + " "
				+ StringUtil.getString(R.string.TEXT_USD_GERMAN) + " "
				+ "\n\n\n");
		c1 = new PdfPCell(new Phrase(strContent1.toString(), smallBold));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table1.addCell(c1);

		subCatPart.add(table1);
	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: HaiTC3
	 * @param subCatPart
	 * @param total
	 * @return: void
	 * @throws:
	 * @since: Apr 3, 2013
	 */
	private void createContentA_table3(Section subCatPart) {
		// TODO Auto-generated method stub

		PdfPTable table1 = new PdfPTable(1);

		PdfPCell c1 = new PdfPCell(new Phrase(""));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(c1);
		table1.setHeaderRows(1);

		// content 1
		StringBuffer strContent1 = new StringBuffer();
		strContent1.append(StringUtil.getString(R.string.TEXT2) + "\n\n\n");
		c1 = new PdfPCell(new Phrase(strContent1.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		subCatPart.add(table1);
	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: HaiTC3
	 * @param subCatPart
	 * @return: void
	 * @throws:
	 * @since: Apr 3, 2013
	 */
	private void createContentA_table2(Section subCatPart) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
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
		strContent1.append("\n\nAngebot");
		c1 = new PdfPCell(new Phrase(strContent1.toString(), subFont));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT | Element.ALIGN_MIDDLE);
		table1.addCell(c1);

		// content 2
		StringBuffer strContent2 = new StringBuffer();
		Date currentDateTime = new Date();
		SimpleDateFormat format = null;
		format = new SimpleDateFormat("dd.MM.yyyy");
		String line = "Datum: " + format.format(currentDateTime);
		strContent2.append("\n\n" + line + "\n");
		strContent2.append("Angebotsnr.: "
				+ fileName_A.substring(0, fileName_A.length() - 4) + "\n\n");

		c1 = new PdfPCell(new Phrase(strContent2.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		subCatPart.add(table1);
	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: HaiTC3
	 * @param subCatPart
	 * @return: void
	 * @throws:
	 * @since: Apr 3, 2013
	 */
	private void createContentA_table1(Section subCatPart) {
		// TODO Auto-generated method stub
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
		strContent1.append("\n\nFirma \n");
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactName != null ? invoiceInfo.invoiceOrder.contactInvoice.contactName
						: " ")
						+ "\n");
		String nameCus = (invoiceInfo.invoiceOrder.contactInvoice.firstName != null ? invoiceInfo.invoiceOrder.contactInvoice.firstName
				: " ")
				+ (invoiceInfo.invoiceOrder.contactInvoice.lastName != null ? invoiceInfo.invoiceOrder.contactInvoice.lastName
						: "") + "\n";
		if (invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			strContent1.append("Herr " + nameCus);
		} else {
			strContent1.append("Frau " + nameCus);
		}
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactAddress != null ? invoiceInfo.invoiceOrder.contactInvoice.contactAddress
						: " ")
						+ "\n");
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactPLZ != null ? (invoiceInfo.invoiceOrder.contactInvoice.contactPLZ + " ")
						: ""));
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactStadt != null ? invoiceInfo.invoiceOrder.contactInvoice.contactStadt
						: ""));

		c1 = new PdfPCell(new Phrase(strContent1.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		// content 2
		StringBuffer strContent2 = new StringBuffer();
		strContent2.append("\n\n");
		strContent2
				.append((companyInfo.companyName != null ? companyInfo.companyName
						: " ")
						+ "\n");
		strContent2
				.append((companyInfo.companyAddress != null ? companyInfo.companyAddress
						: " ")
						+ "\n");
		strContent2
				.append((companyInfo.companyPLZ != null ? companyInfo.companyPLZ
						: " ")
						+ " "
						+ (companyInfo.companyCity != null ? companyInfo.companyCity
								: " ") + "\n\n");
		strContent2.append("lhre Ansprechpartner/in \n");
		if (companyInfo.sex == ContactDTO.SEX_MALE) {
			strContent2
					.append("Herr "
							+ (companyInfo.certificateOfOrigin != null ? companyInfo.certificateOfOrigin
									: " ") + "\n");
		} else {
			strContent2
					.append("Faur "
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

		c1 = new PdfPCell(new Phrase(strContent2.toString()));
		c1.setBorder(0);
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(c1);

		subCatPart.add(table1);
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