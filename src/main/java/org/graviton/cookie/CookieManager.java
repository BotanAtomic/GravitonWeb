package org.graviton.cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

@Service
public class CookieManager {

    @Value("${session.cookie.expire}")
    private int expireTime;

    public void addCookie(HttpServletResponse response, String attribute, String value) {
        response.addCookie(new Cookie(attribute, value) {{
            setMaxAge(expireTime);
            setPath("/");
        }});
    }

    private Cookie emptyCopy(Cookie cookie) {
        return new Cookie(cookie.getName(), "") {{
            setMaxAge(0);
            setPath("/");
        }};
    }

    public void clearCookie(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null)
            Stream.of(request.getCookies()).forEach(cookie -> response.addCookie(emptyCopy(cookie)));
    }

}
