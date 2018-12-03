package filter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ${todo}(这里用一句话描述这个类的作用)
 *
 * @author ${user}    ${date} ${time}
 *         ${tags}
 */
public class CharacterCustomFilter extends CharacterEncodingFilter {
    private String encoding;
    private boolean forceEncoding = false;
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setForceEncoding(boolean forceEncoding) {
        this.forceEncoding = forceEncoding;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
            if(request.getRequestURL().indexOf("page/bbgs/excelHtml/")==-1) {
                request.setCharacterEncoding(this.encoding);
                if (this.forceEncoding) {
                     response.setCharacterEncoding(this.encoding);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
