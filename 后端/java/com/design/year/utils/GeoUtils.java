package com.design.year.utils;
import com.alibaba.fastjson.*;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class GeoUtils {

    /**
     * wkt转GeoJson包括属性值（feature）
     * @param wkt wkt
     * @param map 属性值
     * @return GeoJson
     */
    public static HashMap<Object, Object> wktToJson(String wkt, Map<String, Object> map) {
        String json = null;
        // geoJson
        HashMap<Object, Object> feature = new HashMap<>();
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            StringWriter writer = new StringWriter();
            GeometryJSON g = new GeometryJSON();
            g.write(geometry, writer);
            JSONObject jsonObject = JSONObject.parseObject(writer.toString());
            feature.put("type", "Feature");
            feature.put("geometry", jsonObject);
            feature.put("properties", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feature;
    }

    /**
     * wkt转GeoJson（geometry）  不包括属性
     * @param wkt wkt
     * @return geoJson
     */
    public static JSONObject wktToJson(String wkt) {
        JSONObject jsonObject = null;
        try {
            WKTReader reader = new WKTReader();
            Geometry geometry = reader.read(wkt);
            StringWriter writer = new StringWriter();
            GeometryJSON g = new GeometryJSON();
            g.write(geometry, writer);
            jsonObject = JSONObject.parseObject(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String jsonToWkt(String geoJson) {
        String wkt = null;
        GeometryJSON gJson = new GeometryJSON();
        Reader reader = new StringReader(geoJson);
        try {
            Geometry geometry = gJson.read(reader);

            wkt = geometry.toText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wkt;
    }
}
