package com.crabstick.myapp;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter{

	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * web.xml 에 정의한 URL 패턴에 의해서 이 웹 서버로 오는 모든 요청이 이 메서드를 지나치게 됩니다. 
		 * 이 메서드를 한 번 지나고 나면 결과를 내려받는 HTML 페이지 Header에 Access-Control 과 관련된 4개의 라인이 추가되게 됩니다.
		*/
		HttpServletResponse response = (HttpServletResponse) res;
		//POST, GET, OPTIONS, DELETE 요청에 대해 허용하겠다는 의미입니다.
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		/*
		 * HTTP Request 요청에 앞서 Preflight Request 라는 요청이 발생되는데, 이는 해당 서버에 요청하는 메서드가 실행 가능한지(권한이 있는지) 확인을 위한 요청입니다. 
		 * Preflight Request는 OPTIONS 메서드를 통해 서버에 전달됩니다. (위의 Methods 설정에서 OPTIONS 를 허용해 주었습니다.)
		 * 여기서 Access-Control-Max-Age 는 Preflight request를 캐시할 시간입니다. 단위는 초단위이며, 3,600초는 1시간입니다. 
		 * Preflight Request를 웹브라우저에 캐시한다면 최소 1시간동안에는 서버에 재 요청하지 않을 것입니다.
		 */
        response.setHeader("Access-Control-Max-Age", "3600");
        /*
         * 이는 표준화된 규약은 아니지만, 보통 AJAX 호출이라는 것을 의미하기 위해 비공식적으로 사용되는 절차입니다. 
         * JQuery 또한 AJAX 요청 시, 이 헤더(x-requested-with)를 포함하는 것을 확인하실 수 있습니다. 
         * 여기서는 이 요청이 Ajax 요청임을 알려주기 위해 Header 에 x-request-width를 설정합니다. 
         * Form을 통한 요청과 Ajax 요청을 구분하기 위해 사용된 비표준 규약지만, 많은 라이브러리에서 이를 채택하여 사용하고 있습니다. 
         * (참고로 HTML5 부터는 Form 과 Ajax 요청을 구분할 수 있는 Header가 추가되었습니다.)
         */
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        /*
         * 이 부분이 가장 중요한 부분입니다. * 는 모든 도메인에 대해 허용하겠다는 의미입니다. 즉 어떤 웹사이트라도 이 서버에 접근하여 AJAX 요청하여 결과를 가져갈 수 있도록 허용하겠다는 의미입니다.
         * 만약 보안 이슈가 있어서 특정 도메인만 허용해야 한다면 * 대신 특정 도메인만을 지정할 수 있습니다.
         */
        //response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Origin", "https://openapi.naver.com/v1/search/local.xml");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8805");
        chain.doFilter(req, res);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
