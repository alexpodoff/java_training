package ru.auto.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.188.26.64");
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
    }

    @Test
    public void testInvalidIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.188.26.xxx");
        assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
    }
}

