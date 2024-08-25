# testTao-irclib4java
irclib4java

Design a new class to extend TaoTask whitch analyze the lines from the irc server,
Then create a socket,get the i\o stream from it,
After that,create a DThreadPool instance,
Finally create a TaoIO instance,

EXAMPLE CODE BELOW:
```java

///Author: Hugh
///TIME: 2024 8 25 9 22 started
///Main.java

import java.io.*;
import java.net.Socket;
import java.util.*;
import test.hugh.t.*;
import test.hugh.t.util.*;

public class Main {
	public static TaoIO tao;
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(Servers.servers.get(0),6667);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Input your nickname:");
			String nick = scanner.nextLine();
			System.out.println("And then realname:");
			String user = scanner.nextLine();
			System.out.println("Your password:");
			String pass = scanner.nextLine();
			tao = new TaoIO(new InputStreamReader(socket.getInputStream()),
					new OutputStreamWriter(socket.getOutputStream()),
					new DTThreadPool<TaoTask>(10),new DemoTask(),nick,user,pass);
			new Thread(() -> {
                try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
                    String message;
                    System.out.println("Enter messages,/quit to quit");
                    while (true) {
                        message = userInput.readLine();
                        TMessage msg = null;
                        if (message.startsWith("/quit")) {
                        	System.exit(0);
                        }else if (message.startsWith("/nick")) {
                        	msg = new TTMessage(TMessage.NICK + " " + message.substring(5));
                        }else if (message.startsWith("/user")) {
                        	String[] m = message.substring(5).trim().split(" ");
                        	msg = new TUMessage(m[0].trim(),m[1].trim());
                        }else if (message.startsWith("/join")) {
                        	String[] m = message.substring(5).trim().split(" ");
                        	if (m.length > 1) msg = new TJMessage(m[0].trim(),m[1].trim());
                        	else msg = new TJMessage(m[0].trim(),"");
                        }else if (message.startsWith("/msg")) {
                        	String[] m = message.substring(4).trim().split(" ");
                        	if (m.length > 1) msg = new TPRMessage(m[0],
                        			message.substring(4).trim().substring(m[0].length()));
                        }else if (message.startsWith("/part")) {
                        	msg = new TPAMessage(message.substring(5));
                        }else if (message.startsWith("/mode")) {
                        	String[] m = message.substring(5).trim().split(" ");
                        	if (m.length > 1) {
                        		msg = new TMMessage(m[0],m[1]);
                        	}
                        }else msg = new TTMessage(message);
                        
                        if (msg != null) tao.send(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	static class DemoTask extends TaoTask {
		@Override
		public void run() {
			String line = getLine();
			System.out.println(line);
		}
	}
}

```

In the end , u make a the simplest irc client in the world!
