import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import net.sf.jooreports.templates.image.ImageSource;
import net.sf.jooreports.templates.image.RenderedImageSource;

public class JodTest {
	public static void main(String[] args) throws IOException, DocumentTemplateException {
		DocumentTemplateFactory documentTemplateFactory =
				new DocumentTemplateFactory();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		DocumentTemplate template = documentTemplateFactory.getTemplate(classLoader.getResourceAsStream("template.odt"));
		Map data = new HashMap();
		data.put("name", "John Doe");
		data.put("userList", createUserList());

		ImageSource image = new RenderedImageSource(ImageIO.read(classLoader.getResourceAsStream("hamster1.jpg")));
		//ImageSource image = new FileImageSource(imagePath);
		//ImageSource image = new ClasspathImageSource(imagePath);
		data.put("image", image);

		template.createDocument(data, new FileOutputStream("document.odt"));
	}

	private static ArrayList<User> createUserList() {
		ArrayList<User> userList = new ArrayList<>();

		User user = new User();
		user.setFirstName("Mary");
		user.setLastName("Poppins");

		userList.add(user);

		user = new User();
		user.setFirstName("Willy");
		user.setLastName("Wonka");

		userList.add(user);

		return userList;
	}
}
