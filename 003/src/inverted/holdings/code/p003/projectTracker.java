package inverted.holdings.code.p003;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class projectTracker 
{
	public projectTracker()  {}

	public static void main(String[] args) 
	{
		try {
			File projectList = new File("projectList/default_projects.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(projectList);
			doc.getDocumentElement().normalize();
			
			NodeList nlist = doc.getElementsByTagName("project");
			
			for (int i = 0; i < nlist.getLength(); i++)
			{
				Node curElement = nlist.item(i);
				 
				if (curElement.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) curElement;
		 
					System.out.println(eElement.getAttribute("num") + " " 
							+ eElement.getElementsByTagName("name").item(0).getTextContent() + " - " 
							+ eElement.getElementsByTagName("lang").item(0).getTextContent());
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
}
