
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageSystem {

    static class Message {

        private String content;
//chỉ có thể truy cập từ bên trong lớp Message
        public Message(String content) {
            this.content = content;
        }
//gán giá trị của tham số content cho biến thành viên content của đối tượng Message hiện tại
        public String getContent() {
            return content;
        }
    }
    // trả về giá trị của biến thành viên content cho người gọi phương thức.
    static class MessageQueue {

        private List<Message> messages = new ArrayList<>();
        private int front = 0;
        private int rear = -1;

        public void enqueueMessage(Message message) {
            if (message != null) {
                messages.add(message);
                rear++;
            }
        }

        public Message dequeueMessage() {
            if (!isEmpty()) {
                Message message = messages.get(front);
                messages.remove(front);
                rear--;
                return message;
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return front > rear;
        }

        public int size() {
            return rear - front + 1;
        }
    }

    static class MessageStack {

        private List<Message> messages = new ArrayList<>();
        private int top = -1;
        //top để theo dõi chỉ số đầu tiên(đỉnh) trong ngăn xếp
        public void pushMessage(Message message) {
            if (message != null) {
                messages.add(message);
                top++;
            }
        }

        public Message popMessage() {
            if (!isEmpty()) {
                Message message = messages.get(top);
                messages.remove(top);
                top--;
                return message;
            } else {
                return null;
            }
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public int size() {
            return top + 1;
        }
    }

    private static void displayUserMessages(MessageStack messageStack) {

        if (messageStack.isEmpty()) {
            System.out.println("No messages to display.");
        } else {
            while (!messageStack.isEmpty()) {
                Message message = messageStack.popMessage();
                System.out.println("User messages: " + message.getContent());
            }
        }

    }

    private static void processUserMessage(String userInput, MessageQueue messageQueue, MessageStack messageStack) {
        if (userInput.length() <= 0) {
            System.out.println("\nError!! (Please enter a message)\n");
        } else if (userInput.length() <= 250) {
            Message receivedMessage = new Message(userInput);
            messageQueue.enqueueMessage(receivedMessage);
            messageStack.pushMessage(receivedMessage);
            System.out.println("Received messages: " + userInput);
        } else {
            System.out.println("Invalid message: Messages should not exceed 250 characters");
        }
    }

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        MessageStack messageStack = new MessageStack();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter Message (Enter 'Exit' to escape): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }
            processUserMessage(userInput, messageQueue, messageStack);
        }
        System.out.println("Number of messages in queue: " + messageQueue.size());
        System.out.println("Number of messages in stack: " + messageStack.size());

        displayUserMessages(messageStack);

        scanner.close();
    }
}