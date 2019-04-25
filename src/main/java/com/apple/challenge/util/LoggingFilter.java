package com.apple.challenge.util;


import com.apple.challenge.service.MetricService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;

import static com.apple.challenge.util.ApplicationConstants.THREAD_CONTEXT_RID;

@Component
public class LoggingFilter implements Filter{
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Autowired
    private MetricService metricService;

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Initializing MDC Logging Filter");
    }

    /**
     * This method is to handle MDC parameters for logging and for calculating request time for performance metrics
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long processingStartTime = System.currentTimeMillis();
        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(servletRequest);
            try {
                //Add a request Id to MDC. This will make debugging easy
                MDC.put(THREAD_CONTEXT_RID, UUID.randomUUID().toString());

            } catch (Exception ex) {
                logger.error("Failed to build MDC for request", ex);
            }
            metricService.increaseCount(((HttpServletRequest) request).getQueryString());
            chain.doFilter(requestWrapper, response);
        } finally {
            long totalProcessingTime = System.currentTimeMillis() - processingStartTime;
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            logger.info("ExecutionTime: TotalTime={}, Status={}", totalProcessingTime, servletResponse.getStatus());
            MDC.remove(THREAD_CONTEXT_RID);
        }
    }
}