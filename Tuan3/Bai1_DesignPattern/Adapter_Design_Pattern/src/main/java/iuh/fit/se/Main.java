package iuh.fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        XmlService xmlService = new XmlService();
        JsonService adapter = new XmlToJsonAdapter(xmlService);

        System.out.println(adapter.getJsonData());
    }
}
