package cz.zcu.fav.pia;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter({
	"*"
})
public class HelloWorldFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType("text/plain; charset=" + StandardCharsets.UTF_8.name());
		chain.doFilter(request, response);
	}

}
