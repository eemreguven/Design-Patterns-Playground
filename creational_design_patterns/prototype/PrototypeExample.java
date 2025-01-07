package prototype;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// prototype interface
interface Document extends Cloneable {
    Document clone() throws CloneNotSupportedException;
    void setContent(String content);
    String getContent();
    void showDocumentDetails();
    void setModifyDate();
}

// abstract base class for common properties
abstract class BaseDocument implements Document {
    protected String content;
    protected Date createDate;
    protected Date modifyDate;

    public BaseDocument() {
        this.createDate = new Date();
        this.modifyDate = new Date();
    }

    @Override
    public Document clone() throws CloneNotSupportedException {
        return (Document) super.clone();
    }

    @Override
    public void setContent(String content) {
        this.content = content;
        setModifyDate();
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setModifyDate() {
        this.modifyDate = new Date();
    }

    protected String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public void showCommonDetails() {
        System.out.println("Create Date: " + formatDate(createDate));
        System.out.println("Modify Date: " + formatDate(modifyDate));
        System.out.println("Content: " + content);
    }
}

// concrete prototype: Text Document
class TextDocument extends BaseDocument {
    private String font;
    private int fontSize;

    public TextDocument(String font, int fontSize) {
        super();
        this.font = font;
        this.fontSize = fontSize;
    }

    @Override
    public TextDocument clone() throws CloneNotSupportedException {
        TextDocument cloned = (TextDocument) super.clone();
        cloned.createDate = (Date) this.createDate.clone();
        cloned.modifyDate = (Date) this.modifyDate.clone();
        return cloned;
    }

    @Override
    public void showDocumentDetails() {
        System.out.println("Text Document Details:");
        System.out.println("Font: " + font);
        System.out.println("Font Size: " + fontSize);
        showCommonDetails();
    }
}

// concrete prototype: Spreadsheet Document
class SpreadsheetDocument extends BaseDocument {
    private int rows;
    private int columns;

    public SpreadsheetDocument(int rows, int columns) {
        super();
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public SpreadsheetDocument clone() throws CloneNotSupportedException {
        SpreadsheetDocument cloned = (SpreadsheetDocument) super.clone();
        cloned.createDate = (Date) this.createDate.clone();
        cloned.modifyDate = (Date) this.modifyDate.clone();
        return cloned;
    }

    @Override
    public void showDocumentDetails() {
        System.out.println("Spreadsheet Document Details:");
        System.out.println("Rows: " + rows);
        System.out.println("Columns: " + columns);
        showCommonDetails();
    }
}

// prototype registry (cache)
class DocumentRegistry {
    private Map<String, Document> documents = new HashMap<>();

    public void addDocument(String type, Document document) {
        documents.put(type, document);
    }

    public Document getDocument(String type) {
        try {
            Document prototype = documents.get(type);
            if (prototype == null) {
                throw new RuntimeException("Document type not found: " + type);
            }
            return prototype.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Failed to clone the document: " + e.getMessage(), e);
        }
    }
}

public class PrototypeExample {
    public static void main(String[] args) {
        DocumentRegistry registry = new DocumentRegistry();

        // Add document prototypes to the registry
        TextDocument textDocTemplate = new TextDocument("Arial", 12);
        textDocTemplate.setContent("Base Content for Text Document");
        registry.addDocument("Text", textDocTemplate);

        SpreadsheetDocument sheetDocTemplate = new SpreadsheetDocument(100, 50);
        sheetDocTemplate.setContent("Base Content for Spreadsheet");
        registry.addDocument("Spreadsheet", sheetDocTemplate);
        
        // Clone documents from the registry
        TextDocument clonedTextDoc1 = (TextDocument) registry.getDocument("Text");
        clonedTextDoc1.setContent("Cloned Text Document 1 Content");

        SpreadsheetDocument clonedSheetDoc = (SpreadsheetDocument) registry.getDocument("Spreadsheet");
        clonedSheetDoc.setContent("Cloned Spreadsheet Content");

        // Display
        System.out.println("Original Text Document:");
        textDocTemplate.showDocumentDetails();

        System.out.println("\nCloned Documents:");
        clonedTextDoc1.showDocumentDetails();
        clonedSheetDoc.showDocumentDetails();
    }
}