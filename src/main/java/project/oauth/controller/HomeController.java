package project.oauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";  // просто welcome, с ссылкой на login
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // кастомная страница с кнопкой Google
    }

    @GetMapping("/dashboard")
    public String dashboard(
            @AuthenticationPrincipal OidcUser principal,
            Model model
    ) {
        model.addAttribute("name", principal.getFullName());
        model.addAttribute("email", principal.getEmail());
        model.addAttribute("verified", principal.getEmailVerified());
        model.addAttribute("picture", principal.getPicture());
        return "dashboard";
    }
}