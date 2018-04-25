package com.sugar.com.util;

import freemarker.cache.URLTemplateLoader;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteTemplateLoader extends URLTemplateLoader {
    // 远程模板文件的存储路径（目录）
    private String remotePath;
    public RemoteTemplateLoader(String remotePath) {
        if (remotePath == null) {
            throw new IllegalArgumentException("remotePath is null");
        }
        this.remotePath = canonicalizePrefix(remotePath);
        if (this.remotePath.indexOf('/') == 0) {
            this.remotePath = this.remotePath.substring(this.remotePath.indexOf('/') + 1);
        }
    }

    @Override
    protected URL getURL(String name) {
        name = name.replace("_zh_CN", "");
        String fullPath = this.remotePath + name;
        System.out.println(fullPath);
        if ((this.remotePath.equals("/")) && (!isSchemeless(fullPath))) {
            return null;
        }
        URL url = null;
        try {
            url = new URL(fullPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static boolean isSchemeless(String fullPath) {
        int i = 0;
        int ln = fullPath.length();

        if ((i < ln) && (fullPath.charAt(i) == '/')) {
            i++;
        }

        while (i < ln) {
            char c = fullPath.charAt(i);
            if (c == '/') {
                return true;
            }
            if (c == ':') {
                return false;
            }
            i++;
        }
        return true;
    }
}
