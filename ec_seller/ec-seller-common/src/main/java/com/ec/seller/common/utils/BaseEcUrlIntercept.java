package com.ec.seller.common.utils;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;


public  class BaseEcUrlIntercept implements EcUrlIntercept {
    private final static Log log = LogFactory.getLog(BaseEcUrlIntercept.class);

    protected Map<String, String[]> urlMaps = new HashMap<String, String[]>();

    protected String urlSeparate = "-";

    protected String urlSuffix = ".html";

    public void doIntercept(EcUrl jdUrl) {
        String path = jdUrl.getPath();
        if (StringUtils.isNotBlank(path)) {
            if (urlMaps.containsKey(path)) {
                Object o = urlMaps.get(path);
                int start = path.lastIndexOf('.');
                int start1 = path.lastIndexOf('/');
                StringBuilder builder;

                if (start > start1) {
                    builder = new StringBuilder(path.substring(0, start));
                } else {
                    builder = new StringBuilder(path);
                }
                if (o != null) {
                    String[] parameters = (String[]) o;
                    Map<String, Object> queryMap = jdUrl.getQuery();
                    for (String parameter : parameters) {
                        builder.append(urlSeparate);
                        if (StringUtils.isNotEmpty(parameter)) {
                            Object o1 = queryMap.get(parameter);
                            if (o1 != null) {
                                builder.append(jdUrl.encodeUrl(o1.toString()));
                            }
                        }
                        queryMap.remove(parameter);
                    }
                }
                builder.append(urlSuffix);
                jdUrl.setPath(builder.toString());
            }
        }
    }

    public void setUrlMaps(Map<String, String[]> urlMaps) {
        this.urlMaps = urlMaps;
    }

    public void setUrlSeparate(String urlSeparate) {
        this.urlSeparate = urlSeparate;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }
}
