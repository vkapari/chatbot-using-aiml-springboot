package demo.project.chatbot.service;

import java.io.File;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import demo.project.chatbot.model.Bot;
import demo.project.chatbot.model.Chat;
import demo.project.chatbot.model.History;
import demo.project.chatbot.model.MagicBooleans;
import demo.project.chatbot.model.MagicStrings;

@Service
public class ChatService {

	 private static final boolean TRACE_MODE = false;
	 static String botName = "jarvis";
	 private static final Log LOGGER = LogFactory.getLog(ChatService.class);
	 
	private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        System.out.println("resourcesPath=> "+resourcesPath);
        return resourcesPath;
    }
	
	public String startChat(String message) {
		String textLine = "";
		String response = "";
		try {
			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot(botName, resourcesPath);
			Chat chatSession = new Chat(bot);
			bot.brain.nodeStats();

			System.out.println("Human : " + message);
			textLine = message; // IOUtils.readInputTextLine();
			if ((textLine == null) || (textLine.length() < 1)) {
				textLine = MagicStrings.null_input;
			}
			/*if (textLine.equals("q")) {
				System.exit(0);
			} else if (textLine.equals("wq")) {
				bot.writeQuit();
				System.exit(0);
			} else if (textLine.equalsIgnoreCase("quit")) {
				bot.writeQuit();
				System.exit(0);
			}*/ else {
				String request = textLine;
				if (MagicBooleans.trace_mode)
					System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0)
							+ ":TOPIC=" + chatSession.predicates.get("topic"));
				response = chatSession.multisentenceRespond(request);
				System.out.println("=> "+response);
				
				while (response.contains("&lt;"))
					response = response.replace("&lt;", "<");
				while (response.contains("&gt;"))
					response = response.replace("&gt;", ">");
				while (response.contains("<oob>"))
					response = response.replace("<oob>", "");
				while (response.contains("<search>"))
					response = response.replace("<search>", "");
				while (response.contains("</search>"))
					response = response.replace("</search>", "");
				while (response.contains("</oob>"))
					response = response.replace("</oob>", "");
				while (response.contains("\n"))
					response = response.replace("\n", " ");
				response = response.trim();
				System.out.println("Jarvis : " + response);
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured while getting response => "+e.getMessage());
			//e.printStackTrace();
			System.out.println("Can't find about it, Sorry!!");
		}
		return response;
	}
}
