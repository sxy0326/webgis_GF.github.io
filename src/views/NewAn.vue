<template>
  <div class="common-layout">
    <el-container>
      <el-header id="head"><Header></Header></el-header>
      <el-container>
      <el-aside width="300px">
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
      >
      <el-sub-menu index="1">
          <template #title>
            <span>区域影像分析</span>
          </template>
        <el-menu-item-group>
        <el-menu-item index="1-1">城市:
          <el-cascader
            size="large"
            :options="options"
            v-model="selectedOptions"
            @change="handleChange">
          </el-cascader>
        </el-menu-item>
         <el-button type="primary" @click="dialogFormVisible =  true">查询</el-button>
        </el-menu-item-group>
      </el-sub-menu>


      <el-sub-menu index="2">
          <template #title>
            <span>高分系列卫星分析</span>
          </template>
        <el-menu-item-group>
          <!-- <el-menu-item index="2-1">城市:
          <el-cascader
            size="large"
            :options="options"
            v-model="selectedOptions"
            @change="handleChange">
          </el-cascader>
        </el-menu-item> -->
          <el-menu-item index="2-2">卫星:<br/>
          <el-select v-model="value2" class="m-1" placeholder="Select">
              <el-option
                v-for="item in options1"
                :key="item.value2"
                :label="item.label2"
                :value="item.value2"
              />
          </el-select>
        </el-menu-item>
        <el-button type="primary" @click="search2()">查询</el-button>
        <!-- <el-button type="primary" @click="test ()">测试</el-button> -->
        </el-menu-item-group>
      </el-sub-menu>

      <el-sub-menu index="3">
          <template #title>
            <span>归档数据统计</span>
          </template>
          <el-menu-item-group>
        <el-form :model="form">
          <el-form-item label="区域选择">
            <el-cascader
              size="large"
              :options="options"
              v-model="selectedOptions"
              @change="handleChange">
            </el-cascader>
          </el-form-item>
          <el-form-item label="日期选择">
            <el-checkbox-group v-model="form.date">
            <el-checkbox label="最近一年" name="type" />
            <el-checkbox label="最近一月" name="type" />
          </el-checkbox-group>
          </el-form-item>
          <el-form-item label="卫星类型">
            <el-checkbox-group v-model="form.type">
            <el-checkbox label="光学" name="type" />
            <el-checkbox label="高光谱" name="type" />
            <el-checkbox label="雷达" name="type" />
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="两米">
            <el-checkbox-group v-model="form.level">
            <el-checkbox label="GF-1" name="type" />
            <el-checkbox label="GF-6" name="type" />
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="亚米">
            <el-checkbox-group v-model="form.level">
            <el-checkbox label="GF-2" name="type" />
            <el-checkbox label="GF-7" name="type" />
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="其他">
            <el-checkbox-group v-model="form.level">
            <el-checkbox label="GF-3" name="type" />
            <el-checkbox label="GF-4" name="type" />
            <el-checkbox label="GF-5" name="type" />
            </el-checkbox-group>
          </el-form-item> 
          <el-form-item>
            <el-button type="primary" @click="dialogFormVisible1 = true">统计</el-button>
          </el-form-item>       
        </el-form>
          </el-menu-item-group>
      </el-sub-menu>

</el-menu>
      <!-- <el-button type="primary" size="large" style="margin: 20px;" @click="dialogFormVisible = true">测试2</el-button> -->

    <el-dialog v-model="dialogFormVisible" 
              title="结果展示" 
              @open="open()"
              append-to-body>
      <el-form :inline="true" size="medium" label-width="80px">
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item :label="regionname">
              <div id="newEcharts" style="width:650px;height:500px;padding-top:40px;"></div>
             </el-form-item>
            </el-col>
         </el-row>
     </el-form> 

      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible =  false">
          Confirm
        </el-button>
      </span>
    </template>
    </el-dialog>

    <el-dialog v-model="dialogFormVisible1" 
              title="高分卫星归档统计图" 
              @open="open1()"
              append-to-body>
      <el-form :inline="true" size="medium" label-width="80px">
       <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="24" :lg="24">
            <el-form-item :label="regionname">
              <div id="newEcharts1" style="width:650px;height:500px;padding-top:40px;"></div>
             </el-form-item>
           </el-col>
         </el-row>
     </el-form> 

      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible1 =  false">
          Confirm
        </el-button>
      </span>
    </template>
    </el-dialog>
        </el-aside>
        <el-main id="newmap" class="newmap" ref="newmap"></el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
