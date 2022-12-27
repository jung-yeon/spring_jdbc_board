package com.nhnacademy.jdbc.board.user.controller;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.domain.UserType;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;
import java.util.Optional;


@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@CookieValue(value = "CSESSION", required = false) String session,
                        Model model) {
        if (StringUtils.hasText(session)) {
            model.addAttribute("userId", session);
            if (session.equals(userService.getUser(session).get())) {
            }
            return "login/loginSuccess";
        }
        return "login/login";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            Cookie cookie = new Cookie("CSESSION", null);
            response.addCookie(cookie);
            session.invalidate();
        }
        return "login/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("userId") String userId,
                          @RequestParam("password") String pwd,
                          HttpServletRequest req,
                          HttpServletResponse resp,
                          ModelMap modelMap) {

        String userType = (req.getParameter("userType").toUpperCase());
        Optional<User> user = userService.getUser(userId);
        try {
            if (userService.getUser(userId) != null) {
                if (user.get().getUserId().equals(userId)
                        && user.get().getPassword().equals(pwd)
                        && user.get().getType().toString().equals(userType)) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute(userId, userId);
                    Cookie cookie = new Cookie("CSESSION", String.valueOf(session.getAttribute(userId)));
                    resp.addCookie(cookie);
                    modelMap.put("userId", user.get().getUserId());
                    return "login/loginSuccess";
                } else if (!user.get().getUserId().equals(userId) ||
                        !user.get().getPassword().equals(pwd) ||
                        !user.get().getType().equals(userType)) {

                    modelMap.put("msg", "아이디, 비밀번호, 타입을 확인후 로그인 해주세요.");
                    modelMap.put("url", "/login");
                    return "alert";
                }

            }
        } catch (NoSuchElementException e) {
            modelMap.put("msg", "회원정보가 없습니다.");
            modelMap.put("url", "/login");
            return "alert";
        } catch (NullPointerException e) {
            modelMap.put("msg", "입력값이 없습니다.");
            modelMap.put("url", "/login");
            return "alert";
        }
        return "redirect:/login";
    }
}
