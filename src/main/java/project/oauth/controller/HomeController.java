package project.oauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User principal, Model model) {
        // Общие атрибуты
        String name = principal.getAttribute("name");
        if (name == null) name = principal.getAttribute("login");

        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("avatar_url");

        // Для Google verified_email (если нужно)
        Boolean verified = principal.getAttribute("email_verified");

        model.addAttribute("name", name);
        model.addAttribute("email", email != null ? email : "Not provided");
        model.addAttribute("verified", verified != null ? verified : false);
        model.addAttribute("picture", picture);

        return "dashboard";
    }
}