package net;

import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     IP处理工具类
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class IpHelper {
    /**
     * 从http请求中获取请求源的IP信息
     *
     */
    public static String getHttpRequestIP(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 字符串ip转换为long
     *
     */
    public static long getLongIpFromString(String ip) {
        String[] ips = ip.split("[.]");
        // ip有可能是这种情形(0:0:0:0:0:0:0:1)，作下修复
        if (ips == null || ips.length < 4) {
            return 0;
        }
        if (!NumberUtils.isDigits(ips[0] + ips[1] + ips[2] + ips[3])) {
            return 0;
        }
        long num = 16777216L * Long.parseLong(ips[0]) + 65536L * Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2])
                + Long.parseLong(ips[3]);
        return num;
    }

    /**
     * long型ip转为string
     * @param ipLong
     * @return
     */
    public static String getStringIpFromLong(long ipLong){
        long mask[] = {
                0xFF000000, 0x00FF0000, 0x0000FF00, 0x000000FF};
        long num = 0;
        StringBuilder ipInfo = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            num = (ipLong & mask[i]) >> ((3-i) * 8);
            if (i > 0) {
                ipInfo.append(".");
            }
            ipInfo.append(Long.toString(num, 10));
        }
        return ipInfo.toString();
    }


}
