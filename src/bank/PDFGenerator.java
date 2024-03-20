package bank;
import java.io.*;
import java.util.List;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
 
public class PDFGenerator {
	static int n;
	 public void display(List<String> statement,List<String> add,int statementnumber){
	int n=statement.size()/6;
     String pdfDirectory = "D:/jdk-11.0.2/BankTransaction";
     String pdfName = "hello.pdf";
      Document document = new Document();
        try {
            // Ensure that the directory exists
            File directory = new File(pdfDirectory);
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }
 
            // Create the PDF file
            //PdfWriter pdfWriter = 
            PdfWriter.getInstance(document, new FileOutputStream(new File(pdfDirectory + pdfName)));
            document.open();
            
         // Create PdfPTable
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setPaddingTop(100);
 
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            PdfPTable table0 = new PdfPTable(2);
            table0.getDefaultCell().setBorderWidth(0);
            table0.setWidthPercentage(100);
            
            
            
            PdfPCell cellp;
             cellp = new PdfPCell(new Paragraph(add.get(0) + "\n"+add.get(1)+"\n\n Statement no:"+statementnumber, boldFont));
            
            cellp.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table0.addCell(cellp);
            table0.getDefaultCell().setBorderWidth(0);
            cellp.disableBorderSide(2);
            if(n>21)
            {
            	n=2;
            }
            else
            {
            n=1;	
            }
            
            cellp = new PdfPCell(new Paragraph(add.get(2)+"\n"+add.get(3)+"\n\n"
            		+ "Date of statement:"+add.get(4)+"\npage "+n, boldFont));
            cellp.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table0.addCell(cellp);
            table0.getDefaultCell().setBorderWidth(0);
            document.add(table0);
 
            PdfPCell deductionsCell = new PdfPCell();
            Paragraph deductionsParagraph = new Paragraph("Bank statement for period 1 month "+add.get(5)+" to "+add.get(6), boldFont); // Add bold font to the paragraph
            deductionsParagraph.setAlignment(Element.ALIGN_CENTER); // Align text to the center
            deductionsCell.addElement(deductionsParagraph); // Add paragraph to cell
            deductionsCell.setColspan(7);
            table.addCell(deductionsCell);
           
            
            PdfPCell earningsCell1 = new PdfPCell();
            Paragraph earningsParagraph1 = new Paragraph("Date", boldFont); // Add bold font to the paragraph
            earningsParagraph1.setAlignment(Element.ALIGN_CENTER); // Align text to the center
            earningsCell1.addElement(earningsParagraph1); // Add paragraph to cell
            earningsCell1.setColspan(1);
            table.addCell(earningsCell1);
            
            PdfPCell earningsCell2 = new PdfPCell();
            Paragraph earningsParagraph2 = new Paragraph("Transaction Description", boldFont); // Add bold font to the paragraph
            earningsParagraph1.setAlignment(Element.ALIGN_CENTER); // Align text to the center
            earningsCell2.addElement(earningsParagraph2); // Add paragraph to cell
            earningsCell2.setColspan(2);
            table.addCell(earningsCell2);
            
            PdfPCell earningsCell3 = new PdfPCell();
            Paragraph earningsParagraph3 = new Paragraph("Reference", boldFont); // Add bold font to the paragraph
            earningsParagraph1.setAlignment(Element.ALIGN_CENTER); // Align text to the center
            earningsCell3.addElement(earningsParagraph3); // Add paragraph to cell
            earningsCell3.setColspan(1);
            table.addCell(earningsCell3);
            
            PdfPCell earningsCell4 = new PdfPCell();
            Paragraph earningsParagraph4 = new Paragraph("Debit", boldFont); // Add bold font to the paragraph
            earningsParagraph4.setAlignment(Element.ALIGN_CENTER); // Align text to the center
            earningsCell4.addElement(earningsParagraph4); // Add paragraph to cell
            earningsCell4.setColspan(1);
            table.addCell(earningsCell4);
            PdfPCell earningsCell5 = new PdfPCell();
            Paragraph earningsParagraph5 = new Paragraph("Credit", boldFont); // Add bold font to the paragraph
            earningsParagraph5.setAlignment(Element.ALIGN_CENTER); // Align text to the center
            earningsCell5.addElement(earningsParagraph5); // Add paragraph to cell
            earningsCell5.setColspan(1);
            table.addCell(earningsCell5);
            PdfPCell earningsCell6 = new PdfPCell();
            Paragraph earningsParagraph6 = new Paragraph("Balance", boldFont); // Add bold font to the paragraph
            earningsParagraph1.setAlignment(Element.ALIGN_CENTER); // Align text to the center
            earningsCell6.addElement(earningsParagraph6); // Add paragraph to cell
            earningsCell6.setColspan(1);
            table.addCell(earningsCell6);
            int j;
           
           //j=13;
            for(j=7;j<13;j++)
            {
       	
       	if(j==8)
       	{
       		PdfPCell earningsCellp = new PdfPCell();
               Paragraph earningsParagraphp = new Paragraph(add.get(j)); // Add bold font to the paragraph
               earningsParagraph1.setAlignment(Element.ALIGN_CENTER); // Align text to the center
               earningsCellp.addElement(earningsParagraphp); // Add paragraph to cell
               earningsCellp.setColspan(2);
               earningsCellp.setBorder(Rectangle.NO_BORDER);
               table.addCell(earningsCellp);
       		
       	}else
       	{
       	
           table.addCell(add.get(j));}
       
            }
            
            
 
          
           int k=0;
                	for(int i=0;i<statement.size();i=i+6)
                	{
                		k=0;
                     for(j=0;j<6;j++)
                     {
                	k++;
                	if(k==2)
                	{
                		PdfPCell earningsCellp = new PdfPCell();
                        Paragraph earningsParagraphp = new Paragraph(statement.get(i+j)); // Add bold font to the paragraph
                        earningsParagraph1.setAlignment(Element.ALIGN_CENTER); // Align text to the center
                        earningsCellp.addElement(earningsParagraphp); // Add paragraph to cell
                        earningsCellp.setColspan(2);
                        earningsCellp.setBorder(Rectangle.NO_BORDER);
                        table.addCell(earningsCellp);
                		
                	}else
                	{
                	
                    table.addCell(statement.get(i+j));}
                
                     }}
                	
                	for(j=13;j<19;j++)
                    {
               	
               	if(j==14)
               	{
               		PdfPCell earningsCellp = new PdfPCell();
                       Paragraph earningsParagraphp = new Paragraph(add.get(j)); // Add bold font to the paragraph
                       earningsParagraph1.setAlignment(Element.ALIGN_CENTER); // Align text to the center
                       earningsCellp.addElement(earningsParagraphp); // Add paragraph to cell
                       earningsCellp.setColspan(2);
                       earningsCellp.setBorder(Rectangle.NO_BORDER);
                       table.addCell(earningsCellp);
               		
               	}else
               	{
               	
                   table.addCell(add.get(j));}
               
                    }
                	
            
            String[] headers1 = {"Definition of codes","Deposits","Interest received","cheques", "unpaid", "other debits", "Bank charge"};
            for (String header : headers1) {
                PdfPCell headerCell = new PdfPCell(new Paragraph(header, boldFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(headerCell);
            }
            
            PdfPCell earningsCellp = new PdfPCell();
            Paragraph earningsParagraphp = new Paragraph("co-commision\ndo-Debit\nint-interest\nsf-service fee"); // Add bold font to the paragraph
            earningsParagraph1.setAlignment(Element.ALIGN_CENTER); // Align text to the center
            earningsCellp.addElement(earningsParagraphp); // Add paragraph to cell
            earningsCellp.setRowspan(2);
            table.addCell(earningsCellp);
            table.addCell(add.get(19));
            table.addCell(add.get(22));
            table.addCell(add.get(20));
            table.addCell("0.00");
            table.addCell("0.00");
           table.addCell(add.get(21));
           Font font = FontFactory.getFont(FontFactory.HELVETICA, 12);
           Paragraph paragraph = new Paragraph();
           String text = "               Important Notice\nStatement are accepted as correct unless queried within30 days.Any cheques reflected on this statement which are not attached,will be included with your next statemnet.";
           int indexOfBoldText = text.indexOf("Important Notice");
           paragraph.add(new Chunk(text.substring(0, indexOfBoldText), font));
           Font boldFont1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
           paragraph.add(new Chunk("Important Notice", boldFont1));
           paragraph.add(new Chunk(text.substring(indexOfBoldText + "Important Notice".length()), font));

           PdfPCell cell = new PdfPCell(paragraph);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setColspan(6);
           table.addCell(cell);
                       
            document.add(table);
            
           
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
