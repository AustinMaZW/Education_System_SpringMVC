package sg.edu.iss.caps.email;

public interface EmailService {
    void send(String from, String to, String title, String body);
}
