package utils.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import utils.entities.OCShare;

public class ShareSAXHandler extends DefaultHandler {

    private OCShare share;
    private ArrayList<OCShare> allShares;
    private static String text = null;


    @Override
    public void startElement(String uri, String localName, String node, Attributes attributes) {
        switch (node) {
            case ("ocs"): {
                allShares = new ArrayList<>();
            }
            case ("element"): {
                share = new OCShare();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String node) {
        switch (node) {
            case ("id"): {
                share.setId(text);
                break;
            }
            case ("uid_file_owner"): {
                share.setOwner(text);
                break;
            }
            case ("share_type"): {
                share.setType(text);
                break;
            }
            case ("share_with"): {
                share.setShareeName(text);
                break;
            }
            case ("name"): {
                share.setLinkName(text);
                break;
            }
            case ("path"): {
                share.setItemName(text.substring(1, text.length()));
                break;
            }
            case ("permissions"): {
                share.setPermissions(text);
                break;
            }
            case ("expiration"): {
                share.setExpiration(text);
                break;
            }
            case ("element"): {
                allShares.add(share);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = String.copyValueOf(ch, start, length).trim();
    }

    public OCShare getShare() {
        return share;
    }

    public ArrayList<OCShare> getAllShares() {
        return allShares;
    }

}
