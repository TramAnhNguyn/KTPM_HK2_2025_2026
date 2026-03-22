package iuh.fit.se;

public class XmlToJsonAdapter implements JsonService {
    private XmlService xmlService;

    public XmlToJsonAdapter(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @Override
    public String getJsonData() {
        String xml = xmlService.getXmlData();
        return "{ \"data\": \"" + xml + "\" }"; // convert đơn giản
    }
}
