package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import ctrl.DBField;
import ctrl.DBSubject;
import data.Field;
import data.Subject;

public class PdfBox {

	public static int headX = 50;
	public static int headY = 660;
	public static PDXObjectImage ximage;

	public static void main(String[] args) {

		System.out.println(processSubjects(DBSubject.loadSubjects()));
	}

	private static String processSubjects(List<Subject> loadSubjects) {
		List<String> subtitles=new LinkedList<String>();
		for(Subject s:loadSubjects){
			subtitles.add(processSubject(s));
		}
		//merge list of files - http://java.dzone.com/news/merging-pdf%E2%80%99s-with-pdfbox
		//return name of merged file
		return null;
	}

	/**
	 * Function that takes a subject that prints a PDF into the ./pdf directory.
	 * 
	 * @param list
	 */
	private static String processSubject(Subject sub) {
		// LOAD FIELDS FROM DB
		List<Field> fields = DBField.loadFieldList(sub.getModTitle(), sub.getVersion(), sub.getSubTitle());
		
		try {
			return printPDF(sub, fields);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private static String printPDF(Subject sub, List<Field> fields)
			throws IOException {
		File logo = new File("D:/logo_50_sw.jpg");
		PDDocument doc = null;
		PDPage page = null;
		int y = 0;

		BufferedImage buff = ImageIO.read(logo);
		try {
			doc = new PDDocument();
			page = new PDPage();

			doc.addPage(page);
			PDXObjectImage ximage = new PDJpeg(doc, buff, 1f);
			PDPageContentStream content = new PDPageContentStream(doc, page);

			content.drawXObject(ximage, 180, 700, 411, 80);
			System.out.println("height/width: "
					+ page.getMediaBox().getHeight() + " / "
					+ page.getMediaBox().getWidth());
			printLine(content, "Universität Ulm - MMS: ", headX, headY - y, 16);
			y += 5;
			content.drawLine(headX, headY - y,
					page.getMediaBox().getWidth() - 50, headY - y);
			y = 20;
			printLine(content, sub.getSubTitle(), headX + 150, headY - 20, 14);
			y += 5;
			content.drawLine(headX, headY - y,
					page.getMediaBox().getWidth() - 50, headY - y);
			// draw Subject
			y += 12;
			printLine(content, "Modul: " + sub.getModTitle(), headX + 20, headY
					- y, 10);
			y += 12;
			printLine(content, "Version: " + sub.getVersion(), headX + 20,
					headY - y, 10);
			y += 12;
			List<String> lines = getParts(sub.getDescription(), 100);
			printMultipleLines(content, lines, headX + 20, headY - y, 10);
			y += 20;
			printFields(content, page, doc, fields, false, y);

			content.close();
			String name = sub.getSubTitle();
			String version = "v" + sub.getVersion();
			String alphaOnly = name.replaceAll("[^a-zA-Z]+", "");
			doc.save("pdf/" + alphaOnly + "_" + version + ".pdf");
			doc.close();
			return "pdf/" + alphaOnly + "_" + version + ".pdf";
		} catch (IOException | COSVisitorException e) {
			System.out.println(e);
		}
		return "";
	}

	private static void printFields(PDPageContentStream content, PDPage page,
			PDDocument doc, List<Field> fields, boolean printLogo, int y)
			throws IOException {

		List<String> strings = new LinkedList<String>();
		int offset = 0;
		int tmpsize = 0;
		int bottomborder = 115;
		int tmpY = headY - y + offset;
		for (Field f : fields) {
			tmpY -= 55;
			System.out.println(f.getFieldTitle());
			if (tmpY < bottomborder) {
				if (printLogo)
					bottomborder = 150;
				tmpY = headY + 100;
				content.close();
				page = new PDPage();
				doc.addPage(page);
				content = new PDPageContentStream(doc, page);
				// optional - draw university logo on page 2++
				if (printLogo)
					content.drawXObject(ximage, 180, -20, 411, 80);
			}

			strings = getParts(f.getDescription(), 90);

			printLine(content, f.getFieldTitle() + ": ", headX + 20, tmpY, 10);
			tmpsize = printMultipleLines(content, strings, headX + 50,
					tmpY - 14, 10) * 10;
			tmpY = tmpY - tmpsize + 20;
			System.out.println("location y: " + tmpY);
		}
		content.close();
	}

	private static List<String> getParts(String string, int partitionSize) {
		List<String> parts = new ArrayList<String>();
		int len = string.length();
		for (int i = 0; i < len; i += partitionSize) {
			parts.add(string.substring(i, Math.min(len, i + partitionSize)));
		}
		return parts;

	}

	/**
	 * prints a message to the specified content, at location (x,y) with
	 * specified font size.
	 * 
	 * @param content
	 * @param message
	 * @param x
	 * @param y
	 * @param size
	 * @throws IOException
	 */
	private static void printLine(PDPageContentStream content, String message,
			int x, int y, float size) throws IOException {
		content.beginText();
		content.setFont(PDType1Font.HELVETICA, size);
		content.moveTextPositionByAmount(x, y);
		content.drawString(message);

		content.endText();
	}

	/**
	 * prints multiple lines to the specified content, starts at location (x,y)
	 * with specified fontsize.
	 * 
	 * @param contentStream
	 * @param lines
	 * @param x
	 * @param y
	 * @param size
	 * @return
	 * @throws IOException
	 */
	private static int printMultipleLines(PDPageContentStream contentStream,
			List<String> lines, float x, float y, float size)
			throws IOException {
		if (lines.size() == 0) {
			return 0;
		}
		final int numberOfLines = lines.size();
		final float fontHeight = 12;
		contentStream.beginText();
		contentStream.setFont(PDType1Font.HELVETICA, size);

		contentStream.appendRawCommands(fontHeight + " TL\n");
		contentStream.moveTextPositionByAmount(x, y);
		for (int i = 0; i < numberOfLines; i++) {
			contentStream.drawString(lines.get(i));
			if (i < numberOfLines - 1) {
				contentStream.appendRawCommands("T*\n");
			}
		}
		contentStream.endText();
		System.out.println("no of lines: " + numberOfLines);
		return numberOfLines;
	}
}