import Header from '../components/Header.vue'
import Map from 'ol/Map';
import XYZ from 'ol/source/XYZ';
import TileLayer from 'ol/layer/Tile';
import View from 'ol/View';
import 'ol/ol.css';
import { provinceAndCityData } from 'element-china-area-data'
import { TileWMS,ImageStatic } from 'ol/source';
import request from "@/utils/request";
import {Vector,Image} from "ol/layer";
import VectorLayer from 'ol/layer/Vector'
import VectorSource from 'ol/source/Vector';
import {Fill, Stroke, Circle, Style, Text} from 'ol/style';
import { GeoJSON, WKT } from 'ol/format';
import Projection from 'ol/proj/Projection'
import {fromLonLat} from 'ol/proj';
import ImageLayer from 'ol/layer/Image';
import * as echarts from 'echarts';
import staticImag from '@/assets/bg.jpg'

export default {
  components: {
    Header,
  },
  data() {
    return {
      form:[],
      sizeForm: {},
      regionname:"", 
      dialogFormVisible: false,
      dialogFormVisible1: false,
      isCollapse: true,
      options: provinceAndCityData,
      selectedOptions: [],
      options1: [{
          value2: '1',
          label2: 'GF1'
        }, {
          value2: '2',
          label2: 'GF2'
        }, {
          value1: '3',
          label2: 'GF3'
        }, {
          value2: '4',
          label2: 'GF4'
        }, {
          value2: '5',
          label2: 'GF5'
        },{
          value2: '6',
          label2: 'GF6'
        }],
        value1: '',
    };
  },
  name: 'HelloWorld',
  
  methods: {
    initChart() {
      const Chart = echarts.init(document.getElementById('newEcharts'));
      var data = [
          { value: 666, name: '高分一号' },
          { value: 193, name: '高分二号' },
          { value: 300, name: '高分三号' },
          { value: 200, name: '高分四号' },
          { value: 100, name: '高分五号' },
          { value: 400, name: '高分六号' }
        ];
        var color = ['#146de0', '#27ef5e', '#ffc140', '#d151d8', 'red','#ce9178'];
        const option = {
          backgroundColor: '#081736',
          color: color,
          tooltip: {
            trigger: 'item',
            backgroundColor: 'rgba(0,0,0,0.5)',
            color: '#fff',
            formatter: function (params) {
              return (
                params.name +
                '<br/>' +
                params.marker +
                '<span style="color:' +
                params.color +
                '">' +
                params.data['name'] +
                '\n' +
                params.data['value'] +
                '</span>'
              );
            }
          },
          legend: {
            orient: 'vertical',
            icon: "circle",
            left: '24.2%',
            top: '42%',
            itemWidth: 14,
            itemGap: 20,
            textStyle: {
              rich: {
                a: {
                  color: '#fff',
                  fontSize: 16,
                  padding: [0, 10, 0, 0]
                },
                b: {
                  color: '#fff',
                  fontSize: 16,
                  padding: [0, 10, 0, 10]
                }
              }
            },
            formatter: function (name) {
              var target, unit;
              for (var i = 0, l = data.length; i < l; i++) {
                if (data[i].name == name) {
                  target = data[i].value;
                  unit = data[i].unit;
                }
              }
              return `{a| ${name}}{b|${target}}`;
            }
          },
          series: [
            {
              name: '',
              type: 'pie',
              radius: ['27%', '50%'],
              center: ['75%', '50%'],
              avoidLabelOverlap: true,
              label: {
                normal: {
                  show: true,
                  position: 'inside',
                  formatter: '{d}%',
                  textStyle: {
                    align: 'center',
                    baseline: 'middle',
                    fontSize: 16,
                    fontWeight: '100',
                    color: '#fff'
                  }
                }
              },
              labelLine: {
                show: false
              },

              data: data
            }
          ]
        };

      Chart.setOption(option)
    },

    handleChange(value) {
      console.log(value)
    },

    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    init() {
      this.view = new View({
        projection: 'EPSG:4326',
        center:[113.625351, 34.746303], // 郑州
        zoom: 4
      })
      this.map = new Map({
        target: "newmap",
        layers: [
          new TileLayer({
            title: '网格',
            source: new XYZ({
              visible: true,
              wrapX: true,
              url: 'http://map.geoq.cn/ArcGIS/rest/services/ChinaOnlineStreetPurplishBlue/MapServer/tile/{z}/{y}/{x}'
            })
          }),

          new TileLayer({
            title: '数据',
            source: new XYZ({
              visible: true,
              wrapX: true,
              url: 'http://map.geoq.cn/ArcGIS/rest/services/ChinaOnlineStreetPurplishBlue/MapServer/tile/{z}/{y}/{x}'
            })
          })
        ],
        view: this.view
      })
    },

    search2() {

      var shpfilewms = new TileWMS({
            ration:1,
            // url:"http://localhost:8080/geoserver/worktest/wms",
              url: 'http://localhost:8080/geoserver/satellitedata/wms',
              params:
              {
                  layers: 'satellitedata:polygon',
                  TILED: true,
                  transition: 0,
                  VERSION: '1.1.0',
                //   FORMAT:'application/openlayers3',
              },
              serverType: 'geoserver',


          });
          var shpLayer = new TileLayer({
              projection: "EPSG:3857", //默认
              source: shpfilewms,
              visible: true,
              zIndex: 3,
          })

          this.map.addLayer(shpLayer)
    },
    search3() {
      const ResuletChart = echarts.init(document.getElementById('newEcharts1'));
      var option = {
            backgroundColor: '#fff',
            tooltip: {
            trigger: 'axis',
          },
          title: {
                  text: ``,
                  textStyle: {
                      rich: {
                          a: {
                              fontSize: 16,
                              fontWeight: 600,
                          },
                      },
                  },
                  top: '2%',
                  left: '2%',
              },
              legend: {
                  data: ['GF1', 'GF2','GF3', 'GF4','GF5','GF6'],
                  textStyle: {
                      align: 'right',
                  },
                  top: '2%',
                  right: '2%',
              },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true,
          },
          xAxis: {
            type: 'category',
            boundaryGap: false, //坐标轴两边留白
            data: ['12月', '1月', '2月', '3月', '4月', '5月'],
            axisLabel: {
            //坐标轴刻度标签的相关设置。
            interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
            //	margin:15,
            textStyle: {
              color: '#1B253A',
              fontStyle: 'normal',
              fontFamily: '微软雅黑',
              fontSize: 12,
            },
            },
            axisTick: {
            show: false,
            },
            axisLine: {
            lineStyle: {
              color: '#E5E9ED',
              // opacity:0.2
            },
            },
            splitLine: {
            show: false,
            },
          },
          yAxis: [
            {
            type: 'value',
            axisLabel: {
              textStyle: {
              color: '#a8aab0',
              fontStyle: 'normal',
              fontFamily: '微软雅黑',
              fontSize: 12,
              },
            },
            axisLine: {
              show: false,
            },
            axisTick: {
              show: false,
            },
            splitLine: {
              show: true,
              lineStyle: {
              color: '#E5E9ED',
              // 	opacity:0.1
              },
            },
            axisLabel: {
                          show: true,
                          margin: 100,
                          formatter: '{value}',
                      },
            },
          ],
          series: [
            {
            name: 'GF1',
            type: 'line',
            itemStyle: {
              normal: {
              color: '#1EE882',
              lineStyle: {
                color: '#1EE882',
                width: 1,
              },
              //  areaStyle: {
              //   color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
              //    {
              //     offset: 0,
              //     color: 'rgba(30,232,130,0)',
              //    },
              //    {
              //     offset: 1,
              //     color: 'rgba(30,232,130,0.5)',
              //    },
              //   ]),
              //  },
              },
            },
            data: [165, 260, 258, 253, 254, 152],
            },
            {
            name: 'GF2',
            type: 'line',
            itemStyle: {
              normal: {
              color: 'rgba(180,20,215,1)',
              lineStyle: {
                color: 'rgba(180,20,215,1)',
                width: 1,
              },
              //  areaStyle: {
              //   color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
              //    {
              //     offset: 0,
              //     color: 'rgba(253,222,9,0)',
              //    },
              //    {
              //     offset: 1,
              //     color: 'rgba(253,222,9,0.5)',
              //    },
              //   ]),
              //  },
              },
            },
            data: [0, 0, 125, 227, 128, 130],
            },
            {
            name: 'GF3',
            type: 'line',
            itemStyle: {
              normal: {
              color: 'rgba(20,111,215,1)',
              lineStyle: {
                color: 'rgba(20,111,215,1)',
                width: 1,
              },
              //  areaStyle: {
              //   color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
              //    {
              //     offset: 0,
              //     color: 'rgba(20,111,215,0)',
              //    },
              //    {
              //     offset: 1,
              //     color: 'rgba(20,111,215,0.5)',
              //    },
              //   ]),
              //  },
              },
            },
            data: [143, 237, 336, 149, 251, 46],
            },
            {
            name: 'GF4',
            type: 'line',
            itemStyle: {
              normal: {
              color: 'rgba(250,34,71,1)',
              lineStyle: {
                color: 'rgba(250,34,71,1)',
                width: 1,
              },
              //  areaStyle: {
              //   color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
              //    {
              //     offset: 0,
              //     color: 'rgba(250,34,71,0)',
              //    },
              //    {
              //     offset: 1,
              //     color: 'rgba(250,34,71,0.5)',
              //    },
              //   ]),
              //  },
              },
            },
            data: [56, 149, 245, 244, 246, 44],
            },
            {
            name: 'GF5',
            type: 'line',
            itemStyle: {
              normal: {
              color: 'rgb(231.66,65.00,235.00)',
              lineStyle: {
                color: 'rgb(231.66,65.00,235.00)',
                width: 1,
              },
              //  areaStyle: {
              //   color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
              //    {
              //     offset: 0,
              //     color: 'rgba(250,34,71,0)',
              //    },
              //    {
              //     offset: 1,
              //     color: 'rgba(250,34,71,0.5)',
              //    },
              //   ]),
              //  },
              },
            },
            data: [30,103,254,156,210,36],
            },
            {
            name: 'GF6',
            type: 'line',
            itemStyle: {
              normal: {
              color: 'rgb(235.00,136.00,65.00)',
              lineStyle: {
                color: 'rgb(235.00,136.00,65.00)',
                width: 1,
              },
              //  areaStyle: {
              //   color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
              //    {
              //     offset: 0,
              //     color: 'rgba(250,34,71,0)',
              //    },
              //    {
              //     offset: 1,
              //     color: 'rgba(250,34,71,0.5)',
              //    },
              //   ]),
              //  },
              },
            },
            data: [20,156,189,241,210,30],
            },
          ],
      };
      ResuletChart.setOption(option)
    },
    test() {
      const extent = [0,0,640,640]
      const projection = new Projection({
        code: 'xkcd-image',
        units: 'pixels',
        extent: extent
      })

      let imageSource = new ImageStatic({ // 静态图片
        attributions: '© <a href="https://xkcd.com/license.html">xkcd</a>',
        url: 'http://localhost:9999/files/3784f0c7ca3e4177a412833c5a20100c_GF1_WFV1_E113.0_N22.9_20191124_L1A0004418304.jpg',
        projection: projection,
        imageExtent: [111.622, 21.8094, 114.282, 24.0636]

      })
      let imageLayer = new ImageLayer({
        source: imageSource
      })
      this.map.addLayer(imageLayer)
      
    },
    open() {
            this.$nextTick(() => {
                this.initChart(); 
            })
                var regionname = document.getElementById("")
    },
    open1() {
            this.$nextTick(() => {
                this.search3(); 
            })
        },
  },
  mounted() {
    this.init();


  }
  }
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 300px;
    min-height: 400px;
  }
  .header{
    background-color: transparent;
  } 
  #head{
    background-color: transparent;
  }
</style>