package com.example.washingstore.util;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000119670152";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDIwt6AofD+WfwtTmA8zG+WkBVtCl5UKSn1/a6rccQ1aMGsHmxjXkYsDicwRTDqyYzecdG2ieOWDWUJ7d1fTiD7I43nnwZPmQSTluzxeshKqqW+VLVC8VElgVsy6y+BMisH97swfFk7c9CRmLdU+X+Wtgx5rm/YrVaMgOhEZwzaqhvguqimXZ2FAf/CS1uhTMIa1MfvgrwOgip81E3fyI2QF7Ph4H8drsDxGVaaripgfSoeUtx0E6SEjqfh0vL1BbolMsB5UG+pG+m+beLIvjrnDZbLzZv1En2slCCFtXLpB/c0L046+UPgIMvnWAGrkq8cRLJAK3+pmd3I45zdaNsvAgMBAAECggEAY0XxLpxlhfDLQUALWKUi3MncvnGtGWLNjAVolQCJrHtjoY/duvlIK8rH2Beuy7rkfK69q8zdjDqNZu6HU/RXXLhFGG7LeRrKZ4zvF63GUx21HbvXcgXQQYKIrVIcyH8dRjBbEOu8WCi5zvU8WrmRPN5Cpvz586QrMwkfUYu/kHRDZTtat85XHUDWZmOZCvuBT2Psy/Dx6k7MRvlM9Tc2w1sR++FMnv3CUwmnBPWqq8y3lEF2EiXtUQm3E8C9ClZs8Q34gJ2encJ4sRcXfbyGzuZxB25dz0vRgjuN4jyfCTiTH50v9mUtk9oezNP9uCKl3aglHBb+4gl9HOMEkFWZEQKBgQDjwgvgwSEosFIE4Ofh/FM2A/u8X4c4QvEkfr7yIYDYyVdLqQ7kbnRSoiYoSurMrvfwwZEjM7CnaUikafVso7FjfQMkrBE2tcpmiNo18w9ZF/TWvKUl4FdtxsBHMTJucVhUGyp5VUN7yqLv6FOx97fyfh5UuEFDWtHKCRZMfqdXKQKBgQDhp9YY4VFJvfoGZ2+IyohgU6IB9y8Xi8OnnmBHLT5QVFICLyO9VFtinmNEUJhXFfOyK4bMaTQb9l3cfwQPadSBkkHwYqBGM++UGhiakoVtzxY5ErWv5nlY88MFo+XmzljTk11DJXtbXM2VimnfCdqMFKaNhs7nRucc+xZBLzkilwKBgE1M18ExazKnHT9AohLmE8vacD8P4RHsTnHHfsEpM9TgCPvJ5h8LifldxTuZqbVMkSQqXjsnWQaIyjnbKIv1xsfwbvesWq9J5ecE/aKf2w6u/c8/+YEwchv0/99pyCcJ2yO4kKLbmGrUbJamMHnPjKQFv/+bBbN5iIhcqy/NMLh5AoGAZiKQPhylrUnmg8WHbxO0nFzeRCN8Vhz1hsWhcDXjMXqoz8JEW+p1nnJwHbiY6znNXoRi89xeMJXXL/jxfoR5IdhwLfyF7KCPPKp/Hl9ZMzV7KoP0ZlRb8k4KpF/lrA0TmVfm64BokBa2GoxWlRQhriueowo0Vev6qVxgW0qjgqsCgYA5KDI/rVY5owt3f6B6Liwq7vSKvEV3oQY3vZUkAMtOk2QPhovShZj8qROL5ljRumLSwW3H20Dv/F/ihMlPPVPQJSB9SYw+L7TrWUusOb0MsOUvlBpK9YZL1yb0E+2OOClpLns/oM5NSfb+e6NpLHfzxievcnAfZRmAc9zZxX+bWA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApeOSsuMVxxwinFuof4YMM0AmD54rnQXmaVGUWL3swUqZEFsdwVC8Je08P9CUfAkqQyulNtfdPEQChf5F9IYlPFpKv3/FRmGaeelt87LJT+05ob+p5yZ0hCDE4rwIBB/A3wRxJDTEq03IRWtBGYoseGb0rJNakh1reea3GUexvqRJ8fCWtzbSHuWV6vCJelyKmjAOV+JocRdzz4UNtV4GNalY0KK6qbT+zw7YA+JoV3pDSXgISgJmrP9Y7Y8xrstMVgF2qP/dpIkDHb+cGkiLL9ym95MbpBCbhZdO0fSg4TO3WL7tV+VzPYjAjFX0wfUmNwMTVWXuUpdmD1WqaPTHzwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/client/welcome";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/client/welcome";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


}
