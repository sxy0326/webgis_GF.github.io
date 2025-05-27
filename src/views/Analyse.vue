<template>
 <div>
  <Header></Header>
   <el-container>
  <el-container style="height:980px">   
    <el-main >
      <div id="map" ref="rootMap" class="map" style="heigt:100%">
    </div>
    </el-main>
    <el-aside width="400px" style="height:980px">


<div  style="display: flex;margin-right:50px">
  <h5 style="width:70px;margin-top:10px;margin-left:10px" >年份：</h5>
  <el-select v-model="value" placeholder="请选择年份" style="flex: 1;width:150px;">
    <el-option
     
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      >
    </el-option>
  </el-select>
</div>


<div style="display: flex;margin-right:50px">
  <h5 style="width:70px;margin-top:10px;margin-left:10px" >季度：</h5>
  <el-select v-model="value1" placeholder="请选择季度" style="flex: 1;width:150px;">
    <el-option
     
      v-for="item1 in options1"
      :key="item1.value1"
      :label="item1.label1"
      :value="item1.value1"
      >
    </el-option>
  </el-select>
</div>

<div>
  <el-button type="primary" icon="el-icon-search" @click="select()">查询</el-button>
</div>
<div style="display: flex;margin-right:50px;margin-top: 10px;">
  <h5 style="width:70px;margin-top:10px;margin-left:10px;margin-top: 5px;" >卫星:</h5>
  <el-select v-model="value2" placeholder="请选择卫星" style="flex: 1;width:150px;">
    <el-option
      v-for="item2 in options2"
      :key="item2.value2"
      :label="item2.label2"
      :value="item2.value2"
      >
    </el-option>
  </el-select>
</div>
  <div>
 
 <el-button type="primary" icon="el-icon-search" @click="choose()">选择查询</el-button>
  </div >
  <div id="numtable" style="text-align:left;border:10px dashed white;" v-for="data in datalist" :key="data.id">
    <h5 style="display:inline-block">{{data.region}}</h5>
    <h5 style="display:inline-block">（共{{data.num}}景）</h5>
    <el-progress :percentage="data.percent" color="#add8e6">{{data.percent}}%</el-progress>
  </div>
  
    </el-aside>
  </el-container>
</el-container>
   
 </div>
</template>

<script>
import "ol/ol.css";
import Map from "ol/Map";
import View from "ol/View";
import axios from 'axios';
import Feature from 'ol/Feature';
import TileLayer from "ol/layer/Tile";
import TileWMS from "ol/source/TileWMS";
import OSM from "ol/source/OSM";
import XYZ from 'ol/source/XYZ';
import ZoomSlider from "ol/control/ZoomSlider";
import {Vector} from "ol/layer";
import VectorLayer from 'ol/layer/Vector'
import VectorSource from 'ol/source/Vector';
import {Fill, Stroke, Circle, Style, Text} from 'ol/style';
import { GeoJSON, WKT } from 'ol/format';

// import wktCon from "terraformer-wkt-parser" //WKT可换为wkt
// import axios from "axios";
import request from "@/utils/request";
import { Polygon, MultiPolygon } from "ol/geom";
import { defaults as defaultControls, OverviewMap } from "ol/control";
import {fromLonLat} from 'ol/proj';
import { Select } from "ol/interaction";
import { click } from "ol/events/condition";
import {transform} from 'ol/proj';

