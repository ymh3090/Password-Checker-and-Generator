import java.util.Random;

public class PasswordGenerator {

    /**
     * يا احويا، دي الدالة بتاعتك (Your Task)
     * أنا جهزتلك الـ Structure بتاعها عشان أقدر أنادي عليها في الـ Main عندي.
      * المطلوب منك تنفيذه هنا حسب ملف المشروع
     * 1. لازم الباسورد يحتوي على حرف Capital واحد على الأقل.
     * 2. لازم يحتوي على حرف Small واحد على الأقل.
     * 3. لازم يحتوي على رقم (Digit) واحد على الأقل.
     * 4. لازم يحتوي على رمز خاص (Symbol) واحد على الأقل.
     * 5. كمل باقي الطول (length) بحروف عشوائية من الأنواع دي.
     */
    public static String generatePassword(int length) {
        
        // ---------------------------------------------------------------
        // (تحذير: الكود اللي تحت ده كود مؤقت  أنا كاتبه)
        // (هدفه بس إن الـ Main Menu تشتغل معايا وميديش Error وأنا بجرب الـ Rules)
        // (لما تستلم الملف، امسح اللي تحت ده واكتب اللوجيك الصح بتاعك)
        // ---------------------------------------------------------------
        
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder pass = new StringBuilder();
        Random rnd = new Random();
        
        for (int i = 0; i < length; i++) {
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        
        return pass.toString();
    }
}