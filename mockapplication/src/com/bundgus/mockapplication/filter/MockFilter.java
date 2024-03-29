/*
 * Copyright (c) 2006 Mark Bundgus
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 */

/*
 * MockFilter.java
 *
 * Created on December 20, 2006, 3:47 PM
 *
 */

package com.bundgus.mockapplication.filter;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author  mbundgus
 * @version
 */

public class MockFilter implements Filter {
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;
    
    public MockFilter() {
    }
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
    throws IOException, ServletException {
        if (debug) log("MockFilter:DoBeforeProcessing");
        //
        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        //
        
        //
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        //
        /*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
             String name = (String)en.nextElement();
             String values[] = request.getParameterValues(name);
             int n = values.length;
             StringBuffer buf = new StringBuffer();
             buf.append(name);
             buf.append("=");
             for(int i=0; i < n; i++) {
                 buf.append(values[i]);
                 if (i < n-1)
                     buf.append(",");
             }
             log(buf.toString());
         }
         */
        
    }
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
    throws IOException, ServletException {
        if (debug) log("MockFilter:DoAfterProcessing");
        //
        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        //
        
        //
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed.
        //
        /*
        for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
            String name = (String)en.nextElement();
            Object value = request.getAttribute(name);
            log("attribute: " + name + "=" + value.toString());
         
        }
         */
        //
        
        //
        // For example, a filter might append something to the response.
        //
        /*
        PrintWriter respOut = new PrintWriter(response.getWriter());
        respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }
    
    /**
     *
     * @param request The servlet request we are processing
     * @param result The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) log("MockFilter:doFilter()");
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        HttpServletRequest req = (HttpServletRequest)request;
        try {
            //RequestDispatcher rd = filterConfig.getServletContext().getRequestDispatcher(req.getContextPath()+filterConfig.getInitParameter("ResourcePath"));
            RequestDispatcher rd = filterConfig.getServletContext().getRequestDispatcher(filterConfig.getInitParameter("ResourcePath"));
            rd.forward(request,response);
            //chain.doFilter(request, response);
        } catch(Throwable t) {
            //
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            //
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(request, response);
        
        //
        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        //
        if (problem != null) {
            if (problem instanceof ServletException) throw (ServletException)problem;
            if (problem instanceof IOException) throw (IOException)problem;
            sendProcessingError(problem, response);
        }
    }
    
    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }
    
    
    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        
        this.filterConfig = filterConfig;
    }
    
    /**
     * Destroy method for this filter
     *
     */
    public void destroy() {
    }
    
    
    /**
     * Init method for this filter
     *
     */
    public void init(FilterConfig filterConfig) {
        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("MockFilter:Initializing filter");
            }
        }
    }
    
    /**
     * Return a String representation of this object.
     */
    public String toString() {
        
        if (filterConfig == null) return ("MockFilter()");
        StringBuffer sb = new StringBuffer("MockFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
        
    }
    
    
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        
        String stackTrace = getStackTrace(t);
        
        if(stackTrace != null && !stackTrace.equals("")) {
            
            try {
                
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
                
                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();;
            }
            
            catch(Exception ex){ }
        }
else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();;
            }
catch(Exception ex){ }
}
    }
    
    public static String getStackTrace(Throwable t) {
        
        String stackTrace = null;
        
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        }
catch(Exception ex) {}
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
    
    private static final boolean debug = false;
}
