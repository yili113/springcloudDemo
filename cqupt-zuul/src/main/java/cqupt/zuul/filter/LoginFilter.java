package cqupt.zuul.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yiLi
 * @create 2019-11-02 9:52
 */
@Component
public class LoginFilter extends ZuulFilter {// 使用zuul的过滤器 需要继承这个基类
    @Override
    public String filterType() {// 返回字符串 pre/route/post/error
        return "pre";
    }

    @Override
    public int filterOrder() {// 通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高
        return 1;
    }

    /**
     * 该过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {// 来自IZuulFilter
        return true;
    }

    /**
     * 编写过滤器的业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {// IZuulFilter
        // 获取zuul提供的上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 从上下文对象中获取请求对象
        HttpServletRequest request = currentContext.getRequest();
        // 获取token信息
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {// true 是token为空的情况
            // 过滤该请求，不对其进行路由
            currentContext.setSendZuulResponse(false);
            // 设置状态码 未认证的登录 401
            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            // 设置响应信息
            currentContext.setResponseBody("error");

        }
        // 校验通过，把登陆信息放入上下文信息，继续向后执行
        currentContext.set("token", token);


        // return null 表示过滤器什么都不做
        return null;
    }
}
