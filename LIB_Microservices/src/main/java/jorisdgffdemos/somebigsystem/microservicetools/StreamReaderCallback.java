package jorisdgffdemos.somebigsystem.microservicetools;

import jorisdgffdemos.somebigsystem.cqrskit.AbstractCommand;

public interface StreamReaderCallback {

    public void eventReceived(Message message, String content);
}
