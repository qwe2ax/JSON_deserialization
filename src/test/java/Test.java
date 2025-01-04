import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode("MaskOfMadness");
        System.out.println(pass);
    }
}
