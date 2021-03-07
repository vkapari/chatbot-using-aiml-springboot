package demo.project.chatbot.model;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This is just a stub to make the contactaction.aiml file work on a PC
 * with some extension tags that are defined for mobile devices.
 */
public class PCAIMLProcessorExtension implements AIMLProcessorExtension {
	private static final Log log = LogFactory.getLog(PCAIMLProcessorExtension.class);
    public Set<String> extensionTagNames = Utilities.stringSet("contactid","multipleids","displayname","dialnumber","emailaddress","contactbirthday","addinfo");
    @Override
	public Set <String> extensionTagSet() {
        return extensionTagNames;
    }
    private String newContact(Node node, ParseState ps) {
        NodeList childList = node.getChildNodes();
        String emailAddress="unknown";
        String displayName="unknown";
        String dialNumber="unknown";
        String emailType="unknown";
        String phoneType="unknown";
        String birthday="unknown";
        for (int i = 0; i < childList.getLength(); i++)  {
            if (childList.item(i).getNodeName().equals("birthday")) {
                birthday = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
            if (childList.item(i).getNodeName().equals("phonetype")) {
                phoneType = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
            if (childList.item(i).getNodeName().equals("emailtype")) {
                emailType = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
            if (childList.item(i).getNodeName().equals("dialnumber")) {
                dialNumber = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
            if (childList.item(i).getNodeName().equals("displayname")) {
                displayName = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
            if (childList.item(i).getNodeName().equals("emailaddress")) {
                emailAddress = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
        }
        log.info("Adding new contact "+displayName+" "+phoneType+" "+dialNumber+" "+emailType+" "+emailAddress+" "+birthday);
        Contact contact = new Contact(displayName, phoneType, dialNumber, emailType, emailAddress, birthday);
        return "";
    }
    private String contactId(Node node, ParseState ps) {
        String displayName = AIMLProcessor.evalTagContent(node, ps, null);
        String result = Contact.contactId(displayName);
        //log.info("contactId("+displayName+")="+result);
        return result;
    }
    private String multipleIds(Node node, ParseState ps){
        String contactName = AIMLProcessor.evalTagContent(node, ps, null);
        String result = Contact.multipleIds(contactName);
        //log.info("multipleIds("+contactName+")="+result);
        return result;
    }
    private String displayName(Node node, ParseState ps){
        String id = AIMLProcessor.evalTagContent(node, ps, null);
        String result = Contact.displayName(id);
        //log.info("displayName("+id+")="+result);
        return result;
    }
    private String dialNumber(Node node, ParseState ps) {
        NodeList childList = node.getChildNodes();
        String id="unknown";
        String type="unknown";
        for (int i = 0; i < childList.getLength(); i++)  {
            if (childList.item(i).getNodeName().equals("id")) {
                id = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
            if (childList.item(i).getNodeName().equals("type")) {
                type = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
        }
        String result = Contact.dialNumber(type, id);
        //log.info("dialNumber("+id+")="+result);
        return result;
    }

    private String emailAddress(Node node, ParseState ps){
        NodeList childList = node.getChildNodes();
        String id="unknown";
        String type="unknown";
        for (int i = 0; i < childList.getLength(); i++)  {
            if (childList.item(i).getNodeName().equals("id")) {
                id = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
            if (childList.item(i).getNodeName().equals("type")) {
                type = AIMLProcessor.evalTagContent(childList.item(i), ps, null);
            }
        }
        String result = Contact.emailAddress(type, id);
        //log.info("emailAddress("+id+")="+result);
        return result;
    }


    private String contactBirthday(Node node, ParseState ps){
        String id = AIMLProcessor.evalTagContent(node, ps, null);
        String result = Contact.birthday(id);
        //log.info("birthday("+id+")="+result);
        return result;
    }
    @Override
	public String recursEval(Node node, ParseState ps) {
        try {
            String nodeName = node.getNodeName();
            if (nodeName.equals("contactid"))
                return contactId(node, ps);
            else if (nodeName.equals("multipleids"))
                return multipleIds(node, ps);
            else if (nodeName.equals("dialnumber"))
                return dialNumber(node, ps);
            else if (nodeName.equals("addinfo"))
                return newContact(node, ps);
            else if (nodeName.equals("displayname"))
                return displayName(node, ps);
            else if (nodeName.equals("emailaddress"))
                return emailAddress(node, ps);
            else if (nodeName.equals("contactbirthday"))
                return contactBirthday(node, ps) ;
            else return (AIMLProcessor.genericXML(node, ps));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}