import Header from '../components/Header.vue'
export default {
  components: {
    Header,
},
  data() {
    return {     
      options: [{
          value: '2022',
          label: '2022'
        }, {
          value: '2021',
          label: '2021'
        }, {
          value: '2020',
          label: '2020'
        }, {
          value: '2019',
          label: '2019'
        }, {
          value: '2018',
          label: '2018'
        }, {
          value: '2017',
          label: '2017'
        }, {
          value: '2016',
          label: '2016'
        }, {
          value: '2015',
          label: '2015'
        }],
        value: '2022',

        options1: [{
          value1: '01',
          label1: '一季度'
        }, {
          value1: '02',
          label1: '二季度'
        }, {
          value1: '03',
          label1: '三季度'
        }, {
          value1: '04',
          label1: '四季度'
        }, {
          value1: '0',
          label1: '全年'
        }],
        options2: [{
          value2: '1',
          label2: '高分一号'
        }, {
          value2: '2',
          label2: '高分二号'
        }, {
          value1: '3',
          label2: '高分三号'
        }, {
          value2: '4',
          label2: '高分四号'
        }, {
          value2: '5',
          label2: '高分五号'
        },{
          value2: '6',
          label2: '高分六号'
        }],
      value1: '0',
      value2: '1',
      activeIndex:'1',
      see:true,
      datalist: [],
      wktdata:null,
    };
  },

  methods: {

    choose() {
      var productid = "GF1"
      // request还没跑完，这个后面的就调用了
      // const data = axios.get("/newinfo" + "/" + productid)
      console.log("----------data-------------")
      request.get("/newinfo"+"/" + productid, {
      }, {}).then((response) => {
        console.log("111111111111+2222");
        console.log(response);
        console.log("----------------")
        console.log(response.data)
        console.log("----------------")
        let data = {"type":"FeatureCollection","features":[{"type":"Feature","id":916,"properties":{},"geometry":{"type":"Polygon","coordinates":[[[88.74148592,36.45833503],[98.28193495,34.21233622],[92.58097874,30.57857592],[89.40083832,31.14451617],[88.74148592,36.45833503]]]}}],"gid":1898}
        var vectorSource = new VectorSource({
          features: new GeoJSON({featureProjection: 'EPSG:3857'}).readFeatures(response.data)
        });
        console.log("-------------source"+vectorSource)
        var vectorLayer = new Vector({
          source: vectorSource,
          Style:new Style({
    stroke: new Stroke({
      color: 'blue',
      lineDash: [4],
      width: 3,
    }),
    fill: new Fill({
      color: 'rgba(0, 0, 255, 0.1)',
    }),
  }),
              });
        this.map.addLayer(vectorLayer)
      });
    //   let geosource = new VectorSource({
    //     features:new GeoJSON().readFeatures(this.datalist)
    //   })
    //   var geoLayer = new VectorLayer({
    //     source: geosource,
    //     // 设置样式，边框和填充
    //     style: new Style({
    //        stroke: new Stroke({
    //        color: 'green',
    //        width: 5
    //       }),
    //   fill: new Fill({
    //     color: 'rgba(255, 255, 0, 0.5)'
    //   })
    // }) 
    //   })
    // this.map.addLayer(geoLayer)

      // const wkt = this.datalist;
      // var WKT = require('terraformer-wkt-parser');
      // var geojson = WKT.parse(wkt);
      // console.log("wkt转geojson",geojson )
      // const format = new WKT();
      // console.log(format+"This is format")
      // const feature = format.readFeature(wkt, {
      //   dataProjection: 'EPSG:4326',
      //   featureProjection: 'EPSG:3857',
      // });
      // const vector = new VectorLayer({
      //   source: new VectorSource({
      //     features: [feature],
      //   }),
      //   visible: true,
      //   zIndex:3,
      // });
      // this.map.addLayer(vector);
    },
    
   select(){
         var valuelist = this.value + this.value1;
         console.log(valuelist);
     request.get("/cover"+"/"+valuelist, {
      //  params: {
      //        id: valuelist,
      //     }
          },{}).then((response) => {
          console.log(response,1111);
          this.datalist = response.data;
          
        });
    
    // this.wmsLayer1.setVisible(false);
    
    var wmsLayer = new TileLayer({
        // extent: [
        //   // 边界
        //   10836963.12, 3004762.63, 12083339.77, 4070833.45,
        // ],
        source: new TileWMS({
          url: 'http://www.sasclouds.com/map/cloudPlatform/wms',
					// Layers需要指定要显示的图层名
					params: { 'SERVICE': 'WMS',
              'VERSION': '1.0.0',
              'REQUEST': 'GetMap',
              'FORMAT': 'image/png',
              'TRANSPARENT': 'true',
              'SRS': 'EPSG:3857',
              'layers': 'cloudPlatform:acquisition_report',
              'exceptions': 'application/vnd.ogc.se_inimage',
              'CQL_FILTER': 'id = 12950761',
              'STYLES':'' ,
              // 'WIDTH': '2196',
              // 'HEIGHT': '636',
              // 'BBOX': '870770.6262247264,1487158.822316389,22356302.03284835,7709744.420956017',
              },
          serverType: "geoserver",
          
        }),
        visible: true,
        zIndex: 20,
      });
      this.map.addLayer(wmsLayer);
    },
 
    format(percentage) {
        return percentage === 100 ? '满' : `${percentage}%`;
      },
    initMap() {
      console.log(13214654);
      this.map = new Map({      
        layers: [
          new TileLayer({
            source: new OSM(),
            // visible: true,
            // zIndex: 1,
            // name: "OSM",
          }),
        ],
        target: "map",
        view: new View({
          projection: "EPSG:3857", //默认
          center: fromLonLat([105.21, 29.272848]),
          zoom: 4.5,
        }),
      });
    },
    // addArea() {
    //     var wmsLayer1 = new TileLayer({
    //     // extent: [
    //     //   // 边界
    //     //   10836963.12, 3004762.63, 12083339.77, 4070833.45,
    //     // ],
    //     source: new TileWMS({
    //       url: 'http://www.sasclouds.com/map/cloudPlatform/wms',
		// 			// Layers需要指定要显示的图层名
		// 			params: { 'SERVICE': 'WMS',
    //         'VERSION': '1.0.0',
    //         'REQUEST': 'GetMap',
    //         'FORMAT': 'image/png',
    //         'TRANSPARENT': 'true',
    //         'SRS': 'EPSG:3857',
    //         'layers': 'cloudPlatform:acquisition_report',
    //         'exceptions': 'application/vnd.ogc.se_inimage',
    //         'CQL_FILTER': 'id = 12950761',
    //         'STYLES':'' ,
    //         // 'WIDTH': '2196',
    //         // 'HEIGHT': '636',
    //         // 'BBOX': '870770.6262247264,1487158.822316389,22356302.03284835,7709744.420956017',
    //         },

    //        serverType: "geoserver",
          
    //     }),
        
    //     zIndex: 20,
    //   });
    //   console.log(22222222222)
    //   this.map.addLayer(wmsLayer1);
       
    // },
    // mapdetail(){
    //         this.$router.push('/mapdetail')
    //     },
    //  introduce(){
    //    this.$router.push('/home')
    //  }, 
    //  home(){
    //    this.$router.push('/')
    //  }

 },
 mounted(){
   this.initMap();
   console.log(123456789);
    
   request.get("/cover/2022",{   
     },{}).then((response) => {
          console.log(response,1111);
          this.datalist = response.data;
        })
 }
 
};

</script>

<style>
.title2{
  width: 400px;
  position: absolute;
  left: 600px;
  top: -5px;
}
.el-menu-demo{
  width: 350px;
  height: 20px;
  position: fixed;
  left: 20px;
}
#map {
  margin-top: -20px;
  margin-left: -20px;
  width: 1200px;
  height: 100%;
  
}
.el-container{
  width: 1520px;
  height: 1000px;
}
.el-header {
    height: 117px;
    background-color:#add8e6;
    color: rgb(58, 143, 146);
    text-align: center;
    line-height: 60px;
    font-size: 30px;
  }
.el-aside {
    height: 900px;
    background-color: #ffffff;
    color: #333;
    text-align: center;
    /* line-height: 200px; */
  }

</style>
