package utils.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.logging.Level;

import utils.entities.OCFile;
import utils.log.Log;

public class FileSAXHandler extends DefaultHandler {

    private OCFile file;
    private ArrayList<OCFile> listFiles = new ArrayList<OCFile>();
    private static String text = null;

    @Override
    public void startElement(String uri, String localName, String node, Attributes attributes)
            throws SAXException {
        if (node.equals("d:response")) {
            file = new OCFile();
        }
    }

    @Override
    public void endElement(String uri, String localName, String node) {
        switch (node) {
            case "d:href" -> file.setPath(text);
            case "oc:permissions" -> file.setPermissions(text);
            case "oc:size" -> file.setSize(text);
            case "oc:privatelink" -> file.setPrivateLink(text);
            case "d:getlastmodified" -> file.setLastModified(text);
            case "d:response" -> {
                file.setName(getFileNameFromPath(file.getPath()));
                listFiles.add(file);
            }
            case "d:getcontenttype" -> file.setType(text);
            case "oc:favorite" -> file.setFavorite(text);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = String.copyValueOf(ch, start, length).trim();
    }

    public ArrayList<OCFile> getListFiles() {
        return listFiles;
    }

    public OCFile getItemByname(String itemName) {
        for (OCFile file : listFiles) {
            if (file.getName().equals(itemName)) {
                return file;
            }
        }
        return null;
    }

    private String getFileNameFromPath(String path) {
        try {
            String[] pathSplitted = path.split("/");
            return URLDecoder.decode(pathSplitted[pathSplitted.length - 1], "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.log(Level.SEVERE, "Unsupported Encoding Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
