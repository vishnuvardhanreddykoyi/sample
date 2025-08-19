import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhishingURLDetector {

    public static boolean isPhishing(String url) {
        // Extract domain part only (after "://")
        String domain = url;
        if (url.contains("://")) {
            domain = url.substring(url.indexOf("://") + 3);
        }

        // Rule 1: Too many dots (subdomain confusion)
        int dotCount = domain.length() - domain.replace(".", "").length();
        if (dotCount > 3) return true;

        // Rule 2: URL contains an IP address instead of domain
        String ipRegex = "([0-9]{1,3}\\.){3}[0-9]{1,3}";
        Pattern ipPattern = Pattern.compile(ipRegex);
        Matcher ipMatcher = ipPattern.matcher(domain);
        if (ipMatcher.find()) return true;

        // Rule 3: Suspicious symbols in domain
        if (domain.contains("@") || domain.contains("-")) return true;

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("🔍 Phishing URL Detector (type 'exit' to quit)");

        while (true) {
            System.out.print("\nEnter a URL: ");
            String url = sc.nextLine();

            // Exit condition
            if (url.equalsIgnoreCase("exit")) {
                System.out.println("Exiting... Stay safe online!");
                break;
            }

            if (isPhishing(url)) {
                System.out.println("Warning: Phishing suspected!");
            } else {
                System.out.println("Safe URL");
            }
        }

        sc.close();
    }
}
