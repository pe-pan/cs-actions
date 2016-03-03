package io.cloudslang.content.xml.services;

import io.cloudslang.content.xml.entities.inputs.CommonInputs;
import io.cloudslang.content.xml.entities.inputs.CustomInputs;
import io.cloudslang.content.xml.utils.ResultUtils;
import io.cloudslang.content.xml.utils.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by markowis on 03/03/2016.
 */
public class AppendChildService {
    public Map<String, String> execute(CommonInputs commonInputs, CustomInputs customInputs){
        Map<String, String> result = new HashMap<>();

        try {
            Document doc = XmlUtils.parseXML(commonInputs.getXmlDocument(), commonInputs.getSecureProcessing());
            NamespaceContext context = XmlUtils.createNamespaceContext(commonInputs.getXmlDocument());

            Document childDoc = XmlUtils.parseXML(customInputs.getXmlElement(), commonInputs.getSecureProcessing());
            Node childNode = doc.importNode(childDoc.getDocumentElement(), true);

            NodeList nodeList = XmlUtils.evaluateXPathQuery(doc, context, commonInputs.getXPathQuery());

            if(nodeList.getLength() == 0){
                ResultUtils.populateFailureResult(result, "Child not added: element not found");
                return result;
            }

            XmlUtils.appendChildToList(nodeList, childNode);
            ResultUtils.populateSuccessResult(result, "Attribute set successfully.", XmlUtils.nodeToString(doc));

        } catch (XPathExpressionException e) {
            ResultUtils.populateFailureResult(result, "XPath parsing error: " + e.getMessage());
        } catch (TransformerException te) {
            ResultUtils.populateFailureResult(result, "Transformer error: " + te.getMessage());
        } catch (Exception e) {
            ResultUtils.populateFailureResult(result, "Parsing error: " + e.getMessage());
        }

        return result;
    }
}
